package Model.Skills.Wizard;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skills.Skill;
import Model.StatusEffects.StatusEffectMovement;

public class SkillBlizzard extends Skill {
	public SkillBlizzard(){
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE
				super("Blizzard", 11000, 400, 100, 3, 25, 15, 300, 300, 30,"Blizzard:\nA rain of icicles which \nslows every enemy in the \ntargeted area.\n" +
						"\nLevel 1: 15 damage\n" +
						"Level 2: 25 damage\n" +
						"Level 3: 35 damage\n" +
						"Level 4: 45 damage");
				
				Image attackImage = null;
				Image[] animation = new Image[20];
				Image[] skillBar = new Image[3];
				
				super.setOffensiveStatusEffectShell(new StatusEffectMovement(this, -0.7, 1), true);
				
				try {
					attackImage = new Image("res/animations/blizzard/blizzard1.png");
					
					animation[0] = new Image("res/animations/blizzard/blizzard1.png");
					animation[1] = new Image("res/animations/blizzard/blizzard2.png");
					animation[2] = new Image("res/animations/blizzard/blizzard3.png");
					animation[3] = new Image("res/animations/blizzard/blizzard4.png");
					animation[4] = new Image("res/animations/blizzard/blizzard5.png");
					animation[5] = new Image("res/animations/blizzard/blizzard6.png");
					animation[6] = new Image("res/animations/blizzard/blizzard7.png");
					animation[7] = new Image("res/animations/blizzard/blizzard8.png");
					animation[8] = new Image("res/animations/blizzard/blizzard9.png");
					animation[9] = new Image("res/animations/blizzard/blizzard10.png");
					animation[10] = new Image("res/animations/blizzard/blizzard11.png");
					animation[11] = new Image("res/animations/blizzard/blizzard12.png");
					animation[12] = new Image("res/animations/blizzard/blizzard13.png");
					animation[13] = new Image("res/animations/blizzard/blizzard14.png");
					animation[14] = new Image("res/animations/blizzard/blizzard15.png");
					animation[15] = new Image("res/animations/blizzard/blizzard16.png");
					animation[16] = new Image("res/animations/blizzard/blizzard17.png");
					animation[17] = new Image("res/animations/blizzard/blizzard18.png");
					animation[18] = new Image("res/animations/blizzard/blizzard19.png");
					animation[19] = new Image("res/animations/blizzard/blizzard20.png");
					
					
					
					skillBar[0] = new Image("res/skillIcons/blizzard.png");
					skillBar[1] = new Image("res/skillIcons/blizzard_active.png");
					skillBar[2] = new Image("res/skillIcons/blizzard_disabled.png");
				} catch (SlickException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				super.setImage(attackImage);
				super.setEndState(animation, 2000, 1010);
				super.setSkillBarImages(skillBar);
			}
}
