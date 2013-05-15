package Model.Skills.Warrior;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skills.Skill;
import Model.StatusEffects.StatusEffectLeap;
import Model.StatusEffects.StatusEffectPreLeap;
import Model.StatusEffects.StatusEffectStun;
import Model.StatusEffects.StatusEffectTeleport;

public class SkillLeapAttack extends Skill {
	public SkillLeapAttack(){
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE
				super("Leap attack", 10000, 500, 0.4, 8, 50, 150, 300, 300, 300,"Leap attack \n" +
						"Level 1: 15 damage\n" +
						"Level 2: 25 damage\n" +
						"Level 3: 35 damage\n" +
						"Level 4: 45 damage");

				Image[] attackImages = new Image[4];
				Image[] animation = new Image[1];
				Image[] skillBar = new Image[3];
				
		//		super.setSelfAffectingStatusEffectShell(new StatusEffectPreLeap(this));
				super.setSelfAffectingOnHitStatusEffectShell(new StatusEffectLeap(this));
				super.setOffensiveStatusEffectShell(new StatusEffectStun(this, 1));
				
				try {
					
					attackImages[0] = new Image("res/animations/leapattack/leapattack1.png");
					attackImages[1] = new Image("res/animations/leapattack/leapattack2.png");
					attackImages[2] = new Image("res/animations/leapattack/leapattack3.png");
					attackImages[3] = new Image("res/animations/leapattack/leapattack4.png");
					
					animation[0] = new Image("res/animations/warstomp/warstompGround.png");
					
					skillBar[0] = new Image("res/skillIcons/leapattack.png");
					skillBar[1] = new Image("res/skillIcons/leapattack_active.png");
					skillBar[2] = new Image("res/skillIcons/leapattack_disabled.png");
				} catch (SlickException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				super.setImage(attackImages, 1000);
				super.setEndState(animation, 2000, 2000);
				super.setSkillBarImages(skillBar);
			}
}
