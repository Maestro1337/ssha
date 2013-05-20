package Model.Skills.Wizard;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skills.Skill;

public class SkillFlamewave extends Skill{

	public SkillFlamewave() {
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE
		super("Flamewave", 11000, 400, 0.4, 3, 50, 150,"Flame wave:\nA majestic shot of flames that \ntakes the form of a phoenix \nwhile scorching your enemies.\n" +
				"\nLevel 1: 15 damage\n" +
				"Level 2: 25 damage\n" +
				"Level 3: 35 damage\n" +
				"Level 4: 45 damage");
		
		Image[] attackImages = new Image[2];
		Image[] animation = new Image[2];
		Image[] skillBar = new Image[3];
		
		try {
			attackImages[0] = new Image("res/animations/Flamewave/Flamewave1.png");
			attackImages[1] = new Image("res/animations/Flamewave/Flamewave2.png");
			
			animation[0] = new Image("res/animations/Flamewave/Flamewave1.png");
			animation[1] = new Image("res/animations/Flamewave/Flamewave2.png");
			
			
			skillBar[0] = new Image("res/skillIcons/flamewave.png");
			skillBar[1] = new Image("res/skillIcons/flamewave_active.png");
			skillBar[2] = new Image("res/skillIcons/flamewave_disabled.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		super.setImage(attackImages,200);
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
