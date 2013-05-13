package Model.Skills.Hunter;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skills.Skill;
import Model.StatusEffects.StatusEffectStun;

public class SkillArrow extends Skill{
	
	public SkillArrow(){
		// name, cd, range, speed, aoe, cost, damageLvl1, damageLvl2, damageLvl3, damageLvl4, describe, affectSelf
		super("Arrow", 1000, 800, 0.5, 3, 0, 30, 300, 300, 300,"Arrow: \n Shoots a plain arrow." +
				"Level 1: 10 damage\n" +
				"Level 2: 20 damage\n" +
				"Level 3: 30 damage\n" +
				"Level 4: 40 damage", false);
		
		Image[] attackImages = new Image[1];
		Image[] skillBar = new Image[3];
		
		super.setStatusEffect(new StatusEffectStun(this, 5));
		
		try {
			attackImages[0] = new Image("res/animations/arrow.png");
			
			skillBar[0] = new Image("res/skillIcons/guidedarrow.png");
			skillBar[1] = new Image("res/skillIcons/guidedarrow_active.png");
			skillBar[2] = new Image("res/skillIcons/guidedarrow_disabled.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		
		super.setImage(attackImages, 200);
		super.setSkillBarImages(skillBar);
	}

}
