package Model.Skills;


import Model.StatusEffect;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class SkillSlash extends Skill{
	
	public SkillSlash(){
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE
		super("Slash", 5000, 200, 0.2, 3, 0, 10, null);
		
		Image attackImage = null;
		Image[] animation = new Image[4];
		
		try {
			attackImage = new Image("res/awesomeGreenSquare.png");
			
			animation[0] = new Image("res/slash1.png");
			animation[1] = new Image("res/slash2.png");
			animation[2] = new Image("res/slash3.png");
			animation[3] = new Image("res/slash4.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		super.setImage(attackImage, attackImage.getHeight(), attackImage.getWidth());
		super.setEndState(animation, 3000, 350);
		
	}
}
