package Model.Skills.Hunter;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skills.Skill;

public class SkillSuperSlowTestSkill extends Skill{
	
	public SkillSuperSlowTestSkill(){
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE
		super("SuperSlowTestSkill", 1000, 800, 1.5, 3, 0, 300);
		
		Image attackImage = null;
		Image[] skillBar = new Image[2];
		try {
			attackImage = new Image("res/awesomeGreenSquare.png");
			
			skillBar[0] = new Image("res/pbs4.png");
			skillBar[1] = new Image("res/pbs4_active.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		super.setImage(attackImage, attackImage.getHeight(), attackImage.getWidth());
		super.setSkillBarImages(skillBar);
	}

}
