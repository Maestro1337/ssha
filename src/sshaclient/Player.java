package sshaclient;

import Model.Skills.Hunter.*;
import Model.Skills.Warrior.*;
import Model.Skills.Wizard.*;

public class Player {

	private String[] stats;
	private String name;
	private int id;
	private String ctrlType;
	private float x;
	private float y;
	private float angle = 0;
	private String mode = "lobby";
	private boolean connected = false;
	private boolean isReady;
	
	private Skill[] skillList;
	private boolean isAlive;
	
	private String classType;
	private int HP;
	private int maxHP;
	private int kills;
	private int coins;
	private double moveSpeed;
	private boolean isRunning;
	private boolean isStunned;
	
	public Player(String name, String ctrlType, String classType, float x, float y, int maxHP, double speed, double armor ,int index) {
		this.name = name;
		this.ctrlType = ctrlType;
		this.x = x;
		this.y = y;
		this.id = index;
		
		stats = new String[7];
		
		HP = 100;
		maxHP = 100;
		kills = 0;
		coins = 0;
		moveSpeed = 0;
		isRunning = false;
		isStunned = false;
		
		isAlive = true;
		
		skillList = new Skill[5];
		
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
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public float getRotation() {
		return angle;
	}
	
	public String getMode() {
		return mode;
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
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public void setRotation(float angle) {
		this.angle = angle;
	}
	
	public void setMode(String mode) {
		this.mode = mode;
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
	
	public int getKills() {
		return kills;
	}
	
	public void setKills(int kills) {
		this.kills = kills;
	}
	
	public int getGold() {
		return coins;
	}
	
	public void setGold(int coins) {
		this.coins = coins;
	}
	
	public boolean isRunning(){
		return isRunning;
	}
	
	public void setRunningState(boolean state){
		isRunning = state;
	}
	
	public boolean isStunned(){
		return isStunned;
	}
	
	public void setStunState(boolean state){
		isStunned = state;
	}
	
	public double getMoveSpeed() {
		return moveSpeed;
	}
	
	public void setMoveSpeed(double speed) {
		this.moveSpeed = speed;
	}
	
	public boolean isReady() {
		return isReady;
	}
	
	public void setReady(boolean ready) {
		isReady = ready;
	}
	
}
