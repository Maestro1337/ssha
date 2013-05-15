package Model;

import java.util.Random;

import Model.Skills.Skill;
import Model.Timers.*;

public abstract class StatusEffectShell {
	
	
	private Player player;
	private Skill skill;
	private String name;
	
	private int dmgEff;
	private float moveXEff;
	private float moveYEff;
	private double armEff;
	private int atkSpeedEff;
	private int rangeEff;
	private double moveSpeedEff;
	private int evasionEff;
	private int counts;
	private int maxCounts;
	private int interval;
	private boolean hasStun;
	private boolean isChanneling;
	
	private boolean commitedChange = false;
	private String[] playersGivenTo;
	
	private StatusEffectTimer ESIT;
	private int delay;
	
	private boolean changeModel = false;
	
	public StatusEffectShell(Player player, Skill skill, String name,int damage, float moveX, float moveY, double moveSpeed, double arm, int attackSpeed, int range, int evasion, boolean isStun, boolean isChanneling, int counts, int delay){
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
		this.isChanneling = isChanneling;
		evasionEff = evasion;
		
		playersGivenTo = new String[3];
		ESIT = new StatusEffectTimer(delay);
		
	}
	public boolean getChannel(){
		return isChanneling;
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
	public double getArmEff(){
		return armEff;
	}
	public int getAttackSpeedEff(){
		return atkSpeedEff;
	}
	public int getRangeEff(){
		return rangeEff;
	}
	public int getEvasionEff(){
		return evasionEff;
	}
	public double getMoveSpeedEff(){
		return moveSpeedEff;
	}
	public boolean hasStun(){
		return hasStun;
	}
	public void setChangeModel(){
		changeModel = true;
	}
	public boolean getChangeModel(){
		return changeModel;
	}
	public int getMaxCounts(){
		return maxCounts;
	}
	protected int getDelay(){
		return delay;
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
		if(evasionEff!=0 && !commitedChange){
			player.addEvasion(evasionEff);
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
			if(moveXEff != 0 || moveYEff != 0){
				player.setPushState(false);
			}
			if(evasionEff!=0){
				player.addEvasion(-evasionEff);
			}
			player.setStunState(false);
			resetCloning();
		}
	}
	
	public abstract StatusEffect createStatusEffectTo(Player newPlayer);
	
	protected void addPlayerGivenTo(String name){
		for(int i=0; i<playersGivenTo.length; i++){
			if(playersGivenTo[i] == null){
				playersGivenTo[i] = name;
				break;
			}
		}
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
