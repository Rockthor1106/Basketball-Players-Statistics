package ui;

import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
		
		tvPlayersInfo.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				try {
					playerInformationScreen(tvPlayersInfo.getSelectionModel().getSelectedItem());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		});
		
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
    	
		tvSearchedPlayerInformation.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				try {
					playerInformationScreen(tvSearchedPlayerInformation.getSelectionModel().getSelectedItem());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		});
    }
    
    @FXML
    void home(ActionEvent event) throws IOException {
		mainPane.getChildren().clear();
    	mainPane.setTop(tvPlayersInfo);
    }
    
    //--------------------------------------------------------------------------------
    
    //--------------------------PlayerInformationScreen.fxml--------------------------
    
    @FXML
    private TextField playerName;

    @FXML
    private TextField playerPoints;

    @FXML
    private TextField playerRebounds;
    
    @FXML
    private TextField playerAssists;

    @FXML
    private TextField playerRobberies;

    @FXML
    private TextField playerBlocks;

    @FXML
    private TextField playerAge;

    @FXML
    private TextField playerTeam;
    
    @FXML
    private Button saveChanguesBtn;
    
    @FXML
    private Button editPlayerDataBtn;
    
    private Player playerToEdit = null;

    @FXML
    void deletePlayerData(ActionEvent event) {
    	
    }

    @FXML
    void editPlayerData(ActionEvent event) {
    	playerName.setEditable(true);
    	playerAge.setEditable(true);
    	playerTeam.setEditable(true);
    	playerPoints.setEditable(true);
    	playerRebounds.setEditable(true);
    	playerAssists.setEditable(true);
    	playerRobberies.setEditable(true);
    	playerBlocks.setEditable(true);
    	alert(AlertType.INFORMATION, "Edit Player Information", "Now you can edit the player information, click on any fields and edit them");
    	editPlayerDataBtn.setVisible(false);
    	saveChanguesBtn.setVisible(true);

    }
    
    @FXML
    void saveChanges(ActionEvent event) {
//    	String[] parts = playerName.getText().split(" ");
//    	String currentName = parts[0];
//    	String currentLastName = parts[1];
//    	String currentTeam = playerTeam.getText();
//    	int currentAge = Integer.parseInt(playerAge.getText().split(" ")[0]);
//    	double P = Double.parseDouble(playerPoints.getText());
//    	double R = Double.parseDouble(playerRebounds.getText()); 
//    	double A = Double.parseDouble(playerAssists.getText());
//    	double RB = Double.parseDouble(playerRobberies.getText());  
//    	double B = Double.parseDouble(playerBlocks.getText());
    	
//    	if (!currentName.equals(playerName.getText().split(" ")[0])) {
			playerToEdit.setName(playerName.getText().split(" ")[0]);
//		}
//    	else if (!currentLastName.equals(playerName.getText().split(" ")[1])) {
    		playerToEdit.setLastName(playerName.getText().split(" ")[1]);
//		}
//    	else if (currentAge != Integer.parseInt(playerAge.getText().split(" ")[0])) {
			playerToEdit.setAge(Integer.parseInt(playerAge.getText().split(" ")[0]));
//		}
//    	else if (!currentTeam.equals(playerTeam.getText())) {
			playerToEdit.setTeam(playerTeam.getText());
//		}
//    	else if (P != Double.parseDouble(playerPoints.getText())) {
			playerToEdit.setPointsPerGame(Double.parseDouble(playerPoints.getText()));
//		}
//    	else if (R != Double.parseDouble(playerRebounds.getText())) {
    		playerToEdit.setReboundsPerGame(Double.parseDouble(playerRebounds.getText()));
//		}
//    	else if (A != Double.parseDouble(playerAssists.getText())) {
    		playerToEdit.setAssistsPerGame(Double.parseDouble(playerAssists.getText()));
//		}
//    	else if (RB != Double.parseDouble(playerRobberies.getText())) {
    		playerToEdit.setRobberiesPerGame(Double.parseDouble(playerRobberies.getText()));
//		}
//    	else if (B != Double.parseDouble(playerBlocks.getText())) {
    		playerToEdit.setBlocksPerGame(Double.parseDouble(playerBlocks.getText()));
    		
    		saveChanguesBtn.setVisible(false);
    		editPlayerDataBtn.setVisible(true);
//		}
    }
    
    public void playerInformationScreen(Player foundPlayer) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PlayerInformationScreen.fxml"));
		fxmlLoader.setController(this);    	
		Parent playerInformationScreen = fxmlLoader.load();
		mainPane.getChildren().clear();
    	mainPane.setTop(playerInformationScreen);
    	playerToEdit = foundPlayer;
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
    	long timeStart = System.currentTimeMillis();
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
    	initializeTableViewOfSearchedPlayersInformation(criteria, timeStart);
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
    
    private void initializeTableViewOfSearchedPlayersInformation(String searchingCriteria, long timeStart) throws IOException {
    	ObservableList<Player> observableList = null;
    	switch (searchingCriteria) {
		case "Points":
			if (filters.getSelectionModel().getSelectedItem().equals("Equals to") && dataManagement.getStadisticPPG(filters.getSelectionModel().getSelectedItem(), Double.parseDouble(toSearch.getText())).size() == 1) {
				playerInformationScreen(FXCollections.observableArrayList(dataManagement.getStadisticPPG(filters.getSelectionModel().getSelectedItem(), Double.parseDouble(toSearch.getText()))).get(0));
			}
			observableList = FXCollections.observableArrayList(dataManagement.getStadisticPPG(filters.getSelectionModel().getSelectedItem(), Double.parseDouble(toSearch.getText())));
			break;
		case "Rebounds":
			if (filters.getSelectionModel().getSelectedItem().equals("Equals to") && dataManagement.getStadisticRPG(filters.getSelectionModel().getSelectedItem(), Double.parseDouble(toSearch.getText())).size() == 1) {
				playerInformationScreen(FXCollections.observableArrayList(dataManagement.getStadisticRPG(filters.getSelectionModel().getSelectedItem(), Double.parseDouble(toSearch.getText()))).get(0));
			}
			observableList = FXCollections.observableArrayList(dataManagement.getStadisticRPG(filters.getSelectionModel().getSelectedItem(), Double.parseDouble(toSearch.getText())));
			break;
		case "Assists":
			if (filters.getSelectionModel().getSelectedItem().equals("Equals to") && dataManagement.getStadisticAPG(filters.getSelectionModel().getSelectedItem(), Double.parseDouble(toSearch.getText())).size() == 1) {
				playerInformationScreen(FXCollections.observableArrayList(dataManagement.getStadisticAPG(filters.getSelectionModel().getSelectedItem(), Double.parseDouble(toSearch.getText()))).get(0));
			}
			observableList = FXCollections.observableArrayList(dataManagement.getStadisticAPG(filters.getSelectionModel().getSelectedItem(), Double.parseDouble(toSearch.getText())));
			break;
		case "Robberies":
			if (filters.getSelectionModel().getSelectedItem().equals("Equals to") && dataManagement.getStadisticRBPG(filters.getSelectionModel().getSelectedItem(), Double.parseDouble(toSearch.getText())).size() == 1) {
				playerInformationScreen(FXCollections.observableArrayList(dataManagement.getStadisticRBPG(filters.getSelectionModel().getSelectedItem(), Double.parseDouble(toSearch.getText()))).get(0));
			}
			observableList = FXCollections.observableArrayList(dataManagement.getStadisticRBPG(filters.getSelectionModel().getSelectedItem(), Double.parseDouble(toSearch.getText())));
			break;
		case "Blocks":
			if (filters.getSelectionModel().getSelectedItem().equals("Equals to") && dataManagement.getStadisticBPG(filters.getSelectionModel().getSelectedItem(), Double.parseDouble(toSearch.getText())).size() == 1) {
				playerInformationScreen(FXCollections.observableArrayList(dataManagement.getStadisticBPG(filters.getSelectionModel().getSelectedItem(), Double.parseDouble(toSearch.getText()))).get(0));
			}
			observableList = FXCollections.observableArrayList(dataManagement.getStadisticBPG(filters.getSelectionModel().getSelectedItem(), Double.parseDouble(toSearch.getText())));
			break;
		default:
			break;
		}
    	if (observableList == null) {
			alert(AlertType.INFORMATION, "Searching", "Does not exists coincidences");
			long timeFinal = System.currentTimeMillis();
        	alert(AlertType.INFORMATION, "Searching Time", "The time required by this searching in miliseconds was " + (timeFinal-timeStart));
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
        	long timeFinal = System.currentTimeMillis();
        	alert(AlertType.INFORMATION, "Searching Time", "The time required by this searching in miliseconds was " + (timeFinal-timeStart));
		}
    }
    
    //--------------------------------------------------------------------------------
    
}
