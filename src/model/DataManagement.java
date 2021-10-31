package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import datastructure.HashTable;

public class DataManagement {
	
	private HashTable<Integer, Player> dataTable;
	private final int size = 1000;
	
	public DataManagement() throws IOException {
		dataTable = new HashTable<>(size);
		
		importData("data/data.csv");
		
		System.out.println(printPlayers());
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
			float ppg = Float.parseFloat(pData[4]);
			float rpg = Float.parseFloat(pData[5]);
			Player newPlayer = new Player(n, ls, t, age, ppg, rpg);
			dataTable.addItem(key, newPlayer);
			
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
}
