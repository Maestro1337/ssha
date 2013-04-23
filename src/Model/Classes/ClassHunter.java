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
	private Skill[] chosenSkills;

	public ClassHunter(String name, float x, float y) {
		super(name, x, y);
		try {
			playerImage = new Image("res/hunter_stand.png");
			firstStep = new Image("res/hunter_walk1.png");
			secondStep = new Image("res/hunter_walk2.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		chosenSkills[0] = new SkillSlash();
		chosenSkills[1] = new SkillFireball();
		chosenSkills[2] = new SkillFirestorm();
		chosenSkills[3] = new SkillIceNeedle();
		chosenSkills[4] = new SkillSuperSlowTestSkill();

		super.setImage(playerImage);
		super.setSkillList(chosenSkills);
	}

}
