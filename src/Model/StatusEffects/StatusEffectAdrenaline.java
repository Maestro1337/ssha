package Model.StatusEffects;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Player;
import Model.Skill;
import Model.StatusEffect;
import Model.StatusEffectShell;

public class StatusEffectAdrenaline extends StatusEffectShell {

	public StatusEffectAdrenaline(Skill skill, int seconds) {
		
		super(null, skill, "Adrenaline", 0, 0, 0, 0, 0, 0, 0, 0, false, false, false, seconds+1, 0);
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
		
		
		
		newSE.setChangeOfMainSkill(getImagesOfCurrentLevel(), getDamageOfCurrentLevel());
		
		return newSE;
	}
	
	private Image[] getImagesOfCurrentLevel(){
		Image[] images = new Image[4];
		try {
			images[0] = new Image("res/animations/slash/adrenaline_slash1.png");
			images[1] = new Image("res/animations/slash/adrenaline_slash2.png");
			images[2] = new Image("res/animations/slash/adrenaline_slash3.png");
			images[3] = new Image("res/animations/slash/adrenaline_slash4.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return images;
	}

	private int getDamageOfCurrentLevel(){
		switch(super.getSkill().getCurrentLvl()){
		case 1:
			return 200;
		case 2:
			return 250;
		case 3:
			return 300;
		case 4:
			return 350;
		}
		
		return 0;
	}
}
