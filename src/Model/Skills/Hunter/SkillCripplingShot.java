package Model.Skills.Hunter;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skills.Skill;
import Model.StatusEffects.StatusEffectCripple;
import Model.StatusEffects.StatusEffectImmobilize;

public class SkillCripplingShot extends Skill {
	public SkillCripplingShot(){
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE
		super("Crippling shot", 11000, 400, 0.4, 3, 25, 150, 300, 300, 300,"Crippling shot: \n A shot which slows the enemy." +
				"Level 1: 150 damage\n" +
				"Level 2: 300 damage\n" +
				"Level 3: 300 damage\n" +
				"Level 4: 300 damage", false);
		
		Image attackImage = null;
		Image[] skillBar = new Image[3];
		
		super.setStatusEffect(new StatusEffectCripple(this, -0.3, 4));
		
		try {
			attackImage = new Image("res/animations/arrow.png");
			skillBar[0] = new Image("res/skillIcons/cripplingshot.png");
			skillBar[1] = new Image("res/skillIcons/cripplingshot_active.png");
			skillBar[2] = new Image("res/skillIcons/cripplingshot_disabled.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		super.setImage(attackImage);
		super.setSkillBarImages(skillBar);
	}
}