package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import presenter.GameInfo;

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
    private Label errorField;
	
	@FXML
	private void playGame(ActionEvent event){
	    int player = 0;
		try {
		    int freeze = Integer.parseInt(getFreeze());
            int backward = Integer.parseInt(getBackward());
            int snake = Integer.parseInt(getSnake());
            int ladder = Integer.parseInt(getLadder());
            if(freeze<0 || backward<0 || snake<0 || ladder<0) {
                throw new IllegalArgumentException();
            }
			FXMLLoader loader = new FXMLLoader(getClass().getResource("gameGUI.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene((Parent) loader.load()));
			GraphicUI controller = loader.getController();
            GameInfo.GameCreator creator = new GameInfo.GameCreator().snake(snake).ladder(ladder).backward(backward).freeze(freeze);
            if(!getNamePlayer1().isEmpty()) {
                creator.addPlayer(getNamePlayer1(),1);
                player++;
            }
            if(!getNamePlayer2().isEmpty()) {
                creator.addPlayer(getNamePlayer2(),2);
                player++;
            }
            if(!getNamePlayer3().isEmpty()) {
                creator.addPlayer(getNamePlayer3(),3);
                player++;
            }
            if(!getNamePlayer4().isEmpty()) {
                creator.addPlayer(getNamePlayer4(),4);
                player++;
            }
            if(player < 2) {
                throw new IllegalStateException("Must be at least 2 players");
            }
			controller.setCreator(creator);
			stage.show();
			Stage beginPage = (Stage) startButton.getScene().getWindow();

			beginPage.close();
        } catch (NumberFormatException ne) {
            errorField.setText("Please enter the number");
            errorField.setVisible(true);
        } catch (IllegalStateException se) {
            errorField.setText(se.getMessage());
            errorField.setVisible(true);
        } catch (IllegalArgumentException ae) {
            errorField.setText("Please enter zero or positive number");
            errorField.setVisible(true);
        } catch (Exception e) {
			e.printStackTrace();
            errorField.setVisible(true);
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
