package Model.Skills.Hunter;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skills.Skill;
import Model.StatusEffects.StatusEffectImmobilize;

public class SkillArrowFlurry extends Skill {
	public SkillArrowFlurry(){
		// name, cd, range, speed, aoe, cost, damageLvl1, damageLvl2, damageLvl3, damageLvl4, describe, affectSelf
		super("Arrow flurry", 1000, 400, 100, 3, 50, 40, 200, 400, 800,"Arrow flurry: \nA rain of arrows on a targetted \narea. Immobilizes enemies in  \nthe area and damages over time.\n" +
				"Level 1: 100 damage\n" +
				"Level 2: 200 damage\n" +
				"Level 3: 400 damage\n" +
				"Level 4: 800 damage");
		
		Image attackImage = null;
		Image[] animation = new Image[17];
		Image[] skillBar = new Image[3];
		
		super.setOffensiveStatusEffectShell(new StatusEffectImmobilize(this, 1),false);
		
		try {
			attackImage = new Image("res/animations/arrowFlurry.png");
			
			animation[0] = new Image("res/animations/arrowflurry/arrowflurry1.png");
			animation[1] = new Image("res/animations/arrowflurry/arrowflurry2.png");
			animation[2] = new Image("res/animations/arrowflurry/arrowflurry3.png");
			animation[3] = new Image("res/animations/arrowflurry/arrowflurry4.png");
			animation[4] = new Image("res/animations/arrowflurry/arrowflurry5.png");
			animation[5] = new Image("res/animations/arrowflurry/arrowflurry6.png");
			animation[6] = new Image("res/animations/arrowflurry/arrowflurry7.png");
			animation[7] = new Image("res/animations/arrowflurry/arrowflurry8.png");
			animation[8] = new Image("res/animations/arrowflurry/arrowflurry9.png");
			animation[9] = new Image("res/animations/arrowflurry/arrowflurry10.png");
			animation[10] = new Image("res/animations/arrowflurry/arrowflurry11.png");
			animation[11] = new Image("res/animations/arrowflurry/arrowflurry12.png");
			animation[12] = new Image("res/animations/arrowflurry/arrowflurry13.png");
			animation[13] = new Image("res/animations/arrowflurry/arrowflurry14.png");
			animation[14] = new Image("res/animations/arrowflurry/arrowflurry15.png");
			animation[15] = new Image("res/animations/arrowflurry/arrowflurry16.png");
			animation[16] = new Image("res/animations/arrowflurry/arrowflurry_end.png");
			
			skillBar[0] = new Image("res/skillIcons/arrowflurry.png");
			skillBar[1] = new Image("res/skillIcons/arrowflurry_active.png");
			skillBar[2] = new Image("res/skillIcons/arrowflurry_disabled.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		super.setImage(attackImage);
		super.setEndState(animation, 1000, 500);
		super.setSkillBarImages(skillBar);
	}
}
