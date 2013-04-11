package Model.Skills;

import Model.StatusEffect;

public class Skill {

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
