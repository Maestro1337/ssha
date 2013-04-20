package Model;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skills.*;

public class Player {
	
	private Image userImage;
	private Image firstStepImage;
	private Image secondStepImage;
	private float imgX;
	private float imgY;
	
	
	
	private int HP;
	private String name;
	private int armor;
	private int kills;
	private int deaths;
	private int coins;
	private Skill[] skillList = new Skill[5];
	
	//Movement variables
	private float mouseXPosMove;
	private float mouseYPosMove;
	private double moveSpeed = 0.3;
	
	double rotation=0;
	
	private int moveCounter=0;
	private float xDirMove;
	private float yDirMove;
	private float genDirMove;
	private Double findNaN;
	
	
	
	
	
	private boolean isRunning = false;
	
	public Player(String name, float x, float y){
		this.name = name;
		try {
			userImage = new Image("res/stand.png");
			firstStepImage = new Image("res/walk1.png");
			secondStepImage = new Image("res/walk2.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		skillList[0] = new SkillSlash();
		skillList[1] = new SkillFireball();
		skillList[2] = new SkillFirestorm();
		skillList[3] = new SkillIceNeedle();
		skillList[4] = new SkillSuperSlowTestSkill();
		
		
		imgX = x;
		imgY = y;
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
	
	public void addX(float x){
		imgX += x;
	}
	public void addY(float y){
		imgY += y;
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
	
	
	public Skill[] getSkills(){
		return skillList;
	}
	public boolean isRunning(){
		return isRunning;
	}
	public void setRunningState(boolean state){
		isRunning = state;
	}
	public double getRotation(){
		return rotation;
	}
	
	public void setRotation(float angle){
		userImage.setRotation(angle);
		firstStepImage.setRotation(angle);
		secondStepImage.setRotation(angle);
	}
	
	//Getters and setters for stats
	public int getHP(){
		return HP;
	}
	public void dealDamage(int damage){
		HP -= damage;
		System.out.println("DAMAGE DEALT!");
	}
	public void resetHP(){
		HP = 100;
		System.out.println("HEALTH RESTORED");
	}
	public String getName(){
		return name;
	}
	
	
}
