package Model.Skills.Hunter;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skill;
import Model.StatusEffects.StatusEffectMovement;

public class SkillCripplingTrap extends Skill {
	public SkillCripplingTrap(){
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE
		super("Crippling shot", 20000, 0, 0.4, 5, 60, 200,"Crippling trap: \nA trap which slows and damages\nthe enemy.\n" +
				"Level 1: 200 damage\n" +
				"Level 2: 250 damage\n" +
				"Level 3: 300 damage\n" +
				"Level 4: 350 damage");
		
		Image[] attackImage = new Image[1];
		Image[] skillBar = new Image[3];
		
		super.setOffensiveStatusEffectShell(new StatusEffectMovement(this, -0.4, 4, 0), false);
		
		try {
			attackImage[0] = new Image("res/animations/trap/trap.png");
			skillBar[0] = new Image("res/skillIcons/cripplingtrap.png");
			skillBar[1] = new Image("res/skillIcons/cripplingtrap_active.png");
			skillBar[2] = new Image("res/skillIcons/cripplingtrap_disabled.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		super.setImage(attackImage, 200);
		super.setEndState(attackImage, 20000, 1000);
		super.setSkillBarImages(skillBar);
	}

	private int lvl2 = 250;
	private int lvl3 = 300;
	private int lvl4 = 350;
	
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