package Model.StatusEffects;

import java.util.Random;

import Model.Player;
import Model.Skill;
import Model.StatusEffect;
import Model.StatusEffectShell;

public class StatusEffectLifesteal extends StatusEffectShell{

	public StatusEffectLifesteal(Skill skill) {
		
		//player, skill, name, damage, moveX, moveY, speed, arm, attackSpeed, range, isStun, isChanneling, counts, delay
		
		super(null, skill, "Lifestealing Arrow", 0, 0, 0, 1, 0, 0, 0, 0,false, false, false, 1, 0);

		super.setChangeModel();
	}
	
	@Override
	public StatusEffect createStatusEffectTo(Player newPlayer) {
		//Finding the next free space in list to add player to
		super.addPlayerGivenTo(newPlayer.getName());
		
		StatusEffect newSE;
		newSE = new StatusEffect(newPlayer, super.getSkill(), super.getName(), -super.getSkill().getDamage(), 
				super.getMoveXEff(), super.getMoveYEff(), super.getMoveSpeedEff(),
				super.getArmEff(), super.getAttackSpeedEff(), super.getRangeEff(), super.getEvasionEff(), 
				super.hasStealth(),	super.hasStun(), super.getChannel(), super.getMaxCounts(), super.getDelay());
		newSE.setChangeModel();
		
		return newSE;
	}
}


