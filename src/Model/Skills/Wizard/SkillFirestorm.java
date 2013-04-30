package Model.Skills.Wizard;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skills.Skill;

public class SkillFirestorm extends Skill{

	public SkillFirestorm(){
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE
		super("Firestorm", 5000, 350, 100, 8, 0, 50,"The wizard \n" +
				"Level 1: 15 damage\n" +
				"Level 2: 25 damage\n" +
				"Level 3: 35 damage\n" +
				"Level 4: 45 damage");
		
		Image attackImage = null;
		Image[] animation = new Image[1];
		Image[] skillBar = new Image[2];
		
		try {
			attackImage = new Image("res/miscImages/awesomeMiniRedSquare.png");
			
			animation[0] = new Image("res/skillIcons/Firestorm.png");
			
			skillBar[0] = new Image("res/skillIcons/Firestorm.png");
			skillBar[1] = new Image("res/skillIcons/Firestorm_active.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.setImage(attackImage, attackImage.getHeight(), attackImage.getWidth());
		super.setEndState(animation, 3000, 350);
		super.setSkillBarImages(skillBar);
	}
}
