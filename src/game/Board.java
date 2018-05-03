package game;

import java.util.Random;

public class Board {

    public static final int SIZE = 64;


    private Square[] squares;
    private Random random = new Random();

    private Board(Square.SquareBuilder sb[]) {
        squares = new Square[sb.length];
        for(int i=0; i<sb.length; i++) {
            squares[i] = sb[i].build();
        }
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
        return squares[getPiecePosition(piece)].getSquareStatus() == Square.GOAL_SQUARE;
    }

    public boolean isOnBackwardSquare(Piece piece) {
        return squares[getPiecePosition(piece)].getSquareStatus() == Square.BACKWARD_SQUARE;
    }

    public boolean isOnFreezeSquare(Piece piece) {
        return squares[getPiecePosition(piece)].getSquareStatus() == Square.FREEZE_SQUARE;
    }

    public boolean isOnSnakeSquare(Piece piece) {
        return squares[getPiecePosition(piece)].getSquareStatus() == Square.SNAKE_SQUARE;
    }

    public boolean isOnLadderSquare(Piece piece) {
        return squares[getPiecePosition(piece)].getSquareStatus() == Square.LADDER_SQUARE;
    }

    public int snakeLadderSquare(Piece piece) {
        return squares[getPiecePosition(piece)].getMoveSteps();
    }

    public int snakeLadderSquare(int pos) {
        return squares[pos].getMoveSteps();
    }

    public int getSquareStatus(Piece piece) {
        return squares[getPiecePosition(piece)].getSquareStatus();
    }

    public int getSquareStatus(int pos) {
        return squares[pos].getSquareStatus();
    }

    public int[] getSquaresStatus() {
        int ret[] = new int[squares.length];
        for(int i=0; i<squares.length; i++) {
            ret[i] = squares[i].getSquareStatus();
        }
        return ret;
    }

    public static class BoardBuilder {

        private Square.SquareBuilder sb[];
        private int limit, size;
        private int ladder, snake, backward, freeze;
        private boolean occupied[];

        private Random random = new Random();

        public BoardBuilder() {
            this(Board.SIZE);
        }

        public BoardBuilder(int size) {
            if(size < Die.MAX_FACE+2) {
                throw new IllegalArgumentException("Unplayable Game!");
            }
            sb = new Square.SquareBuilder[size];
            for(int i=0; i<size; i++) {
                sb[i] = new Square.SquareBuilder(i);
            }
            limit = (int) ((size-2)/2.0);
            occupied = new boolean[size];

            sb[size-1] = sb[size-1].setGoal();
            occupied[size-1] = true;
            occupied[0] = true;
            this.size = size;
        }

        public BoardBuilder ladder(int amount) {
            check(amount*2);
            ladder += amount;
            return this;
        }

        public BoardBuilder snake(int amount) {
            check(amount*2);
            snake += amount;
            return this;
        }

        public BoardBuilder backward(int amount) {
            check(amount);
            backward += amount;
            return this;
        }

        public BoardBuilder freeze(int amount) {
            check(amount);
            freeze += amount;
            return this;
        }

        public Board build() {

            // Create Ladder
            for(int i=0; i<ladder; i++) {
                int src = randomAvailableSquare(1,size-2);
                // Replace if more efficient algorithm is found.
                while(true) {
                    int dest = randomAvailableSquare(src+1,size-2);
                    if(dest > 0) {
                        int move = dest-src;
                        sb[src] = sb[src].snakeLadder(move);
                        occupied[src] = true;
                        occupied[dest] = true;
                        break;
                    } else {
                        src--;
                    }
                }
            }

            // Create Snake
            for(int i=0; i<snake; i++) {
                int src = randomAvailableSquare(1,size-2);
                while(true) {
                    int dest = randomAvailableSquare(1,src-1);
                    if(dest > 0) {
                        int move = dest-src;
                        sb[src] = sb[src].snakeLadder(move);
                        occupied[src] = true;
                        occupied[dest] = true;
                        break;
                    } else {
                        src++;
                    }
                }
            }

            // Create Backward Square
            for(int i=0; i<backward; i++) {
                int res = randomAvailableSquare(1,size-2);
                sb[res] = sb[res].setBackward();
                occupied[res] = true;
            }

            // Create Freeze Square
            for(int i=0; i<freeze; i++) {
                int res = randomAvailableSquare(1,size-2);
                sb[res] = sb[res].setFreeze();
                occupied[res] = true;
            }

            return new Board(sb);
        }

        private void check(int amount) {
            if(limit - amount < 0) {
                throw new IllegalStateException("Setting reach above the limit!");
            } else {
                limit -= amount;
            }
        }

        private int randomAvailableSquare(int lowerBound, int upperBound) {
            int available = getAvailableAmount(lowerBound,upperBound);
            if(available <= 0) {
                return -1;
            }

            int result = random.nextInt(available);

            for(int i=lowerBound; i<upperBound; i++) {
                if(occupied[i]) {
                    continue;
                }
                if(result == 0) {
                    return i;
                }
                result--;
            }
            return upperBound;
        }

        private int getAvailableAmount(int lowerBound, int upperBound) {
            int available = 0;
            for(int i=lowerBound; i<=upperBound; i++) {
                if(!occupied[i]) {
                    available++;
                }
            }
            return available;
        }
    }

}