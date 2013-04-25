package Model.Classes;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Player;
import Model.Skills.Skill;
import Model.Skills.SkillFireball;
import Model.Skills.SkillFirestorm;
import Model.Skills.SkillIceNeedle;
import Model.Skills.SkillSlash;
import Model.Skills.SkillSuperSlowTestSkill;

public class ClassWizard extends Player {

	Image playerImage;
	Image firstStep;
	Image secondStep;
	static Skill[] chosenSkills = new Skill[5];

	public ClassWizard(String name, float x, float y) {
		super(name, x, y, 90);
		try {
			playerImage = new Image("res/mage_stand.png");
			firstStep = new Image("res/mage_walk1.png");
			secondStep = new Image("res/mage_walk2.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		chosenSkills[0] = new SkillSlash();
		chosenSkills[1] = new SkillFireball();
		chosenSkills[2] = new SkillFirestorm();
		chosenSkills[3] = new SkillIceNeedle();
		chosenSkills[4] = new SkillSuperSlowTestSkill();

		super.setImages(playerImage, firstStep, secondStep);
		super.setSkillList(chosenSkills);
	}

}
