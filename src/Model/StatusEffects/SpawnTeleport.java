package Model.StatusEffects;

import Model.Player;
import Model.StatusEffect;
import Model.StatusEffectShell;

public class SpawnTeleport extends StatusEffectShell{

	public SpawnTeleport() {
		
		//player, skill, name, damage, moveX, moveY, arm, attackSpeed, range, isStun, isChanneling, counts, delay
		super(null, null, "Spawning", 0, 1, 1, 0, 0, 0, 0, 0, false, false, false, 1, 0);
	}
	
	@Override
	public StatusEffect createStatusEffectTo(Player newPlayer) {
		//Finding the next free space in list to add player to
		super.addPlayerGivenTo(newPlayer.getName());
		
		StatusEffect newSE;
		//checks if it is supposed to move the player
		newSE = new StatusEffect(newPlayer, null, super.getName(), super.getDmgEff(), 
				newPlayer.getStartX(), newPlayer.getStartY(), super.getMoveSpeedEff(), 
				super.getArmEff(), super.getAttackSpeedEff(), super.getRangeEff(), super.getEvasionEff(), 
				super.hasStealth(), super.hasStun(), super.getChannel(), super.getMaxCounts(), super.getDelay());
		
		return newSE;
	}

}
