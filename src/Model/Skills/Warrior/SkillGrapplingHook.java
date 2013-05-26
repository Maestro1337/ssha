package Model.Skills.Warrior;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skill;
import Model.StatusEffects.StatusEffectStun;

public class SkillGrapplingHook extends Skill {
	public SkillGrapplingHook(){
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE
				super("Grappling hook", 1000, 500, 7, 3, 60, 150,"Grappling hook \nHook yourself to an \nobstacle or player dealing\ndamage and stunning them\n" +
						"Level 1: 150 damage\n" +
						"Level 2: 200 damage\n" +
						"Level 3: 250 damage\n" +
						"Level 4: 300 damage");
				
				Image[] attackImage = new Image[18];
				Image[] animation = new Image[18];
				Image[] skillBar = new Image[3];
				
				super.setOffensiveStatusEffectShell(new StatusEffectStun(this, 2), false);
				
				try {
					attackImage[0] = new Image("res/animations/grapplingHook/grapp1.png");
					attackImage[1] = new Image("res/animations/grapplingHook/grapp2.png");
					attackImage[2] = new Image("res/animations/grapplingHook/grapp3.png");
					attackImage[3] = new Image("res/animations/grapplingHook/grapp4.png");
					attackImage[4] = new Image("res/animations/grapplingHook/grapp5.png");
					attackImage[5] = new Image("res/animations/grapplingHook/grapp6.png");
					attackImage[6] = new Image("res/animations/grapplingHook/grapp7.png");
					attackImage[7] = new Image("res/animations/grapplingHook/grapp8.png");
					attackImage[8] = new Image("res/animations/grapplingHook/grapp9.png");
					attackImage[9] = new Image("res/animations/grapplingHook/grapp10.png");
					attackImage[10] = new Image("res/animations/grapplingHook/grapp11.png");
					attackImage[11] = new Image("res/animations/grapplingHook/grapp12.png");
					attackImage[12] = new Image("res/animations/grapplingHook/grapp13.png");
					attackImage[13] = new Image("res/animations/grapplingHook/grapp14.png");
					attackImage[14] = new Image("res/animations/grapplingHook/grapp15.png");
					attackImage[15] = new Image("res/animations/grapplingHook/grapp16.png");
					attackImage[16] = new Image("res/animations/grapplingHook/grapp17.png");
					attackImage[17] = new Image("res/animations/grapplingHook/grapp18.png");
					
					animation[0] = new Image("res/animations/grapplingHook/grapp18.png");
					animation[1] = new Image("res/animations/grapplingHook/grapp17.png");
					animation[2] = new Image("res/animations/grapplingHook/grapp16.png");
					animation[3] = new Image("res/animations/grapplingHook/grapp15.png");
					animation[4] = new Image("res/animations/grapplingHook/grapp14.png");
					animation[5] = new Image("res/animations/grapplingHook/grapp13.png");
					animation[6] = new Image("res/animations/grapplingHook/grapp12.png");
					animation[7] = new Image("res/animations/grapplingHook/grapp11.png");
					animation[8] = new Image("res/animations/grapplingHook/grapp10.png");
					animation[9] = new Image("res/animations/grapplingHook/grapp9.png");
					animation[10] = new Image("res/animations/grapplingHook/grapp8.png");
					animation[11] = new Image("res/animations/grapplingHook/grapp7.png");
					animation[12] = new Image("res/animations/grapplingHook/grapp6.png");
					animation[13] = new Image("res/animations/grapplingHook/grapp5.png");
					animation[14] = new Image("res/animations/grapplingHook/grapp4.png");
					animation[15] = new Image("res/animations/grapplingHook/grapp3.png");
					animation[16] = new Image("res/animations/grapplingHook/grapp2.png");
					animation[17] = new Image("res/animations/grapplingHook/grapp1.png");
					
					skillBar[0] = new Image("res/skillIcons/grapplinghook.png");
					skillBar[1] = new Image("res/skillIcons/grapplinghook_active.png");
					skillBar[2] = new Image("res/skillIcons/grapplinghook_disabled.png");
				} catch (SlickException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				super.setGrapplingHook();
				super.setImage(attackImage, 1000);
				super.setEndState(animation, 1000, 400);
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
