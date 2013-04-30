package Model.Skills.Wizard;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.StatusEffect;
import Model.Skills.Skill;
import Model.StatusEffects.*;


public class SkillFireball extends Skill{
	
String description;

	public SkillFireball(){
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE
		super("Fireball", 11000, 400, 0.4, 3, 0, 150, "A fireball that burns the enemy until oblivion.\n" +
				"Level 1: 15 damage\n" +
				"Level 2: 25 damage\n" +
				"Level 3: 35 damage\n" +
				"Level 4: 45 damage");
		
		Image attackImage = null;
		Image[] animation = new Image[7];
		Image[] skillBar = new Image[2];
		
		super.setStatusEffect(new StatusEffectBurning(null, this));
		
		try {
			attackImage = new Image("res/explode1.png");
			
			animation[0] = new Image("res/explode1.png");
			animation[1] = new Image("res/explode2.png");
			animation[2] = new Image("res/explode3.png");
			animation[3] = new Image("res/explode4.png");
			animation[4] = new Image("res/explode5.png");
			animation[5] = new Image("res/explode6.png");
			animation[6] = new Image("res/explode7.png");
			
			skillBar[0] = new Image("res/fireball.png");
			skillBar[1] = new Image("res/fireball_active.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		super.setImage(attackImage, attackImage.getHeight(), attackImage.getWidth());
		super.setEndState(animation, 200, 400);
		super.setSkillBarImages(skillBar);
	}
	public String getDescription(){
		return description;
	}
}
