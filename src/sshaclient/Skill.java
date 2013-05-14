package sshaclient;

//Almost empty test-class
public class Skill {
	
	private String name;
	private String smallName;
	private int lvlOfSkill;
	private boolean isAttacking;
	private float attImgX;
	private float attImgY;
	private float rotation;
	
	public Skill(String name) {
		this.name = name;
		this.smallName = name.toLowerCase().replaceAll("\\s", "");
		lvlOfSkill = 1;
		isAttacking = false;
		
		attImgX = 0;
		attImgY = 0;
	}
	
	public String getSmallName() {
		return smallName;
	}
	
	public String getName() {
		return name;
	}
	
	public int getCurrentLvl() {
		return lvlOfSkill;
	}
	
	public void setCurrentLvl(int lvl) {
		lvlOfSkill = lvl;
	}
	
	public boolean isAttacking() {
		return isAttacking;
	}
	
	public void setAttackingState(boolean state) {
		isAttacking = state;
	}
	
	public float getAttX() {
		return attImgX;
	}
	
	public float getAttY() {
		return attImgY;
	}
	
	public void setAttX(float x){
		attImgX = x;
	}
	
	public void setAttY(float y){
		attImgY = y;
	}
	
	public float getRotation() {
		return rotation;
	}
	
	public void setRotation(float rotation) {
		this.rotation = rotation;
	}
}
