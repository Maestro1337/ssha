package Model.StatusEffects;

import Model.Player;
import Model.StatusEffect;
import Model.StatusEffectShell;
import Model.Skills.Skill;

public class StatusEffectImmobilize extends StatusEffectShell{

	public StatusEffectImmobilize(Skill skill, int seconds) {
		//player,skill,name,damage,moveX,moveY,moveSpeed,arm,attackSpeed,range,isStun,counts,delay
		//plus one to add a count after the first hit which is instant
		super(null, skill, "Immobilize", 0, 0, 0, -1, 0, 0, 0, 0, false, seconds+1, 0);
		// TODO Auto-generated constructor stub
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
