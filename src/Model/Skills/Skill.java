package Model.Skills;

import Model.StatusEffect;

import java.sql.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Skill{

	private String name;
	private int cooldown;
	private int range;
	private int areaOfEffect;
	private int cost;
	private int damage;
	private StatusEffect spellEffect = new StatusEffect();
	

	private long startTime = 0;
	private long elapsedTime = 0;
	
	public Skill(String name, int cd, int range, int aoe, int cost, int damage, StatusEffect SE){
		this.name = name;
		cooldown = cd;
		this.range = range;
		areaOfEffect = aoe;
		this.cost = cost;
		this.damage = damage;
		spellEffect = SE;
		
		
	}
	
	 
	
    
    public void activateSkill(){
    	startTime = System.currentTimeMillis();
    	elapsedTime = 0;

    }
    
    public long checkCooldown(){
    	elapsedTime = System.currentTimeMillis() - startTime;
    	if(elapsedTime >= cooldown){
    		elapsedTime = 0;
    	}
    	return elapsedTime;
    }
	
	public String getName(){
		return name;
	}
	public int getCoolDown(){
		return cooldown;
	}
	public int getRange(){
		return range;
	}
	public int getAOE(){
		return areaOfEffect;
	}
	public int cost(){
		return cost;
	}
	public int damage(){
		return damage;
	}

}
