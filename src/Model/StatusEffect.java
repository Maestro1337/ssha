package Model;

import Control.Timers.*;
import Model.Skills.Skill;

public class StatusEffect {
	
	
	private Player player;
	private Skill skill;
	private int dmgEff;
	private int moveEff;
	private int armEff;
	private int atkSpeedEff;
	private int rangeEff;
	private int time;
	
	private SkillCheckingTimer ESIT;
	
	public StatusEffect(Player player, Skill skill, int damage, int move, int arm, int attackSpeed, int range, int time){
		this.player = player;
		this.skill = skill;
		
		dmgEff = damage;
		moveEff = move;
		armEff = arm;
		atkSpeedEff = attackSpeed;
		rangeEff = range;
		this.time = time;
		
		ESIT = new SkillCheckingTimer(time, player, skill);
		
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
	public int getMoveEff(){
		return moveEff;
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
	
	public boolean checkStatusEffect(){
		ESIT.checkESColTimer();
		return false;
	}
	
	
	
}
