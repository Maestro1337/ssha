package Model.StatusEffects;

import Model.Player;
import Model.Skill;
import Model.StatusEffect;
import Model.StatusEffectShell;

public class StatusEffectPreLeap extends StatusEffectShell{

	public StatusEffectPreLeap(Skill skill) {
		//player, skill, name, damage, moveX, moveY, arm, attackSpeed, range, isStun, isChanneling, counts, delay
		super(null, skill, "Pre Leap", 0, 1000, -1000, 0, 0, 0, 0, 0, true, false, false, 1, 0);
	}
	
	@Override
	public StatusEffect createStatusEffectTo(Player newPlayer) {
		//Finding the next free space in list to add player to
		super.addPlayerGivenTo(newPlayer.getName());
		
		StatusEffect newSE;
		//checks if it is supposed to move the player
		newSE = new StatusEffect(newPlayer, super.getSkill(), super.getName(), super.getDmgEff(), 
				super.getMoveXEff(), super.getMoveYEff(), super.getMoveSpeedEff(), 
				super.getArmEff(), super.getAttackSpeedEff(), super.getRangeEff(), super.getEvasionEff(), 
				super.hasStealth(), super.hasStun(), super.getChannel(), super.getMaxCounts(), super.getDelay());
		
		return newSE;
	}

}
