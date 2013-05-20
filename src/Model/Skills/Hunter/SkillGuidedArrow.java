package Model.Skills.Hunter;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skills.Skill;

public class SkillGuidedArrow extends Skill {
	public SkillGuidedArrow(){
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE
		super("Guided Arrow", 11000, 400, 0.4, 3, 25, 150,"Guided arrow: \nAn arrow that seeks out \nenemies.\n" +
				"Level 1: 150 damage\n" +
				"Level 2: 300 damage\n" +
				"Level 3: 300 damage\n" +
				"Level 4: 300 damage");
		
		
		
		Image attackImage = null;
		Image[] skillBar = new Image[3];
		
		try {
			attackImage = new Image("res/animations/guidedarrow/guidedarrow.png");
			
			skillBar[0] = new Image("res/skillIcons/guidedarrow.png");
			skillBar[1] = new Image("res/skillIcons/guidedarrow_active.png");
			skillBar[2] = new Image("res/skillIcons/guidedarrow_disabled.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		super.setGuided();
		super.setImage(attackImage);
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
