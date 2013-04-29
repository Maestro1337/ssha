package Model.StatusEffects;

import Model.Player;
import Model.StatusEffect;
import Model.Skills.Skill;

public class StatusEffectBurning extends StatusEffect{

	public StatusEffectBurning(Player player, Skill skill) {
		
		super(player, skill, 10, 0, 0, 0, 0, 0, 5, 1000);
	}

}
