package Model.Skills.Hunter;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skills.Skill;

public class SkillSuperSlowTestSkill extends Skill{
	
	public SkillSuperSlowTestSkill(){
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE
		super("SuperSlowTestSkill", 1000, 800, 1.5, 3, 0, 300, 300, 300, 300,"The hunter \n" +
				"Level 1: 15 damage\n" +
				"Level 2: 25 damage\n" +
				"Level 3: 35 damage\n" +
				"Level 4: 45 damage", false);
		
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
		
		super.setImage(attackImage, attackImage.getHeight(), attackImage.getWidth());
		super.setSkillBarImages(skillBar);
	}

}
