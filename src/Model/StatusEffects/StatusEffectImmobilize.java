package Model.StatusEffects;

import Model.Player;
import Model.Skill;
import Model.StatusEffect;
import Model.StatusEffectShell;

public class StatusEffectImmobilize extends StatusEffectShell{

	public StatusEffectImmobilize(Skill skill, int seconds) {
		//player,skill,name,damage,moveX,moveY,moveSpeed,arm,attackSpeed,range,isStun,isChanneling,counts,delay
		//plus one to add a count after the first hit which is instant
		super(null, skill, "Immobilize", 0, 0, 0, -1, 0, 0, 0, 0, false, false, false, seconds+1, 0);
		// TODO Auto-generated constructor stub
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
