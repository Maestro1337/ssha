package Model.Skills.Wizard;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skill;
import Model.StatusEffects.StatusEffectArmor;

public class SkillAbsorb extends Skill{
	public SkillAbsorb(){
	//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE


		super("Absorb", 30000, 0, 0.4, 3, 25, 0,"Absorb:\nThe wizards gains an absorbing \nshield around him. \n" +
				"Level 1: 3 sec invulnerability\n" +
				"Level 2: 5 sec invulnerability\n" +
				"Level 3: 7 sec invulnerability\n" +
				"Level 4: 10 sec invulnerability");


		
		Image attackImage = null;
		Image[] animation = new Image[7];
		Image[] skillBar = new Image[3];
		super.setSelfAffectingStatusEffectShell(new StatusEffectArmor(this, 1, 3));
		
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
	
	@Override
	public void upgradeSkill() {
		if(super.getCurrentLvl() < 4){
			super.incCurrentLvl();
			
			switch(super.getCurrentLvl()){
			case 2:
				super.setSelfAffectingStatusEffectShell(new StatusEffectArmor(this, 1, 5));
				break;
			case 3:
				super.setSelfAffectingStatusEffectShell(new StatusEffectArmor(this, 1, 7));
				break;
			case 4:
				super.setSelfAffectingStatusEffectShell(new StatusEffectArmor(this, 1, 10));
				break;
			}
		}
	}
}
