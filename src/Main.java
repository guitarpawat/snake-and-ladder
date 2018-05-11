import presenter.GameInfo;
import ui.ConsoleUI;
import ui.GameUI;

import java.util.Random;

public class Main {

    static Random random = new Random();

    public static void main(String[] args) {

        GameUI ui = new ConsoleUI(new GameInfo.GameCreator(10).addPlayer("Guitar").addPlayer("Sept")
                .freeze(randInt()).backward(randInt()).ladder(randInt()).snake(randInt()));
        ui.startGame();
    }

    public static int randInt() {
        return random.nextInt(1);
    }
}
