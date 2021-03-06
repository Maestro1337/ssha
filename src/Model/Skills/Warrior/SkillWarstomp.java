package Model.Skills.Warrior;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skill;
import Model.StatusEffects.StatusEffectStun;

public class SkillWarstomp extends Skill {
	public SkillWarstomp(){
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE
		super("Warstomp", 11000, 0, 0.4, 3, 60, 150,"Warstomp \nStunns and deals AoE damage\n" +
				"Level 1: 150 damage\n" +
				"Level 2: 200 damage\n" +
				"Level 3: 250 damage\n" +
				"Level 4: 300 damage");
		
		Image attackImage = null;
		Image[] animation = new Image[1];
		Image[] skillBar = new Image[3];

		
		super.setOffensiveStatusEffectShell(new StatusEffectStun(this, 2),false);
		
		try {
			attackImage = new Image("res/animations/warstomp/warstompGround.png");
			
			animation[0] = new Image("res/animations/warstomp/warstompGround.png");
			
			skillBar[0] = new Image("res/skillIcons/warstomp.png");
			skillBar[1] = new Image("res/skillIcons/warstomp_active.png");
			skillBar[2] = new Image("res/skillIcons/warstomp_disabled.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		super.setImage(attackImage);
		super.setEndState(animation, 5000, 5000);
		super.setSkillBarImages(skillBar);
	}

	private int lvl2 = 200;
	private int lvl3 = 250;
	private int lvl4 = 350;
	
	@Override
	public void upgradeSkill() {
		if(super.getCurrentLvl() < 4){
			super.incCurrentLvl();
			
			switch(super.getCurrentLvl()){
			case 2:
				super.setOffensiveStatusEffectShell(new StatusEffectStun(this, 2),false);
				super.setDamage(lvl2);
				break;
			case 3:
				super.setOffensiveStatusEffectShell(new StatusEffectStun(this, 3),false);
				super.setDamage(lvl3);
				break;
			case 4:
				super.setOffensiveStatusEffectShell(new StatusEffectStun(this, 3),false);
				super.setDamage(lvl4);
				break;
			}
		}
	}
}
