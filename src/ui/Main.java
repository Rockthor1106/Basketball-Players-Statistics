package ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.DataManagement;

public class Main extends Application {
	
	private MainController mainController;
	private DataManagement dataManagement;
	
	
	public static void main(String[] args) throws IOException {
		DataManagement dM = new DataManagement();
		launch(args);
	}
	
	public Main() throws IOException {
		dataManagement = new DataManagement();
		mainController = new MainController(dataManagement);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
		
		fxmlLoader.setController(mainController);
		
		Parent root = fxmlLoader.load();
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Basketball Players Statistics ");
		primaryStage.show();
	}
}
