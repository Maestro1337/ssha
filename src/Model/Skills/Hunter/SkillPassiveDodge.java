package Model.Skills.Hunter;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skill;
import Model.StatusEffects.StatusEffectDodge;

public class SkillPassiveDodge extends Skill {
	public SkillPassiveDodge(){
		super("Passive Dodge", 11000, 0, 0.4, 3, 20, 0, "Dodge: \nGives the chance to avoid \nincoming damage.\n" +
				"Level 1: 5% chance\n" +
				"Level 2: 10% chance\n" +
				"Level 3: 15% chance\n" +
				"Level 4: 20% chance");
		
		Image attackImage = null;
		Image[] animation = new Image[7];
		Image[] skillBar = new Image[3];
		
		super.setPassive();
		super.setSelfAffectingStatusEffectShell(new StatusEffectDodge(this, 5, 0));
		
		try {
			attackImage = new Image("res/animations/explode/explode1.png");
			
			animation[0] = new Image("res/animations/explode/explode1.png");
			animation[1] = new Image("res/animations/explode/explode2.png");
			animation[2] = new Image("res/animations/explode/explode3.png");
			animation[3] = new Image("res/animations/explode/explode4.png");
			animation[4] = new Image("res/animations/explode/explode5.png");
			animation[5] = new Image("res/animations/explode/explode6.png");
			animation[6] = new Image("res/animations/explode/explode7.png");
			
			skillBar[0] = new Image("res/skillIcons/dodge.png");
			skillBar[1] = new Image("res/skillIcons/dodge_active.png");
			skillBar[2] = new Image("res/skillIcons/dodge_disabled.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		super.setImage(attackImage);
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
