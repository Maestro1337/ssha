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

import sshaclient.Constants;





import Control.*;
import Model.*;
import Model.Arenas.Arena;
import Model.Classes.*;
import Model.Obstacles.ObstaclePillar;
import Model.Obstacles.Obstacle;
import Model.Obstacles.ObstacleTestDamage;
import Model.Skills.*;
import Model.Timers.AnimationTimer;


public class MainView extends BasicGameState implements ActionListener {	
	boolean shouldCalcGold;
	ArrayList <Player> placingInRound = null; 
	long TimeRoundStart=System.currentTimeMillis();
	
	Image bg;
	Image treetop;
	private String mouse = "No input yet";
	
	String test;
	TiledMap map;
	
	Player[] playerList;
	
	String endRoundText = "";
	String winningPlayer= "<Fix code to get> \n<the winning> \n<players name>";
	Image nextRoundButton;
	Image nextRoundBg;
	boolean roundOver = false;
	private boolean firstTimeRoundOver;
	private int roundOverMultiCheck = 0;
	Image roundOverAnimationImage;
	
	AnimationTimer victoryAnimation;
	
//	private PlayerModel Control; 
//	private PlayerModel enemyControl;
//	Skill[] playerSkills;
//	Skill activeSkill;
	
//	Image playerPortrait;
//	Player player;
//	Player enemy;
//	Image enemyImage;
//	Skill[] enemySkills;
	
	
	
	private int activePlayer;
	private PlayerModel currentActiveController;
	private Skill[] activeSkillList;
	private PlayerModel[] players = new PlayerModel[MainHub.nbrOfPlayers];
	private AIModel[] aiModels = new AIModel[MainHub.nbrOfPlayers];
	private int nbrOfCurrentPlayers;
	private Obstacle[] obstacles = new Obstacle[100];
	
//	Image userImage;
//	Image user;
//	Image move1;
//	Image move2;
	
	
//	float mouseXPosMove;
//	float mouseYPosMove;
	
//	float prevMouseXPosMove;
//	float prevMouseYPosMove;
	
	
//	float mouseXPosAtt;
//	float mouseYPosAtt;

//	private boolean start = true;

	
	public MainView(int state) {
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		
		map = new TiledMap("res/tileset/grassBackground.tmx");
	}
	
	public void initRound() throws SlickException{		
		TimeRoundStart = System.currentTimeMillis();
		shouldCalcGold=true;
		treetop = new Image ("res/tileset/Treetop2.png");
		
		Arena arena = MainHub.getController().getMapSelected();
		if(arena != null){
			obstacles = arena.getObstacles();
		}else{
			Random obsGenerator = new Random();
			for(int i=0; i<obsGenerator.nextInt(50); i++){
				obstacles[i] = new ObstaclePillar(obsGenerator.nextInt(GameEngine.screenWidth), obsGenerator.nextInt(GameEngine.screenHeight-1) + 1);
			}

		}
		playerList = MainHub.getController().getPlayers();
		activePlayer = MainHub.getController().getActivePlayerIndex();
		
//		playerPortrait = playerList[activePlayer].getFramedImage();
		
		
		for(int i=0; i<MainHub.getController().getPlayers().length; i++){
			Player newPlayer = MainHub.getController().getPlayer(i);
			if(newPlayer != null){
				System.out.println(newPlayer.getName() + " " + newPlayer.getPlayerListIndex() + " " + i);
				nbrOfCurrentPlayers++;
				players[i] = new PlayerModel(newPlayer);
				if(newPlayer.getControlType() == "ai"){
					aiModels[i] = new AIModel(newPlayer);
				}
				
			}
		}
		
		
		//TODO Is to be removed later (is still here because of AI methods)-------
		
	/*	Control = new PlayerModel(playerList[activePlayer], obstacles);
		player = playerList[activePlayer];
		playerSkills = player.getSkillList();

		Control.ressurectPlayer();

		userImage = player.getImage();

		Control.checkPlayerObstacleCollision(0, 0);

		enemyControl = new PlayerModel(playerList[enemyPlayer], obstacles);


		enemy = enemyControl.getPlayer();
		enemySkills = enemy.getSkillList();
		enemyImage = enemy.getImage();
		enemyControl.ressurectPlayer();

		enemyControl.checkPlayerObstacleCollision(0, 0);*/
		
		//----------------- Up until this point
		
	//	players.clear();
	//	players.add(new PlayerModel(playerList[activePlayer], obstacles));
		
	//	players.add(enemyControl);
		
		for(int i=0; i<players.length; i++){
			PlayerModel currentController = players[i];
			if(currentController != null){
				currentController.ressurectPlayer();
				currentController.checkPlayerObstacleCollision(0, 0);
			}
		}
		nextRoundButton = new Image("res/buttons/Ready.png");
		nextRoundBg = new Image("res/miscImages/skillDescBg.png");
		
		Image[] victoryanimation = new Image[10];
		
		victoryanimation[0] = new Image("res/animations/victory/victory10.png");
		victoryanimation[1] = new Image("res/animations/victory/victory9.png");
		victoryanimation[2] = new Image("res/animations/victory/victory8.png");
		victoryanimation[3] = new Image("res/animations/victory/victory7.png");
		victoryanimation[4] = new Image("res/animations/victory/victory6.png");
		victoryanimation[5] = new Image("res/animations/victory/victory5.png");
		victoryanimation[6] = new Image("res/animations/victory/victory4.png");
		victoryanimation[7] = new Image("res/animations/victory/victory3.png");
		victoryanimation[8] = new Image("res/animations/victory/victory2.png");
		victoryanimation[9] = new Image("res/animations/victory/victory1.png");
		
		victoryAnimation = new AnimationTimer(500,victoryanimation);
		firstTimeRoundOver = true;
		roundOverMultiCheck = 0;
		
		MainHub.getController().getPlayer(MainHub.getController().getActivePlayerIndex()).setMode("arena");
		for(int lol = 0; lol < MainHub.getController().getPlayers().length; lol++) {
			if(MainHub.getController().getPlayers()[lol] != null) {
				if(MainHub.getController().getPlayers()[lol].getControlType().equals("server")) {
					MainHub.getController().getPlayerControllers()[lol].changePlayer(MainHub.getController().getPlayers()[lol]);
					System.out.println("Player " + lol + " is now changed.");
				}
				
			
			}
		}
		
		for(int i=0; i<players.length; i++){
			if(players[i] != null){
				System.out.println(players[i].getPlayer().getName() + " " + players[i].getPlayer().getPlayerListIndex() + " " + i);
			}
		}
		currentActiveController = players[activePlayer];
		activeSkillList = currentActiveController.getPlayer().getSkillList();
		
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
				g.drawImage(obstacles[i].getCurrentImage(), obstacles[i].getX(), obstacles[i].getY());
			}
		}
		
		//Draw player stats and image
		
		for(int i=0; i<playerList.length; i++){
			if(playerList[i] != null){
				Player currentPlayer = playerList[i];
				Skill[] currentSkillset = currentPlayer.getSkillList();
				g.drawString(currentPlayer.getName() + "\nHP: "+currentPlayer.getHP() + "\nArmor: " + (int)(currentPlayer.getArmor()*100) 
						+ "%\nKills: " + currentPlayer.getKillsThisRound() + "\nMovement: " + currentPlayer.getMovementSpeed() 
						+ "\nx:" + currentPlayer.getX() + " y: " + currentPlayer.getY() + "\n DMG: "+ currentPlayer.getRoundDamageDone(),900+150*i,25);
				
				
				for(int j=0; j<currentSkillset.length; j++){
					if(currentSkillset[j] != null){
						if(currentSkillset[j].isAttacking() && !currentSkillset[j].isEndState()){
							g.drawImage(currentSkillset[j].getAttImage(), currentSkillset[j].getAttX(),currentSkillset[j].getAttY());
						}else if(currentSkillset[j].isEndState()){
							g.drawImage(currentSkillset[j].getEndStateImage(), currentSkillset[j].getAttX(),currentSkillset[j].getAttY());
						}
					}
				}
				if(!currentPlayer.isStealthed() || currentPlayer.getPlayerListIndex() == MainHub.getController().getActivePlayerIndex()){
					g.drawImage(currentPlayer.getImage(), currentPlayer.getX(),currentPlayer.getY());
				}
			}
		}
		
		//Draw images of obstacles that are at a higher altitude than the player
		for(int i=0; i<obstacles.length; i++){
			if(obstacles[i] != null){
				if(obstacles[i].getType()== "TreePillar"){
					g.drawImage(treetop,obstacles[i].getX()-25, obstacles[i].getY()-21);
				}
			}
		}
		
		//Attempt to draw oval around player which displays max range (Currently not working correctly)
//		g.drawOval(player.getX()-Control.getCurrentActiveSkill().getAttackRange()/2, player.getY()-Control.getCurrentActiveSkill().getAttackRange()/2, Control.getCurrentActiveSkill().getAttackRange(), Control.getCurrentActiveSkill().getAttackRange());
//		System.out.println(Control.getCurrentActiveSkill().getAttackRange());
		g.drawImage(playerList[activePlayer].getFramedImage(), 20, 585);
		//Draw the actionbar
		Skill[] activePlayerSkills = playerList[activePlayer].getSkillList();
		for(int j=0; j<activePlayerSkills.length; j++){
		//	g.setColor(Color.white);
		//	g.fillRect(140 + j*64, 640, 64, 64);
		//	g.setColor(Color.black);
			
			if(activePlayerSkills[j] != null){
				
			//	if(activePlayerSkills[j].checkCooldown() == activePlayerSkills[j].getCoolDown()){
					g.drawImage(activePlayerSkills[j].getSkillBarImage(),140 + j*64, 640);
			//	}
				g.drawString(""+activePlayerSkills[j].checkCooldown(), activePlayerSkills[j].getSkillBarImage().getWidth()/2 + 140 + j*64, 610);
				
			}
		}
		g.drawString("Attack Timer: "+players[activePlayer].checkGlobalAttackCooldown(),20, 570);
		g.drawString("Walk Timer : "+players[activePlayer].checkGlobalWalkCooldown(),20, 580);
		
	
		if(roundOver){
			/*if (shouldCalcGold && nbrOfCurrentPlayers>1){
				goldreward();
			}
			shouldCalcGold=false;*/
			g.drawImage(nextRoundBg, GameEngine.screenWidth/2 - nextRoundBg.getWidth()/2, 200);
			g.drawImage(nextRoundButton, GameEngine.screenWidth/2 - nextRoundButton.getWidth()/2, 200 + nextRoundBg.getHeight()/2);
			g.drawString(endRoundText, GameEngine.screenWidth/2 - nextRoundBg.getWidth()/4, 210);
			if(players[activePlayer].getPlayer().getHP()>0){
				
				Image testAnimationImage = victoryAnimation.getCurrentAnimationImage();
				if(testAnimationImage != null){
					roundOverAnimationImage = testAnimationImage;
				}
				if(roundOverAnimationImage != null)
					g.drawImage(roundOverAnimationImage, GameEngine.screenWidth/2 - nextRoundBg.getWidth()/2, 200-nextRoundBg.getHeight()/2);
			}
		}
		//g.drawString("Singleplayer", 640, 200);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		
		
		
		
		
		//System.out.println("Can Attack: " + currentActiveController.getPlayer().canAttack());
		//System.out.println("Can Walk: " + currentActiveController.getPlayer().canWalk());
		
		//Update current mouse position
		int xPos = Mouse.getX();
		int yPos = GameEngine.screenHeight - Mouse.getY();
		mouse = "Mouse position: (" + xPos + "," + yPos + ")";
		
		for(int i=0; i<players.length; i++){
			PlayerModel currentController = players[i];
			if(currentController != null){
				//Checking status effects
				currentController.checkStatusEffects();
				
				//Checking userImageChange
				currentController.checkUserImageChange();
				
				//Checking collision from other players
				for(int j=0; j<players.length; j++){
					PlayerModel checkController;
					//Check to see it is another player
					if(j != i && currentController.getPlayer().isAlive()){
						checkController = players[j];
						if(checkController != null){
							currentController.checkCollision(checkController.getPlayer(), checkController.getPlayer().getSkillList());
						}
					}
				}
			
				//Check if player is running to update positioning
				if(currentController.getPlayer().isRunning()){
					currentController.isRunning();
				}
				
				Skill[] currentSkillList = currentController.getPlayer().getSkillList();
				for(int j=0; j<currentSkillList.length; j++){
					//Check if players skills are in use to update positioning
					if(currentSkillList[j] != null && currentSkillList[j].isAttacking()){
						currentController.isAttacking(currentSkillList[j]);
					}
				}
			}
		}
		
		for(int j=0; j<aiModels.length; j++){
			if(aiModels[j] != null){
				aiModels[j].AI();
			}
		}
		
		Input input = gc.getInput();
		if(input.isKeyDown(Input.KEY_1)){
			if(activeSkillList[0] != null && !activeSkillList[0].isPassive()){
				currentActiveController.setCurrentActiveSkill(0);
			}
		}
		if(input.isKeyDown(Input.KEY_2)){
			if(activeSkillList[1] != null && !activeSkillList[1].isPassive()){
				currentActiveController.setCurrentActiveSkill(1);
			}
		}
		if(input.isKeyDown(Input.KEY_3)){
			if(activeSkillList[2] != null && !activeSkillList[2].isPassive()){
				currentActiveController.setCurrentActiveSkill(2);
			}
		}
		if(input.isKeyDown(Input.KEY_4)){
			if(activeSkillList[3] != null && !activeSkillList[3].isPassive()){
				currentActiveController.setCurrentActiveSkill(3);
			}
		}
		if(input.isKeyDown(Input.KEY_5)){
			if(activeSkillList[4] != null && !activeSkillList[4].isPassive()){
				currentActiveController.setCurrentActiveSkill(4);
			}
		}
		
		 for (int i =0;i<players.length;i++){
			 if (!players [i].getPlayer().isAlive()){
				 placingInRound.add(players [i].getPlayer());
			 }		
		 }
		
		
		
		//If left mousebutton is clicked, move the player
		if(input.isMouseButtonDown(1)){
			currentActiveController.move(Mouse.getX(), GameEngine.screenHeight - Mouse.getY());
			//enemyControl.move(generator.nextInt(GameEngine.screenWidth), generator.nextInt(GameEngine.screenHeight-1) + 1);
		}
		//If right mousebutton is clicked, attack that point
		if(input.isMouseButtonDown(0) && currentActiveController.getCurrentActiveSkill().checkCooldown() == currentActiveController.getCurrentActiveSkill().getCoolDown()){
			currentActiveController.attack(Mouse.getX(), GameEngine.screenHeight - Mouse.getY());
		}
		
		if(roundOverCheck()){
			if (shouldCalcGold && nbrOfCurrentPlayers>1){
				goldreward();
				placingInRound.clear();
				currentActiveController.getPlayer().incTotalKills(currentActiveController.getPlayer().getKillsThisRound());
				currentActiveController.getPlayer().setKillsThisRound(0);
			}
			shouldCalcGold=false;
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
		
		
	//	AI();
	}

	
	private boolean roundOverCheck(){
		//Checks if round should be ended
		if(roundOverMultiCheck >= MainHub.syncFrames){
			return true;
		}
		int stillAlive = 0;
		String winningPlayer = null;
		for(int i=0; i<players.length; i++){
			if(players[i] != null){
				if(players[i].getPlayer().isAlive()){
					stillAlive++;
					winningPlayer = players[i].getPlayer().getName();
					placingInRound.add(players[i].getPlayer());
				}
			}
		}
		if(stillAlive == 1){
			roundOverMultiCheck++;
		}else{
			roundOverMultiCheck = 0;
		}
		//Ends round if only 1 player is alive
		if (stillAlive == 1 && nbrOfCurrentPlayers > 1 && roundOverMultiCheck >= MainHub.syncFrames){
			if(firstTimeRoundOver){
				victoryAnimation.resetCounterAndTimer();
				firstTimeRoundOver = false;
			}
			roundOver = true;
			endRoundText = winningPlayer + " " + "wins!";
			return true;
		}
		return false;
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
	
	public void goldreward(){
		int numberofplayers=0;
		int allDamageDone=0;
		double damageDonePercentage;
		double activeRoundMultiplier=0;
		double placingInRoundMultiplier=0;
		int activeplayerkills = players [activePlayer].getPlayer().getKillsThisRound();
		
		// A round that ends fast is an active round that should earn the player more gold.
		if(System.currentTimeMillis()-TimeRoundStart<1000*60*1)
			activeRoundMultiplier = 2;
		else if(System.currentTimeMillis()-TimeRoundStart<1000*60*2)
			activeRoundMultiplier = 1.75;
		else if(System.currentTimeMillis()-TimeRoundStart<1000*60*3)
			activeRoundMultiplier = 1.5;
		else if(System.currentTimeMillis()-TimeRoundStart<1000*60*4)
			activeRoundMultiplier = 1.25;
		else if(System.currentTimeMillis()-TimeRoundStart<1000*60*5)
			activeRoundMultiplier = 1;
	
		for (int i=0;i<players.length;i++){
			if (players [i]!= null){
				allDamageDone += players [i].getPlayer().getRoundDamageDone();
				numberofplayers++;
			}
		}for (int i=0;i<placingInRound.size();i++){
			if (placingInRound.get(i)==players[activePlayer].getPlayer()){
				placingInRoundMultiplier = 0.8+i/10;
			}	
			else placingInRoundMultiplier = 1;
		}
		damageDonePercentage = players[activePlayer].getPlayer().getRoundDamageDone()/allDamageDone;
		
		//Gives the player gold based on how well he/she did this round.
		players [activePlayer].getPlayer().addGold((int)((damageDonePercentage*numberofplayers*10+activeplayerkills*5+20)*activeRoundMultiplier*placingInRoundMultiplier));
	}
}
