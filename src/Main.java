import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import presenter.GameInfo;
import ui.ConsoleUI;
import ui.GameUI;

import java.util.Arrays;

public class Main extends Application {
    public static void main(String[] args) {
        if(args.length > 0) {
            try {
                int freeze = Integer.parseInt(args[0]);
                int backward = Integer.parseInt(args[1]);
                int ladder = Integer.parseInt(args[2]);
                int snake = Integer.parseInt(args[3]);
                if(freeze<0 || backward<0 || snake<0 || ladder<0) {
                    throw new IllegalArgumentException("The number must me zero or positive");
                }
                String[] player = Arrays.copyOfRange(args,4,args.length);
                if(player.length > 4 || player.length < 2) {
                    throw new IllegalArgumentException("Must be 2-4 players");
                }
                cli(freeze,backward,ladder,snake,player);
            } catch(NumberFormatException | ArrayIndexOutOfBoundsException e) {
                hint();
            } catch(IllegalStateException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        } else {
            launch();
        }
    }

    public static void cli(int freeze,int backward,int ladder,int snake, String... names) {
        GameInfo.GameCreator creator = new GameInfo.GameCreator().freeze(freeze)
                .backward(backward).ladder(ladder).snake(snake);

        for(String name: names) {
            creator = creator.addPlayer(name);
        }

        GameUI ui = new ConsoleUI(creator);
        ui.startGame();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ui/Begin.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("ui/application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void hint() {
        System.out.println("Expected parameters: <#freeze square> <#backward square> <#ladder square> <#snake square> <player name>... ");
        System.out.println("#square must be an integer and must have 2-4 players.");
        System.exit(1);
    }
}
