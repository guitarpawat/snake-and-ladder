package game;

import java.util.Random;

public class Board {


    //TODO: Change setMove step to be more efficient, not creating the loops.
    // Size of the board.
    public static final int SIZE = 64;

    //TODO: Change #Squares.
    // Probability from 100
    public static final int FREEZE_CHANCE = 5;
    public static final int BACKWARD_CHANCE = 5;
    public static final int SNAKE_LADDER_CHANCE = 20;

    private Square[] squares;
    private Random random = new Random();

    public Board() {
        squares = new Square[SIZE];
        for(int i = 1; i < squares.length -1; i++) {
            Square.SquareBuilder sb = new Square.SquareBuilder(i);
            if(chanceOf100(FREEZE_CHANCE)) {
                sb = sb.setFreeze();
            } else if(chanceOf100(BACKWARD_CHANCE)) {
                sb = sb.setBackward();
            } else if(chanceOf100(SNAKE_LADDER_CHANCE)) {
                if(chanceOf100(50)) {
                    sb = sb.snakeLadder(-(random.nextInt(i)+1));
                } else {
                    sb.snakeLadder(random.nextInt(SIZE-i-1));
                }
            }
            squares[i] = sb.build();
        }
        squares[0] = new Square.SquareBuilder(0).build();
        squares[squares.length - 1] = new Square.SquareBuilder(squares.length-1).setGoal().build();
    }

    public void addPiece(Piece piece, int pos) {
        squares[pos].addPiece(piece);
    }

    public void movePiece(Piece piece, int steps) {
        int pos = getPiecePosition(piece);
        squares[pos].removePiece(piece);
        int newPos = pos + steps;
        if(newPos >= squares.length) {
            newPos = squares.length - 1;
        } else if(newPos < 0) {
            newPos = 0;
        }
        addPiece(piece, newPos);
    }

    public int getPiecePosition(Piece piece) {
        for(Square s : squares) {
            if(s.hasPiece(piece)) {
                return s.getNumber();
            }
        }
        return -1;
    }

    public boolean pieceIsAtGoal(Piece piece) {
        return squares[getPiecePosition(piece)].isGoal();
    }

    public boolean isOnBackwardSquare(Piece piece) {
        return squares[getPiecePosition(piece)].isBackward();
    }

    public boolean isOnFreezeSquare(Piece piece) {
        return squares[getPiecePosition(piece)].isFreeze();
    }

    public int snakeLadderSquare(Piece piece) {
        return squares[getPiecePosition(piece)].getMoveSteps();
    }

    private boolean chanceOf100(int chance) {
        int result = random.nextInt(100);
        return result < chance;
    }

}