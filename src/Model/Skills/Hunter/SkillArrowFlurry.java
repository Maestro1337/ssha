package Model.Skills.Hunter;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skills.Skill;
import Model.StatusEffects.StatusEffectImmobilize;

public class SkillArrowFlurry extends Skill {
	public SkillArrowFlurry(){
		// name, cd, range, speed, aoe, cost, damageLvl1, damageLvl2, damageLvl3, damageLvl4, describe, affectSelf
		super("Arrow flurry", 1000, 400, 100, 3, 50, 100, 200, 400, 800,"Arrow flurry: \nA rain of arrows on a targetted \narea. Immobilizes enemies in  \nthe area and damages over time.\n" +
				"Level 1: 100 damage\n" +
				"Level 2: 200 damage\n" +
				"Level 3: 400 damage\n" +
				"Level 4: 800 damage", false);
		
		Image attackImage = null;
		Image[] animation = new Image[1];
		Image[] skillBar = new Image[3];
		
		super.setStatusEffect(new StatusEffectImmobilize(this, 1));
		
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
