package Model.Skills.Hunter;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skills.Skill;
import Model.StatusEffects.StatusEffectBurning;

public class SkillFlamingArrow extends Skill {
	public SkillFlamingArrow(){
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE
		super("Flaming Arrow", 11000, 400, 0.4, 3, 10, 150, 300, 300, 300,"Flaming arrow: \nAn arrow which burns \nthe enemies over time.\n" +
				"Level 1: 150 damage\n" +
				"Level 2: 300 damage\n" +
				"Level 3: 300 damage\n" +
				"Level 4: 300 damage");
		
		Image[] attackImages = new Image[4];
		Image[] animation = new Image[7];
		Image[] skillBar = new Image[3];
		
		super.setOffensiveStatusEffectShell(new StatusEffectBurning(this, 3),false);
		
		try {
			attackImages[0] = new Image("res/animations/flamingarrow/flamingarrow1.png");
			attackImages[1] = new Image("res/animations/flamingarrow/flamingarrow2.png");
			attackImages[2] = new Image("res/animations/flamingarrow/flamingarrow3.png");
			attackImages[3] = new Image("res/animations/flamingarrow/flamingarrow4.png");
			
			
			animation[0] = new Image("res/animations/explode/explode1.png");
			animation[1] = new Image("res/animations/explode/explode2.png");
			animation[2] = new Image("res/animations/explode/explode3.png");
			animation[3] = new Image("res/animations/explode/explode4.png");
			animation[4] = new Image("res/animations/explode/explode5.png");
			animation[5] = new Image("res/animations/explode/explode6.png");
			animation[6] = new Image("res/animations/explode/explode7.png");
			
			skillBar[0] = new Image("res/skillIcons/flamingarrow.png");
			skillBar[1] = new Image("res/skillIcons/flamingarrow_active.png");
			skillBar[2] = new Image("res/skillIcons/flamingarrow_disabled.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.setImage(attackImages, 200);
		super.setEndState(animation, 200, 400);
		super.setSkillBarImages(skillBar);
	}

	@Override
	public void upgradeSkill() {
		// TODO Auto-generated method stub
		
	}
}
