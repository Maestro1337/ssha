package Model.Skills.Hunter;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skills.Skill;

public class SkillSprint extends Skill {
	public SkillSprint(){
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE
		super("Sprint", 11000, 400, 0.4, 3, 10, 150, 300, 300, 300,"Sprint: \nIncreases movementspeed for a short duration." +
				"Level 1: 3 sec\n" +
				"Level 2: 5 sec\n" +
				"Level 3: 7 sec\n" +
				"Level 4: 9 sec", true);
		
		Image attackImage = null;
		Image[] animation = new Image[7];
		Image[] skillBar = new Image[3];
		
		try {
			attackImage = new Image("res/animations/explode1.png");
			
			animation[0] = new Image("res/animations/explode1.png");
			animation[1] = new Image("res/animations/explode2.png");
			animation[2] = new Image("res/animations/explode3.png");
			animation[3] = new Image("res/animations/explode4.png");
			animation[4] = new Image("res/animations/explode5.png");
			animation[5] = new Image("res/animations/explode6.png");
			animation[6] = new Image("res/animations/explode7.png");
			
			skillBar[0] = new Image("res/skillIcons/sprint.png");
			skillBar[1] = new Image("res/skillIcons/sprint_active.png");
			skillBar[2] = new Image("res/skillIcons/sprint_disabled.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		super.setImage(attackImage);
		super.setEndState(animation, 200, 400);
		super.setSkillBarImages(skillBar);
	}
}
