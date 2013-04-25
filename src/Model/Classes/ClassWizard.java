package Model.Classes;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Player;
import Model.Skills.Skill;
import Model.Skills.Hunter.SkillSuperSlowTestSkill;
import Model.Skills.Warrior.SkillSlash;
import Model.Skills.Wizard.SkillFireball;
import Model.Skills.Wizard.SkillFirestorm;
import Model.Skills.Wizard.SkillIceNeedle;

public class ClassWizard extends Player {

	Image playerImage;
	Image firstStep;
	Image secondStep;
	static Skill[] chosenSkills = new Skill[5];

	public ClassWizard(String name, float x, float y) {
		super(name, x, y, 900, 0.8, 0.2);
		try {
			playerImage = new Image("res/mage_stand.png");
			firstStep = new Image("res/mage_walk1.png");
			secondStep = new Image("res/mage_walk2.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		chosenSkills[0] = new SkillFireball();
		chosenSkills[1] = new SkillFirestorm();
		chosenSkills[2] = new SkillIceNeedle();
		chosenSkills[3] = new SkillFireball();
		chosenSkills[4] = new SkillFirestorm();

		super.setImages(playerImage, firstStep, secondStep);
		super.setSkillList(chosenSkills);
	}

}
