package Model.Skills.Warrior;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skills.Skill;
import Model.StatusEffects.StatusEffectFirstAid;
import Model.StatusEffects.StatusEffectUnstableMagic;

public class SkillFirstAid extends Skill {
	public SkillFirstAid(){
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE
				super("First aid", 11000, 0, 0.4, 3, 50, 0, 0, 0, 0,"First Aid \n" +
						"Level 1: 15 damage\n" +
						"Level 2: 25 damage\n" +
						"Level 3: 35 damage\n" +
						"Level 4: 45 damage");
				
				super.setSelfAffectingStatusEffectShell(new StatusEffectFirstAid(this, 10));
				
				Image attackImage = null;
				Image[] animation = new Image[5];
				Image[] skillBar = new Image[3];
				
				try {
					attackImage = new Image("res/animations/explode1.png");
					
					animation[0] = new Image("res/animations/firstaid/firstAid1.png");
					animation[1] = new Image("res/animations/firstaid/firstAid2.png");
					animation[2] = new Image("res/animations/firstaid/firstAid3.png");
					animation[3] = new Image("res/animations/firstaid/firstAid4.png");
					animation[4] = new Image("res/animations/firstaid/firstAid5.png");
					
					skillBar[0] = new Image("res/skillIcons/firstaid.png");
					skillBar[1] = new Image("res/skillIcons/firstaid_active.png");
					skillBar[2] = new Image("res/skillIcons/firstaid_disabled.png");
				} catch (SlickException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				super.setImage(attackImage);
				super.setEndState(animation, 10000, 200);
				super.setSkillBarImages(skillBar);
			}
}
