package Model.StatusEffects;

import Model.Player;
import Model.StatusEffect;
import Model.StatusEffectShell;
import Model.Skills.Skill;

public class StatusEffectDodge extends StatusEffectShell{

	public StatusEffectDodge(Skill skill, int evasion, int seconds) {
		
		super(null, skill, "Dodge", 50, 0, 0, 0, 0, 0, 0, evasion, false, false, seconds+1, 0);
	}
	
	@Override
	public StatusEffect createStatusEffectTo(Player newPlayer) {
		//Finding the next free space in list to add player to
		super.addPlayerGivenTo(newPlayer.getName());
		
		StatusEffect newSE;
		newSE = new StatusEffect(newPlayer, super.getSkill(), super.getName(), super.getDmgEff(), 
				super.getMoveXEff(), super.getMoveYEff(), super.getMoveSpeedEff(), 
				super.getArmEff(), super.getAttackSpeedEff(), super.getRangeEff(), super.getEvasionEff(), 
				super.hasStun(), super.getChannel(), super.getMaxCounts(), super.getDelay());

		return newSE;
	}

}
