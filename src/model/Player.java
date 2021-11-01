package model;

public class Player {
	
	private int key;
	private String name;
	private String lastName;
	private String team;
	private int age;
	
	// Datos estadisticos.
	private double pointsPerGame;
	private double reboundsPerGame;
	private double assistsPerGame;
	private double robberiesPerGame;
	private double blocksPerGame;
	
	public Player(int k, String n, String ls, String t, int a, double ppg, double rpg, double apg, double rbpg, double bpg) {
		key = k;
		name = n;
		lastName = ls;
		team = t;
		age = a;
		pointsPerGame = ppg;
		reboundsPerGame = rpg;
		assistsPerGame = apg;
		robberiesPerGame = rbpg;
		blocksPerGame = bpg;
	}

	public int getKey() {
		return key;
	}
	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}

	public String getTeam() {
		return team;
	}

	public int getAge() {
		return age;
	}

	public double getPointsPerGame() {
		return pointsPerGame;
	}

	public void setPointsPerGame(double pointsPerGame) {
		this.pointsPerGame = pointsPerGame;
	}

	public double getReboundsPerGame() {
		return reboundsPerGame;
	}

	public void setReboundsPerGame(double reboundPerGame) {
		this.reboundsPerGame = reboundPerGame;
	}
	
	public double getAssistsPerGame() {
		return assistsPerGame;
	}

	public void setAssistsPerGame(double assistsPerGame) {
		this.assistsPerGame = assistsPerGame;
	}

	public double getRobberiesPerGame() {
		return robberiesPerGame;
	}

	public void setRobberiesPerGame(double robberiesPerGame) {
		this.robberiesPerGame = robberiesPerGame;
	}

	public double getBlocksPerGame() {
		return blocksPerGame;
	}

	public void setBlocksPerGame(double blocksPerGame) {
		this.blocksPerGame = blocksPerGame;
	}
	
	public String toString() {
		String str = "";
		str += getTeam()+", ";
		str += getName()+" ";
		str += getLastName()+", ";
		str += getAge()+"\n";
		
		str += getPointsPerGame()+" ";
		str += getReboundsPerGame()+" ";
		str += getAssistsPerGame()+" ";
		str += getRobberiesPerGame()+" ";
		str += getBlocksPerGame();
		str += "\n";
		return str;
	}
}
