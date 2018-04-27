package game;

import java.util.ArrayList;
import java.util.List;

/**
 * The square in the game.Board.
 * @author Pawat Nakpiphatkul
 * @author Noppawan Kulchol
 * @author Kanchanok Kannee
 */
public class Square {

    private List<Piece> pieces;
    private int number;
    private int moveSteps;
    private boolean goal;
    private boolean freeze;
    private boolean backward;

    /**
     * Constructor of game.Square class.
     * @param number is the square number.
     */
    private Square(int number, int moveSteps, boolean goal, boolean freeze, boolean backward) {
        this.number = number;
        this.moveSteps = moveSteps;
        this.goal = goal;
        this.freeze = freeze;
        this.backward = backward;
        pieces = new ArrayList<>();
    }

    /**
     * Adds piece to this square.
     * @param piece is the piece to be added.
     */
    public void addPiece(Piece piece) {
        if(!hasPiece(piece)) {
            pieces.add(piece);
        }
    }

    /**
     * Remove piece from this square.
     * @param piece is the piece to be removed.
     */
    public void removePiece(Piece piece) {
        pieces.remove(piece);
    }

    /**
     * Checks that the square contains a piece.
     * @param piece is the piece to be checked.
     * @return true if the piece is contains in this square, otherwise false.
     */
    public boolean hasPiece(Piece piece) {
        return pieces.contains(piece);
    }

    /**
     * Checks that this square is a goal.
     * @return true if this square is the goal, otherwise false.
     */
    public boolean isGoal() {
        return goal;
    }

    /**
     * Get the number of this square.
     * @return number of this square.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Get the snakeLadder of this square.
     * @return snakeLadder of this square.
     */
    public int getMoveSteps() {
        return moveSteps;
    }

    /**
     * Checks that this square is a backward square.
     * @return true if this is a backward square, otherwise false.
     */
    public boolean isBackward() {
        return backward;
    }

    /**
     * Checks that this square is a freeze square.
     * @return true if this is a freeze square, otherwise false.
     */
    public boolean isFreeze() {
        return freeze;
    }

    /**
     * Class for build the Square.
     */
    public static class SquareBuilder{
        private int number;

        // Optional
        private int moveSteps = 0;
        private boolean goal = false;
        private boolean freeze = false;
        private boolean backward = false;

        /**
         * Constructor of SquareNumber.
         * @param number is the number of the square to build.
         */
        public SquareBuilder(int number) {
            this.number = number;
        }

        /**
         * Set the snakeLadder to the square to build.
         */
        public SquareBuilder snakeLadder(int moveSteps) {
            this.moveSteps = moveSteps;
            return this;
        }

        /**
         * Set the goal to the square to build.
         */
        public SquareBuilder setGoal() {
            this.goal = true;
            return this;
        }

        /**
         * Set the freeze to the square to build.
         */
        public SquareBuilder setFreeze() {
            this.freeze = true;
            return this;
        }

        /**
         * Set the backward to the square to build.
         */
        public SquareBuilder setBackward() {
            this.backward = true;
            return this;
        }

        /**
         * Build a square with desired properties.
         * @return a new square.
         */
        public Square build() {
            return new Square(number,moveSteps,goal,freeze,backward);
        }

    }
}