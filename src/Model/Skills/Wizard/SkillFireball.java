package Model.Skills.Wizard;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skill;
import Model.StatusEffects.*;


public class SkillFireball extends Skill{
	
String description;

	public SkillFireball(){
		//String name, int cd, int range, double speed, int aoe, int cost, int damage lvl1, int damage lvl2,  int damage lvl3, int damage lvl4,  StatusEffect SE
		super("Fireball", 11000, 400, 1.5, 3, 10, 100,"Fireball:\nA fireball that burns the \nenemy to oblivion.\n" +
				"\nLevel 1: 15 damage\n" +
				"Level 2: 25 damage\n" +
				"Level 3: 35 damage\n" +
				"Level 4: 45 damage");
		
		Image[] attackImages = new Image[4];
		Image[] animation = new Image[7];
		Image[] skillBar = new Image[3];
		
		super.setOffensiveStatusEffectShell(new StatusEffectBurning(this, 25, 2),false);
		
		try {
			attackImages[0] = new Image("res/animations/fireball/fireball1.png");
			attackImages[1] = new Image("res/animations/fireball/fireball2.png");
			attackImages[2] = new Image("res/animations/fireball/fireball3.png");
			attackImages[3] = new Image("res/animations/fireball/fireball4.png");
			
			animation[0] = new Image("res/animations/explode/explode1.png");
			animation[1] = new Image("res/animations/explode/explode2.png");
			animation[2] = new Image("res/animations/explode/explode3.png");
			animation[3] = new Image("res/animations/explode/explode4.png");
			animation[4] = new Image("res/animations/explode/explode5.png");
			animation[5] = new Image("res/animations/explode/explode6.png");
			animation[6] = new Image("res/animations/explode/explode7.png");
			
			skillBar[0] = new Image("res/skillIcons/fireball.png");
			skillBar[1] = new Image("res/skillIcons/fireball_active.png");
			skillBar[2] = new Image("res/skillIcons/fireball_disabled.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		super.setImage(attackImages, 200);
		super.setEndState(animation, 200, 400);
		super.setSkillBarImages(skillBar);
	}

	private int lvl2 = 120;
	private int lvl3 = 140;
	private int lvl4 = 180;
	
	@Override
	public void upgradeSkill() {
		if(super.getCurrentLvl() < 4){
			super.incCurrentLvl();
			
			switch(super.getCurrentLvl()){
			case 2:
				super.setOffensiveStatusEffectShell(new StatusEffectBurning(this, 25, 3),false);
				super.setDamage(lvl2);
				break;
			case 3:
				super.setOffensiveStatusEffectShell(new StatusEffectBurning(this, 25, 4),false);
				super.setDamage(lvl3);
				break;
			case 4:
				super.setOffensiveStatusEffectShell(new StatusEffectBurning(this, 25, 5),false);
				super.setDamage(lvl4);
				break;
			}
		}
	}

	
}
