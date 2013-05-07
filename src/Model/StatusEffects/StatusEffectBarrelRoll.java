package Model.StatusEffects;

import Model.Player;
import Model.StatusEffect;
import Model.Skills.Skill;

public class StatusEffectBarrelRoll extends StatusEffect{

	public StatusEffectBarrelRoll(Player player, Skill skill, int x, int y) {
		
		//player, skill, name, damage, moveX, moveY, arm, attackSpeed, range, counts, delay
		super(player, skill, "Barrel Roll", 0, 1, 1, 0, 0, 0, 1, 100);
	}

}