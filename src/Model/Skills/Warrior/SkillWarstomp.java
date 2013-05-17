package Model.Skills.Warrior;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skills.Skill;
import Model.StatusEffects.StatusEffectImmobilize;
import Model.StatusEffects.StatusEffectStun;

public class SkillWarstomp extends Skill {
	public SkillWarstomp(){
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE
				super("Warstomp", 11000, 0, 0.4, 3, 25, 150, 300, 300, 300,"Warstomp \n" +
						"Level 1: 15 damage\n" +
						"Level 2: 25 damage\n" +
						"Level 3: 35 damage\n" +
						"Level 4: 45 damage");
				
				Image attackImage = null;
				Image[] animation = new Image[1];
				Image[] skillBar = new Image[3];

				
				super.setOffensiveStatusEffectShell(new StatusEffectStun(this, 2),false);
				
				try {
					attackImage = new Image("res/animations/warstomp/warstompGround.png");
					
					animation[0] = new Image("res/animations/warstomp/warstompGround.png");
					
					skillBar[0] = new Image("res/skillIcons/warstomp.png");
					skillBar[1] = new Image("res/skillIcons/warstomp_active.png");
					skillBar[2] = new Image("res/skillIcons/warstomp_disabled.png");
				} catch (SlickException e) {
					e.printStackTrace();
				}
				super.setImage(attackImage);
				super.setEndState(animation, 5000, 5000);
				super.setSkillBarImages(skillBar);
			}
}
