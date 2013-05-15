package Model.Skills.Wizard;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Skills.Skill;

public class SkillWandattack extends Skill {

	public SkillWandattack() {
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE
		super("Wand attack", 1000, 400, 0.4, 3, 0, 150, 300, 300, 300,"Wand attack \n" +
				"\nLevel 1: 15 damage\n" +
				"Level 2: 25 damage\n" +
				"Level 3: 35 damage\n" +
				"Level 4: 45 damage");
		
		Image attackImage = null;
		Image[] animation = new Image[6];
		Image[] skillBar = new Image[3];
		
		try {
			attackImage = new Image("res/animations/WandAttack/WandAttack.png");
			
			animation[0] = new Image("res/animations/WandAttack/Wandexplode1.png");
			animation[1] = new Image("res/animations/WandAttack/Wandexplode2.png");
			animation[2] = new Image("res/animations/WandAttack/Wandexplode3.png");
			animation[3] = new Image("res/animations/WandAttack/Wandexplode4.png");
			animation[4] = new Image("res/animations/WandAttack/Wandexplode5.png");
			animation[5] = new Image("res/animations/WandAttack/Wandexplode6.png");
			
			
			skillBar[0] = new Image("res/skillIcons/wandattack.png");
			skillBar[1] = new Image("res/skillIcons/wandattack_active.png");
			skillBar[2] = new Image("res/skillIcons/wandattack_disabled.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		super.setImage(attackImage);
		super.setEndState(animation, 200, 400);
		super.setSkillBarImages(skillBar);
	}

}
