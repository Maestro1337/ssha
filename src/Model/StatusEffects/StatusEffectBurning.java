package Model.StatusEffects;

import Model.Player;
import Model.StatusEffect;
import Model.StatusEffectShell;
import Model.Skills.Skill;

public class StatusEffectBurning extends StatusEffectShell{

	public StatusEffectBurning(Skill skill, int seconds) {
		
		super(null, skill, "Burning", 50, 0, 0, 0, 0, 0, 0,  0, false, seconds+1, 0);
	}
	
	@Override
	public StatusEffect createStatusEffectTo(Player newPlayer) {
		//Finding the next free space in list to add player to
		super.addPlayerGivenTo(newPlayer.getName());
		
		StatusEffect newSE;

		newSE = new StatusEffect(newPlayer, super.getSkill(), super.getName(), super.getDmgEff(), 
				super.getMoveXEff(), super.getMoveYEff(), super.getMoveSpeedEff(), 
				super.getArmEff(), super.getAttackSpeedEff(), super.getRangeEff(), super.getEvasionEff(), 
				super.hasStun(), super.getMaxCounts(), super.getDelay());

		return newSE;
	}

}
