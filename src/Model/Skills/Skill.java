package Model.Skills;

import Model.Player;
import Model.StatusEffect;

import java.sql.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Skill{

	private String name;
	private int cooldown;
	private int range;
	private int areaOfEffect;
	private int cost;
	private int damage;
	private StatusEffect spellEffect = new StatusEffect();
	
	private Image attackImage;
	private float attImgX;
	private float attImgY;
	private int imgWidth;
	private int imgHeight;
	
	private Image endStateImage;
	private int endStateImgWidth;
	private int endStateImgHeight;
	
	//Attack variables
	private float mouseXPosAtt;
	private float mouseYPosAtt;
	private double attSpeed;
	
	private int attCounter=0;
	private float xDirAtt;
	private float yDirAtt;
	private float genDirAtt;

	private float attackRange;
	private boolean isAttacking = false;
	
	private boolean isColliding = false;
	
	private boolean isProjectile = true;
	private long endStateStartTime = 0;
	private long endStateElapsedTime = 0;
	private int endStateDuration = 1000;
	
	private boolean nonProjectileEndState = false;

	private long CDstartTime = 0;
	private long CDelapsedTime = 0;
	
	public Skill(String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE){
		this.name = name;
		cooldown = cd;
		this.range = range;
		areaOfEffect = aoe;
		this.cost = cost;
		this.damage = damage;
		spellEffect = SE;
		attackRange = range;
		if(speed < 100){
			attSpeed = speed;
		}else{
			isProjectile = false;
		}
		
		//Backup image if it doesn't get one set by the extended skillClass
		try {
			attackImage = new Image("res/awesomeGreenSquare.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setImage(attackImage, 5, 4);
		
		
	}
	
	public void setImage(Image image, int height, int width){
		if(image != null)
			attackImage = image;
		
		imgHeight = height;
		imgWidth = width;
	}
	public void setEndStateImage(Image image, int height, int width){
		if(image != null)
			endStateImage = image;
		
		endStateImgHeight = height;
		endStateImgWidth = width;
		
		isProjectile = false;
	}
	
	public int getImgHeight(){
		return imgHeight;
	}
	public int getImgWidth(){
		return imgWidth;
	}
	public int getEndStateImgHeight(){
		return endStateImgHeight;
	}
	public int getEndStateImgWidth(){
		return endStateImgWidth;
	}
	public Image getEndStateImage(){
		return endStateImage;
	}
	public String getName(){
		return name;
	}
	public int getCoolDown(){
		return cooldown/1000;
	}
	public int getRange(){
		return range;
	}
	public int getAOE(){
		return areaOfEffect;
	}
	public int getCost(){
		return cost;
	}
	public int getDamage(){
		return damage;
	}
	
	public Image getAttImage(){
		return attackImage;
	}
	public float getAttX(){
		return attImgX;
	}
	public float getAttY(){
		return attImgY;
	}
	
	
	
	public void addAttX(float x){
		attImgX += x;
	}
	public void addAttY(float y){
		attImgY += y;
	}
	public void setAttX(float x){
		attImgX = x;
	}
	public void setAttY(float y){
		attImgY = y;
	}
	
	public void resetShot(Player player){
		attImgX = player.getX();
		attImgY = player.getY();
	}
	
	public void collidedShot(){
		attImgX = -1000;
		attImgY = -1000;
	}
	
	
	public float getMouseXPosAtt(){
		return mouseXPosAtt;
	}
	public float getMouseYPosAtt(){
		return mouseYPosAtt;
	}
	public double getAttSpeed(){
		return attSpeed;
	}
	public int getAttCounter(){
		return attCounter;
	}
	public void incAttCounter(){
		attCounter++;
	}
	public void resetAttCounter(){
		attCounter = 0;
	}
	public float getXDirAtt(){
		return xDirAtt;
	}
	public float getYDirAtt(){
		return yDirAtt;
	}
	public float getGenDirAtt(){
		return genDirAtt;
	}
	public void setXDirAtt(float dir){
		xDirAtt = dir;
	}
	public void setYDirAtt(float dir){
		yDirAtt = dir;
	}
	public void setGenDirAtt(float dir){
		genDirAtt = dir;
	}
	
	public float getAttackRange(){
		return attackRange;
	}
	
	
	public boolean isAttacking(){
		return isAttacking;
	}
	
	public void setAttackingState(boolean state){
		isAttacking = state;
	}
	
	public void setCollidingState(boolean state){
		isColliding = state;
	}
	public boolean isColliding(){
		return isColliding;
	}
	
	public void activateSkill(){
    	CDstartTime = System.currentTimeMillis();
    	CDelapsedTime = 0;

    }
    
    public long checkCooldown(){
    	CDelapsedTime = System.currentTimeMillis() - CDstartTime;
    	if(CDelapsedTime >= cooldown){
    		CDelapsedTime = 0;
    	}
    	return (cooldown - CDelapsedTime)/1000;
    }
	
	public boolean isProjectile(){
		return isProjectile;
	}
	public void activateEndState(){
		endStateStartTime = System.currentTimeMillis();
		endStateElapsedTime = 0;
		nonProjectileEndState = true;
	}
	public long checkEndStateTimer(){
		endStateElapsedTime = System.currentTimeMillis() - endStateStartTime;
		if(endStateElapsedTime >= endStateDuration){
			endStateElapsedTime = 0;
		}
		return (endStateDuration - endStateElapsedTime);
	}
	public int getEndStateDuration(){
		return endStateDuration;
	}
	public void setEndState(boolean state){
		nonProjectileEndState = state;
	}
	public boolean isEndState(){
		return nonProjectileEndState;
	}
}
