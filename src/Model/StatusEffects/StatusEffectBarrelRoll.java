package Model.StatusEffects;

import Model.Player;
import Model.StatusEffect;
import Model.StatusEffectShell;
import Model.Skills.Skill;

public class StatusEffectBarrelRoll extends StatusEffectShell{

	public StatusEffectBarrelRoll(Skill skill) {
		
		//player, skill, name, damage, moveX, moveY, speed, arm, attackSpeed, range, evasion, isStun, isChannel, counts, delay
		super(null, skill, "Barrel Roll", 0, 1, 1, 0, 0, 0, 0, 100, false, false, 1, 0);
	}

	@Override
	public StatusEffect createStatusEffectTo(Player newPlayer) {
		//Finding the next free space in list to add player to
		super.addPlayerGivenTo(newPlayer.getName());
		
		StatusEffect newSE;
		
		newSE = new StatusEffect(newPlayer, super.getSkill(), super.getName(), super.getDmgEff(), 
				super.getSkill().getAttX(), super.getSkill().getAttY(), super.getMoveSpeedEff(), 
				super.getArmEff(), super.getAttackSpeedEff(), super.getRangeEff(), super.getEvasionEff(), 
				super.hasStun(), super.getChannel(), super.getMaxCounts(), super.getDelay());
		
		return newSE;
	}

}
