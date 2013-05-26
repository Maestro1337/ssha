package Model.Skills.Wizard;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skill;
import Model.StatusEffects.StatusEffectArmor;

public class SkillIroncloak extends Skill {

	public SkillIroncloak() {
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE
		super("Iron cloak", 11000, 400, 0.4, 3, 20, 150,"Iron cloak: \n" +
				"\nLevel 1: 15 damage\n" +
				"Level 2: 25 damage\n" +
				"Level 3: 35 damage\n" +
				"Level 4: 45 damage");
		
		Image attackImage = null;
		Image[] animation = new Image[7];
		Image[] skillBar = new Image[3];
		
		super.setPassive();
		super.setSelfAffectingStatusEffectShell(new StatusEffectArmor(this, 0.25, 0));
		
		try {
			attackImage = new Image("res/animations/explode/explode1.png");
			
			animation[0] = new Image("res/animations/explode/explode1.png");
			animation[1] = new Image("res/animations/explode/explode2.png");
			animation[2] = new Image("res/animations/explode/explode3.png");
			animation[3] = new Image("res/animations/explode/explode4.png");
			animation[4] = new Image("res/animations/explode/explode5.png");
			animation[5] = new Image("res/animations/explode/explode6.png");
			animation[6] = new Image("res/animations/explode/explode7.png");
			
			skillBar[0] = new Image("res/skillIcons/ironcloak.png");
			skillBar[1] = new Image("res/skillIcons/ironcloak_active.png");
			skillBar[2] = new Image("res/skillIcons/ironcloak_disabled.png");
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
				super.setSelfAffectingStatusEffectShell(new StatusEffectArmor(this, 0.5, 0));
				super.setDamage(lvl2);
				break;
			case 3:
				super.setSelfAffectingStatusEffectShell(new StatusEffectArmor(this, 0.75, 0));
				super.setDamage(lvl3);
				break;
			case 4:
				super.setSelfAffectingStatusEffectShell(new StatusEffectArmor(this, 1, 0));
				super.setDamage(lvl4);
				break;
			}
		}
	}

}
