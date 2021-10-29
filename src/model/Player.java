package model;

public class Player {
	private String name;
	private String lastName;
	private String team;
	private int age;
	
	// Datos estadisticos.
	private float pointsPerGame;
	private float reboundPerGame;
	//private float assistsPerGame;
	//private float robberiesPerGame;
	//private float blocksPerGame;
	
	public Player(String n, String ls, String t, int a, float ppg, float rpg/*, float apg, float rbpg, float bpg*/) {
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

	public float getPointsPerGame() {
		return pointsPerGame;
	}

	public void setPointsPerGame(float pointsPerGame) {
		this.pointsPerGame = pointsPerGame;
	}

	public float getReboundPerGame() {
		return reboundPerGame;
	}

	public void setReboundPerGame(float reboundPerGame) {
		this.reboundPerGame = reboundPerGame;
	}
	/*
	public float getAssistsPerGame() {
		return assistsPerGame;
	}

	public void setAssistsPerGame(float assistsPerGame) {
		this.assistsPerGame = assistsPerGame;
	}

	public float getRobberiesPerGame() {
		return robberiesPerGame;
	}

	public void setRobberiesPerGame(float robberiesPerGame) {
		this.robberiesPerGame = robberiesPerGame;
	}

	public float getBlocksPerGame() {
		return blocksPerGame;
	}

	public void setBlocksPerGame(float blocksPerGame) {
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
