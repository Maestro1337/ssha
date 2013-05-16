package View;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Control.GlobalClassSelector;
import Model.Skills.Skill;
import Model.Skills.Hunter.SkillArrow;
import Model.Skills.Hunter.SkillArrowFlurry;
import Model.Skills.Hunter.SkillBarrelRoll;
import Model.Skills.Hunter.SkillCripplingShot;
import Model.Skills.Hunter.SkillFlamingArrow;
import Model.Skills.Hunter.SkillGuidedArrow;
import Model.Skills.Hunter.SkillLifestealingArrows;
import Model.Skills.Hunter.SkillPassiveDodge;
import Model.Skills.Hunter.SkillSprint;
import Model.Skills.Hunter.SkillStealth;
import Model.Skills.Warrior.SkillAdrenaline;
import Model.Skills.Warrior.SkillFirstAid;
import Model.Skills.Warrior.SkillGrapplingHook;
import Model.Skills.Warrior.SkillImprovedArmor;
import Model.Skills.Warrior.SkillIncreasedMovement;
import Model.Skills.Warrior.SkillLeapAttack;
import Model.Skills.Warrior.SkillShieldStance;
import Model.Skills.Warrior.SkillSlash;
import Model.Skills.Warrior.SkillThrowingAxe;
import Model.Skills.Warrior.SkillWarstomp;
import Model.Skills.Wizard.SkillAbsorb;
import Model.Skills.Wizard.SkillBlizzard;
import Model.Skills.Wizard.SkillFireball;
import Model.Skills.Wizard.SkillFirestorm;
import Model.Skills.Wizard.SkillFlamewave;
import Model.Skills.Wizard.SkillIceblock;
import Model.Skills.Wizard.SkillIroncloak;
import Model.Skills.Wizard.SkillTeleport;
import Model.Skills.Wizard.SkillUnstableMagic;
import Model.Skills.Wizard.SkillWandattack;

public class OptionsView extends BasicGameState {
	
	int mapSelected=1;
	int difficultySelected=2;
	
	Image background;
	Image readyButton;
	
	Image easyButton;
	Image normalButton;
	Image hardButton;
	
	Image Map1;
	Image Map2;
	Image Map3;

	public OptionsView (int state){
			
	}
	
	public int getID(){
		return 5;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)throws SlickException {
		
		background = new Image("res/miscImages/OptionsBackground.png");
		
		easyButton = new Image("res/buttons/Easy_1.png");
		normalButton = new Image("res/buttons/Normal_3.png");
		hardButton = new Image("res/buttons/Hard_1.png");
		
		readyButton = new Image ("res/buttons/Ready.png");
		
	}

	public void enter(GameContainer container, StateBasedGame game){
	     

	   }
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)throws SlickException {
		g.drawImage(background, 0, 0);
		
		g.drawImage(readyButton, 1120, 670);
		

	
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException {
		
		int xPos = Mouse.getX();
		int yPos = 720 - Mouse.getY();
		
		Input input = gc.getInput();
		
		
		if((1120<xPos && xPos<1240) && (670<yPos && yPos<715)){
			readyButton = new Image("res/buttons/ReadyOver.png");
			if(input.isMousePressed(0)){ // 0 = leftclick, 1 = rightclick
				readyButton = new Image("res/buttons/Ready.png");
				sbg.enterState(4);
			}		
		}
	}
}