package Model.StatusEffects;

import Model.Player;
import Model.StatusEffect;
import Model.StatusEffectShell;
import Model.Skills.Skill;

public class StatusEffectBarrelRoll extends StatusEffectShell{

	public StatusEffectBarrelRoll(Skill skill) {
		
		//player, skill, name, damage, moveX, moveY, speed, arm, attackSpeed, range, evasion, isStun, isChannel, counts, delay
		super(null, skill, "Barrel Roll", 0, 1, 1, 0, 0, 0, 0, 0, false, false, 1, 0);
	}

	@Override
	public StatusEffect createStatusEffectTo(Player newPlayer) {
		//Finding the next free space in list to add player to
		super.addPlayerGivenTo(newPlayer.getName());
		
		if(super.getName() == "Unstable Magic"){
	//		moveSpeedEff = getRandomSpeed();
		}
		
		StatusEffect newSE;
		//checks if it is supposed to move the player
		if(super.getMoveXEff() != 0 || super.getMoveYEff() != 0){
			newSE = new StatusEffect(newPlayer, super.getSkill(), super.getName(), super.getDmgEff(), 
					super.getSkill().getMouseXPos(), super.getSkill().getMouseYPos(), super.getMoveSpeedEff(), 
					super.getArmEff(), super.getAttackSpeedEff(), super.getRangeEff(), super.getEvasionEff(), 
					super.hasStun(), super.getChannel(), super.getMaxCounts(), super.getDelay());
		}else{
			newSE = new StatusEffect(newPlayer, super.getSkill(), super.getName(), super.getDmgEff(), 
					super.getMoveXEff(), super.getMoveYEff(), super.getMoveSpeedEff(), 
					super.getArmEff(), super.getAttackSpeedEff(), super.getRangeEff(), super.getEvasionEff(), 
					super.hasStun(), super.getChannel(), super.getMaxCounts(), super.getDelay());
		}
		if(super.getChangeModel()){
			newSE.setChangeModel();
		}
		return newSE;
	}

}
