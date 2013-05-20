package Model.Skills.Hunter;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skills.Skill;

public class SkillStealth extends Skill {
	public SkillStealth(){
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE
		super("Stealth", 11000, 400, 0.4, 3, 50, 150,"Stealth: \nFades into the shadows \nrendering the hunter invisible.\n" +
				"Level 1: 5 sec\n" +
				"Level 2: 10 sec\n" +
				"Level 3: 15 sec\n" +
				"Level 4: 20 sec");
		
		Image[] attackImages = new Image[3];
		Image[] animation = new Image[3];
		Image[] skillBar = new Image[3];
		
		try {
			attackImages[0] = new Image("res/animations/stealth/hunter_stealthstand.png");
			attackImages[1] = new Image("res/animations/stealth/hunter_stealthwalk1.png");
			attackImages[2] = new Image("res/animations/stealth/hunter_stealthwalk2.png");
			
			animation[0] = new Image("res/animations/stealth/hunter_stealthstand.png");
			animation[1] = new Image("res/animations/stealth/hunter_stealthwalk1.png");
			animation[2] = new Image("res/animations/stealth/hunter_stealthwalk2.png");
			
			skillBar[0] = new Image("res/skillIcons/stealth.png");
			skillBar[1] = new Image("res/skillIcons/stealth_active.png");
			skillBar[2] = new Image("res/skillIcons/stealth_disabled.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		super.setImage(attackImages, 200);
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
