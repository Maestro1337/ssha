package View;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import Model.*;
import Model.Classes.*;
import Model.Obstacles.ObstaclePillar;
import Model.Obstacles.Obstacle;
import Model.Skills.*;
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
import Model.Timers.AnimationTimer;

import Control.*;

public class MainView extends BasicGameState implements ActionListener {
	
	Image bg;
	private String mouse = "No input yet";
	
	String test;
	TiledMap map;
	
	String endRoundText = "";
	String winningPlayer= "<Fix code to get> \n<the winning> \n<players name>";
	Image nextRoundButton;
	Image nextRoundBg;
	boolean roundOver = false;
	boolean firstTimeRoundOver;
	Image roundOverAnimationImage;
	
	AnimationTimer victoryAnimation;
	
	private PlayerModel Control; 
	private PlayerModel enemyControl;
	Player player;
	Player enemy;
	Skill[] playerSkills;
	Skill activeSkill;
	
	Image playerPortrait;
	
	Image enemyImage;
	Skill[] enemySkills;
	
	private int enemyPlayer = 1;
	
	private int activePlayer;
	private ArrayList<PlayerModel> players = new ArrayList<PlayerModel>();
	private Obstacle[] obstacles = new Obstacle[100];
	
	Image userImage;
	Image user;
	Image move1;
	Image move2;
	
	float mouseXPosMove;
	float mouseYPosMove;
	
	float prevMouseXPosMove;
	float prevMouseYPosMove;
	
	
	float mouseXPosAtt;
	float mouseYPosAtt;
	
	public MainView(int state) {
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		
		map = new TiledMap("res/tileset/bg.tmx");
	//	initRound();
		
	}
	
	public void initRound() throws SlickException{
		Random obsGenerator = new Random();
		for(int i=0; i<obsGenerator.nextInt(50); i++){
			obstacles[i] = new ObstaclePillar(obsGenerator.nextInt(1280), obsGenerator.nextInt(719) + 1);
		}
		switch(GlobalClassSelector.getController().getPlayers().get(GlobalClassSelector.getController().getActivePlayerIndex()).getType()){
		case "Wizard":
			playerPortrait = new Image("res/classImages/Portrait_Wizard.png");
		break;
		case "Hunter":
			playerPortrait = new Image("res/classImages/Portrait_Hunter.png");
		break;
		case "Warrior":
			playerPortrait = new Image("res/classImages/Portrait_Warrior.png");
		break;
      	}
		activePlayer = GlobalClassSelector.getController().getActivePlayerIndex();
		
		//TODO Is to be removed later (is still here because of AI methods)-------
		
		Control = new PlayerModel(GlobalClassSelector.getController().getPlayers().get(activePlayer), obstacles);
		player = GlobalClassSelector.getController().getPlayers().get(activePlayer);
		playerSkills = player.getSkillList();

		Control.ressurectPlayer();

		userImage = player.getImage();

		Control.checkSpawnCollision();

		enemyControl = new PlayerModel(GlobalClassSelector.getController().getPlayers().get(enemyPlayer), obstacles);


		enemy = enemyControl.getPlayer();
		enemySkills = enemy.getSkillList();
		enemyImage = enemy.getImage();
		enemyControl.ressurectPlayer();

		enemyControl.checkSpawnCollision();
		
		//----------------- Up until this point
		
		players.clear();
		players.add(new PlayerModel(GlobalClassSelector.getController().getPlayers().get(activePlayer), obstacles));
		
		players.add(enemyControl);
		
		for(int i=0; i<players.size(); i++){
			PlayerModel currentController = players.get(i);
			currentController.ressurectPlayer();
		}
		nextRoundButton = new Image("res/buttons/Ready.png");
		nextRoundBg = new Image("res/miscImages/skillDescBg.png");
		
		Image[] victoryanimation = new Image[5];
		
		victoryanimation[0] = new Image("res/animations/victory5.png");
		victoryanimation[1] = new Image("res/animations/victory4.png");
		victoryanimation[2] = new Image("res/animations/victory3.png");
		victoryanimation[3] = new Image("res/animations/victory2.png");
		victoryanimation[4] = new Image("res/animations/victory1.png");
		
		victoryAnimation = new AnimationTimer(2000,victoryanimation);
		firstTimeRoundOver = true;
	}
	
	@Override
	   public void enter(GameContainer container, StateBasedGame game)
	         throws SlickException {
	      // TODO Auto-generated method stub
	      super.enter(container, game);
	      initRound();

	   }
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		map.render(0,0);
		
		//Show the coodinates of the mouse
		g.drawString(mouse, 900, 10);
		
		
		//Draw Obstacles
		for(int i=0; i<obstacles.length; i++){
			if(obstacles[i] != null){
				g.drawImage(obstacles[i].getImage(), obstacles[i].getX(), obstacles[i].getY());
			}
		}

		g.drawImage(playerPortrait, 20, 585);
		//Draw the actionbar
		Skill[] activePlayerSkills = players.get(activePlayer).getPlayer().getSkillList();
		for(int j=0; j<activePlayerSkills.length; j++){
			g.setColor(Color.white);
			g.fillRect(140 + j*64, 640, 64, 64);
			g.setColor(Color.black);
			
			if(activePlayerSkills[j] != null){
				
			//	if(activePlayerSkills[j].checkCooldown() == activePlayerSkills[j].getCoolDown()){
					g.drawImage(activePlayerSkills[j].getSkillBarImage(),140 + j*64, 640);
			//	}
				g.drawString(""+activePlayerSkills[j].checkCooldown(), activePlayerSkills[j].getSkillBarImage().getWidth()/2 + 140 + j*64, 610);
				
			}
		}
		
		
		//Draw player stats and image
		
		for(int i=0; i<players.size(); i++){
			Player currentPlayer = players.get(i).getPlayer();
			Skill[] currentSkillset = currentPlayer.getSkillList();
			g.drawString(currentPlayer.getName() + "\nHP: "+currentPlayer.getHP() + "\nArmor: " + (int)(currentPlayer.getArmor()*100) 
					+ "%\nKills: " + currentPlayer.getKills(),900+150*i,25);
			g.drawImage(currentPlayer.getImage(), currentPlayer.getX(),currentPlayer.getY());
			
			for(int j=0; j<currentSkillset.length; j++){
				if(currentSkillset[j] != null){
					if(currentSkillset[j].isAttacking() && !currentSkillset[j].isEndState()){
						g.drawImage(currentSkillset[j].getAttImage(), currentSkillset[j].getAttX(),currentSkillset[j].getAttY());
					}else if(currentSkillset[j].isEndState()){
						g.drawImage(currentSkillset[j].getEndStateImage(), currentSkillset[j].getAttX(),currentSkillset[j].getAttY());
					}
				}
			}		
		}
		if(roundOver){
			g.drawImage(nextRoundBg, 1280/2 - nextRoundBg.getWidth()/2, 200);
			g.drawImage(nextRoundButton, 1280/2 - nextRoundButton.getWidth()/2, 200 + nextRoundBg.getHeight()/2);
			g.drawString(endRoundText, 1280/2 - nextRoundBg.getWidth()/4, 210);
			if(Control.getPlayer().getHP()>0){
				Image testAnimationImage = victoryAnimation.getCurrentAnimationImage();
				if(testAnimationImage != null){
					roundOverAnimationImage = testAnimationImage;
				}
				if(roundOverAnimationImage != null)
					g.drawImage(roundOverAnimationImage, 1280/2 - nextRoundBg.getWidth()/2, 200);
			}
		}
	}
	
	
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		
		PlayerModel currentActiveController = players.get(activePlayer);
		Skill[] activeSkillList = currentActiveController.getPlayer().getSkillList();
		
		//Update current mouse position
		int xPos = Mouse.getX();
		int yPos = 720 - Mouse.getY();
		mouse = "Mouse position: (" + xPos + "," + yPos + ")";
		
		for(int i=0; i<players.size(); i++){
			PlayerModel currentController = players.get(i);
			
			//Checking status effects
			currentController.checkStatusEffects();
			
			//Checking collision from other players
			for(int j=0; j<players.size(); j++){
				PlayerModel checkController;
				//Check to see it is another player
				if(j != i && currentController.getPlayer().isAlive()){
					checkController = players.get(j);
					currentController.checkCollision(checkController.getPlayer().getSkillList());
					if(!currentController.getPlayer().isAlive()){
						checkController.getPlayer().incKills();
					}
				}
			}
			
			//Check if player is running to update positioning
			if(currentController.getPlayer().isRunning()){
				currentController.isRunning();
			}
			
			//Check if players skills are in use to update positioning
			Skill[] currentSkillList = currentController.getPlayer().getSkillList();
			for(int j=0; j<currentSkillList.length; j++){
				if(currentSkillList[j] != null && currentSkillList[j].isAttacking()){
					currentController.isAttacking(currentSkillList[j]);
				}
			}
		}
		
		Input input = gc.getInput();
		if(input.isKeyDown(Input.KEY_1)){
			if(activeSkillList[0] != null){
				currentActiveController.setCurrentActiveSkill(0);
			}
		}
		if(input.isKeyDown(Input.KEY_2)){
			if(activeSkillList[1] != null){
				currentActiveController.setCurrentActiveSkill(1);
			}
		}
		if(input.isKeyDown(Input.KEY_3)){
			if(activeSkillList[2] != null){
				currentActiveController.setCurrentActiveSkill(2);
			}
		}
		if(input.isKeyDown(Input.KEY_4)){
			if(activeSkillList[3] != null){
				currentActiveController.setCurrentActiveSkill(3);
			}
		}
		if(input.isKeyDown(Input.KEY_5)){
			if(activeSkillList[4] != null){
				currentActiveController.setCurrentActiveSkill(4);
			}
		}
		
		
		//If left mousebutton is clicked, move the player
		if(input.isMouseButtonDown(1)){
			currentActiveController.move(Mouse.getX(), 720 - Mouse.getY());
			//enemyControl.move(generator.nextInt(1280), generator.nextInt(719) + 1);
		}
		//If right mousebutton is clicked, attack that point
		if(input.isMouseButtonDown(0)){
			currentActiveController.attack(Mouse.getX(), 720 - Mouse.getY());
			
			//Setting target to guide if skill permits it
			if(currentActiveController.getCurrentActiveSkill().isGuided()){
				currentActiveController.getCurrentActiveSkill().setGuidedTarget(GlobalClassSelector.getController().getPlayers().get(enemyPlayer));
			}
		}
		
		
		//Checks if round should be ended
		int endRound = 0;
		for(int i=0; i<players.size(); i++){
			if(!players.get(i).getPlayer().isAlive()){
				endRound++;
				
			}
		}
		//Ends round if only 1 player is alive
		if (endRound >= players.size() - 1){
			if(firstTimeRoundOver){
				victoryAnimation.resetCounterAndTimer();
				firstTimeRoundOver = false;
			}
			roundOver = true;
			endRoundText = winningPlayer + " " + "wins!";
			if((640 - nextRoundButton.getWidth()/2<xPos && xPos<760 - nextRoundButton.getWidth()/2) && (200 + nextRoundBg.getHeight()/2<yPos && yPos<245 + nextRoundBg.getHeight()/2)){
				nextRoundButton = new Image("res/buttons/ReadyOver.png");
				if(input.isMousePressed(0)){
					roundOver = false;
					sbg.enterState(4);
				}
			}else{
				nextRoundButton = new Image("res/buttons/Ready.png");
			}
		}
		
		
		AI();
	}
	
	
	
	public boolean willCollide(){
		float collitionx;
		float collitiony;
		boolean value=false;
		
		for (int i=1;i<Control.getCurrentActiveSkill().getRange();i++){
			collitionx=player.getX()+(i*Control.getCurrentActiveSkill().getAttX());
			collitiony=player.getY()+(i*Control.getCurrentActiveSkill().getAttY());	
			if(collitionx>enemy.getX()&&collitionx<enemy.getX()+enemy.getImage().getWidth()&&collitiony>enemy.getY()&&collitiony<enemy.getY()+enemy.getImage().getHeight()){
				value = true;
			 break;
			}
		}
		return value;
	}
	public boolean aiTimer (long delay){
		return true;
	}
		
	
	
	long time = 0;
	int dodgedirX;
	int dodgedirY;
	
	public void AI(){
		Random generator = new Random();
		long delay=500;
		double dx = enemy.getX()-player.getX();
		double dy = enemy.getY()-player.getY();
		double distance = Math.sqrt((dx*dx)+(dy*dy));
		while (System.currentTimeMillis()>time+delay){
			if(Control.getCurrentActiveSkill().isAttacking()&&Control.getCurrentActiveSkill().getRange()>distance){
				enemyControl.move((int)(enemy.getX()+dy),(int)(enemy.getY()-dx));
				}
				
			
			else if (distance>=100){
				enemyControl.move((int)player.getX(),(int) player.getY());
			}else if (dx<0){
				enemyControl.move(generator.nextInt((int)player.getX()), generator.nextInt(719) + 1);
			}
			else{
				enemyControl.move(generator.nextInt(1280-(int)player.getX())+(int)player.getX(), generator.nextInt(719) + 1);
			}
			time=System.currentTimeMillis();
		}
		for(int i=0;i<enemy.getSkillList().length;i++){
			enemyControl.setCurrentActiveSkill(i);
			if(enemyControl.getCurrentActiveSkill().getRange()>distance &&
							enemyControl.getCurrentActiveSkill().checkCooldown()==enemyControl.getCurrentActiveSkill().getCoolDown()){
				enemyControl.attack((int)player.getX(), (int)player.getY());
			}
		}
		
	}
	
		
	//Returns the state of the game
	public int getID(){
		return 1;
	}
	
	
	//Test for sounds
	@Override
	public void actionPerformed(ActionEvent e) {
		String fileName = "res/sounds/takingDamage.wav";
		if(e.getActionCommand() == "Taking damage"){
			playSound("res/sounds/takingDamage.wav");
		} else if(e.getActionCommand() == "Sword"){
			playSound("res/sounds/swordAttack.wav");
		} else if(e.getActionCommand() == "Bow"){
			playSound("res/sounds/bowShot.wav");
		} else if(e.getActionCommand() == "Fireball"){
			playSound("res/sounds/fireballCast.wav");
		} else if(e.getActionCommand() == "Music"){
			playSound("res/sounds/tickerSound.wav");
		}
	}
	
	//Handling the soundfiles
	public static synchronized void playSound(String filename) {

		    try
		    {
		        Clip clip = AudioSystem.getClip();
		        clip.open(AudioSystem.getAudioInputStream(new File(filename)));
		        clip.start();
		    }
		    catch (Exception exc)
		    {
		        exc.printStackTrace(System.out);
		    }	
	}
}
