package Model.Skills.Warrior;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skills.Skill;

public class SkillAdrenaline extends Skill {
	public SkillAdrenaline(){
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE
				super("Adrenaline", 5000, 0, 0.4, 3, 50, 0, 0, 0, 0,"Adrenaline \n" +
						"Level 1: 15 damage\n" +
						"Level 2: 25 damage\n" +
						"Level 3: 35 damage\n" +
						"Level 4: 45 damage");
				
				Image attackImage = null;
				Image[] animation = new Image[5];
				Image[] skillBar = new Image[3];
				
				try {
					attackImage = new Image("res/animations/explode1.png");
					
					animation[0] = new Image("res/animations/adrenaline/adrenaline1.png");
					animation[1] = new Image("res/animations/adrenaline/adrenaline2.png");
					animation[2] = new Image("res/animations/adrenaline/adrenaline3.png");
					animation[3] = new Image("res/animations/adrenaline/adrenaline4.png");
					animation[4] = new Image("res/animations/adrenaline/adrenaline5.png");
					
					skillBar[0] = new Image("res/skillIcons/adrenaline.png");
					skillBar[1] = new Image("res/skillIcons/adrenaline_active.png");
					skillBar[2] = new Image("res/skillIcons/adrenaline_disabled.png");
				} catch (SlickException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				super.setImage(attackImage);
				super.setEndState(animation, 1000, 1000);
				super.setSkillBarImages(skillBar);
			}
}
