package Model.StatusEffects;

import Model.Player;
import Model.StatusEffect;
import Model.StatusEffectShell;
import Model.Skills.Skill;

public class StatusEffectStun extends StatusEffectShell{

	public StatusEffectStun(Skill skill, int seconds) {
		//plus one to add a count after the first hit which is instant
		super(null, skill, "Stun", 0, 0, 0, -1, 0, 0, 0, 0, false, true, false, seconds+1, 0);
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
				super.hasStealth(), super.hasStun(), super.getChannel(), super.getMaxCounts(), super.getDelay());
		return newSE;
	}

}
