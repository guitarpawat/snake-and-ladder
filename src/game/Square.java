package game;

import java.util.ArrayList;
import java.util.List;

public class Square {

    public static final int NORMAL_SQUARE = 0;
    public static final int LADDER_SQUARE = 1;
    public static final int SNAKE_SQUARE = 2;
    public static final int FREEZE_SQUARE = 3;
    public static final int BACKWARD_SQUARE = 4;
    public static final int GOAL_SQUARE = 5;

    private List<Piece> pieces;
    private int number;
    private int moveSteps;
    private int status;

    private Square(int number, int status, int moveSteps) {
        this.number = number;
        this.moveSteps = moveSteps;
        this.status = status;
        pieces = new ArrayList<>();
    }

    public void addPiece(Piece piece) {
        if(!hasPiece(piece)) {
            pieces.add(piece);
        }
    }

    public void removePiece(Piece piece) {
        pieces.remove(piece);
    }

    public boolean hasPiece(Piece piece) {
        return pieces.contains(piece);
    }

    public int getNumber() {
        return number;
    }

    public int getMoveSteps() {
        return moveSteps;
    }

    public int getSquareStatus() {
        return status;
    }

    public static class SquareBuilder{
        private int number;

        // Optional
        private int moveSteps = 0;
        private int status = Square.NORMAL_SQUARE;

        public SquareBuilder(int number) {
            this.number = number;
        }

        public SquareBuilder snakeLadder(int moveSteps) {
            check();
            this.moveSteps = moveSteps;
            if(moveSteps > 0) {
                status = Square.LADDER_SQUARE;
            } else {
                status = Square.SNAKE_SQUARE;
            }
            return this;
        }

        public SquareBuilder setGoal() {
            check();
            status = Square.GOAL_SQUARE;
            return this;
        }

        public SquareBuilder setFreeze() {
            check();
            status = Square.FREEZE_SQUARE;
            return this;
        }

        public SquareBuilder setBackward() {
            check();
            status = Square.BACKWARD_SQUARE;
            return this;
        }

        public Square build() {
            return new Square(number,status,moveSteps);
        }

        private void check() {
            if(status != Square.NORMAL_SQUARE) {
                throw new IllegalStateException("This square was assigned.");
            }
        }

    }
}