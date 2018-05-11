

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GraphicMain extends Application {
	@Override
	public void start(Stage primaryStage){
		System.out.println("fxml");
		try {
			Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			System.out.println("enter");
			primaryStage.show();
		} catch (Exception e) {
			System.out.println("out");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
