package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import datastructure.AVLTree;
import datastructure.HashTable;

public class DataManagement {
	
	public int SIZE = 200000;
	public HashTable<Integer, Player> dataTable;
	public AVLTree<Double, Integer> pointsPerGame;
	public AVLTree<Double, Integer> reboundsPerGame;
	public AVLTree<Double, Integer> assistsPerGame;
	public AVLTree<Double, Integer> robberiesPerGame;
	
	public DataManagement() throws IOException {
		dataTable = new HashTable<>(SIZE);
		pointsPerGame = new AVLTree<>();
		reboundsPerGame = new AVLTree<>();
		assistsPerGame = new AVLTree<>();
		robberiesPerGame = new AVLTree<>();
		importData("data/data_200k.csv");
	}
	public HashTable<Integer, Player> getDataTable(){
		return dataTable;
	}
	public AVLTree<Double, Integer> getPointsPerGame(){
		return pointsPerGame;
	}
	public AVLTree<Double, Integer> getRoboundsPerGame(){
		return reboundsPerGame;
	}
	public AVLTree<Double, Integer> getAssistsPerGame(){
		return assistsPerGame;
	}
	public AVLTree<Double, Integer> getRobberiesPerGame(){
		return robberiesPerGame;
	}
	public void clearAll() {
		dataTable = new HashTable<>(SIZE);
		pointsPerGame = new AVLTree<>();
		reboundsPerGame = new AVLTree<>();
		assistsPerGame = new AVLTree<>();
		robberiesPerGame = new AVLTree<>();
	}
	//LEER DATOS
	public void importData(String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		br.readLine(); //lee la primera linea con los nombres de las columnas.
		String line = br.readLine();
//		long timeStart = System.currentTimeMillis();
		while(line != null) {
			String[] pData = line.split(",");
			Integer key = new Integer(Integer.parseInt(pData[0])-1);
			String name = pData[1];
			String lastN = pData[2];
			int age = Integer.parseInt(pData[3]);
			String team = pData[4];
			double ppg = Double.parseDouble(pData[5]);
			double rpg = Double.parseDouble(pData[6]);
			double apg = Double.parseDouble(pData[7]);
			double rbpg = Double.parseDouble(pData[8]);
			double bpg = Double.parseDouble(pData[9]);
			Player newPlayer = new Player(key,name, lastN, team, age, ppg, rpg, apg, rbpg, bpg);
			
			dataTable.addItem(key, newPlayer);
			pointsPerGame.insert(ppg, key);
			reboundsPerGame.insert(rpg, key);
			assistsPerGame.insert(apg, key);
			robberiesPerGame.insert(rbpg, key);
			line = br.readLine();
		}
//		long timeFinal = System.currentTimeMillis();
//		System.out.println("Se demoro en leer: "+ (timeFinal-timeStart));
		br.close();
	}
	public void addNewPlayer(String name, String lastN, String team, int age, double ppg, double rpg, double apg, double rbpg, double bpg) {
		Player newP = new Player(SIZE, name, lastN, team, age, ppg, rpg, apg, rbpg, bpg);
		dataTable.addItem(SIZE, newP);
		pointsPerGame.insert(ppg, SIZE);
		reboundsPerGame.insert(rpg, SIZE);
		assistsPerGame.insert(apg, SIZE);
		robberiesPerGame.insert(rbpg, SIZE);
		SIZE++;
	}
	//METODO SIMPLIFICADO PARA BUSCAR LISTA DE JUGADORES.
	public List<Player> getStadistic(String stadistic, String valueType, double value){
		List<Player> players = new ArrayList<>();
		switch(stadistic) {
			case "Points Per Game":
				players = getStadistic(pointsPerGame, valueType, value);
				break;
			case "Rebounds Per Game":
				players = getStadistic(reboundsPerGame, valueType, value);
				break;
			case "Assists Per Game":
				players = getStadistic(assistsPerGame, valueType, value);
				break;
			case "Robberies Per Game":
				players = getStadistic(robberiesPerGame, valueType, value);
				break;
			case "Blocks Per Game":
				players = getStadisticBPG(valueType, value);
				break;
		}
		return players;
	}
	public List<Player> getStadistic(AVLTree<Double, Integer> tree, String valueType, double value){
		List<Player> players = new ArrayList<>();
		switch(valueType) {
			case "Igual":
				List<Integer> pKE = tree.getEquals(new Double(value));
				if(pKE != null) {
					for(int i: pKE) {
						players.add(dataTable.getItem(i));
					}
				}
				break;
			case "Menor":
				List<Integer> pKL = tree.getLess(new Double(value));
				if(pKL != null) {
					for(int i: pKL) {
						players.add(dataTable.getItem(i));
					}
				}
				break;
 			case "Mayor":
				List<Integer> pKH = tree.getHigher(new Double(value));
				if(pKH != null) {
					for(int i: pKH) {
						players.add(dataTable.getItem(i));
					}
				}
				break;
		}
		return players;	
	}
	//BUSCAR LISTA DE JUGADORES POR 'PUNTOS POR JUEGO'. AVL
	public List<Player> getStadisticPPG(String valueType, double value) {
		List<Player> players = new ArrayList<>();
		switch(valueType) {
			case "Equals to":
				List<Integer> pKE = pointsPerGame.getEquals(new Double(value));
				if(pKE != null) {
					for(int i: pKE) {
						players.add(dataTable.getItem(i));
					}
				}
				break;
			case "Less than":
				List<Integer> pKL = pointsPerGame.getLess(new Double(value));
				if(pKL != null) {
					for(int i: pKL) {
						players.add(dataTable.getItem(i));
					}
				}
				break;
 			case "Greater than":
				List<Integer> pKH = pointsPerGame.getHigher(new Double(value));
				if(pKH != null) {
					for(int i: pKH) {
						players.add(dataTable.getItem(i));
					}
				}
				break;
		}
		return players;
	}
	//BUSCAR LISTA DE JUGADORES POR 'REBOTES POR JUEGO'. AVL
	public List<Player> getStadisticRPG(String valueType, double value) {
		List<Player> players = new ArrayList<>();
		switch(valueType) {
			case "Equals to":
				List<Integer> pKE = reboundsPerGame.getEquals(new Double(value));
				if(pKE != null) {
					for(int i: pKE) {
						players.add(dataTable.getItem(i));
					}
				}
				break;
			case "Less than":
				List<Integer> pKL = reboundsPerGame.getLess(new Double(value));
				if(pKL != null) {
					for(int i: pKL) {
						players.add(dataTable.getItem(i));
					}
				}
				break;
			case "Greater than":
				List<Integer> pKH = reboundsPerGame.getHigher(new Double(value));
				if(pKH != null) {
					for(int i: pKH) {
						players.add(dataTable.getItem(i));
					}
				}
				break;
		}
		return players;
	}
	//BUSCAR LISTA DE JUGADORES POR 'ASISTENCIAS POR JUEGO'. AVL
	public List<Player> getStadisticAPG(String valueType, double value) {
		List<Player> players = new ArrayList<>();
		switch(valueType) {
			case "Equals to":
				List<Integer> pKE = assistsPerGame.getEquals(new Double(value));
				if(pKE != null) {
					for(int i: pKE) {
						players.add(dataTable.getItem(i));
					}
				}
				break;
			case "Less than":
				List<Integer> pKL = assistsPerGame.getLess(new Double(value));
				if(pKL != null) {
					for(int i: pKL) {
						players.add(dataTable.getItem(i));
					}
				}
				break;
			case "Greater than":
				List<Integer> pKH = assistsPerGame.getHigher(new Double(value));
				if(pKH != null) {
					for(int i: pKH) {
						players.add(dataTable.getItem(i));
					}
				}
				break;
		}
		return players;
	}
	//BUSCAR LISTA DE JUGADORES POR 'ROBOS POR JUEGO'. AVL
	public List<Player> getStadisticRBPG(String valueType, double value) {
		List<Player> players = new ArrayList<>();
		switch(valueType) {
			case "Equals to":
				List<Integer> pKE = robberiesPerGame.getEquals(new Double(value));
				if(pKE != null) {
					for(int i: pKE) {
						players.add(dataTable.getItem(i));
					}
				}
				break;
			case "Less than":
				List<Integer> pKL = robberiesPerGame.getLess(new Double(value));
				if(pKL != null) {
					for(int i: pKL) {
						players.add(dataTable.getItem(i));
					}
				}
				break;
			case "Greater than":
				List<Integer> pKH = robberiesPerGame.getHigher(new Double(value));
				if(pKH != null) {
					for(int i: pKH) {
						players.add(dataTable.getItem(i));
					}
				}
				break;
		}
		return players;
	}
	//BUSCAR LISTA DE JUGADORES POR 'BLOQUEOS POR JUEGO'. HASHTABLE (LINEAL)
	public List<Player> getStadisticBPG(String valueType, double value){
		List<Player> players = new ArrayList<>();
		switch(valueType) {
			case "Equals to":
				for(int i = 0; i<dataTable.getSize(); i++) {
					if(dataTable.getItem(i).getBlocksPerGame() == value) {
						players.add(dataTable.getItem(i));
					}
				}
				break;
			case "Less than":
				for(int i = 0; i<dataTable.getSize(); i++) {
					if(dataTable.getItem(i).getBlocksPerGame() < value) {
						players.add(dataTable.getItem(i));
					}
				}
				break;
			case "Greater than":
				for(int i = 0; i<dataTable.getSize(); i++) {
					if(dataTable.getItem(i).getBlocksPerGame() > value) {
						players.add(dataTable.getItem(i));
					}
				}
				break;			
		}
		return players;
	}
 	//BUSCAR LISTA DE JUGADORES POR MEDIO DE DOS CRITERIOS DE BUSQUEDA.
	public List<Player> getDataWithTwoCriteria(String criterionF, String vTypeF, double valueF, String criterionS, String vTypeS, double valueS){
 		List<Player> players = new ArrayList<>();
 		switch(criterionF) {
 			case "Points Per Game":
 				players = getStadisticPPG(vTypeF, valueF);
 				break;
 			case "Rebounds Per Game":
 				players = getStadisticRPG(vTypeF, valueF);
 				break;
 			case "Assists Per Game":
 				players = getStadisticAPG(vTypeF, valueF);
 				break;
 			case "Robberies Per Game":
 				players = getStadisticRBPG(vTypeF, valueF);
 				break;
 			case "Blocks Per Game":
 				players = getStadisticBPG(vTypeF, valueF);
 				break;
 		}
 		AVLTree<Double, Integer> order = subTree(players, criterionS);
 		List<Player> solution = new ArrayList<>();
 		switch(vTypeS) {
 			case "Igual":
 				List<Integer> pKE = order.getEquals(valueS);
				if(pKE != null) {
					for(int i: pKE) {
						solution.add(dataTable.getItem(i));
					}
				}
 				break;
 			case "Menor":
 				List<Integer> pKL = order.getLess(valueS);
 				if(pKL != null) {
					for(int i: pKL) {
						solution.add(dataTable.getItem(i));
					}
				}
 				break;
 			case "Mayor":
 				List<Integer> pKH = order.getHigher(valueS);
 				if(pKH != null) {
					for(int i: pKH) {
						solution.add(dataTable.getItem(i));
					}
				}
 				break;
 		}
 		return solution;
 	}
	public AVLTree<Double, Integer> subTree(List<Player> players, String type) {
		if(!players.isEmpty()) {
			AVLTree<Double, Integer> temp = new AVLTree<>();
			switch(type) {
			case "Points Per Game":
				for(Player i: players) {
					temp.insert(i.getPointsPerGame(), i.getKey());
				}
			case "Rebounds Per Game":
				for(Player i: players) {
					temp.insert(i.getReboundsPerGame(), i.getKey());
				}
			case "Assists Per Game":
				for(Player i: players) {
					temp.insert(i.getAssistsPerGame(), i.getKey());
				}
			case "Robberies Per Game":
				for(Player i: players) {
					temp.insert(i.getRobberiesPerGame(), i.getKey());
				}
			case "Blocks Per Game":
				for(Player i: players) {
					temp.insert(i.getBlocksPerGame(), i.getKey());
				}
			}
			return temp;
		}else return null;
	}
	
	public String printPlayers() {
		String players = "";
		for(int i = 0; i<SIZE; i++) {
			players += dataTable.getItem(new Integer(i));
		}
		return players;
	}
	public String printPlayers(List<Player> players) {
		String str = "";
		for(Player i: players) {
			str += i.getPointsPerGame()+" ";
		}
		return str;
	}
}
