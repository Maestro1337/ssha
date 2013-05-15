package Model.StatusEffects;

import Model.Player;
import Model.StatusEffect;
import Model.StatusEffectShell;
import Model.Skills.Skill;

public class StatusEffectFirstAid extends StatusEffectShell{
	
	public StatusEffectFirstAid(Skill skill, int seconds) {
		//player,skill,name,damage,moveX,moveY,moveSpeed,arm,attackSpeed,range,isStun,isChanneling,counts,delay
		//plus one to add a count after the first hit which is instant
		super(null, skill, "FirstAid", 10, 0, 0, 0, 0, 0, 0, 0, false, true, seconds+1, 0);
	}

	@Override
	public StatusEffect createStatusEffectTo(Player newPlayer) {
		//Finding the next free space in list to add player to
		super.addPlayerGivenTo(newPlayer.getName());
		
		StatusEffect newSE;

		newSE = new StatusEffect(newPlayer, super.getSkill(), super.getName(), super.getDmgEff(), 
				super.getMoveXEff(), super.getMoveYEff(), super.getMoveSpeedEff(), 
				super.getArmEff(), super.getAttackSpeedEff(), super.getRangeEff(), super.getEvasionEff(), 
				super.hasStun(), super.getChannel(), super.getMaxCounts(), super.getDelay());

		return newSE;
	}
}
