package Model;

import Model.Obstacles.Obstacle;
import Model.Timers.EndStateAnimationTimer;
import Model.Timers.RepeatingAnimationTimer;
import Model.Timers.SkillCheckingTimer;

import java.util.ArrayList;

import org.newdawn.slick.Image;

public abstract class Skill{

	private String name;
	private String smallName;
	private int cooldown;
	private int range;
	private int areaOfEffect;
	private int cost;
	private int damage;
	private int lvlOfSkill;
	private int pushDistance = 100;
	
	private String describe;
	private boolean affectSelf = false;
	private boolean affectOthers = false;
	
	private boolean repeatingOSE = false;
	private double armorFromTarget = 1;
	private StatusEffectShell offensiveSE = null;
	private StatusEffectShell selfAffectingSE = null;
	private StatusEffectShell selfAffectingOnHitSE = null;
	
	private Image attackImage;
	private float attImgX;
	private float attImgY;
	private int imgWidth;
	private int imgHeight;
	private float rotation;
	
	private Image endStateImage;
	private int endStateImgWidth;
	private int endStateImgHeight;
	
	private int currentWidth;
	private int currentHeight;
	
	//Attack variables
	private float mouseXPos;
	private float mouseYPos;
	private double attSpeed;
	
	private int attCounter=0;
	private float xDirAtt;
	private float yDirAtt;
	private float genDirAtt;
	
	private boolean isChosen = false;
	private Image[] skillBarImages;

	private float attackRange;
	private boolean isAttacking = false;
	
	private boolean hasEndState = false;
	private long endStateStartTime = 0;
	private long endStateElapsedTime = 0;
	private int endStateDuration;
	private boolean isEndState = false;
	private int ESColInterval;
	
	SkillCheckingTimer ESIT;
	ArrayList<SkillCheckingTimer> SCTArray;
	
	EndStateAnimationTimer animation;
	RepeatingAnimationTimer projectileAnimation;
	
	private boolean isProjectile = true;

	private long CDstartTime = 0;
	private long CDelapsedTime = 0;
	
	private boolean isPiercing = false;
	private boolean[] targetHit = new boolean[MainHub.nbrOfPlayers];
	
	private boolean isGuided = false;
	private Player guidedTarget;
	
	private boolean isGrapplingHook;
	
	private boolean isPassive = false;
	private boolean affectSelfOnHit = false;
	
	private boolean canToggle = false;
	private boolean isToggledOn = false;
	
	public Skill(String name, int cd, int range, double speed, int aoe, int cost, int damage, String describe){
		this.name = name;
		this.smallName = name.toLowerCase().replaceAll("\\s", "");
		cooldown = cd;
		this.range = range;
		areaOfEffect = aoe;
		this.cost = cost;
		this.damage = damage;
		lvlOfSkill = 1;
		
		attackRange = range;
		if(speed < 100){
			attSpeed = speed;
			pushDistance *= speed;
		}else{
			isProjectile = false;
		}
		resetTargetsHit();
		this.describe = describe;
		
		SCTArray = new ArrayList<SkillCheckingTimer>();
	}
	
	// Getters
	public float getRotation(){
		return rotation;
	}
	public int getCurrentHeight(){
		return currentHeight;
	}
	public int getCurrentWidth(){
		return currentWidth;
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
	public String getSmallName() {
		return smallName;
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
	public int getPushDistance(){
		return pushDistance;
	}
	public int getCost(){
		return cost;
	}
	public int getDamage(){
		return damage;
	}
	public double getArmorFromTarget(){
		return armorFromTarget;
	}
	public int getCurrentLvl(){
		return lvlOfSkill;
	}
	public String getDescription(){
		return describe;
	}
	public boolean getAffectSelf(){
		return affectSelf;
	}
	public StatusEffectShell getSelfAffectingStatusEffect(){
		return selfAffectingSE;
	}
	public StatusEffectShell getSelfAffectingOnHitStatusEffect(){
		return selfAffectingOnHitSE;
	}
	public boolean getAffectOthers(){
		return affectOthers;
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
	public float getMouseXPos(){
		return mouseXPos;
	}
	public float getMouseYPos(){
		return mouseYPos;
	}
	public double getAttSpeed(){
		return attSpeed;
	}
	public int getAttCounter(){
		return attCounter;
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
	//returns skillbarpicture depending on if it is the active skill or not, or in use
	public Image getSkillBarImage(){ 
		if(checkCooldown() == getCoolDown()){
			if(!isChosen){
				return skillBarImages[0];
			}else{
				return skillBarImages[1];
			}
		}else{
			return skillBarImages[2];
		}
	}
	public Image getRegularSkillBarImage(){
		return skillBarImages[0];
	}
	public Image getDisabledSkillBarImage(){
		return skillBarImages[2];
	}
	public float getAttackRange(){
		return attackRange;
	}	
	public boolean getAttackingState(){
		return isAttacking;
	}	
	//returns true if player has this skill as current active skill
	public boolean getChosenState(){
		return isChosen;
	}	
	public int getEndStateDuration(){
		return endStateDuration;
	}
	public boolean getEndState(){
		return isEndState;
	}
	public ArrayList<SkillCheckingTimer> getSCTArray(){
		return SCTArray;
	}
	public int getESColInterval(){
		return ESColInterval;
	}
	public RepeatingAnimationTimer getProjectileAnimationTimer(){
		return projectileAnimation;
	}
	public EndStateAnimationTimer getAnimationTimer(){
		return animation;
	}
	public Player getGuidedTarget(){
		return guidedTarget;
	}
	public boolean getAffectSelfOnHit(){
		return affectSelfOnHit;
	}
	//Methods for StatusEffect Control
	public StatusEffectShell getOffensiveStatusEffect(){
		return offensiveSE;
	}
	public boolean getPiercing(){
		return isPiercing;
	}
	public boolean getPassive(){
		return isPassive;
	}
	public boolean getGuided(){
		return isGuided;
	}
	public boolean getGrapplingHook(){
		return isGrapplingHook;
	}
	public boolean getHasEndState(){
		return hasEndState;
	}
	public boolean getTargetsHit(int index){
		return targetHit[index];
	}
	public boolean getToggleState(){
		return canToggle;
	}
	public boolean getToggleOn(){
		return isToggledOn;
	}
	
	// Setters
	public void setRotation(float angle){
		attackImage.setRotation(angle);
		rotation = angle;
	}
	public void setEndStateImage(Image image){
		if(image != null)
			endStateImage = image;
		
		endStateImgHeight = image.getHeight();
		endStateImgWidth = image.getWidth();
	}
	public void setCooldown(int cooldown){
		this.cooldown = cooldown;
	}
	public void resetCooldown(){
		CDstartTime = System.currentTimeMillis() - cooldown;
	}
	public void addAttackRange(int range){
		attackRange += range;
	}
	public void setDamage(int damage){
		this.damage = damage;
	}
	public void setCurrentLvl(int lvl) {
		lvlOfSkill = lvl;
		switch(lvl) {
		case 1:
			break;
		case 2:
			upgradeSkill();
			break;
		case 3:
			upgradeSkill();
			upgradeSkill();
			break;
		case 4:
			upgradeSkill();
			upgradeSkill();
			upgradeSkill();
			break;
		}
	}
	public void incCurrentLvl(){
		lvlOfSkill++;
	}
	public void setSelfAffectingStatusEffectShell(StatusEffectShell SE){
		affectSelf = true;
		selfAffectingSE = SE;
	}
	public void setSelfAffectingOnHitStatusEffectShell(StatusEffectShell SE){
		affectSelfOnHit = true;
		selfAffectingOnHitSE = SE;
	}
	public void setOffensiveStatusEffectShell(StatusEffectShell SE, boolean repeatingOSE){
		affectOthers = true;
		this.repeatingOSE = repeatingOSE;
		offensiveSE = SE;
	}
	public void resetOffensiveStatusGivenTo(){
		if(affectOthers && repeatingOSE){
			offensiveSE.resetCloning();
		}
	}
	public void resetTargetsHit(){
		for(int i=0; i<targetHit.length; i++){
			targetHit[i] = false;
		}
	}
	//Set new image for projectile
	public void setImage(Image image){
		if(image != null){
			attackImage = image;
		
			currentHeight = imgHeight = image.getHeight();
			currentWidth = imgWidth = image.getWidth();
		}
	}
	//Set new animation for projectile
	public void setImage(Image[] images, int duration){
		if(images[0] != null){
			attackImage = images[0];
		
			currentHeight = imgHeight = images[0].getHeight();
			currentWidth = imgWidth = images[0].getWidth();
			
			projectileAnimation = new RepeatingAnimationTimer(duration, images);
		}
	}
	//Setting end state by getting the images, the duration and interval on when damage can be dealt again to players during endstate
	public void setEndState(Image[] images, int duration, int interval){
		if(images[0] != null){
			endStateImage = images[0];
		
			endStateImgHeight = images[0].getHeight();
			endStateImgWidth = images[0].getWidth();
			
			hasEndState = true;
			endStateDuration = duration;
			ESColInterval = interval;
			
			animation = new EndStateAnimationTimer(duration, images, this);
		}
	}
	public void setAttX(float x){
		attImgX = x;
	}
	public void setAttY(float y){
		attImgY = y;
	}
	public void addAttX(float x){
		attImgX += x;
	}
	public void addAttY(float y){
		attImgY += y;
	}
	public void setMouseXPos(float x){
		mouseXPos = x;
	}
	public void setMouseYPos(float y){
		mouseYPos = y;
	}
	public void incAttCounter(){
		attCounter++;
	}
	public void resetAttCounter(){
		attCounter = 0;
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
	public void setSkillBarImages(Image[] images){
		skillBarImages = images;
	}
	public void setAttackingState(boolean state){
		isAttacking = state;
		if(state == false){
			collidedShot();
			if(offensiveSE != null){
				offensiveSE.resetCloning();
			}
		}
	}
	public void setChosenState(boolean state){
		isChosen = state;
	}
	public void setEndstate(boolean state) {
		this.isEndState = state;
		if(state) {
			animation.resetCounterAndTimer();
		}
	}
	public SkillCheckingTimer addNewSkillCheckingTimer(Player player){
		SkillCheckingTimer timer = new SkillCheckingTimer(getESColInterval(), player.getName(), this);
		SCTArray.add(timer);
		return timer;
	}
	public SkillCheckingTimer addNewSkillCheckingTimer(Obstacle obstacle){
		SkillCheckingTimer timer = new SkillCheckingTimer(getESColInterval(), obstacle, this);
		SCTArray.add(timer);
		return timer;
	}
	public void removeSkillCheckingTimer(SkillCheckingTimer timer){
		SCTArray.remove(timer);
	}
	public void setGuidedTarget(Player player){
		guidedTarget = player;
	}
	public void setPiercing(boolean isPiercing){
		this.isPiercing = isPiercing;
	}
	public void addTargetHit(int index){
		targetHit[index] = true;
	}
	public void setPassive(){
		isPassive = true;
	}
	public void setGuided(){
		isGuided = true;
	}
	public void setToggled(){
		canToggle = true;
	}
	public void toggleToggle(){
		isToggledOn = !isToggledOn;
	}
	
	public void setGrapplingHook(){
		isGrapplingHook = true;
	}
	
	// Misc methods
	public void resetShot(Player player){
		attImgX = player.getX()+player.getImage().getWidth()/2-currentWidth/2;
		attImgY = player.getY()+player.getImage().getHeight()/2-currentHeight/2;
	}
	public void setNonProjectileShot(){
		animation.resetCounterAndTimer();
		addAttX((float)(getXDirAtt()*getGenDirAtt())/*-endStateImgWidth/2*/);
		addAttY((float)(getYDirAtt()*getGenDirAtt())/*-endStateImgHeight/2*/);
		isEndState = true;
	}
	public void collidedShot(){
		attImgX = -1000;
		attImgY = -1000;
	}
	public void activateSkill(){
    	CDstartTime = System.currentTimeMillis();
    	CDelapsedTime = 0;
    }
    public long checkCooldown(){
    	//TODO make own class for this and remake to make it easier to understand
    	CDelapsedTime = System.currentTimeMillis() - CDstartTime;
    	if(CDelapsedTime >= cooldown){
    		CDelapsedTime = 0;
    	}
    	return (cooldown - CDelapsedTime)/1000;
    }
	public void activateEndState(){
		endStateStartTime = System.currentTimeMillis();
		endStateElapsedTime = 0;
		
		addAttX(currentWidth/2);
		addAttY(currentHeight/2);
		currentHeight = endStateImgHeight;
		currentWidth = endStateImgWidth;
		addAttX(-currentWidth/2);
		addAttY(-currentHeight/2);
		isEndState = true;
    	animation.resetCounterAndTimer();
	}
	public void activateCollisionEndState(){
		//Setting direction to 0 so it will count as reaching it's goal to begin End State
		setXDirAtt(0);
		setYDirAtt(0);
		
		setGenDirAtt(0);
	}
	public long checkEndStateTimer(){
		endStateElapsedTime = System.currentTimeMillis() - endStateStartTime;
		if(endStateElapsedTime >= endStateDuration){
			endStateElapsedTime = 0;
		}
		return (endStateDuration - endStateElapsedTime);
	}
	public void finishEndState(){
		currentHeight = imgHeight;
		currentWidth = imgWidth;
		
		isEndState = false;
		
		if(offensiveSE != null)
			offensiveSE.resetCloning();
	}
	public boolean isProjectile(){
		return isProjectile;
	}
	public abstract void upgradeSkill();
}
