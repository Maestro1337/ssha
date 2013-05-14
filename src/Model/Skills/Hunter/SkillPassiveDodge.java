package Model.Skills.Hunter;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skills.Skill;

public class SkillPassiveDodge extends Skill {
	public SkillPassiveDodge(){
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE
		super("Passive Dodge", 11000, 400, 0.4, 3, 10, 150, 300, 300, 300,"Dodge: \nGives the chance to avoid \nincoming damage.\n" +
				"Level 1: 5% chance\n" +
				"Level 2: 10% chance\n" +
				"Level 3: 15% chance\n" +
				"Level 4: 20% chance", true);
		
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
			
			skillBar[0] = new Image("res/skillIcons/dodge.png");
			skillBar[1] = new Image("res/skillIcons/dodge_active.png");
			skillBar[2] = new Image("res/skillIcons/dodge_disabled.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		super.setImage(attackImage);
		super.setEndState(animation, 200, 400);
		super.setSkillBarImages(skillBar);
	}
}
