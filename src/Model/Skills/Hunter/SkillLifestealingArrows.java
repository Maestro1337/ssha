package Model.Skills.Hunter;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skills.Skill;

public class SkillLifestealingArrows extends Skill {
	public SkillLifestealingArrows(){
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE
		super("Lifestealing Arrows", 11000, 400, 0.4, 3, 25, 150, 300, 300, 300,"Lifestealing arrow: \nArrows that steals the\nlifeforce of the target." +
				"Level 1: 150 damage\n" +
				"Level 2: 300 damage\n" +
				"Level 3: 300 damage\n" +
				"Level 4: 300 damage", false);
		// TODO Lifesteal StatusAffect AffectSelf
		
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
			
			skillBar[0] = new Image("res/skillIcons/healingarrow.png");
			skillBar[1] = new Image("res/skillIcons/healingarrow_active.png");
			skillBar[2] = new Image("res/skillIcons/healingarrow_disabled.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		super.setImage(attackImage);
		super.setEndState(animation, 200, 400);
		super.setSkillBarImages(skillBar);
	}
}
