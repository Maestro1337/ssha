package Model.StatusEffects;

import Model.Player;
import Model.StatusEffect;
import Model.StatusEffectShell;
import Model.Items.Item;
import Model.Skills.Skill;

public class StatusEffectArmor extends StatusEffectShell{

	public StatusEffectArmor(Skill skill, double armor, int seconds) {
		
		super(null, skill, "Armor", 50, 0, 0, 0, armor, 0, 0, 0, false, false, false, seconds+1, 0);
	}
	public StatusEffectArmor(Item item, double armor, int seconds) {
		//Player player, Item item, String name,int damage, double moveSpeed, double arm, int attackSpeed, int counts, int delay
		super(null, item, "Armor", 0, 0, armor, 0 , seconds+1, 0);
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