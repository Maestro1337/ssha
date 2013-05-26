package Model.Skills.Wizard;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skill;
import Model.StatusEffects.StatusEffectMovement;

public class SkillBlizzard extends Skill {
	public SkillBlizzard(){
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE
		super("Blizzard", 11000, 400, 100.4, 3, 60, 150,"Blizzard:\nA A blizzard that slows \nand deals damage over time\n" +
				"Level 1: 15 damage\n" +
				"Level 2: 25 damage\n" +
				"Level 3: 35 damage\n" +
				"Level 4: 45 damage");
		
		Image attackImage = null;
		Image[] animation = new Image[20];
		Image[] skillBar = new Image[3];
		
		super.setOffensiveStatusEffectShell(new StatusEffectMovement(this, -0.7, 1, 0),true);
		
		try {
			attackImage = new Image("res/animations/blizzard/Blizzard1.png");
			
			animation[0] = new Image("res/animations/blizzard/Blizzard1.png");
			animation[1] = new Image("res/animations/blizzard/Blizzard2.png");
			animation[2] = new Image("res/animations/blizzard/Blizzard3.png");
			animation[3] = new Image("res/animations/blizzard/Blizzard4.png");
			animation[4] = new Image("res/animations/blizzard/Blizzard5.png");
			animation[5] = new Image("res/animations/blizzard/Blizzard6.png");
			animation[6] = new Image("res/animations/blizzard/Blizzard7.png");
			animation[7] = new Image("res/animations/blizzard/Blizzard8.png");
			animation[8] = new Image("res/animations/blizzard/Blizzard9.png");
			animation[9] = new Image("res/animations/blizzard/Blizzard10.png");
			animation[10] = new Image("res/animations/blizzard/Blizzard11.png");
			animation[11] = new Image("res/animations/blizzard/Blizzard12.png");
			animation[12] = new Image("res/animations/blizzard/Blizzard13.png");
			animation[13] = new Image("res/animations/blizzard/Blizzard14.png");
			animation[14] = new Image("res/animations/blizzard/Blizzard15.png");
			animation[15] = new Image("res/animations/blizzard/Blizzard16.png");
			animation[16] = new Image("res/animations/blizzard/Blizzard17.png");
			animation[17] = new Image("res/animations/blizzard/Blizzard18.png");
			animation[18] = new Image("res/animations/blizzard/Blizzard19.png");
			animation[19] = new Image("res/animations/blizzard/Blizzard20.png");
			
			
			
			skillBar[0] = new Image("res/skillIcons/blizzard.png");
			skillBar[1] = new Image("res/skillIcons/blizzard_active.png");
			skillBar[2] = new Image("res/skillIcons/blizzard_disabled.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.setImage(attackImage);
		super.setEndState(animation, 2000, 1010);
		super.setSkillBarImages(skillBar);
	}

	private int lvl2 = 200;
	private int lvl3 = 250;
	private int lvl4 = 300;
	
	@Override
	public void upgradeSkill() {
		if(super.getCurrentLvl() < 4){
			super.incCurrentLvl();
			
			switch(super.getCurrentLvl()){
			case 2:
				super.setOffensiveStatusEffectShell(new StatusEffectMovement(this, -0.7, 1, 0),true);
				super.setDamage(lvl2);
				break;
			case 3:
				super.setOffensiveStatusEffectShell(new StatusEffectMovement(this, -0.8, 1, 0),true);
				super.setDamage(lvl3);
				break;
			case 4:
				super.setOffensiveStatusEffectShell(new StatusEffectMovement(this, -0.9, 1, 0),true);
				super.setDamage(lvl4);
				break;
			}
		}
	}
}
