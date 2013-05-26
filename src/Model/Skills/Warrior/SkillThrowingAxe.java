package Model.Skills.Warrior;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skill;

public class SkillThrowingAxe extends Skill {
	public SkillThrowingAxe(){
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE
		super("Throwing axe", 3000, 800, 2.2, 6, 10, 150,"Throwing axe \n" +
				"Level 1: 15 damage\n" +
				"Level 2: 25 damage\n" +
				"Level 3: 35 damage\n" +
				"Level 4: 45 damage");
		
		Image[] animation = new Image[18];
		Image[] skillBar = new Image[3];
		
		try {
			
			animation[0] = new Image("res/animations/throwingAxe/axe1.png");
			animation[1] = new Image("res/animations/throwingAxe/axe2.png");
			animation[2] = new Image("res/animations/throwingAxe/axe3.png");
			animation[3] = new Image("res/animations/throwingAxe/axe4.png");
			animation[4] = new Image("res/animations/throwingAxe/axe5.png");
			animation[5] = new Image("res/animations/throwingAxe/axe6.png");
			animation[6] = new Image("res/animations/throwingAxe/axe7.png");
			animation[7] = new Image("res/animations/throwingAxe/axe8.png");
			animation[8] = new Image("res/animations/throwingAxe/axe9.png");
			animation[9] = new Image("res/animations/throwingAxe/axe10.png");
			animation[10] = new Image("res/animations/throwingAxe/axe11.png");
			animation[11] = new Image("res/animations/throwingAxe/axe12.png");
			animation[12] = new Image("res/animations/throwingAxe/axe13.png");
			animation[13] = new Image("res/animations/throwingAxe/axe14.png");
			animation[14] = new Image("res/animations/throwingAxe/axe15.png");
			animation[15] = new Image("res/animations/throwingAxe/axe16.png");
			animation[16] = new Image("res/animations/throwingAxe/axe17.png");
			animation[17] = new Image("res/animations/throwingAxe/axe18.png");

			skillBar[0] = new Image("res/skillIcons/throwingaxe.png");
			skillBar[1] = new Image("res/skillIcons/throwingaxe_active.png");
			skillBar[2] = new Image("res/skillIcons/throwingaxe_disabled.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.setImage(animation, 200);
		super.setSkillBarImages(skillBar);
	}

	private int lvl2 = 195;
	private int lvl3 = 240;
	private int lvl4 = 305;
	
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
