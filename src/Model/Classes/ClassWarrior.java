package Model.Classes;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Player;
import Model.Skills.Skill;
import Model.Skills.Hunter.SkillArrow;
import Model.Skills.Warrior.SkillAdrenaline;
import Model.Skills.Warrior.SkillFirstAid;
import Model.Skills.Warrior.SkillLeapAttack;
import Model.Skills.Warrior.SkillShieldStance;
import Model.Skills.Warrior.SkillSlash;
import Model.Skills.Warrior.SkillThrowingAxe;
import Model.Skills.Warrior.SkillWarstomp;
import Model.Skills.Wizard.SkillFireball;
import Model.Skills.Wizard.SkillFirestorm;
import Model.Skills.Wizard.SkillIceNeedle;

public class ClassWarrior extends Player {
	
	Image playerImage;
	Image firstStep;
	Image secondStep;
	static Skill[] chosenSkills = new Skill[5];
	Image[] changedModelWalkImages = new Image[2];
	Image[] changedModelStandImages = new Image[1];

	public ClassWarrior(String name, String ctrlType, float x, float y, int index) {
		super(name, ctrlType, "Warrior", x, y, 200, 0.7, 0.7, index);
		try {
			playerImage = new Image("res/animations/warrior_stand.png");
			firstStep = new Image("res/animations/warrior_walk1.png");
			secondStep = new Image("res/animations/warrior_walk2.png");
			
			changedModelWalkImages[0] = new Image("res/animations/Shieldstance/Shielded_warrior_walk1.png");
			changedModelWalkImages[1] = new Image("res/animations/Shieldstance/Shielded_warrior_walk2.png");

					
			changedModelStandImages[0] = new Image("res/animations/UnstableMagic/UMmage_stand1.png");

			
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		chosenSkills[0] = new SkillSlash();
		chosenSkills[1] = new SkillThrowingAxe();
		chosenSkills[2] = new SkillFirstAid();
		chosenSkills[3] = new SkillShieldStance();
		chosenSkills[4] = new SkillLeapAttack();

		super.setImages(playerImage, firstStep, secondStep);
		super.setChangedModelImages(changedModelWalkImages, changedModelStandImages);
		super.setSkillList(chosenSkills);
	}
}