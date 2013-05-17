package Model.Classes;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Player;
import Model.Skills.Skill;
import Model.Skills.Warrior.*;

public class ClassWarrior extends Player {
	
	Image playerImage;
	Image firstStep;
	Image secondStep;
	static Skill[] chosenSkills = new Skill[5];
	ArrayList<Skill> passiveSkills = new ArrayList<Skill>();
	Image[] changedModelWalkImages = new Image[2];
	Image[] changedModelStandImages = new Image[1];

	public ClassWarrior(String name, String ctrlType, float x, float y, int index) {
		super(name, ctrlType, "Warrior", x, y, 200, 1, 0.7, index);
		try {
			playerImage = new Image("res/animations/characters/warrior_stand.png");
			firstStep = new Image("res/animations/characters/warrior_walk1.png");
			secondStep = new Image("res/animations/characters/warrior_walk2.png");
			
			changedModelWalkImages[0] = new Image("res/animations/shieldstance/Shielded_warrior_walk1.png");
			changedModelWalkImages[1] = new Image("res/animations/shieldstance/Shielded_warrior_walk2.png");

					
			changedModelStandImages[0] = new Image("res/animations/shieldstance/Shielded_warrior_stand1.png");

			
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
		super.setPassiveSkillList(passiveSkills);
	}
}