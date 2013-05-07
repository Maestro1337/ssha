package Model.StatusEffects;

import Model.Player;
import Model.StatusEffect;
import Model.Skills.Skill;

public class StatusEffectStun extends StatusEffect{

	public StatusEffectStun(Player player, Skill skill, int seconds) {
		super(player, skill, "Stun", 0, 0, 0, -1000, 0, 0, 0, seconds, 0);
		// TODO Auto-generated constructor stub
	}

}
