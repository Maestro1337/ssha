package Model;

import Control.Timers.*;
import Model.Skills.Skill;

public class StatusEffect {
	
	
	private Player player;
	private Skill skill;
	private int dmgEff;
	private int moveXEff;
	private int moveYEff;
	private int armEff;
	private int atkSpeedEff;
	private int rangeEff;
	private int counts;
	private int maxCounts;
	private int interval;
	
	
	private boolean commitedChange = false;
	
	private SkillCheckingTimer ESIT;
	
	public StatusEffect(Player player, Skill skill, int damage, int moveX, int moveY, int arm, int attackSpeed, int range, int counts, int interval){
		this.player = player;
		this.skill = skill;
		
		dmgEff = damage;
		moveXEff = moveX;
		moveYEff = moveY;
		armEff = arm;
		atkSpeedEff = attackSpeed;
		rangeEff = range;
		this.counts = maxCounts = counts;
		this.interval = interval;
		
		ESIT = new SkillCheckingTimer(interval, player, skill);
		
	}
	
	public Player getPlayer(){
		return player;
	}
	public Skill getSkill(){
		return skill;
	}
	public int getDmgEff(){
		return dmgEff;
	}
	public int getMoveXEff(){
		return moveXEff;
	}
	public int getMoveYEff(){
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
	public void resetStatusEffect(){
		counts = maxCounts;
		ESIT.resetESColTimer();
	}
	
	//Checks if statusEffect is still in use and does the effect it should.
	public boolean checkStatusEffect(){
		if(counts > 0){
			if(ESIT.checkESColTimer() == ESIT.getESColInterval()){
				counts--;
				ESIT.resetESColTimer();
			}
			commitStatusEffect();
			return true;
		}else{
			returnStatsToNormal();
			return false;
		}
	}
	
	private void commitStatusEffect(){
		
		if(dmgEff>0){
			player.dealDamage(dmgEff);
		}
		if(moveXEff != 0 || moveYEff != 0){
			player.addX(moveXEff);
			player.addY(moveYEff);
		}
		if(armEff>0 && !commitedChange){
			player.addArmor(armEff);
		}
		if(atkSpeedEff>0 && !commitedChange){
			
		}
		if(rangeEff>0 && !commitedChange){
			skill.addAttackRange(rangeEff);
		}
		
		commitedChange = true;
	}
	
	private void returnStatsToNormal(){
		if(commitedChange){
			if(armEff>0){
				player.addArmor(-armEff);
			}
			if(atkSpeedEff>0){
				
			}
			if(rangeEff>0){
				skill.addAttackRange(-rangeEff);
			}
		}
	}
	
	public StatusEffect cloneTo(Player newPlayer){
		StatusEffect newSE = new StatusEffect(newPlayer, skill, dmgEff, moveXEff, moveYEff, armEff, atkSpeedEff, rangeEff, maxCounts, interval);
		return newSE;
	}
	
	
	
}
