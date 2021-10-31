package model;

public class Player {
	private String name;
	private String lastName;
	private String team;
	private int age;
	
	// Datos estadisticos.
	private double pointsPerGame;
	private double reboundPerGame;
	//private double assistsPerGame;
	//private double robberiesPerGame;
	//private double blocksPerGame;
	
	public Player(String n, String ls, String t, int a, double ppg, double rpg/*, double apg, double rbpg, double bpg*/) {
		name = n;
		lastName = ls;
		team = t;
		age = a;
		
		setPointsPerGame(ppg);
		setReboundPerGame(rpg);
		//setAssistsPerGame(apg);
		//setRobberiesPerGame(rbpg);
		//setBlocksPerGame(bpg);
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

	public double getReboundPerGame() {
		return reboundPerGame;
	}

	public void setReboundPerGame(double reboundPerGame) {
		this.reboundPerGame = reboundPerGame;
	}
	/*
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
	*/
	public String toString() {
		String str = "";
		str += getTeam()+", ";
		str += getName()+" ";
		str += getLastName()+", ";
		str += getAge()+"\n";
		
		str += getPointsPerGame()+" ";
		str += getReboundPerGame()+" ";
		//str += getAssistsPerGame()+" ";
		//str += getRobberiesPerGame()+" ";
		//str += getBlocksPerGame();
		str += "\n";
		return str;
	}
}
