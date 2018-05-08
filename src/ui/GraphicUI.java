package ui;

import game.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GraphicUI extends Application implements GameUI {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    @Override
    public void initRenderBoard(int[] squares, Player[] players) {

    }

    @Override
    public void reRenderBoard() {

    }

    @Override
    public void focusPlayer(Player player) {

    }

    @Override
    public void roll() {

    }

    @Override
    public void delay() {

    }

    @Override
    public void setMessage(String message) {

    }

    @Override
    public void gameEnded(Player winner) {

    }
}
