package Model.Skills;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class SkillFirestorm extends Skill{

	public SkillFirestorm(){
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE
		super("Firestorm", 5000, 350, 2, 8, 0, 25, null);
		
		Image attackImage = null;
		try {
			attackImage = new Image("res/awesomeRedSquare.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		super.setImage(attackImage, 50, 50);
	}
}
