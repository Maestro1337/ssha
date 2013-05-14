package Model.StatusEffects;

import Model.Player;
import Model.StatusEffect;
import Model.Skills.Skill;

public class StatusEffectDodge extends StatusEffect{

	public StatusEffectDodge(Skill skill, int evasion, int seconds) {
		
		super(null, skill, "Dodge", 50, 0, 0, 0, 0, 0, 0, evasion, false, seconds+1, 0);
	}

}
