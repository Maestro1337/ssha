package Model.Skills.Hunter;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skills.Skill;
import Model.StatusEffects.*;

public class SkillBarrelRoll extends Skill {
	public SkillBarrelRoll(){
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE

		super("Barrel Roll", 4000, 150, 2.2, 3, 50, 0,"Barrel roll: \nRoll out of the fray... \nor into it.\n" +
				"Level 1: 15 sec cd\n" +
				"Level 2: 12 sec cd\n" +
				"Level 3: 8 sec cd\n" +
				"Level 4: 4 sec cd");
		
		Image[] attackImages = new Image[2];
		Image[] animation = new Image[9];
		Image[] skillBar = new Image[3];
		
		super.setSelfAffectingStatusEffectShell(new StatusEffectPreLeap(this));
		super.setSelfAffectingOnHitStatusEffectShell(new StatusEffectLeap(this));
		
		try {
			attackImages[0] = new Image("res/animations/characters/hunter_walk1.png");
			attackImages[1] = new Image("res/animations/characters/hunter_walk2.png");
			
			animation[0] = new Image("res/animations/barrelroll/roll1.png");
			animation[1] = new Image("res/animations/barrelroll/roll2.png");
			animation[2] = new Image("res/animations/barrelroll/roll3.png");
			animation[3] = new Image("res/animations/barrelroll/roll4.png");
			animation[4] = new Image("res/animations/barrelroll/roll5.png");
			animation[5] = new Image("res/animations/barrelroll/roll6.png");
			animation[6] = new Image("res/animations/barrelroll/roll7.png");
			animation[7] = new Image("res/animations/barrelroll/roll8.png");
			animation[8] = new Image("res/animations/barrelroll/roll9.png");
			
			skillBar[0] = new Image("res/skillIcons/barrelroll.png");
			skillBar[1] = new Image("res/skillIcons/barrelroll_active.png");
			skillBar[2] = new Image("res/skillIcons/barrelroll_disabled.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		super.setImage(animation, 1000);
		super.setEndState(attackImages, 20, 200);
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
