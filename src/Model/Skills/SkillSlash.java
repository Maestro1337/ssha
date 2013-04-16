package Model.Skills;


import Model.StatusEffect;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class SkillSlash extends Skill{
	
	public SkillSlash(){
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE
		super("Slash", 5000, 200, 0.2, 3, 0, 10, null);
		
		Image attackImage = null;
		try {
			attackImage = new Image("res/awesomeGreenSquare.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		super.setImage(attackImage, attackImage.getHeight(), attackImage.getWidth());
	}
}
