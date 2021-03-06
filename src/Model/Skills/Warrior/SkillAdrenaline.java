package Model.Skills.Warrior;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skill;
import Model.StatusEffects.StatusEffectAdrenaline;

public class SkillAdrenaline extends Skill {
	public SkillAdrenaline(){
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE
				super("Adrenaline", 5000, 0, 0.4, 3, 150, 0,"Adrenaline increases \ndamage of slash attack \n" +
						"Level 1: 150 damage\n" +
						"Level 2: 200 damage\n" +
						"Level 3: 300 damage\n" +
						"Level 4: 400 damage");
				
				Image attackImage = null;
				Image[] animation = new Image[5];
				Image[] skillBar = new Image[3];
				
				super.setSelfAffectingStatusEffectShell(new StatusEffectAdrenaline(this, 8));
				
				try {
					attackImage = new Image("res/animations/explode/explode1.png");
					
					animation[0] = new Image("res/animations/adrenaline/adrenaline1.png");
					animation[1] = new Image("res/animations/adrenaline/adrenaline2.png");
					animation[2] = new Image("res/animations/adrenaline/adrenaline3.png");
					animation[3] = new Image("res/animations/adrenaline/adrenaline4.png");
					animation[4] = new Image("res/animations/adrenaline/adrenaline5.png");
					
					skillBar[0] = new Image("res/skillIcons/adrenaline.png");
					skillBar[1] = new Image("res/skillIcons/adrenaline_active.png");
					skillBar[2] = new Image("res/skillIcons/adrenaline_disabled.png");
				} catch (SlickException e) {
					e.printStackTrace();
				}
				super.setImage(attackImage);
				super.setEndState(animation, 1000, 1000);
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
