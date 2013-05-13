package Model.Classes;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Player;
import Model.Skills.Skill;
import Model.Skills.Hunter.SkillArrow;
import Model.Skills.Warrior.SkillSlash;
import Model.Skills.Wizard.SkillFireball;
import Model.Skills.Wizard.SkillFirestorm;
import Model.Skills.Wizard.SkillIceNeedle;

public class ClassWarrior extends Player {
	
	Image playerImage;
	Image firstStep;
	Image secondStep;
	static Skill[] chosenSkills = new Skill[5];

	public ClassWarrior(String name, float x, float y, int index) {
		super(name, "Warrior", x, y, 200, 0.7, 0.7, index);
		try {
			playerImage = new Image("res/animations/warrior_stand.png");
			firstStep = new Image("res/animations/warrior_walk1.png");
			secondStep = new Image("res/animations/warrior_walk2.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		chosenSkills[0] = new SkillSlash();
		chosenSkills[1] = new SkillSlash();
		chosenSkills[2] = new SkillSlash();
		chosenSkills[3] = new SkillSlash();
		chosenSkills[4] = new SkillSlash();

		super.setImages(playerImage, firstStep, secondStep);
		super.setSkillList(chosenSkills);
	}
}