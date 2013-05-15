package Model.StatusEffects;

import Model.StatusEffect;
import Model.Skills.Skill;

public class StatusEffectFirstAid extends StatusEffect{
	
	public StatusEffectFirstAid(Skill skill, int seconds) {
		//player,skill,name,damage,moveX,moveY,moveSpeed,arm,attackSpeed,range,isStun,isChanneling,counts,delay
		//plus one to add a count after the first hit which is instant
		super(null, skill, "FirstAid", 10, 0, 0, 0, 0, 0, 0, 0, false, true, seconds+1, 0);
	}
}
