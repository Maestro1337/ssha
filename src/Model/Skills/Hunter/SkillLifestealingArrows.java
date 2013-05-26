package Model.Skills.Hunter;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skill;
import Model.StatusEffects.StatusEffectLifesteal;

public class SkillLifestealingArrows extends Skill {
	public SkillLifestealingArrows(){
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE
		super("Lifestealing Arrows", 1000, 500, 1.75, 3, 25, 100,"Lifestealing arrow: \nArrows that steals the\nlifeforce of the target.\n" +
				"Level 1: 150 damage\n" +
				"Level 2: 300 damage\n" +
				"Level 3: 300 damage\n" +
				"Level 4: 300 damage");
		// TODO Lifesteal StatusAffect AffectSelf
		
		Image[] attackImages = new Image[6];
		Image[] skillBar = new Image[3];
		
		super.setSelfAffectingOnHitStatusEffectShell(new StatusEffectLifesteal(this));
		
		try {
			attackImages[0] = new Image("res/animations/LifestealingArrow/Arrow1.png");
			attackImages[1] = new Image("res/animations/LifestealingArrow/Arrow2.png");
			attackImages[2] = new Image("res/animations/LifestealingArrow/Arrow3.png");
			attackImages[3] = new Image("res/animations/LifestealingArrow/Arrow4.png");
			attackImages[4] = new Image("res/animations/LifestealingArrow/Arrow5.png");
			attackImages[5] = new Image("res/animations/LifestealingArrow/Arrow6.png");
			
			
			
			skillBar[0] = new Image("res/skillIcons/healingarrow.png");
			skillBar[1] = new Image("res/skillIcons/healingarrow_active.png");
			skillBar[2] = new Image("res/skillIcons/healingarrow_disabled.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		super.setImage(attackImages,500);
		super.setSkillBarImages(skillBar);
	}

	private int lvl2 = 200;
	private int lvl3 = 300;
	private int lvl4 = 400;
	
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
