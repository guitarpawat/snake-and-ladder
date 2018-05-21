package game;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Game {

    private Player[] players;
    private Die die = new Die();
    private Board board;
    private int currentPlayerIndex;
    private State state;

    // For replay
    private List<Memento> mementos = new ArrayList<>();
    private Iterator<Memento> mementoIterator = null;

    public Game(Player[] players, Board board) {
        this.players = players;
        this.board = board;
        state = new InitState();
    }

    public Player currentPlayer() {
        return players[currentPlayerIndex];
    }

    public String getActionDescription() {
        return state.getDescription();
    }

    public void doAction() {
        state.doAction();
        if(mementoIterator == null) {
            mementos.add(new Memento(board,state.getData(),state.getDescription()));
        }
    }

    public GameData getActionData() {
        return state.getData();
    }

    public void finishedAction() {
        if(mementoIterator == null) {
            state.toNextState();
        } else if(mementoIterator.hasNext()) {
            GameData oldData = state.getData();
            state.toNextState();
            Class nextState = state.getClass();
            Memento nextMemento = mementoIterator.next();
            try {
                state = (State) nextState.getConstructor(Game.class,GameData.class,GameData.class).newInstance(this,oldData,nextMemento.getData());
            } catch (InstantiationException|InvocationTargetException|IllegalAccessException|NoSuchMethodException e) {
                try {
                    state = (State) nextState.getConstructor(Game.class,GameData.class).newInstance(this,nextMemento.getData());
                } catch (InstantiationException|InvocationTargetException|IllegalAccessException|NoSuchMethodException ex) {}
            }
        }
    }

    public String currentPlayerName() {
        return currentPlayer().getName();
    }

    public Piece currentPlayerPiece() {
        return currentPlayer().getPiece();
    }

    public int currentPlayerPosition() {
        return board.getPiecePosition(currentPlayer().getPiece());
    }

    public void replay() {
        state = new ReplayInitState();
    }

    public void newGame() {
        for(Player p: players) {
            board.movePiece(p.getPiece(),-board.getPiecePosition(p.getPiece()));
        }
        state = new InitState();
        mementos = new ArrayList<>();
        mementoIterator = null;
    }

    private int currentPlayerRollDice() {
        return currentPlayer().roll(die);
    }

    private void currentPlayerMovePiece(int steps) {
        currentPlayer().movePiece(board, steps);
    }

    private void switchPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
    }


    private abstract class State {
        protected GameData oldData;
        protected GameData data;
        protected State next;
        protected boolean done = false;

        public State(GameData old) {
            this(old,new GameData());
        }

        public State(GameData old, GameData now) {
            oldData = old;
            data = now;
        }

        public GameData getOldData() {
            return oldData;
        }

        public GameData getData() {
            if(!done) {
                throw new IllegalStateException("Must call doAction before getting data.");
            }
            return data;
        }

        public void toNextState() {
            state = next;
        }

        public void doAction() {
            if(done) {
                throw new IllegalStateException("The action must do only once in each state.");
            }
            done = true;
        }
        
        public boolean addDataOrDefault(String key, Object info) {
            if(!data.hasKey(key)) {
                data.addData(key, info);
                return true;
            }
            return false;
        }

        public abstract String getDescription();
    }

    private class InitState extends State {

        public InitState() {
            this(new GameData());
        }

        public InitState(GameData now) {
            super(now);
        }

        @Override
        public void doAction() {
            super.doAction();
            addDataOrDefault("state","init");
            addDataOrDefault("player",currentPlayer());
            currentPlayerIndex = 0;
            next = new StartTurnState(data);
        }

        @Override
        public String getDescription() {
            return "init";
        }

        @Override
        public GameData getOldData() {
            throw new IllegalStateException("Cannot get in InitState.");
        }
    }

    private class StartTurnState extends State {
        public StartTurnState(GameData old) {
            super(old);
        }

        public StartTurnState(GameData old, GameData now) {
            super(old,now);
        }

        @Override
        public void doAction() {
            super.doAction();
            addDataOrDefault("state","start_turn");
            addDataOrDefault("player",currentPlayer());
            if(currentPlayer().isFreeze()) {
                next = new UnFreezeState(data);
            } else {
                next = new DefaultRollState(data);
            }
        }

        @Override
        public String getDescription() {
            return "start_turn";
        }
    }

    private abstract class MovableState extends State {

        public MovableState(GameData old) {
            super(old);
        }

        public MovableState(GameData old, GameData now) {
            super(old,now);
        }

        public void setMove(int step) {
            addDataOrDefault("move",step);
            addDataOrDefault("player",currentPlayer());
        }
    }

    private class SnakeLadderState extends MovableState {
        public SnakeLadderState(GameData old) {
            super(old);
        }

        public SnakeLadderState(GameData old, GameData now) {
            super(old,now);
        }

        @Override
        public void doAction() {
            super.doAction();
            int steps = board.snakeLadderSquare(currentPlayerPiece());
            if(steps > 0) {
                addDataOrDefault("state","ladder");
            } else {
                addDataOrDefault("state","snake");
            }
            setMove(board.snakeLadderSquare(currentPlayerPiece()));
            next = new MovePieceState(data);
        }

        @Override
        public String getDescription() {
            return "snake_ladder";
        }
    }

    private abstract class RollableState extends MovableState {

        public RollableState(GameData old) {
            super(old);
        }

        public RollableState(GameData old, GameData now) {
            super(old,now);
        }

        private int roll() {
            return currentPlayerRollDice();
        }

        public int rollOrDataDefault() {
            if(data.hasKey("face")) {
                int face = (Integer) data.get("face");
                return face;
            }
            int face = roll();
            addDataOrDefault("face",face);
            return face;
        }

    }

    private class DefaultRollState extends RollableState {
        public DefaultRollState(GameData old) {
            super(old);
        }

        public DefaultRollState(GameData old, GameData now) {
            super(old,now);
        }

        @Override
        public void doAction() {
            super.doAction();
            addDataOrDefault("state","default_roll");
            int face = rollOrDataDefault();
            setMove(face);
            next = new MovePieceState(data);
        }

        @Override
        public String getDescription() {
            return "default_roll";
        }
    }

    private class LuckyRollState extends RollableState {
        public LuckyRollState(GameData old) {
            super(old);
        }

        public LuckyRollState(GameData old, GameData now) {
            super(old,now);
        }

        @Override
        public void doAction() {
            super.doAction();
            addDataOrDefault("state","lucky_roll");
            int face = rollOrDataDefault();
            setMove(face);
            next = new MovePieceState(data);
        }

        @Override
        public String getDescription() {
            return "lucky_roll";
        }
    }

    private class BackwardRollState extends RollableState {
        public BackwardRollState(GameData old) {
            super(old);
        }

        public BackwardRollState(GameData old, GameData now) {
            super(old,now);
        }

        @Override
        public void doAction() {
            super.doAction();
            addDataOrDefault("state","backward_roll");
            int face = rollOrDataDefault();
            setMove(-face);
            next = new MovePieceState(data);
        }

        @Override
        public String getDescription() {
            return "backward_roll";
        }
    }

    private class MovePieceState extends State {
        public MovePieceState(GameData old) {
            super(old);
        }

        public MovePieceState(GameData old, GameData now) {
            super(old,now);
        }

        @Override
        public void doAction() {
            super.doAction();
            currentPlayerMovePiece((Integer) oldData.get("move"));
            addDataOrDefault("state","move");
            addDataOrDefault("player",currentPlayer());
            addDataOrDefault("face",oldData.get("face"));
            next = new CheckState(data);
        }

        @Override
        public String getDescription() {
            return "move";
        }
    }

    private class CheckState extends State {
        public CheckState(GameData old) {
            super(old);
        }

        public CheckState(GameData old, GameData now) {
            super(old,now);
        }

        @Override
        public void doAction() {
            super.doAction();
            addDataOrDefault("state","check");
            addDataOrDefault("player",currentPlayer());
            Piece current = currentPlayerPiece();
            if(board.isPieceAtGoal(current)) {
                next = new GameEndedState(data);
            } else if(board.isOnFreezeSquare(current)) {
                next = new SetFreezeState(data);
            } else if(board.isOnSnakeSquare(current) || board.isOnLadderSquare(current)) {
                next = new SnakeLadderState(data);
            } else if(board.isOnBackwardSquare(current)) {
                next = new BackwardRollState(data);
            } else if(oldData.get("face") != null) {
                if(oldData.get("face").equals(6)) {
                    next = new LuckyRollState(data);
                }
            }
            if(next == null) {
                next = new SwitchPlayerState(data);
            }
        }

        @Override
        public String getDescription() {
            return "check";
        }
    }

    private class SetFreezeState extends State {
        public SetFreezeState(GameData old) {
            super(old);
        }

        public SetFreezeState(GameData old, GameData now) {
            super(old,now);
        }

        @Override
        public void doAction() {
            super.doAction();
            addDataOrDefault("state","set_freeze");
            currentPlayer().setFreeze(true);
            addDataOrDefault("player",currentPlayer());
            next = new SwitchPlayerState(data);
        }

        @Override
        public String getDescription() {
            return "set_freeze";
        }
    }

    private class UnFreezeState extends State {
        public UnFreezeState(GameData old) {
            super(old);
        }

        public UnFreezeState(GameData old, GameData now) {
            super(old,now);
        }

        @Override
        public void doAction() {
            super.doAction();
            addDataOrDefault("state","un_freeze");
            currentPlayer().setFreeze(false);
            addDataOrDefault("player",currentPlayer());
            next = new SwitchPlayerState(data);
        }

        @Override
        public String getDescription() {
            return "un_freeze";
        }
    }

    private class SwitchPlayerState extends State {
        public SwitchPlayerState(GameData old) {
            super(old);
        }

        public SwitchPlayerState(GameData old, GameData now) {
            super(old,now);
        }

        @Override
        public void doAction() {
            super.doAction();
            addDataOrDefault("state","switch");
            switchPlayer();
            addDataOrDefault("player",currentPlayer());
            next = new StartTurnState(data);
        }

        @Override
        public String getDescription() {
            return "switch";
        }
    }

    private class GameEndedState extends State {
        public GameEndedState(GameData old) {
            super(old);
        }

        public GameEndedState(GameData old, GameData now) {
            super(old,now);
        }

        @Override
        public void doAction() {
            super.doAction();
            addDataOrDefault("state","ended");
            addDataOrDefault("player",currentPlayer());
        }

        @Override
        public String getDescription() {
            return "ended";
        }

        @Override
        public void toNextState() {
            // DO NOTHING
        }
    }

    private class ReplayInitState extends State {

        public ReplayInitState() {
            super(null,new GameData());
        }

        @Override
        public void doAction() {
            super.doAction();
            data.addData("state","replay");
            mementoIterator = mementos.iterator();
            for(Player p: players) {
                board.movePiece(p.getPiece(),-board.getPiecePosition(p.getPiece()));
            }
            currentPlayerIndex = 0;
            next = new InitState();
        }

        @Override
        public String getDescription() {
            return "replay";
        }
    }

    private static class Memento {
        private GameData data;
        private Board board;
        private String description;
        private Memento(Board b, GameData g, String d) {
            board = b;
            data = g;
            description = d;
        }

        public Board getBoard() {
            return board;
        }

        public GameData getData() {
            return data;
        }

        public String getDescription() {
            return description;
        }
    }


}