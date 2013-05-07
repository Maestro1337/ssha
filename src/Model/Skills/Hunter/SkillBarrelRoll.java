package Model.Skills.Hunter;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skills.Skill;
import Model.StatusEffects.*;

public class SkillBarrelRoll extends Skill {
	public SkillBarrelRoll(){
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE
		super("Barrel Roll", 1000, 40, 100.4, 3, 0, 0, 0, 0, 0,"The hunter \n" +
				"Level 1: 15 damage\n" +
				"Level 2: 25 damage\n" +
				"Level 3: 35 damage\n" +
				"Level 4: 45 damage", true);
		
		Image attackImage = null;
		Image[] animation = new Image[1];
		Image[] skillBar = new Image[3];
		
//		super.setStatusEffect(new StatusEffectBarrelRoll(null, this, 0, 0));
		super.setStatusEffect(new StatusEffectTeleport(null, this, 0, 0));
		
		try {
			attackImage = new Image("res/animations/hunter_walk1.png");
			
			animation[0] = new Image("res/animations/hunter_walk2.png");
			
			skillBar[0] = new Image("res/skillIcons/barrelroll.png");
			skillBar[1] = new Image("res/skillIcons/barrelroll_active.png");
			skillBar[2] = new Image("res/skillIcons/barrelroll_disabled.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		super.setImage(attackImage, attackImage.getHeight(), attackImage.getWidth());
		super.setEndState(animation, 200, 400);
		super.setSkillBarImages(skillBar);
	}
}
