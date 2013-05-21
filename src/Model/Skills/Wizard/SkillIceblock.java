package Model.Skills.Wizard;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skills.Skill;

public class SkillIceblock extends Skill {

	public SkillIceblock() {
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, String skillDescription
		super("Ice block", 11000, 0, 0, 20, 50, 0,"Ice block:\nEncapsule yourself inside \nan iceblock to escape from \nharms way.\n" +
				"\nLevel 1: 1 second\n" +
				"Level 2: 2 second\n" +
				"Level 3: 3 second\n" +
				"Level 4: 4 second");
		
		Image attackImage = null;
		Image[] animation = new Image[1];
		Image[] skillBar = new Image[3];
		
		try {
			attackImage = new Image("res/animations/iceblock/Iceblock.png");
			
			animation[0] = new Image("res/animations/iceblock/Iceblock.png");
			
			skillBar[0] = new Image("res/skillIcons/iceblock.png");
			skillBar[1] = new Image("res/skillIcons/iceblock_active.png");
			skillBar[2] = new Image("res/skillIcons/iceblock_disabled.png");
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
