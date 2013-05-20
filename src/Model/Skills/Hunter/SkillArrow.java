package Model.Skills.Hunter;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skills.Skill;
import Model.StatusEffects.StatusEffectStun;

public class SkillArrow extends Skill{
	
	public SkillArrow(){
		// name, cd, range, speed, aoe, cost, damageLvl1, damageLvl2, damageLvl3, damageLvl4, describe, affectSelf
		super("Arrow", 1000, 500, 1.7, 3, 0, 100, "Arrow: \nShoots a plain arrow.\n" +
				"Level 1: 10 damage\n" +
				"Level 2: 20 damage\n" +
				"Level 3: 30 damage\n" +
				"Level 4: 40 damage");
		
		Image[] attackImages = new Image[1];
		Image[] skillBar = new Image[3];
		
		try {
			attackImages[0] = new Image("res/animations/arrow/arrow.png");
			
			skillBar[0] = new Image("res/skillIcons/arrow.png");
			skillBar[1] = new Image("res/skillIcons/arrow_active.png");
			skillBar[2] = new Image("res/skillIcons/arrow_disabled.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		
		super.setImage(attackImages, 200);
		super.setSkillBarImages(skillBar);
	}

	private int lvl2 = 150;
	private int lvl3 = 200;
	private int lvl4 = 250;
	
	@Override
	public void upgradeSkill() {
		if(super.getCurrentLvl() < 4){
			super.incCurrentLvl();
			
			switch(super.getCurrentLvl()){
			case 2:
				super.setDamage(lvl2);
				break;
			case 3:
				super.setDamage(lvl3);
				break;
			case 4:
				super.setDamage(lvl4);
				break;
			}
		}
	}

}
