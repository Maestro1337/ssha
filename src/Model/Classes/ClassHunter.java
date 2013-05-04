package Model.Classes;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Data.Player;
import Model.Skills.Skill;
import Model.Skills.Hunter.SkillSuperSlowTestSkill;
import Model.Skills.Warrior.SkillSlash;
import Model.Skills.Wizard.SkillFireball;
import Model.Skills.Wizard.SkillFirestorm;
import Model.Skills.Wizard.SkillIceNeedle;

public class ClassHunter extends Player {

	Image playerImage;
	Image firstStep;
	Image secondStep;
	static Skill[] chosenSkills = new Skill[5];

	public ClassHunter(String name, float x, float y) {
		super(name, "Hunter", x, y, 1000, 2, 0.4);
		try {
			playerImage = new Image("res/animations/hunter_stand.png");
			firstStep = new Image("res/animations/hunter_walk1.png");
			secondStep = new Image("res/animations/hunter_walk2.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		chosenSkills[0] = new SkillSuperSlowTestSkill();
		chosenSkills[1] = new SkillSuperSlowTestSkill();
		chosenSkills[2] = new SkillSuperSlowTestSkill();
		chosenSkills[3] = new SkillSuperSlowTestSkill();
		chosenSkills[4] = new SkillSuperSlowTestSkill();

		super.setImages(playerImage, firstStep, secondStep);
		super.setSkillList(chosenSkills);
	}

}
