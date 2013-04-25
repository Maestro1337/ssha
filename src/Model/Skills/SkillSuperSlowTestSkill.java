package Model.Skills;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class SkillSuperSlowTestSkill extends Skill{
	
	public SkillSuperSlowTestSkill(){
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE
		super("SuperSlowTestSkill", 1000, 800, 0.3, 3, 0, -30, null);
		
		Image attackImage = null;
		Image[] skillBar = new Image[2];
		try {
			attackImage = new Image("res/awesomeGreenSquare.png");
			
			skillBar[0] = new Image("res/pbs4.png");
			skillBar[1] = new Image("res/pbs4_active.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		super.setImage(attackImage, attackImage.getHeight(), attackImage.getWidth());
		super.setSkillBarImages(skillBar);
	}

}
