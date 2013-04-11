package Model.Skills;

import Model.StatusEffect;

import java.util.Timer;
import java.util.TimerTask;

public class Skill extends TimerTask{

	private String name;
	private int cooldown;
	private int range;
	private int areaOfEffect;
	private int cost;
	private int damage;
	private StatusEffect spellEffect = new StatusEffect();
	
	public Skill(String name, int cd, int range, int aoe, int cost, int damage, StatusEffect SE){
		this.name = name;
		cooldown = cd;
		this.range = range;
		areaOfEffect = aoe;
		this.cost = cost;
		this.damage = damage;
		spellEffect = SE;
	}
	
	private int times = 0;
	 
	 
    public void run() {
        times++;
        if (times <= 5) {
            System.out.println("I'm alive...");
        } else {
            System.out.println("Timer stops now...");
 
            //Stop Timer.
            this.cancel();
        }
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
