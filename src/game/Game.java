package game;

import java.util.Arrays;

public class Game {

    //TODO: May change to MVP.
    private Player[] players;
    private Die die = new Die();
    private Board board = new Board.BoardBuilder().build();

    private int currentPlayerIndex;

    private State state;

    public Game(int person) {
        this(person,null);
    }

    public Game(int person, String... names) {
        players = new Player[person];
        for(int i=0; i<person; i++) {
            try {
                players[i] = new Player(names[i]);
            } catch(ArrayIndexOutOfBoundsException | NullPointerException e) {
                players[i] = new Player(String.format("Player %d",i+1));
            } finally {
                board.addPiece(players[i].getPiece(),0);
            }
        }
        state = new InitState();
    }

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
    }

    public GameData getActionData() {
        return state.getData();
    }

    public void finishedAction() {
        state.toNextState();
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
        private boolean done = false;

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
            if(data == null) {
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
            data.addData("state","init");
            data.addData("player",currentPlayer());
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
            data.addData("state","start_turn");
            data.addData("player",currentPlayer());
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
            data.addData("move",step);
            data.addData("player",currentPlayer());
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
                data.addData("state","ladder");
            } else {
                data.addData("state","snake");
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
            data.addData("face",face);
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
            data.addData("state","default_roll");
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
            data.addData("state","lucky_roll");
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
            data.addData("state","backward_roll");
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
            data.addData("state","move");
            data.addData("player",currentPlayer());
            data.addData("face",oldData.get("face"));
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
            data.addData("state","check");
            data.addData("player",currentPlayer());
            Piece current = currentPlayerPiece();
            if(board.pieceIsAtGoal(current)) {
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
            data.addData("state","set_freeze");
            currentPlayer().setFreeze(true);
            data.addData("player",currentPlayer());
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
            data.addData("state","un_freeze");
            currentPlayer().setFreeze(false);
            data.addData("player",currentPlayer());
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
            data.addData("state","switch");
            switchPlayer();
            data.addData("player",currentPlayer());
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
            data.addData("state","ended");
            data.addData("player",currentPlayer());
        }

        @Override
        public String getDescription() {
            return "ended";
        }

        @Override
        public void toNextState() {
            throw  new IllegalStateException("The game was ended.");
        }
    }
}