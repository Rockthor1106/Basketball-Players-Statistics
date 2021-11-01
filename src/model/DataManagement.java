package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import datastructure.AVLTree;
import datastructure.HashTable;

public class DataManagement {
	
	public HashTable<Integer, Player> dataTable;
	public AVLTree<Double, Integer> pointsPerGame;
	public AVLTree<Double, Integer> reboundsPerGame;
	public AVLTree<Double, Integer> assistsPerGame;
	public AVLTree<Double, Integer> robberiesPerGame;
	public final int size = 1000;
	
	public DataManagement() throws IOException {
		dataTable = new HashTable<>(size);
		pointsPerGame = new AVLTree<>();
		reboundsPerGame = new AVLTree<>();
		assistsPerGame = new AVLTree<>();
		robberiesPerGame = new AVLTree<>();
		importData("data/dataPrueba.csv");
	
		List<Player> players = getStadisticPPG("Menor", 57);
		System.out.println(printPlayers(players));
		players = getStadisticPPG("Igual", 57);
		System.out.println(printPlayers(players));
		players = getStadisticPPG("Mayor", 57);
		System.out.println(printPlayers(players));
	}
	
	public HashTable<Integer, Player> getDataTable() {
		return dataTable;
	}
	
	public void importData(String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		br.readLine(); //lee la primera linea con los nombres de las columnas.
		String line = br.readLine();
		while(line != null) {
			String[] pData = line.split(",");
			Integer key = new Integer(Integer.parseInt(pData[0])-1);
			String n = pData[1];
			String ls = pData[2];
			String t = "huevos"; //Nombre del equipo.
			int age = Integer.parseInt(pData[3]);
			double ppg = Double.parseDouble(pData[4]);
			double rpg = Double.parseDouble(pData[5]);
			Player newPlayer = new Player(n, ls, t, age, ppg, rpg);
			
			dataTable.addItem(key, newPlayer);
			pointsPerGame.insert(ppg, key);
			
			line = br.readLine();
		}
		br.close();
	}
	public String printPlayers() {
		String players = "";
		for(int i = 0; i<size; i++) {
			players += dataTable.getItem(new Integer(i));
		}
		return players;
	}
	
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
	//FALTA
	public List<Player> getStadisticRPG(String valueType, double value) {
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
	//FALTA
	public List<Player> getStadisticAPG(String valueType, double value) {
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
	//FALTA
	public List<Player> getStadisticRBPG(String valueType, double value) {
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
	public String printPlayers(List<Player> players) {
		String str = "";
		for(Player i: players) {
			str += i.getPointsPerGame()+" ";
		}
		return str;
	}
}
