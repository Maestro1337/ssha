package Model.Skills;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class SkillFireball extends Skill{
	
	public SkillFireball(){
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE
		super("Fireball", 11000, 400, 0.4, 3, 0, 15, null);
		
		Image attackImage = null;
		try {
			attackImage = new Image("res/awesomeMiniRedSquare.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Image endStateImage = null;
		try {
			endStateImage = new Image("res/awesomeRedSquare.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		super.setImage(attackImage, attackImage.getHeight(), attackImage.getWidth());
		super.setEndState(endStateImage, endStateImage.getHeight(), endStateImage.getWidth(), 200, 400);
	}

}
