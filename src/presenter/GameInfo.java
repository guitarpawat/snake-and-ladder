package presenter;

import game.Board;
import game.Game;
import game.Player;
import javafx.scene.paint.Color;

import java.util.*;

public class GameInfo {
    private Board board;
    private Game game;
    private Player[] players;

    private GameInfo(Player[] players,Board board, Game game) {
        this.board = board;
        this.players = players;
        this.game = game;
    }

    public int[] getBoardInfo() {
        return board.getSquaresStatus();
    }

    public Player[] getPlayers() {
        return Arrays.copyOf(players,players.length);
    }

    public Game getGame() {
        return game;
    }
    
    public Board getBoard() {
        return board;
    }

    public static class GameCreator {
        private List<Player> players;
        private Board.BoardBuilder boardBuilder;
        private int num_player = 1;

        public GameCreator() {
            this(Board.SIZE);
        }

        public GameCreator(int size) {
            players = new ArrayList<>();
            boardBuilder = new Board.BoardBuilder(size);
        }

        public GameCreator addPlayer(Player p) {
            num_player++;
            players.add(p);
            return this;
        }

        public GameCreator addPlayer(String name, int style) {
            return addPlayer(new Player(name,style));
        }

        public GameCreator addPlayer(String name) {
            return addPlayer(name,num_player);
        }

        public GameCreator ladder(int amount) {
            boardBuilder = boardBuilder.ladder(amount);
            return this;
        }

        public GameCreator snake(int amount) {
            boardBuilder = boardBuilder.snake(amount);
            return this;
        }

        public GameCreator backward(int amount) {
            boardBuilder = boardBuilder.backward(amount);
            return this;
        }

        public GameCreator freeze(int amount) {
            boardBuilder = boardBuilder.freeze(amount);
            return this;
        }

        public GameInfo build() {
            Board board = boardBuilder.build();
            Player[] players = new Player[this.players.size()];
            this.players.toArray(players);
            for(Player p: this.players) {
                board.addPiece(p.getPiece(),0);
            }
            Game game = new Game(players,board);
            return new GameInfo(players,board,game);
        }
    }
}
