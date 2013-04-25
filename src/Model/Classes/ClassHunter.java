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

public class ClassHunter extends Player {

	Image playerImage;
	Image firstStep;
	Image secondStep;
	static Skill[] chosenSkills = new Skill[5];

	public ClassHunter(String name, float x, float y) {
		super(name, x, y, 100);
		try {
			playerImage = new Image("res/hunter_stand.png");
			firstStep = new Image("res/hunter_walk1.png");
			secondStep = new Image("res/hunter_walk2.png");
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
