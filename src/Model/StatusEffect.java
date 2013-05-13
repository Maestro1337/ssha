package Model;

import Model.Skills.Skill;
import Model.Timers.*;

public class StatusEffect {
	
	
	private Player player;
	private Skill skill;
	private String name;
	
	private int dmgEff;
	private float moveXEff;
	private float moveYEff;
	private int armEff;
	private int atkSpeedEff;
	private int rangeEff;
	private double moveSpeedEff;
	private int counts;
	private int maxCounts;
	private int interval;
	private boolean hasStun;
	
	private boolean commitedChange = false;
	private String[] playersGivenTo;
	
	private StatusEffectTimer ESIT;
	private int delay;
	
	public StatusEffect(Player player, Skill skill, String name,int damage, float moveX, float moveY, double moveSpeed, int arm, int attackSpeed, int range, boolean isStun, int counts, int delay){
		this.player = player;
		this.skill = skill;
		this.name = name;
		
		dmgEff = damage;
		moveXEff = moveX;
		moveYEff = moveY;
		armEff = arm;
		atkSpeedEff = attackSpeed;
		rangeEff = range;
		moveSpeedEff = moveSpeed;
		this.counts = maxCounts = counts;
		this.interval = interval;
		this.delay = delay;
		hasStun = isStun;
		
		playersGivenTo = new String[3];
		ESIT = new StatusEffectTimer(delay);
		
	}
	
	public Player getPlayer(){
		return player;
	}
	public Skill getSkill(){
		return skill;
	}
	public String getName(){
		return name;
	}
	public int getDmgEff(){
		return dmgEff;
	}
	public float getMoveXEff(){
		return moveXEff;
	}
	public float getMoveYEff(){
		return moveYEff;
	}
	public int getArmEff(){
		return armEff;
	}
	public int getAttackSpeedEff(){
		return atkSpeedEff;
	}
	public int getRangeEff(){
		return rangeEff;
	}
	public boolean hasStun(){
		return hasStun;
	}
	
	public void resetStatusEffect(){
		counts = maxCounts;
		ESIT.resetTimer();
	}
	
	//Checks if statusEffect is still in use and does the effect it should.
	public boolean checkStatusEffect(){
		if(counts > 0){
			if(ESIT.checkTimer() == ESIT.getInterval()){
				counts--;
				ESIT.resetTimer();
				commitStatusEffect();
			}
			
			return true;
		}else{
			returnStatsToNormal();
			return false;
		}
	}
	public void setResetOfStatusEffect(){
		returnStatsToNormal();
	}
	
	private void commitStatusEffect(){
		if(dmgEff>0){
			player.dealDamage(dmgEff);
		}
		if(moveXEff != 0 || moveYEff != 0){
			player.setX(moveXEff);
			player.setY(moveYEff);
			player.setRunningState(false);
		}
		if(armEff!=0 && !commitedChange){
			player.addArmor(armEff);
		}
		if(moveSpeedEff!=0 && !commitedChange){
			//Calculating how the moveSpeed will be changed to know how much to change it back after return of statusEffect
			moveSpeedEff = moveSpeedEff*player.getMoveSpeed();
			player.addMovementSpeed(moveSpeedEff);
		}
		if(atkSpeedEff!=0 && !commitedChange){
			
		}
		if(rangeEff!=0 && !commitedChange){
			skill.addAttackRange(rangeEff);
		}
		player.setStunState(hasStun);
		commitedChange = true;
	} 
	
	private void returnStatsToNormal(){
		System.out.println("Return status effect");
		if(commitedChange){
			if(armEff!=0){
				player.addArmor(-armEff);
			}
			if(atkSpeedEff!=0){
				
			}
			if(rangeEff!=0){
				skill.addAttackRange(-rangeEff);
			}
			if(moveSpeedEff!=0){
				player.addMovementSpeed(-moveSpeedEff);
			}
			player.setStunState(false);
			resetCloning();
		}
	}
	
	public StatusEffect cloneTo(Player newPlayer){
		//Finding the next free space in list to add player to
		for(int i=0; i<playersGivenTo.length; i++){
			if(playersGivenTo[i] == null){
				playersGivenTo[i] = newPlayer.getName();
				break;
			}
		}
		StatusEffect newSE;
		//checks if it is supposed to move the player
		if(moveXEff != 0 || moveYEff != 0){
			newSE = new StatusEffect(newPlayer, skill, name, dmgEff, skill.getMouseXPos()+skill.getEndStateImgWidth()/2-newPlayer.getImage().getWidth()/2, skill.getMouseYPos()+skill.getEndStateImgHeight()/2-newPlayer.getImage().getHeight()/2, moveSpeedEff,armEff, atkSpeedEff, rangeEff, hasStun, maxCounts, delay);
		}else{
			newSE = new StatusEffect(newPlayer, skill, name, dmgEff, moveXEff, moveYEff, moveSpeedEff, armEff, atkSpeedEff, rangeEff, hasStun, maxCounts, delay);
		}
		return newSE;
	}
	
	public boolean hasBeenGivenTo(String name){
		for(int i=0; i<playersGivenTo.length; i++){
			if(playersGivenTo[i] == name){
				return true;
			}
		}
		return false;
	}
	
	public void resetCloning(){
		playersGivenTo = new String[3];
	}
	
	
	
}
