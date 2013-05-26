package Model.Skills.Wizard;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skill;
import Model.StatusEffects.StatusEffectUnstableMagic;

public class SkillUnstableMagic extends Skill {

	public SkillUnstableMagic() {
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE
		super("Unstable magic", 20000, 0, 0.4, 3, 20,0,"Unstable magic: \nTest your luck for increased or \ndecreased movementspeed" +
				"\nLevel 1: 15 damage\n" +
				"Level 2: 25 damage\n" +
				"Level 3: 35 damage\n" +
				"Level 4: 45 damage");
		
		Image attackImage = null;
		Image[] animation = new Image[7];
		Image[] skillBar = new Image[3];
		
		super.setSelfAffectingStatusEffectShell(new StatusEffectUnstableMagic(this, 20));
		
		try {
			attackImage = new Image("res/animations/explode/explode1.png");
			
			animation[0] = new Image("res/animations/explode/explode1.png");
			animation[1] = new Image("res/animations/explode/explode2.png");
			animation[2] = new Image("res/animations/explode/explode3.png");
			animation[3] = new Image("res/animations/explode/explode4.png");
			animation[4] = new Image("res/animations/explode/explode5.png");
			animation[5] = new Image("res/animations/explode/explode6.png");
			animation[6] = new Image("res/animations/explode/explode7.png");
			
			skillBar[0] = new Image("res/skillIcons/unstablemagic.png");
			skillBar[1] = new Image("res/skillIcons/unstablemagic_active.png");
			skillBar[2] = new Image("res/skillIcons/unstablemagic_disabled.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		super.setImage(attackImage);
		super.setEndState(animation, 200, 400);
		super.setSkillBarImages(skillBar);
	}

	private int lvl2 = 16000;
	private int lvl3 = 12000;
	private int lvl4 = 8000;
	
	@Override
	public void upgradeSkill() {
		if(super.getCurrentLvl() < 4){
			super.incCurrentLvl();
			
			switch(super.getCurrentLvl()){
			case 2:
				super.setCooldown(lvl2);
				super.setSelfAffectingStatusEffectShell(new StatusEffectUnstableMagic(this, 16));
				break;
			case 3:
				super.setCooldown(lvl3);
				super.setSelfAffectingStatusEffectShell(new StatusEffectUnstableMagic(this, 12));
				break;
			case 4:
				super.setCooldown(lvl4);
				super.setSelfAffectingStatusEffectShell(new StatusEffectUnstableMagic(this, 8));
				break;
			}
		}
	}
}
