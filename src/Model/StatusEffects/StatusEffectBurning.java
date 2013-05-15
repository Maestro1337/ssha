package Model.StatusEffects;

import Model.Player;
import Model.StatusEffect;
import Model.Skills.Skill;

public class StatusEffectBurning extends StatusEffect{

	public StatusEffectBurning(Skill skill, int seconds) {
		
		super(null, skill, "Burning", 50, 0, 0, 0, 0, 0, 0,  0, false, false, seconds+1, 0);
	}

}
