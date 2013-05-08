package Model.StatusEffects;

import Model.Player;
import Model.StatusEffect;
import Model.Skills.Skill;

public class StatusEffectBurning extends StatusEffect{

	public StatusEffectBurning(Player player, Skill skill) {
		
		super(player, skill, "Burning", 50, 0, 0, 0, 0, 0, 0, false, 5, 0);
	}

}
