package Model.Skills.Hunter;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skills.Skill;

public class SkillSuperSlowTestSkill extends Skill{
	
	public SkillSuperSlowTestSkill(){
		// name, cd, range, speed, aoe, cost, damageLvl1, damageLvl2, damageLvl3, damageLvl4, describe, affectSelf
		super("SuperSlowTestSkill", 1000, 800, 0.5, 3, 0, 300, 300, 300, 300,"The hunter \n" +
				"Level 1: 300 damage\n" +
				"Level 2: 300 damage\n" +
				"Level 3: 300 damage\n" +
				"Level 4: 300 damage", false);
		
		Image attackImage = null;
		Image[] skillBar = new Image[3];
		try {
			attackImage = new Image("res/miscImages/awesomeGreenSquare.png");
			
			skillBar[0] = new Image("res/skillIcons/fireball.png");
			skillBar[1] = new Image("res/skillIcons/fireball_active.png");
			skillBar[2] = new Image("res/skillIcons/fireball_disabled.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		super.setGuided();
		super.setImage(attackImage, attackImage.getHeight(), attackImage.getWidth());
		super.setSkillBarImages(skillBar);
	}

}
