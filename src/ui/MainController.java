package ui;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import model.DataManagement;
import model.Player;

public class MainController {
	
	private DataManagement dataManagement;
	
	public MainController(DataManagement dataManagement) {
		this.dataManagement = dataManagement;
	}
	
	public void initialize() {
		initializeTableViewOfPlayersInformation();
	}
	
    public void alert(AlertType alertType, String alertTitle, String Alertmsg) {
    	Alert alert = new Alert(alertType);
    	alert.setTitle(alertTitle);
    	alert.setHeaderText(null);
    	alert.setContentText(Alertmsg);
    	alert.show();
    }
	
	//--------------------------------MainScreen.FXML---------------------------------
	
    @FXML
    private BorderPane mainPane;
    
    @FXML
    private TableView<Player> tvPlayersInfo;
    
    @FXML
    private TableColumn<Player, Integer> tcIndex;
    
    @FXML
    private TableColumn<Player, String> tcName;

    @FXML
    private TableColumn<Player, String> tcLastName;
    
    @FXML
    void displayCredits(ActionEvent event) {
    	alert(AlertType.INFORMATION, "Credits", "Developed by\n\n" + "Gilmar Andrés Amezquita\n" + "Jhan Carlos Carvajal");
    }
    
    private void initializeTableViewOfPlayersInformation() {
    	ObservableList<Player> observableList;
    	ArrayList<Player> playersList = new ArrayList<>();
    	for (int i = 0; i < dataManagement.getDataTable().getSize(); i++) {
			playersList.add(i, dataManagement.getDataTable().getItem(i));
		}
    	observableList = FXCollections.observableArrayList(playersList);
    	tvPlayersInfo.setItems(observableList);
    	tcName.setCellValueFactory(new PropertyValueFactory<Player,String>("name"));
    	tcLastName.setCellValueFactory(new PropertyValueFactory<Player,String>("lastName"));
    }
    
    
    //--------------------------------------------------------------------------------
}
