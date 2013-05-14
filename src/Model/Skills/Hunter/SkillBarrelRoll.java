package Model.Skills.Hunter;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skills.Skill;
import Model.StatusEffects.*;

public class SkillBarrelRoll extends Skill {
	public SkillBarrelRoll(){
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE

		super("Barrel Roll", 4000, 40, 0.4, 3, 50, 0, 0, 0, 0,"Barrel roll: \nRoll out of the fray... \nor into it.\n" +
				"Level 1: 15 sec cd\n" +
				"Level 2: 12 sec cd\n" +
				"Level 3: 8 sec cd\n" +
				"Level 4: 4 sec cd", true);
		
		Image attackImage = null;
		Image[] animation = new Image[5];
		Image[] skillBar = new Image[3];
		
		super.setStatusEffect(new StatusEffectBarrelRoll(this));
		
		try {
			attackImage = new Image("res/animations/hunter_walk2.png");
			
			animation[0] = new Image("res/animations/hunter_walk1.png");
			animation[1] = new Image("res/animations/hunter_walk1.png");
			animation[2] = new Image("res/animations/hunter_walk1.png");
			animation[3] = new Image("res/animations/hunter_walk1.png");
			animation[4] = new Image("res/animations/hunter_walk1.png");
			
			skillBar[0] = new Image("res/skillIcons/barrelroll.png");
			skillBar[1] = new Image("res/skillIcons/barrelroll_active.png");
			skillBar[2] = new Image("res/skillIcons/barrelroll_disabled.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		super.setImage(attackImage);
		super.setEndState(animation, 20, 200);
		super.setSkillBarImages(skillBar);
	}
}
