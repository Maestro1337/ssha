package Model.Skills.Wizard;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skills.Skill;

public class SkillAbsorb extends Skill{
	public SkillAbsorb(){
	//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE

		super("Absorb", 11000, 0, 0.4, 3, 25, 150,"Absorb (Passive):\nThe wizards gains an absorbing \n shield around him. \n" +
				"Level 1: 10 % of damage done, max 100\n" +
				"Level 2: 15 % of damage done, max 150\n" +
				"Level 3: 20 % of damage done, max 200\n" +
				"Level 4: 25 % of damage done, max 250");

		
		Image attackImage = null;
		Image[] animation = new Image[7];
		Image[] skillBar = new Image[3];
		
		try {
			attackImage = new Image("res/animations/explode/explode1.png");
			
			animation[0] = new Image("res/animations/Absorb/absorb1.png");
			animation[1] = new Image("res/animations/Absorb/absorb2.png");
			animation[2] = new Image("res/animations/Absorb/absorb3.png");
			animation[3] = new Image("res/animations/Absorb/absorb4.png");
			animation[4] = new Image("res/animations/Absorb/absorb5.png");
			animation[5] = new Image("res/animations/Absorb/absorb6.png");
			animation[6] = new Image("res/animations/Absorb/absorb7.png");
			
			skillBar[0] = new Image("res/skillIcons/absorb.png");
			skillBar[1] = new Image("res/skillIcons/absorb_active.png");
			skillBar[2] = new Image("res/skillIcons/absorb_disabled.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.setImage(attackImage);
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
