package Model.StatusEffects;

import Model.Player;
import Model.Skill;
import Model.StatusEffect;
import Model.StatusEffectShell;

public class StatusEffectTeleport extends StatusEffectShell{

	public StatusEffectTeleport(Skill skill) {
		
		//player, skill, name, damage, moveX, moveY, arm, attackSpeed, range, isStun, isChanneling, counts, delay
		super(null, skill, "Teleport", 0, 1, 1, 0, 0, 0, 0, 0, false, false, false, 1, 100);
	}
	
	@Override
	public StatusEffect createStatusEffectTo(Player newPlayer) {
		//Finding the next free space in list to add player to
		super.addPlayerGivenTo(newPlayer.getName());
		
		StatusEffect newSE;
		//checks if it is supposed to move the player
		newSE = new StatusEffect(newPlayer, super.getSkill(), super.getName(), super.getDmgEff(), 
				super.getSkill().getMouseXPos(), super.getSkill().getMouseYPos(), super.getMoveSpeedEff(), 
				super.getArmEff(), super.getAttackSpeedEff(), super.getRangeEff(), super.getEvasionEff(), 
				super.hasStealth(), super.hasStun(), super.getChannel(), super.getMaxCounts(), super.getDelay());
		
		return newSE;
	}

}
