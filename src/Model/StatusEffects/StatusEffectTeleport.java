package Model.StatusEffects;

import Model.Player;
import Model.StatusEffect;
import Model.Skills.Skill;

public class StatusEffectTeleport extends StatusEffect{

	public StatusEffectTeleport(Player player, Skill skill, int x, int y) {
		
		//player, skill, name, damage, moveX, moveY, arm, attackSpeed, range, counts, delay
		super(player, skill, "Teleport", 0, 1, 1, 0, 0, 0, 1, 100);
	}

}