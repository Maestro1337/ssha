package Model.Classes;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.Player;
import Model.Skill;
import Model.Skills.Hunter.*;

public class ClassHunter extends Player {

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

	public ClassHunter(String name, String ctrlType, float x, float y, int index) {
		//name, ctrlType, String class, x, y , HP, movespeed , armor, index
		super(name, ctrlType, "Hunter", x, y, 1337, 2/*0.68*/, 0.15, index);
		try {
			playerImage = new Image("res/animations/characters/hunter_stand.png");
			portraitImage = new Image("res/classImages/hunter_portrait.png");
			portraitImageMini = new Image("res/classImages/hunter_portraitmini.png");
			framedImage = new Image("res/classImages/Portrait_Hunter.png");
			firstStep = new Image("res/animations/characters/hunter_walk1.png");
			secondStep = new Image("res/animations/characters/hunter_walk2.png");
			
			changedModelWalkImages[0] = new Image("res/animations/stealth/hunter_stealthwalk1.png");
			changedModelWalkImages[1] = new Image("res/animations/stealth/hunter_stealthwalk2.png");
					
			changedModelStandImages[0] = new Image("res/animations/stealth/hunter_stealthstand.png");
			
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		chosenSkills[0] = new SkillArrow();
		super.addSkillAsOwned(chosenSkills[0]);

		super.setImages(playerImage, firstStep, secondStep);
		super.setPortraits(framedImage, portraitImage, portraitImageMini);
		super.setChangedModelImages(changedModelWalkImages, changedModelStandImages);
		super.setSkillList(chosenSkills);
		super.setPassiveSkillList(passiveSkills);
	}
}
