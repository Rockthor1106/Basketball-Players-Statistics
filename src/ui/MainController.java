package ui;

import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    private TableColumn<Player, String> tcName;

    @FXML
    private TableColumn<Player, String> tcLastName;
    
    @FXML
    private TableColumn<Player, Integer> tcAge;
    
    @FXML
    private TableColumn<Player, String> tcTeam;
    
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
    	tcAge.setCellValueFactory(new PropertyValueFactory<Player,Integer>("age"));
    	tcTeam.setCellValueFactory(new PropertyValueFactory<Player,String>("team"));
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
    
    public void playerInformationScreen(Player foundPlayer) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PlayerInformationScreen.fxml"));
		fxmlLoader.setController(this);    	
		Parent playerInformationScreen = fxmlLoader.load();
		mainPane.getChildren().clear();
    	mainPane.setTop(playerInformationScreen);
    	playerName.setText(foundPlayer.getName() + " " + foundPlayer.getLastName());
    	playerAge.setText(String.valueOf(foundPlayer.getAge()) + " Years Old");
    	playerTeam.setText(foundPlayer.getTeam());
    	playerPoints.setText(String.valueOf(foundPlayer.getPointsPerGame()));
    	playerRebounds.setText(String.valueOf(foundPlayer.getReboundsPerGame()));
    	playerAssists.setText(String.valueOf(foundPlayer.getAssistsPerGame()));
    	playerRobberies.setText(String.valueOf(foundPlayer.getRobberiesPerGame()));
    	playerBlocks.setText(String.valueOf(foundPlayer.getBlocksPerGame()));
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
    private TableColumn<Player, Integer> tcSearchedPlayerAge;

    @FXML
    private TableColumn<Player, String> tcSearchedPlayerTeam;

    @FXML
    private TableColumn<Player, Double> tcSearchedPlayerPPG;

    @FXML
    private TableColumn<Player, Double> tcSearchedPlayerRPG;

    @FXML
    private TableColumn<Player, Double> tcSearchedPlayerAPG;

    @FXML
    private TableColumn<Player, Double> tcSearchedPlayerRBPG;

    @FXML
    private TableColumn<Player, Double> tcSearchedPlayerBPG;
    
    @FXML
    void search(ActionEvent event) throws IOException {
    	String criteria = "";
    	if (searchingCriteria.getSelectionModel().getSelectedItem().equals("Points")) {
    		criteria = "Points";
		}
    	else if (searchingCriteria.getSelectionModel().getSelectedItem().equals("Rebounds")) {
			criteria = "Rebounds";
		}
    	else if (searchingCriteria.getSelectionModel().getSelectedItem().equals("Assists")) {
			criteria = "Assists";
		}
    	else if (searchingCriteria.getSelectionModel().getSelectedItem().equals("Robberies")) {
			criteria = "Robberies";
		}
    	else if (searchingCriteria.getSelectionModel().getSelectedItem().equals("Blocks")) {
			criteria = "Blocks";
		}
    	initializeTableViewOfSearchedPlayersInformation(criteria);
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
    
    private void initializeTableViewOfSearchedPlayersInformation(String searchingCriteria) throws IOException {
    	ObservableList<Player> observableList = null;
    	switch (searchingCriteria) {
		case "Points":
			if (filters.getSelectionModel().getSelectedItem().equals("Equals to")) {
				playerInformationScreen(FXCollections.observableArrayList(dataManagement.getStadisticPPG(filters.getSelectionModel().getSelectedItem(), Double.parseDouble(toSearch.getText()))).get(0));
			}
			else {
				observableList = FXCollections.observableArrayList(dataManagement.getStadisticPPG(filters.getSelectionModel().getSelectedItem(), Double.parseDouble(toSearch.getText())));
			}
			break;
		case "Rebounds":
			if (filters.getSelectionModel().getSelectedItem().equals("Equals to")) {
				playerInformationScreen(FXCollections.observableArrayList(dataManagement.getStadisticRPG(filters.getSelectionModel().getSelectedItem(), Double.parseDouble(toSearch.getText()))).get(0));
			}
			else {
				observableList = FXCollections.observableArrayList(dataManagement.getStadisticRPG(filters.getSelectionModel().getSelectedItem(), Double.parseDouble(toSearch.getText())));
			}
			break;
		case "Assists":
			if (filters.getSelectionModel().getSelectedItem().equals("Equals to")) {
				playerInformationScreen(FXCollections.observableArrayList(dataManagement.getStadisticAPG(filters.getSelectionModel().getSelectedItem(), Double.parseDouble(toSearch.getText()))).get(0));
			}
			else {
				observableList = FXCollections.observableArrayList(dataManagement.getStadisticAPG(filters.getSelectionModel().getSelectedItem(), Double.parseDouble(toSearch.getText())));
			}
			break;
		case "Robberies":
			if (filters.getSelectionModel().getSelectedItem().equals("Equals to")) {
				playerInformationScreen(FXCollections.observableArrayList(dataManagement.getStadisticRBPG(filters.getSelectionModel().getSelectedItem(), Double.parseDouble(toSearch.getText()))).get(0));
			}
			else {
				observableList = FXCollections.observableArrayList(dataManagement.getStadisticRBPG(filters.getSelectionModel().getSelectedItem(), Double.parseDouble(toSearch.getText())));
			}
			break;
		case "Blocks":
			if (filters.getSelectionModel().getSelectedItem().equals("Equals to")) {
				playerInformationScreen(FXCollections.observableArrayList(dataManagement.getStadisticBPG(filters.getSelectionModel().getSelectedItem(), Double.parseDouble(toSearch.getText()))).get(0));
			}
			else {
				observableList = FXCollections.observableArrayList(dataManagement.getStadisticBPG(filters.getSelectionModel().getSelectedItem(), Double.parseDouble(toSearch.getText())));
			}
			break;
		default:
			break;
		}
    	if (observableList == null) {
			alert(AlertType.INFORMATION, "Searching", "Does not exists coincidences");
		}
    	else {
        	tvSearchedPlayerInformation.setItems(observableList);
        	tcSearchedPlayerName.setCellValueFactory(new PropertyValueFactory<Player,String>("name"));
        	tcSearchedPlayerLastName.setCellValueFactory(new PropertyValueFactory<Player,String>("lastName"));
        	tcSearchedPlayerAge.setCellValueFactory(new PropertyValueFactory<Player,Integer>("age"));
        	tcSearchedPlayerTeam.setCellValueFactory(new PropertyValueFactory<Player,String>("team"));
        	tcSearchedPlayerPPG.setCellValueFactory(new PropertyValueFactory<Player,Double>("pointsPerGame"));
        	tcSearchedPlayerRPG.setCellValueFactory(new PropertyValueFactory<Player,Double>("reboundsPerGame"));
        	tcSearchedPlayerAPG.setCellValueFactory(new PropertyValueFactory<Player,Double>("assistsPerGame"));
        	tcSearchedPlayerRBPG.setCellValueFactory(new PropertyValueFactory<Player,Double>("robberiesPerGame"));
        	tcSearchedPlayerBPG.setCellValueFactory(new PropertyValueFactory<Player,Double>("blocksPerGame"));
		}
    }
    
    //--------------------------------------------------------------------------------
    
}
