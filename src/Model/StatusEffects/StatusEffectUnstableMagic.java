package Model.StatusEffects;

import java.util.Random;

import Model.Player;
import Model.Skill;
import Model.StatusEffect;
import Model.StatusEffectShell;

public class StatusEffectUnstableMagic extends StatusEffectShell{

	public StatusEffectUnstableMagic(Skill skill, int seconds) {
		
		//player, skill, name, damage, moveX, moveY, speed, arm, attackSpeed, range, isStun, isChanneling, counts, delay
		
		super(null, skill, "Unstable Magic", 0, 0, 0, 1, 0, 0, 0, 0, false, false, false, seconds+1, 0);

		super.setChangeModel();
	}
	
	@Override
	public StatusEffect createStatusEffectTo(Player newPlayer, Player fromPlayer) {
		//Finding the next free space in list to add player to
		super.addPlayerGivenTo(newPlayer.getName());
		
		StatusEffect newSE;
		newSE = new StatusEffect(newPlayer, super.getSkill(), super.getName(), super.getDmgEff(), 
				super.getMoveXEff(), super.getMoveYEff(), getRandomSpeed(), 
				super.getArmEff(), super.getAttackSpeedEff(), super.getRangeEff(), super.getEvasionEff(), 
				super.hasStealth(), super.hasStun(), super.getChannel(), super.getMaxCounts(), super.getDelay());
		newSE.setChangeModel();
		return newSE;
	}
	
	private double getRandomSpeed(){
		Random obsGenerator = new Random();
		double decider = (double)obsGenerator.nextInt(30)/10 - 1;
		
		return decider;
	}
	
}
