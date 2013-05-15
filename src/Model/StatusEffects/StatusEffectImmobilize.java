package Model.StatusEffects;

import Model.Player;
import Model.StatusEffect;
import Model.Skills.Skill;

public class StatusEffectImmobilize extends StatusEffect{

	public StatusEffectImmobilize(Skill skill, int seconds) {
		//player,skill,name,damage,moveX,moveY,moveSpeed,arm,attackSpeed,range,isStun,isChanneling,counts,delay
		//plus one to add a count after the first hit which is instant
		super(null, skill, "Immobilize", 0, 0, 0, -1, 0, 0, 0, 0, false, false, seconds+1, 0);
		// TODO Auto-generated constructor stub
	}

}
