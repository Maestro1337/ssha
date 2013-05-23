package Model.Classes;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Player;
import Model.Skills.Skill;
import Model.Skills.Warrior.*;

public class ClassWarrior extends Player {
	
	Image playerImage;
	Image portraitImage;
	Image portraitImageMini;
	Image framedImage;
	Image firstStep;
	Image secondStep;

	static Skill[] chosenSkills = new Skill[5];
	ArrayList<Skill> passiveSkills = new ArrayList<Skill>();
	Image[] changedModelWalkImages = new Image[2];
	Image[] changedModelStandImages = new Image[1];

	public ClassWarrior(String name, String ctrlType, float x, float y, int index) {
		//name, ctrlType, String class, x, y , HP, movespeed , armor, index
		super(name, ctrlType, "Warrior", x, y, 1600, 0.7, 0.35, index);
		try {
			playerImage = new Image("res/animations/characters/warrior_stand.png");
			portraitImage = new Image("res/classImages/warrior_portrait.png");
			portraitImageMini = new Image("res/classImages/warrior_portraitmini.png");
			framedImage = new Image("res/classImages/Portrait_Warrior.png");
			firstStep = new Image("res/animations/characters/warrior_walk1.png");
			secondStep = new Image("res/animations/characters/warrior_walk2.png");
			
			changedModelWalkImages[0] = new Image("res/animations/Shieldstance/Shielded_warrior_walk1.png");
			changedModelWalkImages[1] = new Image("res/animations/Shieldstance/Shielded_warrior_walk2.png");
		
			changedModelStandImages[0] = new Image("res/animations/Shieldstance/Shielded_warrior_stand1.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		chosenSkills[0] = new SkillSlash();
		super.addSkillAsOwned(chosenSkills[0]);

		super.setImages(playerImage, firstStep, secondStep);
		super.setPortraits(framedImage, portraitImage, portraitImageMini);
		super.setChangedModelImages(changedModelWalkImages, changedModelStandImages);
		super.setSkillList(chosenSkills);
		super.setPassiveSkillList(passiveSkills);
	}
}
