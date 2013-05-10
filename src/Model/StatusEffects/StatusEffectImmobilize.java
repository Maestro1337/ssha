package Model.StatusEffects;

import Model.Player;
import Model.StatusEffect;
import Model.Skills.Skill;

public class StatusEffectImmobilize extends StatusEffect{

	public StatusEffectImmobilize(Player player, Skill skill, int seconds) {
		//player,skill,name,damage,moveX,moveY,moveSpeed,arm,attackSpeed,range,isStun,counts,delay
		//plus one to add a count after the first hit which is instant
		super(player, skill, "Immobilize", 0, 0, 0, -100, 0, 0, 0, false, seconds+1, 0);
		// TODO Auto-generated constructor stub
	}

}
