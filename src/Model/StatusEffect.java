package Model;

import java.util.Random;

import Model.Skills.Skill;
import Model.Timers.*;

public class StatusEffect {
	
	
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
	private boolean isChannel = false;
	private boolean hasStealth = false;
	
	private boolean commitedChange = false;
	private String[] playersGivenTo;
	
	private StatusEffectTimer ESIT;
	private int delay;
	
	private boolean changeModel = false;
	
	public StatusEffect(Player player, Skill skill, String name,int damage, float moveX, float moveY, double moveSpeed, double arm, int attackSpeed, int range, int evasion,boolean isStealth, boolean isStun, boolean isChanneling, int counts, int delay){
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
		this.isChannel = isChanneling;
		evasionEff = evasion;
		hasStealth = isStealth;
		
		playersGivenTo = new String[3];
		ESIT = new StatusEffectTimer(delay);
		
	}
	public boolean getChanneling(){
		return isChannel;
	}
	public boolean hasStun(){
		return hasStun;
	}
	public void setChangeModel(){
		changeModel = true;
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
		if(dmgEff<0){
			player.addHP(-dmgEff);
		}
		if(moveXEff != 0 || moveYEff != 0){
			player.setX(moveXEff);
			player.setY(moveYEff);
			player.setRunningState(false);
		}
		if(armEff!=0 && !commitedChange){
			armEff *= player.getArmor();
			player.addArmor(armEff);
		}
		if(moveSpeedEff!=0 && !commitedChange){
			//Calculating how the moveSpeed will be changed to know how much to change it back after return of statusEffect
			moveSpeedEff = moveSpeedEff*player.getMovementSpeed();
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
		if(isChannel){
			//moveSpeedEff = player.getMoveSpeed();
			//player.setMovementSpeed(0);
			player.setRunningState(false);
			player.setChannel(isChannel);
		}
		if(hasStealth){
			player.setStealthState(true);
		}
		if(changeModel){
			player.setChangedModelState(true);
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
			if(isChannel){
			//	player.setMovementSpeed(moveSpeedEff);
				player.setChannel(false);
			}
			if(hasStealth){
				player.setStealthState(false);
			}
			if(changeModel){
				player.setChangedModelState(false);
			}
			player.setStunState(false);
		}
	}
}
