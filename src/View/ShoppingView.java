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
import Control.PlayerController;
import Model.Player;
import Model.Skills.Skill;
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
import Model.Skills.Wizard.SkillUnstablemagic;


public class ShoppingView extends BasicGameState {
	
	Skill[] offSkills = new Skill[3];
	Skill[] defSkills = new Skill[3];
	Skill[] mobSkills = new Skill[3];
	Skill[] chosenSkills = new Skill[5];
	
	Image playButton;
	//Wizard skillicons
	Image firstOffSkill;
	Image secondOffSkill;
	Image thirdOffSkill; 
	
	Image firstDefSkill;
	Image secondDefSkill;
	Image thirdDefSkill;
	
	Image firstMobSkill;
	Image secondMobSkill;
	Image thirdMobSkill;
	
	Image wandAttackSkill;
	String wandattackDesc;

	public ShoppingView (int state){
		
	}
	
	public int getID(){
		return 4;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)throws SlickException {
		
		
		
		switch(GlobalClassSelector.getController().getPlayer().getType()){
			case "Wizard":
				//Init wizard offensive, defensive and mobility skillists
				offSkills[0] = new SkillFireball();
				offSkills[1] = new SkillFirestorm();
				offSkills[2] = new SkillFlamewave();
				
				defSkills[0] = new SkillIroncloak();
				defSkills[1] = new SkillAbsorb();
				defSkills[2] = new SkillIceblock();
				
				mobSkills[0] = new SkillUnstablemagic();
				mobSkills[1] = new SkillBlizzard();
				mobSkills[2] = new SkillTeleport();
			break;
			case "Hunter":
				//Init Hunter offensive, defensive and mobility skillists
				offSkills[0] = new SkillFlamingArrow();
				offSkills[1] = new SkillGuidedArrow();
				offSkills[2] = new SkillArrowFlurry();
				
				defSkills[0] = new SkillPassiveDodge();
				defSkills[1] = new SkillLifestealingArrows();
				defSkills[2] = new SkillStealth();
				
				mobSkills[0] = new SkillSprint();
				mobSkills[1] = new SkillCripplingShot();
				mobSkills[2] = new SkillBarrelRoll();
			break;
			case "Warrior":
				//Init warrior offensive, defensive and mobility skillists
				offSkills[0] = new SkillThrowingAxe();
				offSkills[1] = new SkillWarstomp();
				offSkills[2] = new SkillAdrenaline();
				
				defSkills[0] = new SkillImprovedArmor();
				defSkills[1] = new SkillShieldStance();
				defSkills[2] = new SkillFirstAid();
				
				mobSkills[0] = new SkillIncreasedMovement();
				mobSkills[1] = new SkillGrapplingHook();
				mobSkills[2] = new SkillLeapAttack();
			break;
		}
		playButton = new Image("res/playButtons.png");
		//Init wizard skillicons
		//Offensive
		firstOffSkill = offSkills[0].getSkillBarImage();
		secondOffSkill = offSkills[1].getSkillBarImage();
		thirdOffSkill = offSkills[2].getSkillBarImage();
		//Defensive
		firstDefSkill = defSkills[0].getSkillBarImage();
		secondDefSkill = defSkills[1].getSkillBarImage();
		thirdDefSkill = defSkills[2].getSkillBarImage();
		//Mobility
		firstMobSkill = mobSkills[0].getSkillBarImage();
		secondMobSkill = mobSkills[1].getSkillBarImage();
		thirdMobSkill = mobSkills[2].getSkillBarImage();
		//Basic
		wandAttackSkill = new Image("res/skillIcons/WandAttack.jpg");
		wandattackDesc = "Wand attack";
	}
	public void enter(GameContainer container, StateBasedGame game)
	         throws SlickException {
	      // TODO Auto-generated method stub
	      super.enter(container, game);
	      chosenSkills = GlobalClassSelector.getController().getPlayer().getSkillList();

	   }
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)throws SlickException {
		g.setColor(Color.black);
		g.drawImage(playButton, 500, 650);
		// TODO Auto-generated method stub
		
		g.setColor(Color.white);

		g.drawString("1", 70, 200);
		g.drawImage(chosenSkills[0].getSkillBarImage(), 50, 225);
		g.drawString("2", 140, 200);
		g.drawImage(chosenSkills[1].getSkillBarImage(), 119, 225);
		g.drawString("3", 215, 200);
		g.drawImage(chosenSkills[2].getSkillBarImage(), 188, 225);
		g.drawString("4", 280, 202);
		g.drawImage(chosenSkills[3].getSkillBarImage(), 257, 225);
		g.drawString("5", 350, 200);
		g.drawImage(chosenSkills[4].getSkillBarImage(), 326, 225);
		
		
		g.drawString("Off", 50, 375);
		g.drawString("Def", 150, 375);
		g.drawString("Mob", 250, 375);
		
		
		//Offensive skills
		g.drawImage(firstOffSkill, 50, 400);
		g.drawImage(secondOffSkill, 50, 475);
		g.drawImage(thirdOffSkill, 50, 550);
			
		//Defensive skills
		g.drawImage(firstDefSkill, 150, 400);
		g.drawImage(secondDefSkill, 150, 475);
		g.drawImage(thirdDefSkill, 150, 550);
			
		//Mobility skills
		g.drawImage(firstMobSkill, 250, 400);
		g.drawImage(secondMobSkill, 250, 475);
		g.drawImage(thirdMobSkill, 250, 550);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException {
		
		int xPos = Mouse.getX();
		int yPos = 720 - Mouse.getY();
		
		Input input = gc.getInput();

		if((500<xPos && xPos<750) && (650<yPos && yPos<704)){
			playButton = new Image("res/playButton_hover.png");
			if(input.isMousePressed(0)){ // 0 = leftclick, 1 = rightclick
				playButton = new Image("res/playButton_pressed.png");
				sbg.enterState(1);
			}
		}else{
			playButton = new Image("res/playButtons.png");
		}
	}
}

