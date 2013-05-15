package Model.StatusEffects;

import Model.Player;
import Model.StatusEffect;
import Model.StatusEffectShell;
import Model.Skills.Skill;

public class StatusEffectTeleport extends StatusEffectShell{

	public StatusEffectTeleport(Skill skill) {
		
		//player, skill, name, damage, moveX, moveY, arm, attackSpeed, range, counts, delay
		super(null, skill, "Teleport", 0, 1, 1, 0, 0, 0, 0, 0, false, 1, 100);
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
				super.hasStun(), super.getMaxCounts(), super.getDelay());
		
		return newSE;
	}

}