package game;

import javafx.scene.paint.Color;

public class Player {

    private String name;
    private Piece piece;
    private boolean freeze = false;

    public Player(String name) {
        this(name,Color.color(Math.random(),Math.random(),Math.random()));
    }

    public Player(String name, Color color) {
        this.name = name;
        piece = new Piece(color);
    }

    public int roll(Die die) {
        die.roll();
        return die.getFace();
    }

    public String getName() {
        return name;
    }

    public void movePiece(Board board, int steps) {
        board.movePiece(piece, steps);
    }

    public Piece getPiece() {
        return piece;
    }

    public void setFreeze(boolean freeze) {
        this.freeze = freeze;
    }

    public boolean isFreeze() {
        return freeze;
    }

    public Color getPieceColor() {
        return getPiece().getColor();
    }

}