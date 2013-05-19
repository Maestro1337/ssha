package View;

//import java.awt.Image;
import java.awt.Font;
import java.awt.font.*;
import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.UnicodeFont;





import Control.GlobalClassSelector;
import Model.Player;
import Model.PlayerModel;
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



public class ShoppingView extends BasicGameState {
	
	Player activePlayer;
	ArrayList<Skill> ownedSkillList;
	
	String buyString = " ";
	
	String classtype="Hunter";
	
	Image skillDescBg;
	String costText;
	
	boolean showingSkillDescription = false;
	
	Image buyUpgradeButton;

	Image chosenSkill = null;
	
	Image playerGold;
	String playerGoldText;
	boolean buyOneTime = false;
	
	
	private String mouse = "No input yet";
	Image menuTab;
	
	Skill[] chosenSkills = new Skill[5];
	Skill[] allSkills = new Skill[9];
	Skill basicSkill;
	Skill selectedSkill;
	
	Image playButton;
	Image optionsButton;
	
	Image classPortrait;

	String skillText;
	
	Image background;
	Image skillsText;
	Image shopText;

	//UnicodeFont uFont;
	//Font font;
	
	public ShoppingView (int state){
		
	}
	
	public int getID(){
		return 4;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)throws SlickException {
		//font = new Font("Calibri", Font.PLAIN, 20);
		//uFont = new UnicodeFont(font, font.getSize(), font.isBold(), font.isItalic());
		
		selectedSkill = new SkillFireball();
		
		background = new Image("res/miscImages/ShoppingviewBackground.png");
		skillsText = new Image("res/miscImages/skillsText.png");
		shopText = new Image("res/miscImages/shopText.png");


		playerGold = new Image("res/miscImages/PlayerGold.png");
		playerGoldText = "" + GlobalClassSelector.getController().getPlayers()[GlobalClassSelector.getController().getActivePlayerIndex()].getGold();
		

		skillText = " ";
		costText = " ";
		skillDescBg = new Image("res/miscImages/initEmptyPic.png");
		
		

		
		playButton = new Image("res/buttons/playButtons.png");
		buyUpgradeButton = new Image("res/miscImages/initEmptyPic.png");
		optionsButton = new Image ("res/buttons/options.png");
	}

	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
	      // TODO Auto-generated method stub
	      super.enter(container, game);
	      activePlayer = GlobalClassSelector.getController().getPlayer(GlobalClassSelector.getController().getActivePlayerIndex());
	      chosenSkills = activePlayer.getSkillList();
	      setCurrentOwnedSkillList();
	      
	      
	      switch(GlobalClassSelector.getController().getPlayers()[GlobalClassSelector.getController().getActivePlayerIndex()].getType()){
			case "Wizard":
				classPortrait = new Image("res/classImages/mage_portrait.png");
				//Init wizard basic, offensive, defensive and mobility skillists
				
				basicSkill = new SkillWandattack();
				
				allSkills[0] = new SkillFireball();
				allSkills[1] = new SkillIroncloak();
				allSkills[2] = new SkillUnstableMagic();
				allSkills[3] = new SkillFirestorm();
				allSkills[4] = new SkillAbsorb();
				allSkills[5] = new SkillBlizzard();
				allSkills[6] = new SkillFlamewave();
				allSkills[7] = new SkillIceblock();
				allSkills[8] = new SkillTeleport();
				break;
			case "Hunter":
				classPortrait = new Image("res/classImages/hunter_portrait.png");
				//Init Hunter basic, offensive, defensive and mobility skillists
				
				basicSkill = new SkillArrow();
				
				allSkills[0] = new SkillFlamingArrow();
				allSkills[1] = new SkillPassiveDodge();
				allSkills[2] = new SkillSprint();
				allSkills[3] = new SkillGuidedArrow();
				allSkills[4] = new SkillLifestealingArrows();
				allSkills[5] = new SkillCripplingShot();
				allSkills[6] = new SkillArrowFlurry();
				allSkills[7] = new SkillStealth();
				allSkills[8] = new SkillBarrelRoll();
				break;
			case "Warrior":
				classPortrait = new Image("res/classImages/warrior_portrait.png");
				//Init warrior basic, offensive, defensive and mobility skillists
				
				basicSkill = new SkillSlash();

				allSkills[0] = new SkillThrowingAxe();
				allSkills[1] = new SkillImprovedArmor();
				allSkills[2] = new SkillIncreasedMovement();
				allSkills[3] = new SkillWarstomp();
				allSkills[4] = new SkillShieldStance();
				allSkills[5] = new SkillGrapplingHook();
				allSkills[6] = new SkillAdrenaline();
				allSkills[7] = new SkillFirstAid();
				allSkills[8] = new SkillLeapAttack();
				break;
	      	}
	   }
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)throws SlickException {
		//g.setFont(uFont);
		
		g.drawImage(background, 0, 0);
		g.drawImage(playButton, 1120, 670);
		g.drawImage(optionsButton,980,670);
		g.setColor(Color.black);
		g.drawImage(skillDescBg, 485, 460);
		g.setColor(Color.white);
		g.drawString(skillText, 560, 475);
		g.drawString(costText, 760, 475);
		g.drawImage(buyUpgradeButton, 710, 610);
		
		if(chosenSkill != null)
			g.drawImage(chosenSkill, 490, 470);

		g.drawString(mouse, 900, 10);
		
		g.drawImage(classPortrait, 70, 30);
		g.drawImage(playerGold,70,185);
		g.drawString(""+GlobalClassSelector.getController().getPlayers()[GlobalClassSelector.getController().getActivePlayerIndex()].getGold(), 140, 198);
		g.drawString(GlobalClassSelector.getController().getPlayers()[GlobalClassSelector.getController().getActivePlayerIndex()].getName() + 
				"\nHP: "+GlobalClassSelector.getController().getPlayers()[GlobalClassSelector.getController().getActivePlayerIndex()].getHP() + 
				"\nArmor: " + (int)(GlobalClassSelector.getController().getPlayers()[GlobalClassSelector.getController().getActivePlayerIndex()].getArmor()*100) 
				+ "%\nKills: " + GlobalClassSelector.getController().getPlayers()[GlobalClassSelector.getController().getActivePlayerIndex()].getKills()
				 , 80 + classPortrait.getWidth(), 20 + classPortrait.getHeight()/2);

		g.drawString("1", 102, 250);
		g.drawImage(chosenSkills[0].getSkillBarImage(), 70, 275);
		g.drawString("2", 170, 250);
		g.drawImage(chosenSkills[1].getSkillBarImage(), 139, 275);
		g.drawString("3", 239, 250);
		g.drawImage(chosenSkills[2].getSkillBarImage(), 208, 275);
		g.drawString("4", 308, 252);
		g.drawImage(chosenSkills[3].getSkillBarImage(), 277, 275);
		g.drawString("5", 377, 250);
		g.drawImage(chosenSkills[4].getSkillBarImage(), 346, 275);
		
		
		//Drawing the skills the player already owns
		int row = 0;
		int col = 0;
		for(int i=0; i<ownedSkillList.size(); i++){
			g.drawImage(ownedSkillList.get(i).getSkillBarImage(), 475+col*64, 50+row*64);
			col++;
			if(i != 0 && col%6 == 0){
				row++;
				col = 0;
			}else if(i==0){
				
			}
		}
		
		
		
		//Offensive skills
		g.drawImage(allSkills[0].getSkillBarImage(), 60, 440);
		g.drawImage(allSkills[3].getSkillBarImage(), 60, 515);
		g.drawImage(allSkills[6].getSkillBarImage(), 60, 590);
			
		//Defensive skills
		g.drawImage(allSkills[1].getSkillBarImage(), 200, 440);
		g.drawImage(allSkills[4].getSkillBarImage(), 200, 515);
		g.drawImage(allSkills[7].getSkillBarImage(), 200, 590);
			
		//Mobility skills
		g.drawImage(allSkills[2].getSkillBarImage(), 335, 440);
		g.drawImage(allSkills[5].getSkillBarImage(), 335, 515);
		g.drawImage(allSkills[8].getSkillBarImage(), 335, 590);
		
		g.drawString(buyString, 640, 200);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException {
		
		int xPos = Mouse.getX();
		int yPos = 720 - Mouse.getY();
		
		
		
		mouse = "Mouse position: (" + xPos + "," + yPos + ")";
		
		Input input = gc.getInput();
		
		buyOneTime = true;
		
		if(showingSkillDescription){
			skillDescBg = new Image("res/miscImages/skillDescBg.png");
			if((710<xPos && xPos<830) && (600<yPos && yPos<645)){
				buyUpgradeButton = new Image("res/buttons/buyOver.png");
				if(input.isMousePressed(0) && buyOneTime){
					buyOneTime = false;
					if(activePlayer.getGold()>=selectedSkill.getCost()){					
						buySkill(selectedSkill);
					}
				}
			}else{
				buyUpgradeButton = new Image("res/buttons/buy.png");
			}
		}
		
		

		if((1120<xPos && xPos<1240) && (670<yPos && yPos<715)){
			playButton = new Image("res/buttons/ReadyOver.png");
			if(input.isMousePressed(0)){ // 0 = leftclick, 1 = rightclick
				playButton = new Image("res/buttons/Ready.png");
				sbg.enterState(1);
			}
			
		}else{
			playButton = new Image("res/buttons/Ready.png");
		}
		
		if((980<xPos && xPos<1100) && (670<yPos && yPos<715)){
			optionsButton = new Image("res/buttons/OptionsOver.png");
			if(input.isMousePressed(0)){ // 0 = leftclick, 1 = rightclick
				optionsButton = new Image("res/buttons/Options.png");
				sbg.enterState(5);
			}
			
		}else{
			optionsButton = new Image("res/buttons/Options.png");
		}
		
		
		if(input.isMousePressed(0)){
			
			//Calculating where the x and y index are in the list that is shown by owned skills
			if(xPos >= 475 && xPos <= 859 && yPos >= 50 && yPos <= 400){
				int xRange = xPos - 475;
				int yRange = yPos - 50;
				int xIndex = 0;
				int yIndex = 0;
				
				while(xRange > 64){
					xIndex++;
					xRange -= 64;
				}
				while(yRange > 64){
					yIndex++;
					yRange -= 64;
				}
				int index = xIndex + 6*yIndex;
				if(ownedSkillList.size() > index){
					setChosenSkill(ownedSkillList.get(index));
				}
				
			}
			
	/*		int xPosIndex = -1;
			int yPosIndex = -1;
			if(60<=xPos && xPos<=124){
				xPosIndex = 0;
			}else if(200<=xPos && xPos<=264){
				xPosIndex = 1;
			}else if(335<=xPos && xPos<=399){
				xPosIndex = 2;
			}
			
			if(yPos >= 440 && yPos <= 504){
				yPosIndex = 0;
			}else if(yPos >= 515 && yPos <= 579){
				yPosIndex = 1;
			}else if(yPos >= 590 && yPos <= 654){
				yPosIndex = 2;
			}*/
			
			int xPosIndex = -1;
			int yPosIndex = -1;
			if(60<=xPos && xPos<=124){
				xPosIndex = 0;
			}else if(200<=xPos && xPos<=264){
				xPosIndex = 1;
			}else if(335<=xPos && xPos<=399){
				xPosIndex = 2;
			}
			
			if(yPos >= 440 && yPos <= 504){
				yPosIndex = 0;
			}else if(yPos >= 515 && yPos <= 579){
				yPosIndex = 1;
			}else if(yPos >= 590 && yPos <= 654){
				yPosIndex = 2;
			}
			
			int totalIndex = xPosIndex + 3*yPosIndex;
			
			if(totalIndex >= 0){
				Skill newChosenSkill = findOwnedSkill(allSkills[totalIndex].getName());
				if(newChosenSkill != null){
					setChosenSkill(newChosenSkill);
				}else{
					setChosenSkill(allSkills[totalIndex]);
				}
			}
			
		/*	if(yPosIndex != -1 && xPosIndex != -1){
				Skill newChosenSkill = null;
				if(xPosIndex == 0){
					newChosenSkill = findOwnedSkill(offSkills[yPosIndex].getName());
					if(newChosenSkill != null){
						setChosenSkill(newChosenSkill);
					}else{
						setChosenSkill(offSkills[yPosIndex]);
					}
				}else if(xPosIndex == 1){
					newChosenSkill = findOwnedSkill(defSkills[yPosIndex].getName());
					if(newChosenSkill != null){
						setChosenSkill(newChosenSkill);
					}else{
						setChosenSkill(defSkills[yPosIndex]);
					}
				}else if(xPosIndex == 2){
					newChosenSkill = findOwnedSkill(mobSkills[yPosIndex].getName());
					if(newChosenSkill != null){
						setChosenSkill(newChosenSkill);
					}else{
						setChosenSkill(mobSkills[yPosIndex]);
					}
				}
			}*/
		}
	}
	
	private void setCurrentOwnedSkillList(){
		ownedSkillList = activePlayer.getOwnedSkills();
	}
	
	private void buySkill(Skill skill){
		boolean alreadyOwnSkill = false;
		for(int i=0; i<ownedSkillList.size(); i++){
			if(ownedSkillList.get(i).getName() == skill.getName()){
				buyString = "You can not buy skills you already own";
				alreadyOwnSkill = true;
				break;
			}
		}
		if(!alreadyOwnSkill){
			buyString = "Succesfully bought a skill for " + skill.getCost() + "!";
			activePlayer.addGold(-skill.getCost());
			activePlayer.addSkillAsOwned(skill);
		}else{
			Skill ownedSkill = findOwnedSkill(skill.getName());
			if(ownedSkill.getCurrentLvl() < 4){
				buyString = "Succesfully upgraded a skill for " + skill.getCost() + "!";
				activePlayer.addGold(-skill.getCost());
				ownedSkill.upgradeSkill();
			}else{
				buyString = "Skill already max level!";
			}
			
		}
		setCurrentOwnedSkillList();
		updateSkillInformation();
	}
	
	private Skill findOwnedSkill(String skillName){
		for(int i=0; i<ownedSkillList.size(); i++){
			if(ownedSkillList.get(i).getName() == skillName){
				return ownedSkillList.get(i);
			}
		}
		
		return null;
	}
	
	private void setChosenSkill(Skill skill){
		chosenSkill = skill.getSkillBarImage();
		skillText = "Level " + skill.getCurrentLvl() + " " + skill.getDescription();
		costText = "Cost : " + skill.getCost();
		showingSkillDescription = true;
		selectedSkill = skill;
	}
	
	private void updateSkillInformation(){
		setChosenSkill(selectedSkill);
	}
}
