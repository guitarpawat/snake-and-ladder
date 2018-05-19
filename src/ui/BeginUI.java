package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BeginUI {

	@FXML
	private TextField player1Name;

	@FXML
	private TextField player2Name;

	@FXML
	private TextField player3Name;

	@FXML
	private TextField player4Name;

	@FXML
	private TextField snakeTF;

	@FXML
	private TextField ladderTF;

	@FXML
	private TextField freezeTF;

	@FXML
	private TextField backwardTF;

	@FXML
	private Button startButton;
	
	@FXML
	private void playGame(ActionEvent event){
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("gameGUI.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene((Parent) loader.load()));
			stage.show();
			Stage beginPage = (Stage) startButton.getScene().getWindow();
			beginPage.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String getNamePlayer1(){
		return player1Name.getText();
	}
	
	private String getNamePlayer2(){
		return player2Name.getText();
	}

	private String getNamePlayer3(){
		return player3Name.getText();
	}
	
	private String getNamePlayer4(){
		return player4Name.getText();
	}
	
	private String getFreeze(){
		return freezeTF.getText();
	}
	
	private String getSnake(){
		return snakeTF.getText();
	}
	
	private String getLadder(){
		return ladderTF.getText();
	}
	
	private String getBackward(){
		return backwardTF.getText();
	}

}
