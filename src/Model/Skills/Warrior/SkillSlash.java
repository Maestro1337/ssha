package Model.Skills.Warrior;


import Model.StatusEffect;
import Model.Skills.Skill;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class SkillSlash extends Skill{
	
	public SkillSlash(){
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE
		super("Slash", 1000, 0, 100, 3, 0, 1000, 1000, 1000, 1000,"The warrior \n" +
				"Level 1: 15 damage\n" +
				"Level 2: 25 damage\n" +
				"Level 3: 35 damage\n" +
				"Level 4: 45 damage", false);
		
		Image attackImage = null;
		Image[] animation = new Image[4];
		Image[] skillBar = new Image[3];
		
		try {
			attackImage = new Image("res/miscImages/awesomeGreenSquare.png");
			
			animation[0] = new Image("res/animations/slash1.png");
			animation[1] = new Image("res/animations/slash2.png");
			animation[2] = new Image("res/animations/slash3.png");
			animation[3] = new Image("res/animations/slash4.png");
			
			
			skillBar[0] = new Image("res/skillIcons/slash.png");
			skillBar[1] = new Image("res/skillIcons/slash_active.png");
			skillBar[2] = new Image("res/skillIcons/slash_disabled.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		super.setImage(attackImage, attackImage.getHeight(), attackImage.getWidth());
		super.setEndState(animation, 400, 350);
		super.setSkillBarImages(skillBar);
		
	}
}
