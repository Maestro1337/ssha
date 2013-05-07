package Model.Skills.Wizard;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skills.Skill;

public class SkillFirestorm extends Skill{

	public SkillFirestorm(){
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE
		super("Firestorm", 5000, 350, 100, 8, 0, 5, 300, 300, 300,"A dangerous explosion that \ndeals damage to an area for a limited \ntime." +
				"\nLevel 1: 15 damage\n" +
				"Level 2: 25 damage\n" +
				"Level 3: 35 damage\n" +
				"Level 4: 45 damage", false);
		
		Image attackImage = null;
		Image[] animation = new Image[1];
		Image[] skillBar = new Image[3];
		
		
		try {
			attackImage = new Image("res/skillIcons/firestorm.png");
			
			animation[0] = new Image("res/skillIcons/firestorm.png");
			
			skillBar[0] = new Image("res/skillIcons/firestorm.png");
			skillBar[1] = new Image("res/skillIcons/firestorm_active.png");
			skillBar[2] = new Image("res/skillIcons/firestorm_disabled.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.setImage(attackImage, attackImage.getHeight(), attackImage.getWidth());
		super.setEndState(animation, 3000, 30);
		super.setSkillBarImages(skillBar);
	}
}
