package Model.StatusEffects;

import Model.Player;
import Model.StatusEffect;
import Model.Skills.Skill;

public class StatusEffectBarrelRoll extends StatusEffect{

	public StatusEffectBarrelRoll(Player player, Skill skill) {
		
		//player, skill, name, damage, moveX, moveY, speed, arm, attackSpeed, range, isStun, counts, delay
		super(player, skill, "Barrel Roll", 0, 1, 1, 0, 0, 0, 0, false, 1, 50);
	}

}