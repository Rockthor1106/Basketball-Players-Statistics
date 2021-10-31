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
		importData("data/data.csv");
		
		List<Player> players = getStadisticPPG("Igual", 50);
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
		if(valueType.equalsIgnoreCase("igual")) {
			List<Integer> pK = pointsPerGame.getEquals(new Double(value));
			if(pK != null) {
				for(int i: pK) {
					players.add(dataTable.getItem(i));
				}
			}
		}
		return players;
	}
	
	public String printPlayers(List<Player> players) {
		String str = "";
		for(Player i: players) {
			str += i.toString();
		}
		return str;
	}
}
