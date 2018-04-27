package game;

import java.util.Random;

public class Die {

    // Max face of the dice.
    public static final int MAX_FACE = 6;

    private int face;

    public Die() {
        face = 0;
    }

    public void roll() {
        face = 1 + new Random().nextInt(MAX_FACE);
    }

    public int getFace() {
        if (face < 1) {
            throw new IllegalStateException("You must roll the dice first!");
        }
        return face;
    }

}
