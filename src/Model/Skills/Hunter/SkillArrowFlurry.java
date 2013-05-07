package Model.Skills.Hunter;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skills.Skill;

public class SkillArrowFlurry extends Skill {
	public SkillArrowFlurry(){
		// name, cd, range, speed, aoe, cost, damageLvl1, damageLvl2, damageLvl3, damageLvl4, describe, affectSelf
		super("Arrow flurry", 1000, 400, 100, 3, 0, 15, 15, 15, 15,"The hunter \n" +
				"Level 1: 15 damage\n" +
				"Level 2: 25 damage\n" +
				"Level 3: 35 damage\n" +
				"Level 4: 45 damage", false);
		
		Image attackImage = null;
		Image[] animation = new Image[1];
		Image[] skillBar = new Image[3];
		
		try {
			attackImage = new Image("res/animations/arrowFlurry.png");
			
			animation[0] = new Image("res/animations/arrowFlurry.png");
			
			skillBar[0] = new Image("res/skillIcons/arrowflurry.png");
			skillBar[1] = new Image("res/skillIcons/arrowflurry_active.png");
			skillBar[2] = new Image("res/skillIcons/arrowflurry_disabled.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		super.setImage(attackImage);
		super.setEndState(animation, 500, 200);
		super.setSkillBarImages(skillBar);
	}
}
