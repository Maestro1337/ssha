package Model;

import java.awt.List;
import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skills.*;

public class Player {
	
	private Image userImage;
	private Image noStepImage;
	private Image firstStepImage;
	private Image secondStepImage;
	private float imgX;
	private float imgY;
	private float startingPosX;
	private float startingPosY;
	
	private boolean isAlive = true;
	
	private int HP;
	private int maxHP;
	private String name;
	private String classType;
	private double armor=0;
	private int kills=0;
	private int deaths;
	private int coins;
	private Skill[] skillList = new Skill[5];
	
	private ArrayList<StatusEffect> statusEffectList = new ArrayList<StatusEffect>();
	
	//Movement variables
	private float mouseXPosMove;
	private float mouseYPosMove;
	private double moveSpeed = 1;
	
	float rotation=0;
	
	private int moveCounter=0;
	private float xDirMove;
	private float yDirMove;
	private float genDirMove;
	private Double findNaN;
	
	private boolean isStunned = false;
	
	
	
	private boolean isRunning = false;
	
	public Player(String name, String type, float x, float y, int maxHP, double speed, double armor){
		this.name = name;
		this.classType = type;
		
		imgX = startingPosX = x;
		imgY = startingPosY = y;
		
		HP = this.maxHP = maxHP;
		moveSpeed = speed;
		this.armor = armor;
		statusEffectList = new ArrayList<StatusEffect>();
	}

	public String getType(){
		return classType;
	}
	
	public boolean isAlive(){
		return isAlive;
	}
	public void setAliveState(boolean state){
		isAlive = state;
	}
	
	//Getters for the movements
	
	public Image getImage(){
		return userImage;
	}
	public Image getFirstStepImage(){
		return firstStepImage;
	}
	public Image getSecondStepImage(){
		return secondStepImage;
	}
	public float getX(){
		return imgX;
	}
	public float getY(){
		return imgY;
	}
	public float getStartX(){
		return startingPosX;
	}
	public float getStartY(){
		return startingPosY;
	}
	
	public void addX(float x){
		imgX += x;
	}
	public void addY(float y){
		imgY += y;
	}
	public void setX(float x){
		imgX = x;
	}
	public void setY(float y){
		imgY = y;
	}
	
	public void setMouseXPosMove(float x){
		mouseXPosMove = x;
	}
	public void setMouseYPosMove(float y){
		mouseYPosMove = y;
	}
	
	public float getMouseXPosMove(){
		return mouseXPosMove;
	}
	public float getMouseYPosMove(){
		return mouseYPosMove;
	}
	public double getMoveSpeed(){
		return moveSpeed;
	}
	public int getMoveCounter(){
		return moveCounter;
	}
	public void incMoveCounter(){
		moveCounter++;
	}
	public void resetMoveCounter(){
		moveCounter = 0;
	}
	
	public float getXDirMove(){
		return xDirMove;
	}
	public float getYDirMove(){
		return yDirMove;
	}
	public float getGenDirMove(){
		return genDirMove;
	}
	public void setXDirMove(float dir){
		xDirMove = dir;
	}
	public void setYDirMove(float dir){
		yDirMove = dir;
	}
	public void setGenDirMove(float dir){
		genDirMove = dir;
	}
	
	
	public double getFindNaN(){
		return findNaN;
	}
	//Getters for the attacks
	public boolean isRunning(){
		return isRunning;
	}
	public void setRunningState(boolean state){
		isRunning = state;
	}
	public float getRotation(){
		return rotation;
	}
	
	public void setRotation(float angle){
		userImage.setRotation(angle);
		firstStepImage.setRotation(angle);
		secondStepImage.setRotation(angle);
		rotation = angle;
	}
	
	//Getters and setters for stats
	public int getHP(){
		return HP;
	}
	public int getMaxHP(){
		return maxHP;
	}
	public void dealDamage(int damage){
		//Remove some damage given depending on armor
		damage *= (1-armor);
		if(damage >= 0){
			HP -= damage;
		}
	//	System.out.println("DAMAGE DEALT!");
	}
	public void resetHP(){
		HP = maxHP;
		isAlive = true;
		System.out.println("HEALTH RESTORED");
	}
	public String getName(){
		return name;
	}
	public double getArmor(){
		return armor;
	}
	public void addArmor(double armor){
		this.armor += armor;
	}
	public void addMovementSpeed(double speed){
		this.moveSpeed += speed;
	}
	public int getKills(){
		return kills;
	}
	public void incKills(){
		kills++;
	}
	
	
	
	public void setImages(Image image, Image first, Image second){
		if(image != null)
			userImage = noStepImage = image;
		
		if(first != null)
			firstStepImage = first;
		
		if(second != null)
			secondStepImage = second;
	}
	public void changeUserImage(){
		if(isRunning && !isStunned){
			if(userImage == noStepImage || userImage == secondStepImage)
				userImage = firstStepImage;
			else if(userImage == noStepImage || userImage == firstStepImage)
				userImage = secondStepImage;
		} else {
			userImage = noStepImage;
		}
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
	public ArrayList<StatusEffect> getStatusEffects(){
		return statusEffectList;
	}
	public void addStatusEffect(StatusEffect SE){
		statusEffectList.add(SE);
	}
	public void removeStatusEffect(StatusEffect SE){
		statusEffectList.remove(SE);
	}
	public void resetStatusEffects(){
		statusEffectList = new ArrayList<StatusEffect>();
	}
	public boolean isStunned(){
		return isStunned;
	}
	public void setStunState(boolean state){
		isStunned = state;
	}
}
