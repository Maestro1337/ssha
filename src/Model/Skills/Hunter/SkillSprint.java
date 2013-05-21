package Model.Skills.Hunter;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skills.Skill;
import Model.StatusEffects.StatusEffectMovement;

public class SkillSprint extends Skill {
	public SkillSprint(){
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE
		super("Sprint", 11000, 0, 0.4, 3, 10, 0, "Sprint: \nIncreases movementspeed for a \nshort duration.\n" +
				"Level 1: 3 sec\n" +
				"Level 2: 5 sec\n" +
				"Level 3: 7 sec\n" +
				"Level 4: 9 sec");
		
		Image attackImage = null;
		Image[] animation = new Image[26];
		Image[] skillBar = new Image[3];
		
		super.setSelfAffectingStatusEffectShell(new StatusEffectMovement(this, 2, 2 , 0));
		
		try {
			attackImage = new Image("res/animations/explode/explode1.png");
			
			animation[0] = new Image("res/animations/sprint/sprint1.png");
			animation[1] = new Image("res/animations/sprint/sprint2.png");
			animation[2] = new Image("res/animations/sprint/sprint3.png");
			animation[3] = new Image("res/animations/sprint/sprint4.png");
			animation[4] = new Image("res/animations/sprint/sprint5.png");
			animation[5] = new Image("res/animations/sprint/sprint6.png");
			animation[6] = new Image("res/animations/sprint/sprint7.png");
			animation[7] = new Image("res/animations/sprint/sprint8.png");
			animation[8] = new Image("res/animations/sprint/sprint9.png");
			animation[9] = new Image("res/animations/sprint/sprint10.png");
			animation[10] = new Image("res/animations/sprint/sprint11.png");
			animation[11] = new Image("res/animations/sprint/sprint12.png");
			animation[12] = new Image("res/animations/sprint/sprint13.png");
			animation[13] = new Image("res/animations/sprint/sprint14.png");
			animation[14] = new Image("res/animations/sprint/sprint15.png");
			animation[15] = new Image("res/animations/sprint/sprint16.png");
			animation[16] = new Image("res/animations/sprint/sprint17.png");
			animation[17] = new Image("res/animations/sprint/sprint18.png");
			animation[18] = new Image("res/animations/sprint/sprint19.png");
			animation[19] = new Image("res/animations/sprint/sprint20.png");
			animation[20] = new Image("res/animations/sprint/sprint21.png");
			animation[21] = new Image("res/animations/sprint/sprint22.png");
			animation[22] = new Image("res/animations/sprint/sprint23.png");
			animation[23] = new Image("res/animations/sprint/sprint24.png");
			animation[24] = new Image("res/animations/sprint/sprint25.png");
			animation[25] = new Image("res/animations/sprint/sprint26.png");
			
			skillBar[0] = new Image("res/skillIcons/sprint.png");
			skillBar[1] = new Image("res/skillIcons/sprint_active.png");
			skillBar[2] = new Image("res/skillIcons/sprint_disabled.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		super.setImage(attackImage);
		super.setEndState(animation, 200, 400);
		super.setSkillBarImages(skillBar);
	}

	private int lvl2 = 0;
	private int lvl3 = 0;
	private int lvl4 = 0;
	
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
