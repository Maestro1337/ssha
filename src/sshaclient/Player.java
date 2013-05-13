package sshaclient;

public class Player {

	private String[] stats;
	private String name;
	private int id = 0;
	private String ctrlType;
	private int x = 0;
	private int y = 0;
	private float angle = 0;
	private String mode = "lobby";
	private boolean connected = false;
	private int miscData = 13;
	private int miscData2 = 37;
	
	private Skill[] skillList;
	private boolean isAlive;
	
	private String classType;
	private int HP;
	private int maxHP;
	
	public Player(String name, String ctrlType, String classType) {
		this.name = name;
		this.ctrlType = ctrlType;
		stats = new String[7];
		
		HP = 100;
		maxHP = 100;
		
		isAlive = true;
		skillList = new Skill[5];
		skillList[0] = new Skill("Test1");
		skillList[2] = new Skill("Test2");
		skillList[2] = new Skill("Test3");
		skillList[3] = new Skill("Test4");
		skillList[4] = new Skill("Test5");
		
		this.classType = classType;
	}
	
	public String[] getStats() {
		if(mode.equals("lobby")) {
			
		} else if(mode.equals("arena")) {
			
		} else {
			
		}
		
		
		return stats;
	}
	
	public void setStats(String[] stats) {
		
	}
	
	public String getName() {
		return name;
	}
	
	public int getID() {
		return id;
	}
	
	public String getControlType() {
		return ctrlType;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public float getRotation() {
		return angle;
	}
	
	public String getMode() {
		return mode;
	}
	
	public int getMisc1() {
		return miscData;
	}

	public int getMisc2() {
		return miscData2;
	}
	
	public boolean isConnected() {
		return connected;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setControlType(String ctrlType) {
		this.ctrlType = ctrlType;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setRotation(float angle) {
		this.angle = angle;
	}
	
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	public void setMisc1(int misc) {
		this.miscData = misc;
	}
	
	public void setMisc2(int misc) {
		this.miscData2 = misc;
	}
	
	public void setConnected(boolean connected) {
		this.connected = connected;
	}
	
	public boolean isAlive(){
		return isAlive;
	}
	
	public void setAliveState(boolean state){
		isAlive = state;
	}
	
	public void setSkillList(Skill[] chosenSkills) {
		if(chosenSkills != null){
			skillList[0] = chosenSkills[0];
			skillList[1] = chosenSkills[1];
			skillList[2] = chosenSkills[2];
			skillList[3] = chosenSkills[3];
			skillList[4] = chosenSkills[4];
		}
	}
	
	public Skill[] getSkillList(){
		return skillList;
	}
	
	public int getHP(){
		return HP;
	}
	
	public int getMaxHP(){
		return maxHP;
	}
	
	public void setHP(int HP) {
		this.HP = HP;
	}
	
	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}
	
	public String getType(){
		return classType;
	}
}
