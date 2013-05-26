package Model.Skills.Warrior;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skill;
import Model.StatusEffects.StatusEffectFirstAid;

public class SkillFirstAid extends Skill {
	public SkillFirstAid(){
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE
				super("First aid", 11000, 0, 0.4, 3, 150, 0,"First Aid: \nChannels a heal while\nstanding still\n" +
						"Level 1: 20 health/sec\n" +
						"Level 2: 30 health/sec\n" +
						"Level 3: 40 health/sec\n" +
						"Level 4: 50 health/sec");
				
				super.setSelfAffectingStatusEffectShell(new StatusEffectFirstAid(this, 20, 3));
				
				Image attackImage = null;
				Image[] animation = new Image[19];
				Image[] skillBar = new Image[3];
				
				try {
					attackImage = new Image("res/animations/explode/explode1.png");
					
					animation[0] = new Image("res/animations/firstaid/firstAid1.png");
					animation[1] = new Image("res/animations/firstaid/firstAid2.png");
					animation[2] = new Image("res/animations/firstaid/firstAid3.png");
					animation[3] = new Image("res/animations/firstaid/firstAid4.png");
					animation[4] = new Image("res/animations/firstaid/firstAid5.png");
					animation[5] = new Image("res/animations/firstaid/firstAid6.png");
					animation[6] = new Image("res/animations/firstaid/firstAid7.png");
					animation[7] = new Image("res/animations/firstaid/firstAid8.png");
					animation[8] = new Image("res/animations/firstaid/firstAid9.png");
					animation[9] = new Image("res/animations/firstaid/firstAid10.png");
					animation[10] = new Image("res/animations/firstaid/firstAid11.png");
					animation[11] = new Image("res/animations/firstaid/firstAid12.png");
					animation[12] = new Image("res/animations/firstaid/firstAid13.png");
					animation[13] = new Image("res/animations/firstaid/firstAid14.png");
					animation[14] = new Image("res/animations/firstaid/firstAid15.png");
					animation[15] = new Image("res/animations/firstaid/firstAid16.png");
					animation[16] = new Image("res/animations/firstaid/firstAid17.png");
					animation[17] = new Image("res/animations/firstaid/firstAid18.png");
					animation[18] = new Image("res/animations/firstaid/firstAid19.png");
					
					skillBar[0] = new Image("res/skillIcons/firstaid.png");
					skillBar[1] = new Image("res/skillIcons/firstaid_active.png");
					skillBar[2] = new Image("res/skillIcons/firstaid_disabled.png");
				} catch (SlickException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				super.setImage(attackImage);
				super.setEndState(animation, 3000, 3000);
				super.setSkillBarImages(skillBar);
			}

	private int lvl2 = 30;
	private int lvl3 = 40;
	private int lvl4 = 50;
	
	@Override
	public void upgradeSkill() {
		if(super.getCurrentLvl() < 4){
			super.incCurrentLvl();
			
			switch(super.getCurrentLvl()){
			case 2:
				super.setSelfAffectingStatusEffectShell(new StatusEffectFirstAid(this, lvl2, 3));
				super.setDamage(lvl2);
				break;
			case 3:
				super.setSelfAffectingStatusEffectShell(new StatusEffectFirstAid(this, lvl3, 3));
				super.setDamage(lvl3);
				break;
			case 4:
				super.setSelfAffectingStatusEffectShell(new StatusEffectFirstAid(this, lvl4, 3));
				super.setDamage(lvl4);
				break;
			}
		}
	}
}
