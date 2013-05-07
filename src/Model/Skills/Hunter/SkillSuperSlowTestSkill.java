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
		
		Image[] attackImages = new Image[1];
		Image[] skillBar = new Image[3];
		try {
			attackImages[0] = new Image("res/animations/arrow.png");
			
			skillBar[0] = new Image("res/skillIcons/guidedarrow.png");
			skillBar[1] = new Image("res/skillIcons/guidedarrow_active.png");
			skillBar[2] = new Image("res/skillIcons/guidedarrow_disabled.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		super.setGuided();
		super.setImage(attackImages, 200);
		super.setSkillBarImages(skillBar);
	}

}
