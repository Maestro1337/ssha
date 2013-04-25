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

public class ClassWarrior extends Player {
	
	Image playerImage;
	Image firstStep;
	Image secondStep;
	static Skill[] chosenSkills = new Skill[5];

	public ClassWarrior(String name, float x, float y) {
		super(name, x, y, 150);
		try {
			playerImage = new Image("res/warrior_stand.png");
			firstStep = new Image("res/warrior_walk1.png");
			secondStep = new Image("res/warrior_walk2.png");
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