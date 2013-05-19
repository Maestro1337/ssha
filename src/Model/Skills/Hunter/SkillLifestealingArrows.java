package Model.Skills.Hunter;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skills.Skill;

public class SkillLifestealingArrows extends Skill {
	public SkillLifestealingArrows(){
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE
		super("Lifestealing Arrows", 11000, 400, 0.4, 3, 25, 150,"Lifestealing arrow: \nArrows that steals the\nlifeforce of the target.\n" +
				"Level 1: 150 damage\n" +
				"Level 2: 300 damage\n" +
				"Level 3: 300 damage\n" +
				"Level 4: 300 damage");
		// TODO Lifesteal StatusAffect AffectSelf
		
		Image[] attackImages = new Image[6];
		Image[] animation = new Image[1];
		Image[] skillBar = new Image[3];
		
		try {
			attackImages[0] = new Image("res/animations/LifestealingArrow/Arrow1.png");
			attackImages[1] = new Image("res/animations/LifestealingArrow/Arrow2.png");
			attackImages[2] = new Image("res/animations/LifestealingArrow/Arrow3.png");
			attackImages[3] = new Image("res/animations/LifestealingArrow/Arrow4.png");
			attackImages[4] = new Image("res/animations/LifestealingArrow/Arrow5.png");
			attackImages[5] = new Image("res/animations/LifestealingArrow/Arrow6.png");
			
			animation[0] = new Image("res/animations/explode/explode1.png");
			
			
			skillBar[0] = new Image("res/skillIcons/healingarrow.png");
			skillBar[1] = new Image("res/skillIcons/healingarrow_active.png");
			skillBar[2] = new Image("res/skillIcons/healingarrow_disabled.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		super.setImage(attackImages,500);
		super.setEndState(animation, 200, 400);
		super.setSkillBarImages(skillBar);
	}

	private int lvl2 = 300;
	private int lvl3 = 300;
	private int lvl4 = 300;
	
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
