package Model.Skills.Hunter;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skill;
import Model.StatusEffects.StatusEffectImmobilize;

public class SkillArrowFlurry extends Skill {
	public SkillArrowFlurry(){
		// name, cd, range, speed, aoe, cost, damageLvl1, damageLvl2, damageLvl3, damageLvl4, describe, affectSelf
		super("Arrow flurry", 11000, 400, 200, 3, 50, 150,"Arrow flurry: \nA rain of arrows on a targetted \narea. Immobilizes enemies in  \nthe area and damages over time.\n" +
				"Level 1: 300 total damage\n" +
				"Level 2: 400 total damage\n" +
				"Level 3: 500 total damage\n" +
				"Level 4: 600 total damage");
		
		Image attackImage = null;
		Image[] animation = new Image[17];
		Image[] skillBar = new Image[3];
		
		super.setOffensiveStatusEffectShell(new StatusEffectImmobilize(this, 1),true);
		
		try {
			attackImage = new Image("res/animations/arrowflurry/arrowflurry_end.png");
			
			animation[0] = new Image("res/animations/arrowflurry/arrowflurry1.png");
			animation[1] = new Image("res/animations/arrowflurry/arrowflurry2.png");
			animation[2] = new Image("res/animations/arrowflurry/arrowflurry3.png");
			animation[3] = new Image("res/animations/arrowflurry/arrowflurry4.png");
			animation[4] = new Image("res/animations/arrowflurry/arrowflurry5.png");
			animation[5] = new Image("res/animations/arrowflurry/arrowflurry6.png");
			animation[6] = new Image("res/animations/arrowflurry/arrowflurry7.png");
			animation[7] = new Image("res/animations/arrowflurry/arrowflurry8.png");
			animation[8] = new Image("res/animations/arrowflurry/arrowflurry9.png");
			animation[9] = new Image("res/animations/arrowflurry/arrowflurry10.png");
			animation[10] = new Image("res/animations/arrowflurry/arrowflurry11.png");
			animation[11] = new Image("res/animations/arrowflurry/arrowflurry12.png");
			animation[12] = new Image("res/animations/arrowflurry/arrowflurry13.png");
			animation[13] = new Image("res/animations/arrowflurry/arrowflurry14.png");
			animation[14] = new Image("res/animations/arrowflurry/arrowflurry15.png");
			animation[15] = new Image("res/animations/arrowflurry/arrowflurry16.png");
			animation[16] = new Image("res/animations/arrowflurry/arrowflurry_end.png");
			
			
			skillBar[0] = new Image("res/skillIcons/arrowflurry.png");
			skillBar[1] = new Image("res/skillIcons/arrowflurry_active.png");
			skillBar[2] = new Image("res/skillIcons/arrowflurry_disabled.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		super.setImage(attackImage);
		super.setEndState(animation, 1000, 500);
		super.setSkillBarImages(skillBar);
	}

	private int lvl2 = 200;
	private int lvl3 = 250;
	private int lvl4 = 300;
	
	@Override
	public void upgradeSkill() {
		if(super.getCurrentLvl() < 4){
			super.incCurrentLvl();
			
			switch(super.getCurrentLvl()){
			case 2:
				super.setOffensiveStatusEffectShell(new StatusEffectImmobilize(this, 1),true);
				super.setDamage(lvl2);
				break;
			case 3:
				super.setOffensiveStatusEffectShell(new StatusEffectImmobilize(this, 2),true);
				super.setDamage(lvl3);
				break;
			case 4:
				super.setOffensiveStatusEffectShell(new StatusEffectImmobilize(this, 2),true);
				super.setDamage(lvl4);
				break;
			}
		}
	}
}
