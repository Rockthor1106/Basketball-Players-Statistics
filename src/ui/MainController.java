package ui;

import java.io.IOException;
import java.util.ArrayList;

import org.omg.CORBA.TCKind;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import model.DataManagement;
import model.Player;
import model.SearchingCriteria;

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
	
	//--------------------------------MainScreen.fxml---------------------------------
	
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
    
    @FXML
    void searchingScreen(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SearchingScreen.fxml"));
		fxmlLoader.setController(this);    	
		Parent searchingScreen = fxmlLoader.load();
    	initializeComboBoxOfCategories();
    	initializeComboBoxOfFilters();
		mainPane.getChildren().clear();
    	mainPane.setTop(searchingScreen);
    }
    
    @FXML
    void home(ActionEvent event) throws IOException {
		mainPane.getChildren().clear();
    	mainPane.setTop(tvPlayersInfo);
    }
    
    //--------------------------------------------------------------------------------
    
    //--------------------------PlayerInformationScreen.fxml--------------------------
    
    @FXML
    private Label playerName;

    @FXML
    private Label playerPoints;

    @FXML
    private Label playerRebounds;

    @FXML
    private Label playerRobberies;

    @FXML
    private Label playerBlocks;

    @FXML
    private Label playerAssists;

    @FXML
    private Label playerAge;

    @FXML
    private Label playerTeam;

    @FXML
    void deletePlayerData(ActionEvent event) {

    }

    @FXML
    void editPlayerData(ActionEvent event) {

    }
    
    public void playerInformationScreen() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PlayerInformationScreen.fxml"));
		fxmlLoader.setController(this);    	
		Parent playerInformationScreen = fxmlLoader.load();
		mainPane.getChildren().clear();
    	mainPane.setTop(playerInformationScreen);
    }
    
    //--------------------------------------------------------------------------------
    
    //------------------------------SearchingScreen.fxml------------------------------
    
    @FXML
    private TextField toSearch;
    

    @FXML
    private ComboBox<String> searchingCriteria;

    @FXML
    private ComboBox<String> filters;
    
    @FXML
    private TableView<Player> tvSearchedPlayerInformation;

    @FXML
    private TableColumn<Player, String> tcSearchedPlayerName;

    @FXML
    private TableColumn<Player, String> tcSearchedPlayerLastName;


    @FXML
    void search(ActionEvent event) throws IOException {
    	if (filters.getSelectionModel().getSelectedItem().equals("Equals to")) {
			playerInformationScreen();
		}
    	initializeTableViewOfSearchedPlayersInformation();
    	
    
    }
    
    public void initializeComboBoxOfCategories() {
    	ObservableList<String> criteria = FXCollections.observableArrayList(SearchingCriteria.values()[0].name(),
    																		SearchingCriteria.values()[1].name(),
															    			SearchingCriteria.values()[2].name(),
															    			SearchingCriteria.values()[3].name(),
															    			SearchingCriteria.values()[4].name());
    	this.searchingCriteria.setItems(criteria);
    }
    
    public void initializeComboBoxOfFilters() {
    	ObservableList<String> filters = FXCollections.observableArrayList("Greater than", "Equals to", "Less than");
    	this.filters.setItems(filters);
    }
    
    private void initializeTableViewOfSearchedPlayersInformation() {
    	ObservableList<Player> observableList;
    	ArrayList<Player> playersList = new ArrayList<>();
    	for (int i = 0; i < dataManagement.getStadisticPPG(filters.getSelectionModel().getSelectedItem(), Double.parseDouble(toSearch.getText())).size(); i++) {
			playersList.add(i, dataManagement.getStadisticPPG(filters.getSelectionModel().getSelectedItem(), Double.parseDouble(toSearch.getText())).get(i));
		}
    	observableList = FXCollections.observableArrayList(playersList);
    	tvSearchedPlayerInformation.setItems(observableList);
    	tcSearchedPlayerName.setCellValueFactory(new PropertyValueFactory<Player,String>("name"));
    	tcSearchedPlayerLastName.setCellValueFactory(new PropertyValueFactory<Player,String>("lastName"));
    }
    
    //--------------------------------------------------------------------------------
    
}
