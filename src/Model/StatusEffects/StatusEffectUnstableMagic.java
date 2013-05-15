package Model.StatusEffects;

import java.util.Random;

import Model.Player;
import Model.StatusEffect;
import Model.Skills.Skill;

public class StatusEffectUnstableMagic extends StatusEffect{

	public StatusEffectUnstableMagic(Skill skill, int seconds) {
		
		//player, skill, name, damage, moveX, moveY, speed, arm, attackSpeed, range, isStun, isChanneling, counts, delay
		
		super(null, skill, "Unstable Magic", 0, 0, 0, 1, 0, 0, 0, 0, false, false, seconds+1, 0);

		super.setChangeModel();
	}
	
	
	
}