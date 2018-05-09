package ui;

import game.Player;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class GraphicUI extends Application implements GameUI {

	@FXML
	private Button rollButton;

	@FXML
	private Label message;

	@FXML
	private ImageView dice1;

	@FXML
	private ImageView dice2;

	@FXML
	private ImageView dice3;

	@FXML
	private ImageView dice4;

	@FXML
	private ImageView dice5;

	@FXML
	private ImageView dice6;

	@FXML
	private ImageView dice;

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		rollButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("124356789");
				message.setText("kuy mai");

			}

		});
		System.out.print("Press enter to roll the dice...");
		// scanner.nextLine();

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

	@Override
	public void diceFace(int face) {

	}

	public static void main(String[] args) {
		launch(args);
	}

}
