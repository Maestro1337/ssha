package Model.Skills.Wizard;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skills.Skill;

public class SkillIceblock extends Skill {

	public SkillIceblock() {
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, String skillDescription
		super("Ice block", 11000, 20, 0, 20, 0, 0, 300, 300, 300,"Encapsule yourself inside \nan iceblock to escape from \nharms way.\n" +
				"\nLevel 1: 1 second\n" +
				"Level 2: 2 second\n" +
				"Level 3: 3 second\n" +
				"Level 4: 4 second", false);
		
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
			
			skillBar[0] = new Image("res/skillIcons/iceblock.png");
			skillBar[1] = new Image("res/skillIcons/iceblock_active.png");
			skillBar[2] = new Image("res/skillIcons/iceblock_disabled.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		super.setImage(attackImage);
		super.setEndState(animation, 200, 400);
		super.setSkillBarImages(skillBar);
	}

}
