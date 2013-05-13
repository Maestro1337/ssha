package Model.Classes;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Player;
import Model.Skills.Skill;
import Model.Skills.Hunter.*;

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
		chosenSkills[0] = new SkillArrowFlurry();
		chosenSkills[1] = new SkillBarrelRoll();
		chosenSkills[2] = new SkillCripplingShot();
		chosenSkills[3] = new SkillArrow();
		chosenSkills[4] = new SkillArrow();

		super.setImages(playerImage, firstStep, secondStep);
		super.setSkillList(chosenSkills);
	}

}
