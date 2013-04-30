package Model.Classes;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Player;
import Model.Skills.Skill;
import Model.Skills.Hunter.SkillSuperSlowTestSkill;
import Model.Skills.Warrior.SkillSlash;
import Model.Skills.Wizard.SkillAbsorb;
import Model.Skills.Wizard.SkillFireball;
import Model.Skills.Wizard.SkillFirestorm;
import Model.Skills.Wizard.SkillFlamewave;
import Model.Skills.Wizard.SkillIceNeedle;
import Model.Skills.Wizard.SkillIceblock;
import Model.Skills.Wizard.SkillIroncloak;
import Model.Skills.Wizard.*;

public class ClassWizard extends Player {

	Image playerImage;
	Image firstStep;
	Image secondStep;
	static Skill[] chosenSkills = new Skill[5];
	Skill[] offensiveSkills = new Skill[3];
	Skill[] defensiveSkills = new Skill[3];
	Skill[] mobilitySkills = new Skill[3];

	public ClassWizard(String name, float x, float y) {
		super(name, "Wizard", x, y, 900, 0.8, 0.2);
		try {
			playerImage = new Image("res/animations/mage_stand.png");
			firstStep = new Image("res/animations/mage_walk1.png");
			secondStep = new Image("res/animations/mage_walk2.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		chosenSkills[0] = new SkillFireball();
		chosenSkills[1] = new SkillFirestorm();
		chosenSkills[2] = new SkillIceNeedle();
		chosenSkills[3] = new SkillFireball();
		chosenSkills[4] = new SkillTeleport();
		
		offensiveSkills[0] = new SkillFireball();
		offensiveSkills[1] = new SkillFirestorm();
		offensiveSkills[2] = new SkillFlamewave();
		
		defensiveSkills[0] = new SkillIroncloak();
		defensiveSkills[1] = new SkillAbsorb();
		defensiveSkills[2] = new SkillIceblock();

		super.setImages(playerImage, firstStep, secondStep);
		super.setSkillList(chosenSkills);
	}
}
