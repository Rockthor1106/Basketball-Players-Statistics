package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import datastructure.AVLTree;
import datastructure.HashTable;

public class DataManagement {
	
	public final int SIZE = 200000;
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
	
		System.out.println(dataTable.getSize());
		System.out.println(pointsPerGame.getSize());
	}
	
	public HashTable<Integer, Player> getDataTable() {
		return dataTable;
	}
	//LEER DATOS
	public void importData(String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		br.readLine(); //lee la primera linea con los nombres de las columnas.
		String line = br.readLine();
		long timeStart = System.currentTimeMillis();
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
			Player newPlayer = new Player(name, lastN, team, age, ppg, rpg, apg, rbpg, bpg);
			
			dataTable.addItem(key, newPlayer);
			pointsPerGame.insert(ppg, key);
			reboundsPerGame.insert(rpg, key);
			assistsPerGame.insert(apg, key);
			robberiesPerGame.insert(rbpg, key);
			line = br.readLine();
		}
		long timeFinal = System.currentTimeMillis();
		System.out.println("Se demoro en leer: "+ (timeFinal-timeStart));
		br.close();
	}
	public String printPlayers() {
		String players = "";
		for(int i = 0; i<SIZE; i++) {
			players += dataTable.getItem(new Integer(i));
		}
		return players;
	}
	//BUSCAR LISTA DE JUGADORES POR 'PUNTOS POR JUEGO'. AVL
	public List<Player> getStadisticPPG(String valueType, double value) {
		List<Player> players = new ArrayList<>();
		switch(valueType) {
			case "Igual":
				List<Integer> pKE = pointsPerGame.getEquals(new Double(value));
				if(pKE != null) {
					for(int i: pKE) {
						players.add(dataTable.getItem(i));
					}
				}
				break;
			case "Menor":
				List<Integer> pKL = pointsPerGame.getLess(new Double(value));
				if(pKL != null) {
					for(int i: pKL) {
						players.add(dataTable.getItem(i));
					}
				}
				break;
 			case "Mayor":
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
			case "Igual":
				List<Integer> pKE = reboundsPerGame.getEquals(new Double(value));
				if(pKE != null) {
					for(int i: pKE) {
						players.add(dataTable.getItem(i));
					}
				}
				break;
			case "Menor":
				List<Integer> pKL = reboundsPerGame.getLess(new Double(value));
				if(pKL != null) {
					for(int i: pKL) {
						players.add(dataTable.getItem(i));
					}
				}
				break;
			case "Mayor":
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
			case "Igual":
				List<Integer> pKE = assistsPerGame.getEquals(new Double(value));
				if(pKE != null) {
					for(int i: pKE) {
						players.add(dataTable.getItem(i));
					}
				}
				break;
			case "Menor":
				List<Integer> pKL = assistsPerGame.getLess(new Double(value));
				if(pKL != null) {
					for(int i: pKL) {
						players.add(dataTable.getItem(i));
					}
				}
				break;
			case "Mayor":
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
			case "Igual":
				List<Integer> pKE = robberiesPerGame.getEquals(new Double(value));
				if(pKE != null) {
					for(int i: pKE) {
						players.add(dataTable.getItem(i));
					}
				}
				break;
			case "Menor":
				List<Integer> pKL = robberiesPerGame.getLess(new Double(value));
				if(pKL != null) {
					for(int i: pKL) {
						players.add(dataTable.getItem(i));
					}
				}
				break;
			case "Mayor":
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
			case "Igual":
				for(int i = 0; i<dataTable.getSize(); i++) {
					if(dataTable.getItem(i).getBlocksPerGame() == value) {
						players.add(dataTable.getItem(i));
					}
				}
				break;
			case "Menor":
				for(int i = 0; i<dataTable.getSize(); i++) {
					if(dataTable.getItem(i).getBlocksPerGame() < value) {
						players.add(dataTable.getItem(i));
					}
				}
				break;
			case "Mayor":
				for(int i = 0; i<dataTable.getSize(); i++) {
					if(dataTable.getItem(i).getBlocksPerGame() > value) {
						players.add(dataTable.getItem(i));
					}
				}
				break;			
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
