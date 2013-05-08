package Model.StatusEffects;

import Model.Player;
import Model.StatusEffect;
import Model.Skills.Skill;

public class StatusEffectStun extends StatusEffect{

	public StatusEffectStun(Player player, Skill skill, int seconds) {
		//plus one to add a count after the first hit which is instant
		super(player, skill, "Stun", 0, 0, 0, 0, 0, 0, 0, true, seconds+1, 0);
		// TODO Auto-generated constructor stub
	}

}
