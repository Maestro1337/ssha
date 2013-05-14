package Model.StatusEffects;

import Model.Player;
import Model.StatusEffect;
import Model.Skills.Skill;

public class StatusEffectUnstableMagic extends StatusEffect{

	public StatusEffectUnstableMagic(Skill skill, int seconds) {
		
		//player, skill, name, damage, moveX, moveY, speed, arm, attackSpeed, range, isStun, counts, delay
		super(null, skill, "Unstable Magic", 0, 0, 0, 0.5, 0, 0, 0, false, seconds+1, 0);
		
		super.setChangeModel();
	}

}