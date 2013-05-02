package View;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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

import Control.*;

public class MainView extends BasicGameState implements ActionListener {
	
	Image bg;
	private String mouse = "No input yet";
	
	String test;
	
	private PlayerController Control; 
	private PlayerController enemyControl;
	Player player;
	Player enemy;
	Skill[] playerSkills;
	Skill activeSkill;
	
	TiledMap map;
	
	Image enemyImage;
	Skill[] enemySkills;
	
	Obstacle[] obstacles = new Obstacle[100];
	
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
		initRound();
		
	}
	
	public void initRound() throws SlickException{
		Random obsGenerator = new Random();
		for(int i=0; i<obsGenerator.nextInt(50); i++){
			obstacles[i] = new ObstaclePillar(obsGenerator.nextInt(1280), obsGenerator.nextInt(719) + 1);
		}
		
		Control = new PlayerController(GlobalClassSelector.getController().getPlayer(), obstacles);
	//	player = Control.getPlayer();
		player = GlobalClassSelector.getController().getPlayer();
		playerSkills = Control.getPlayerSkills();
		
		Control.ressurectPlayer();

		userImage = player.getImage();
		
		Control.checkSpawnCollision();

		enemyControl = new PlayerController(new ClassWarrior("Enemy", obsGenerator.nextInt(1280), obsGenerator.nextInt(719) + 1), obstacles);
		

		enemy = enemyControl.getPlayer();
		enemySkills = enemyControl.getPlayerSkills();
		enemyImage = enemy.getImage();
		enemyControl.ressurectPlayer();
		
		enemyControl.checkSpawnCollision();
		
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
		
		//Show the coodinates for the mouse
		g.drawString(mouse, 900, 10);
		//Show the stats
		
		
		g.drawString(player.getName() + "\nHP: "+player.getHP() + "\nArmor: " + (int)(player.getArmor()*100) + "%\nKills: " + player.getKills(),900,25);
		g.drawString(enemy.getName() + "\nHP: "+enemy.getHP() + "\nArmor: " + (int)(enemy.getArmor()*100) + "%\nKills: " + enemy.getKills(),1050,25);
		//Draw the player
		g.drawImage(userImage, player.getX(),player.getY());
	
		
		
		for(int i=0; i<obstacles.length; i++){
			if(obstacles[i] != null){
				g.drawImage(obstacles[i].getImage(), obstacles[i].getX(), obstacles[i].getY());
			}
		}
		//Random Comment
		//Draw the actionbar
		for(int i=0; i<player.getSkills().length; i++){
			g.setColor(Color.white);
			g.fillRect(10 + i*64, 640, 64, 64);
			g.setColor(Color.black);
			
			if(playerSkills[i] != null){
				g.drawString(""+playerSkills[i].checkCooldown(), playerSkills[i].getSkillBarImage().getWidth()/2 + i*64, 660);
				if(playerSkills[i].checkCooldown() == playerSkills[i].getCoolDown()){
					g.drawImage(playerSkills[i].getSkillBarImage(),10 + i*64, 640);
				}
			}
		}
		
		for(int i=0; i<playerSkills.length; i++){
			if(playerSkills[i] != null){
				if(playerSkills[i].isAttacking()/* && !playerSkills[i].isColliding() */&& !playerSkills[i].isEndState()){
					g.drawImage(playerSkills[i].getAttImage(), playerSkills[i].getAttX(),playerSkills[i].getAttY());
				}else if(playerSkills[i].isEndState()){
					g.drawImage(playerSkills[i].getEndStateImage(), playerSkills[i].getAttX(),playerSkills[i].getAttY());
				}
			}
		}

		
	//	if(enemy.getHP()>0){
			g.drawImage(enemyImage, enemy.getX(), enemy.getY());
			
			//Copy for enemy attack render
			for(int i=0; i<enemySkills.length; i++){
				if(enemySkills[i] != null){
					if(enemySkills[i].isAttacking()/* && !playerSkills[i].isColliding() */&& !enemySkills[i].isEndState()){
						g.drawImage(enemySkills[i].getAttImage(), enemySkills[i].getAttX(),enemySkills[i].getAttY());
					}else if(enemySkills[i].isEndState()){
						g.drawImage(enemySkills[i].getEndStateImage(), enemySkills[i].getAttX(),enemySkills[i].getAttY());
					}
				}
			}
		//}
	}
	
	
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		int xPos = Mouse.getX();
		int yPos = 720 - Mouse.getY();
	/*	if(GlobalClassSelector.getController().checkPlayerAddition()){
			player.changePlayerClass(GlobalClassSelector.getController().getPlayer());
		}*/
		
		Control.checkCollision(enemySkills);
		enemyControl.checkCollision(playerSkills);
		
		Control.checkStatusEffects();
		enemyControl.checkStatusEffects();
		
		mouse = "Mouse position: (" + xPos + "," + yPos + ")";
		
		Input input = gc.getInput();
		if(input.isKeyDown(Input.KEY_Q)){player.addY(-1);}
		if(input.isKeyDown(Input.KEY_S)){player.addY(1);}
		if(input.isKeyDown(Input.KEY_A)){player.addX(-1);}
		if(input.isKeyDown(Input.KEY_D)){player.addX(1);}
		
		if(input.isKeyDown(Input.KEY_1)){
			if(playerSkills[0] != null){
				Control.setCurrentActiveSkill(0);
				//enemyControl.setCurrentActiveSkill(0);
			}
		}
		if(input.isKeyDown(Input.KEY_2)){
			if(playerSkills[1] != null){
				Control.setCurrentActiveSkill(1);
				//enemyControl.setCurrentActiveSkill(1);
			}
		}
		if(input.isKeyDown(Input.KEY_3)){
			if(playerSkills[2] != null){
				Control.setCurrentActiveSkill(2);
				//enemyControl.setCurrentActiveSkill(2);
			}
		}
		if(input.isKeyDown(Input.KEY_4)){
			if(playerSkills[3] != null){
				Control.setCurrentActiveSkill(3);
				//enemyControl.setCurrentActiveSkill(3);
			}
		}
		if(input.isKeyDown(Input.KEY_5)){
			if(playerSkills[4] != null){
				Control.setCurrentActiveSkill(4);
				//enemyControl.setCurrentActiveSkill(4);
			}
		}
		
		//Random generator = new Random();
		//If left mousebutton is clicked, move the player
		if(input.isMouseButtonDown(1)){
			Control.move(Mouse.getX(), 720 - Mouse.getY());
			//enemyControl.move(generator.nextInt(1280), generator.nextInt(719) + 1);
		}
		//If right mousebutton is clicked, attack that point
		if(input.isMouseButtonDown(0)){
			if ((135<xPos && 250>xPos) && (225<yPos && 270>yPos)){
				enemyControl.ressurectPlayer();
				Control.ressurectPlayer();
			}
			Control.attack(Mouse.getX(), 720 - Mouse.getY());
		}
		if(player.isRunning()){
			Control.isRunning();
			if(userImage == player.getImage() || userImage == player.getSecondStepImage())
				userImage = player.getFirstStepImage();
			else if(userImage == player.getImage() || userImage == player.getFirstStepImage())
				userImage = player.getSecondStepImage();
		} else {
			userImage = player.getImage();
		}
		
		//Copy for enemy running
		if(enemy.isRunning()){
			enemyControl.isRunning();
			if(enemyImage == enemy.getImage() || enemyImage == enemy.getSecondStepImage())
				enemyImage = enemy.getFirstStepImage();
			else if(enemyImage == enemy.getImage() || enemyImage == enemy.getFirstStepImage())
				enemyImage = enemy.getSecondStepImage();
		} else {
			enemyImage = enemy.getImage();
		}
		
		
		
		
		
		for(int i=0; i<playerSkills.length; i++){
			if(playerSkills[i] != null && playerSkills[i].isAttacking()){
				Control.isAttacking(playerSkills[i]);
			}
		}
		
		//Copy for enemy attacking
		for(int i=0; i<enemySkills.length; i++){
			if(enemySkills[i] != null && enemySkills[i].isAttacking()){
				enemyControl.isAttacking(enemySkills[i]);
			}
		}
		if (!enemy.isAlive()){
		//	enemy
			Control.getPlayer().incKills();
			sbg.enterState(4);
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
		for(int i=0;i<enemyControl.getPlayerSkills().length;i++){
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
