package Model.Skills;


import Model.StatusEffect;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class SkillIceNeedle extends Skill{
	
	public SkillIceNeedle(){
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE
		super("IceNeedle", 2000, 200, 0.5, 3, 0, 25, null);
		
		Image attackImage = null;
		try {
			attackImage = new Image("res/awesomeBlueSquare.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		super.setImage(attackImage, attackImage.getHeight(), attackImage.getWidth());
	}
}
