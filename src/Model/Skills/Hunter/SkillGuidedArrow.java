package Model.Skills.Hunter;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skills.Skill;

public class SkillGuidedArrow extends Skill {
	public SkillGuidedArrow(){
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE
		super("Guided Arrow", 11000, 400, 0.4, 3, 25, 150, 300, 300, 300,"Guided arrow: \n" +
				"Level 1: 15 damage\n" +
				"Level 2: 25 damage\n" +
				"Level 3: 35 damage\n" +
				"Level 4: 45 damage", false);
		
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
			
			skillBar[0] = new Image("res/skillIcons/guidedarrow.png");
			skillBar[1] = new Image("res/skillIcons/guidedarrow_active.png");
			skillBar[2] = new Image("res/skillIcons/guidedarrow_disabled.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		super.setImage(attackImage);
		super.setEndState(animation, 200, 400);
		super.setSkillBarImages(skillBar);
	}
}
