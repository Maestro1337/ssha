package Model.StatusEffects;

import Model.Player;
import Model.Skill;
import Model.StatusEffect;
import Model.StatusEffectShell;

public class StatusEffectBurning extends StatusEffectShell{

	public StatusEffectBurning(Skill skill, int damage, int seconds) {
		
		super(null, skill, "Burning", damage, 0, 0, 0, 0, 0, 0,  0, false, false, false, seconds+1, 1);
	}
	
	@Override
	public StatusEffect createStatusEffectTo(Player newPlayer, Player fromPlayer) {
		//Finding the next free space in list to add player to
		super.addPlayerGivenTo(newPlayer.getName());
		
		StatusEffect newSE;

		newSE = new StatusEffect(newPlayer, super.getSkill(), super.getName(), super.getDmgEff(), 
				super.getMoveXEff(), super.getMoveYEff(), super.getMoveSpeedEff(), 
				super.getArmEff(), super.getAttackSpeedEff(), super.getRangeEff(), super.getEvasionEff(), 
				super.hasStealth(), super.hasStun(), super.getChannel(), super.getMaxCounts(), super.getDelay());

		return newSE;
	}

}
