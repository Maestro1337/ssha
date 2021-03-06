package Model.Skills.Warrior;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skill;
import Model.StatusEffects.StatusEffectLeap;
import Model.StatusEffects.StatusEffectPreLeap;
import Model.StatusEffects.StatusEffectStun;

public class SkillLeapAttack extends Skill {
	public SkillLeapAttack(){
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE
				super("Leap attack", 10000, 200, 5, 8, 150, 150,"Leap attack \nJump to a location to \ndeal AoE damage\n" +
						"Level 1: 150 damage\n" +
						"Level 2: 200 damage\n" +
						"Level 3: 250 damage\n" +
						"Level 4: 300 damage");

				Image[] attackImages = new Image[4];
				Image[] animation = new Image[8];
				Image[] skillBar = new Image[3];
				
				super.setSelfAffectingStatusEffectShell(new StatusEffectPreLeap(this));
				super.setSelfAffectingOnHitStatusEffectShell(new StatusEffectLeap(this));
				super.setOffensiveStatusEffectShell(new StatusEffectStun(this, 1),false);
				
				try {
					
					attackImages[0] = new Image("res/animations/leapattack/leapattack1.png");
					attackImages[1] = new Image("res/animations/leapattack/leapattack2.png");
					attackImages[2] = new Image("res/animations/leapattack/leapattack3.png");
					attackImages[3] = new Image("res/animations/leapattack/leapattack4.png");
					
					animation[0] = new Image("res/animations/warstomp/warstompGround4.png");
					animation[1] = new Image("res/animations/warstomp/warstompGround3.png");
					animation[2] = new Image("res/animations/warstomp/warstompGround2.png");
					animation[3] = new Image("res/animations/warstomp/warstompGround.png");
					animation[4] = new Image("res/animations/warstomp/warstompGround.png");
					animation[5] = new Image("res/animations/warstomp/warstompGround.png");
					animation[6] = new Image("res/animations/warstomp/warstompGround.png");
					animation[7] = new Image("res/animations/warstomp/warstompGround.png");
					
					skillBar[0] = new Image("res/skillIcons/leapattack.png");
					skillBar[1] = new Image("res/skillIcons/leapattack_active.png");
					skillBar[2] = new Image("res/skillIcons/leapattack_disabled.png");
				} catch (SlickException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				super.setImage(attackImages, 1000);
				super.setEndState(animation, 300, 2000);
				super.setSkillBarImages(skillBar);
			}

	private int lvl2 = 200;
	private int lvl3 = 250;
	private int lvl4 = 300;
	
	@Override
	public void upgradeSkill() {
		if(super.getCurrentLvl() < 4){
			super.incCurrentLvl();
			
			switch(super.getCurrentLvl()){
			case 2:
				super.setDamage(lvl2);
				break;
			case 3:
				super.setDamage(lvl3);
				break;
			case 4:
				super.setDamage(lvl4);
				break;
			}
		}
	}
}
