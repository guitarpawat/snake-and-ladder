package game;

import javafx.scene.paint.Color;

public class Player {

    private String name;
    private Piece piece;
    private boolean freeze = false;

    public Player(String name, int style) {
        this.name = name;
        piece = new Piece(style);
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

    public int getStyle() {
        return getPiece().getStyle();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(obj.getClass() != getClass()) return false;
        Player p = (Player) obj;
        return p.getName().equals(name) && p.getStyle()==getStyle();
    }
}