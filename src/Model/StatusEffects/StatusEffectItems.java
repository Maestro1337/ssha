package Model.StatusEffects;

import Model.Player;
import Model.StatusEffect;
import Model.StatusEffectShell;
import Model.Items.Item;

public class StatusEffectItems extends StatusEffectShell {

	public StatusEffectItems(Item item, int damage, double movement, double armor, int seconds) {
		//Player player, Item item, String name,int damage, double moveSpeed, double arm,  int counts, int delay
		super(null, item, "Armor", damage, movement, armor, seconds+1, 0);
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
