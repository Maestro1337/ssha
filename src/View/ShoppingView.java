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
import Model.Skills.Hunter.SkillCripplingTrap;
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
	private int xPos = 0;
	private int yPos = 0;
	
	Image buyUpgradeButton;
	
	private Image playerGold;
	private String playerGoldText;
	private boolean buyOneTime = false;
	private int grabbedFromChosenIndex = -1;
	private boolean dragMouse = false;
	
	
	private String mouse = "No input yet";
	Image menuTab;
	
	Skill[] chosenSkills = new Skill[5];
	Skill[] allSkills = new Skill[9];
	Skill basicSkill;
	Skill selectedSkill = null;
	
	Image playButton;
	Image optionsButton;
	Image goButton;
	
	Image classPortrait;

	String skillText;
	
	Image background;
	Image skillsText;
	Image shopText;
	
	Image [] LevelofSkills = new Image [9];
	Image [] LobbyPlayers = new Image [100];

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
		
		LevelofSkills [0] = new Image ("res/skillIcons/Level 0.png");
		LevelofSkills [1] = new Image ("res/skillIcons/Level 0.png");
		LevelofSkills [2] = new Image ("res/skillIcons/Level 0.png");
		LevelofSkills [3] = new Image ("res/skillIcons/Level 0.png");
		LevelofSkills [4] = new Image ("res/skillIcons/Level 0.png");
		LevelofSkills [5] = new Image ("res/skillIcons/Level 0.png");
		LevelofSkills [6] = new Image ("res/skillIcons/Level 0.png");
		LevelofSkills [7] = new Image ("res/skillIcons/Level 0.png");
		LevelofSkills [8] = new Image ("res/skillIcons/Level 0.png");
		
		for (int i=0;i<GlobalClassSelector.getController().getPlayerControllers().length;i++){
			LobbyPlayers[i] = new Image ("res/miscImages/LobbyPlayer.png");
		}
		
				
		background = new Image("res/miscImages/ShoppingviewBackground.png");
		skillsText = new Image("res/miscImages/skillsText.png");
		shopText = new Image("res/miscImages/shopText.png");


		playerGold = new Image("res/miscImages/PlayerGold.png");
		if(GlobalClassSelector.getController().getPlayer(GlobalClassSelector.getController().getActivePlayerIndex()) != null)
			playerGoldText = "" + GlobalClassSelector.getController().getPlayers()[GlobalClassSelector.getController().getActivePlayerIndex()].getGold();
		

		skillText = " ";
		costText = " ";
		skillDescBg = new Image("res/miscImages/initEmptyPic.png");
		
		

		
		playButton = new Image("res/buttons/playButtons.png");
		buyUpgradeButton = new Image("res/miscImages/initEmptyPic.png");
		optionsButton = new Image ("res/buttons/options.png");
		goButton = new Image ("res/buttons/Ready.png");
	}

	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
	      // TODO Auto-generated method stub
	      super.enter(container, game);
	      activePlayer = GlobalClassSelector.getController().getPlayer(GlobalClassSelector.getController().getActivePlayerIndex());
	      updateSkillLists();
	      
	      
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
				allSkills[5] = new SkillCripplingTrap();
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
	      
	      	if(true && !false)
	      		activePlayer.setIndex(GlobalClassSelector.getController().getActivePlayerIndex());
	      	if(1 == 1)
	      		GlobalClassSelector.getController().addPlayer(activePlayer, GlobalClassSelector.getController().getActivePlayerIndex());
	      	if(8 != 7)
	      		GlobalClassSelector.getController().getSocketClient().changePlayer(activePlayer);
	      	if(true == !false)
	      		activePlayer.setMode("lobby");
			
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
		
		if(selectedSkill != null)
			g.drawImage(selectedSkill.getSkillBarImage(), 490, 470);

		g.drawString(mouse, 900, 10);
		
		g.drawImage(classPortrait, 70, 30);
		g.drawImage(playerGold,70,185);
		g.drawString(""+activePlayer.getGold(), 140, 198);
		g.drawString(activePlayer.getName() + "\nHP: "+activePlayer.getHP() + "\nArmor: " + (int)(activePlayer.getArmor()*100) 
				+ "%\nKills: " + activePlayer.getKills() , 80 + classPortrait.getWidth(), 20 + classPortrait.getHeight()/2);


		for(int j=0; j<chosenSkills.length; j++){
			g.drawString("" + (j+1), 100 + 69*j, 250);
			if(chosenSkills[j] != null)
				g.drawImage(chosenSkills[j].getSkillBarImage(), 70 + 69*j, 275);
		}
		
		
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
		
		//Draw the Connected players in lobby (not done)
		//for (int i=0;i<LobbyPlayers.length;i++){
			//if(GlobalClassSelector.getController().getPlayer(i).isConnected()){
				g.drawImage(LobbyPlayers[0],898,400+59);
				//if(GlobalClassSelector.getController().getPlayer(i).isReady()){
					LobbyPlayers[0] = new Image ("res/miscImages/LobbyPlayerReady.png");
				//}
			//}	
		//}
		
		//Offensive skills	
		g.drawImage((findOwnedSkill(allSkills[0].getName())) != null ? allSkills[0].getRegularSkillBarImage() : allSkills[0].getDisabledSkillBarImage(), 60, 440);
		g.drawImage(LevelofSkills[0],114,494);
		g.drawImage((findOwnedSkill(allSkills[3].getName())) != null ? allSkills[3].getRegularSkillBarImage() : allSkills[3].getDisabledSkillBarImage(), 60, 515);
		g.drawImage(LevelofSkills[3],114,569);
		g.drawImage((findOwnedSkill(allSkills[6].getName())) != null ? allSkills[6].getRegularSkillBarImage() : allSkills[6].getDisabledSkillBarImage(), 60, 590);
		g.drawImage(LevelofSkills[6],114,644);
			
		//Defensive skills
		g.drawImage((findOwnedSkill(allSkills[1].getName())) != null ? allSkills[1].getRegularSkillBarImage() : allSkills[1].getDisabledSkillBarImage(), 200, 440);
		g.drawImage(LevelofSkills[1],254,494);
		g.drawImage((findOwnedSkill(allSkills[4].getName())) != null ? allSkills[4].getRegularSkillBarImage() : allSkills[4].getDisabledSkillBarImage(), 200, 515);
		g.drawImage(LevelofSkills[4],254,569);
		g.drawImage((findOwnedSkill(allSkills[7].getName())) != null ? allSkills[7].getRegularSkillBarImage() : allSkills[7].getDisabledSkillBarImage(), 200, 590);
		g.drawImage(LevelofSkills[7],254,644);
			
		//Mobility skills
		g.drawImage((findOwnedSkill(allSkills[2].getName())) != null ? allSkills[2].getRegularSkillBarImage() : allSkills[2].getDisabledSkillBarImage(), 335, 440);
		g.drawImage(LevelofSkills[2],389,494);
		g.drawImage((findOwnedSkill(allSkills[5].getName())) != null ? allSkills[5].getRegularSkillBarImage() : allSkills[5].getDisabledSkillBarImage(), 335, 515);
		g.drawImage(LevelofSkills[5],389,569);
		g.drawImage((findOwnedSkill(allSkills[8].getName())) != null ? allSkills[8].getRegularSkillBarImage() : allSkills[8].getDisabledSkillBarImage(), 335, 590);
		g.drawImage(LevelofSkills[8],389,644);
		
		g.drawString(buyString, 640, 200);
		
		if(dragMouse){
			g.drawImage(selectedSkill.getSkillBarImage(), xPos, yPos);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException {
		
		xPos = Mouse.getX();
		yPos = 720 - Mouse.getY();
		
		for(int i=0;i<9;i++){
			if (findOwnedSkill(allSkills[i].getName()) != null){
				switch (allSkills[i].getCurrentLvl()){	
					case 1:
						LevelofSkills [i] = new Image ("res/skillIcons/Level 1.png");
						break;
					case 2:
						LevelofSkills [i] = new Image ("res/skillIcons/Level 2.png");
						break;
					case 3:
						LevelofSkills [i] = new Image ("res/skillIcons/Level 3.png");
						break;
					case 4:
						LevelofSkills [i] = new Image ("res/skillIcons/Level 4.png");
						break;
				}
			
			}
		}
		
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
			if (!activePlayer.isReady()){
				playButton = new Image("res/buttons/ReadyOver.png");
			}else{
				playButton = new Image("res/buttons/GoButtonOver.png");
			}
			if(input.isMousePressed(0)){ // 0 = leftclick, 1 = rightclick
				playButton = new Image("res/buttons/GoButton.png");
				//LobbyPlayers[0] = new Image ("res/miscImages/LobbyPlayerReady.png");
				
				if(activePlayer.isReady()){
					pressedReadyOrGo(sbg);
				}
				activePlayer.setReady(true);
				
			}
			
		}else if(!activePlayer.isReady()){
			playButton = new Image("res/buttons/Ready.png");
		}else{
			playButton = new Image("res/buttons/GoButton.png");
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
		//Checking if mouse is pressed down
		if(input.isMousePressed(0) && !dragMouse){
			int chosenIndex;
			if(xPos >= 60 && xPos <= 399 && yPos >= 440 && yPos <= 654){
				int xAllIndex = -1;
				int yAllIndex = -1;
				int totalIndex = -1;
				if(60<=xPos && xPos<=124){
					xAllIndex = 0;
				}else if(200<=xPos && xPos<=264){
					xAllIndex = 1;
				}else if(335<=xPos && xPos<=399){
					xAllIndex = 2;
				}
				
				if(yPos >= 440 && yPos <= 504){
					yAllIndex = 0;
				}else if(yPos >= 515 && yPos <= 579){
					yAllIndex = 1;
				}else if(yPos >= 590 && yPos <= 654){
					yAllIndex = 2;
				}
				
				if(xAllIndex != -1 && yAllIndex != -1)
					totalIndex = xAllIndex + 3*yAllIndex;
				
				if(totalIndex >= 0){		
					Skill newChosenSkill = findOwnedSkill(allSkills[totalIndex].getName());
					if(newChosenSkill != null){
						setSelectedSkill(newChosenSkill);
						if (!allSkills[totalIndex].isPassive()){
							dragMouse=true;	
							grabbedFromChosenIndex = -1;
						}
					}else{
						setSelectedSkill(allSkills[totalIndex]);
						dragMouse=false;
					}
					
				}
			}else if((chosenIndex = getChosenSkillIndex()) > 0){
				if(chosenSkills[chosenIndex] != null){
					setSelectedSkill(chosenSkills[chosenIndex]);
					dragMouse = true;
					grabbedFromChosenIndex = chosenIndex;
				}
			}
			
			
			
			
		}

		//Checking if mouse is released
		if(dragMouse && !input.isMouseButtonDown(0)){
			int xIndex = getChosenSkillIndex();
			if(xIndex == 0){
				buyString = "You can not change that skill";
			}else if(xIndex >= 1){
				boolean alreadyInChosenSkills = false;
				for(int i=0; i<chosenSkills.length; i++){
					if(chosenSkills[i] != null && chosenSkills[i].getName() == selectedSkill.getName())
						alreadyInChosenSkills = true;
				}
				if(alreadyInChosenSkills){
					if(grabbedFromChosenIndex != -1){
						Skill tempSkill = chosenSkills[xIndex];
						activePlayer.setSkill(selectedSkill, xIndex);
						activePlayer.setSkill(tempSkill, grabbedFromChosenIndex);
					}else{
						buyString = "Already got that skill in your skillbar";
					}
				}else{
					activePlayer.setSkill(selectedSkill, xIndex);
					updateSkillLists();
				}
			}else{
				if(grabbedFromChosenIndex != -1){
					activePlayer.setSkill(null, grabbedFromChosenIndex);
				}
			}
			grabbedFromChosenIndex = -1;
			dragMouse = false;
		}
		
		/*
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
					dragMouse = true;
				}
				
			}
		}*/
	}
	

	/**
	 * Returns the index in chosen skills that the mouse is above
	 * @return the index of chosen skills and -1 if mouse is not over
	 */
	private int getChosenSkillIndex(){
		if(xPos >= 70 && xPos <= 415 && yPos >= 275 && yPos <= 339){
			int xRange = xPos - 70;
			int xIndex = -1;
			while(xRange > 0){
				xIndex++;
				xRange -= 69;
			}
			return xIndex;
		}
		return -1;
	}
	
	private void updateSkillLists(){
		chosenSkills = activePlayer.getSkillList();
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
			if(skill.isPassive()){
				activePlayer.addPassiveSkill(skill);
			}
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
		updateSkillLists();
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
	
	private void setSelectedSkill(Skill skill){
		skillText = "Level " + skill.getCurrentLvl() + " " + skill.getDescription();
		costText = "Cost : " + skill.getCost();
		showingSkillDescription = true;
		selectedSkill = skill;
	}
	
	private void updateSkillInformation(){
		setSelectedSkill(selectedSkill);
	}
	
	private void pressedReadyOrGo(StateBasedGame sbg){
		if(GlobalClassSelector.getController().isMulti()){
			activePlayer.setMode("arena");
			sbg.enterState(2);
		}else{
			sbg.enterState(1);
		}
		
		
	}
}
