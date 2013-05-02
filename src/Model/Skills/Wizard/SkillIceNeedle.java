package Model.Skills.Wizard;


import Model.StatusEffect;
import Model.Skills.Skill;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class SkillIceNeedle extends Skill{
	
	public SkillIceNeedle(){
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE
		super("IceNeedle", 2000, 200, 0.5, 3, 0, 250,"Spikes of ice shoots out \nfrom underground, impaling your enemies." +
				"\nLevel 1: 15 damage\n" +
				"Level 2: 25 damage\n" +
				"Level 3: 35 damage\n" +
				"Level 4: 45 damage", false);
		
		Image attackImage = null;
		Image[] skillBar = new Image[2];
		try {
			attackImage = new Image("res/miscImages/awesomeBlueSquare.png");
			
			skillBar[0] = new Image("res/skillIcons/iceneedle.png");
			skillBar[1] = new Image("res/skillIcons/iceneedle_active.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		super.setImage(attackImage, attackImage.getHeight(), attackImage.getWidth());
		super.setSkillBarImages(skillBar);
	}
}
