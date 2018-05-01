package presenter;

import game.Board;
import game.Game;
import game.Player;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class GameInfo {
    Board board;
    Game game;
    Player[] players;

    private GameInfo(Player[] players,Board board, Game game) {
        this.board = board;
        this.players = players;
        this.game = game;
    }

    public int[] getBoardInfo() {
        return board.getSquaresStatus();
    }

    public String getPlayerName(int p) {
        return players[p].getName();
    }

    public Color getPlayerColor(int p) {
        return players[p].getPieceColor();
    }

    public Game getGame() {
        return game;
    }

    public static class GameCreater {
        List<Player> players;
        Board.BoardBuilder boardBuilder;

        public GameCreater() {
            players = new ArrayList<>();
            boardBuilder = new Board.BoardBuilder(Board.SIZE);
        }

        public GameCreater addPlayer(Player p) {
            players.add(p);
            return this;
        }

        public GameCreater addPlayer(String name) {
            addPlayer(new Player(name));
            return this;
        }

        public GameCreater addPlayer(String name, Color color) {
            addPlayer(new Player(name,color));
            return this;
        }

        public Board.BoardBuilder getBoardBuilder() {
            return boardBuilder;
        }

        public GameInfo build() {
            Board board = boardBuilder.build();
            Player[] players = new Player[this.players.size()];
            this.players.toArray(players);
            Game game = new Game(players,board);
            return new GameInfo(players,board,game);
        }
    }
}
