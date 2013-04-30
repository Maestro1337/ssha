package Model.Skills.Wizard;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skills.Skill;

public class SkillBlizzard extends Skill {
	public SkillBlizzard(){
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE
				super("Blizzard", 11000, 400, 0.4, 3, 0, 150,"The wizard \n" +
						"Level 1: 15 damage\n" +
						"Level 2: 25 damage\n" +
						"Level 3: 35 damage\n" +
						"Level 4: 45 damage", false);
				
				Image attackImage = null;
				Image[] animation = new Image[7];
				Image[] skillBar = new Image[2];
				
				try {
					attackImage = new Image("res/animations/explode1.png");
					
					animation[0] = new Image("res/animations/explode1.png");
					animation[1] = new Image("res/animations/explode2.png");
					animation[2] = new Image("res/animations/explode3.png");
					animation[3] = new Image("res/animations/explode4.png");
					animation[4] = new Image("res/animations/explode5.png");
					animation[5] = new Image("res/animations/explode6.png");
					animation[6] = new Image("res/animations/explode7.png");
					
					skillBar[0] = new Image("res/skillIcons/Blizzard.jpg");
					skillBar[1] = new Image("res/skillIcons/fireball_active.png");
				} catch (SlickException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				super.setImage(attackImage, attackImage.getHeight(), attackImage.getWidth());
				super.setEndState(animation, 200, 400);
				super.setSkillBarImages(skillBar);
			}
}
