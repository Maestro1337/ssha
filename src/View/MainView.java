package View;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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

import Model.*;
import Model.Skills.*;

import Control.*;

public class MainView extends BasicGameState implements ActionListener {
	
	Image bg;
	private String mouse = "No input yet";
	
	private PlayerController Control, enemyControl;
	Player player,enemy;
	Skill[] playerSkills;
	Skill activeSkill;
	
	Image enemyImage;
	
/*	int eWidth;
	int eHeight;*/
	
	
	Image userImage;
	Image user;
	Image move1;
	Image move2;
	Image slash;
	Image fireball;
	Image firestorm;
	Image iceneedle;
	Image pedobear;
	
	float mouseXPosMove;
	float mouseYPosMove;
	
	float prevMouseXPosMove;
	float prevMouseYPosMove;
	
	
	float mouseXPosAtt;
	float mouseYPosAtt;
	

	public MainView(int state) {
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		
	
		
		slash = new Image("res/slash.png");
		fireball = new Image("res/fireball.png");
		firestorm = new Image("res/Firestorm.png");
		iceneedle = new Image("res/iceneedle.png");
		pedobear = new Image("res/pbs4.png");
		
		user = new Image("res/stand.png");
		move1 = new Image("res/walk1.png");
		move2 = new Image("res/walk2.png");
		
		Control = new PlayerController("Player", 190, 90);
		enemyControl = new PlayerController("Enemy", 600,300);

//		enemy = Control.getEnemy();
		enemy = enemyControl.getPlayer();
		enemyImage = new Image("res/awesomePinkSquare.png");
		enemy.resetHP();
			
			
		player = Control.getPlayer();
		playerSkills = Control.getPlayerSkills();
		
		player.resetHP();
		
		userImage = player.getImage();
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		
		//Background
		bg = new Image("res/bg.png");
		//Draw the background
		g.drawImage(bg, 0, 0);
		g.drawImage(bg, 500, 0);
		g.drawImage(bg, 1000, 0);
		g.drawImage(bg, 0, 500);
		g.drawImage(bg, 500, 500);
		g.drawImage(bg, 1000, 500);
		//Show the coodinates for the mouse
		g.drawString(mouse, 800, 10);
		//Show the enemys hp
		g.drawString(enemy.getName() + " HP: "+enemy.getHP(),500,500);
		g.drawString(player.getName() + " HP: "+player.getHP(),500,515);
		//Draw the player
		g.drawImage(userImage, player.getX(),player.getY());
	
		
		
		
		
		//Draw the actionbar
		for(int i=0; i<player.getSkills().length; i++){
			g.setColor(Color.white);
			g.fillRect(10 + i*50, 660, 50, 50);
			g.setColor(Color.black);
			g.drawString(""+playerSkills[i].checkCooldown(), 30 + i*50, 675);
			if(playerSkills[i] != null){
				if(playerSkills[i].checkCooldown() == playerSkills[i].getCoolDown()){
				//	System.out.println(playerSkills[i].getName());
					switch (playerSkills[i].getName()) {
						
			            case "Slash":g.drawImage(slash,10 + i*50, 660);
			                     break;
			            case "Fireball":g.drawImage(fireball,10 + i*50, 660);
			                     break;
			            case "Firestorm":g.drawImage(firestorm,10 + i*50, 660);
			                     break;
			            case "IceNeedle":g.drawImage(iceneedle,10 + i*50, 660);
			                     break;
			            case "SuperSlowTestSkill":g.drawImage(pedobear,10 + i*50, 660);
			                     break;
			        }
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
		
		
		

		
		if(enemy.getHP()>0){
			g.drawImage(enemyImage, enemy.getX(), enemy.getY());
		}
	}
	
	
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		int xPos = Mouse.getX();
		int yPos = 720 - Mouse.getY();
		
		enemyControl.checkCollision(playerSkills);
		
		mouse = "Mouse position: (" + xPos + "," + yPos + ")";
		
		Input input = gc.getInput();
		if(input.isKeyDown(Input.KEY_Q)){player.addY(-1);}
		if(input.isKeyDown(Input.KEY_S)){player.addY(1);}
		if(input.isKeyDown(Input.KEY_A)){player.addX(-1);}
		if(input.isKeyDown(Input.KEY_D)){player.addX(1);}
		
		if(input.isKeyDown(Input.KEY_1)){
			if(playerSkills[0] != null){
				Control.setCurrentActiveSkill(0);
				slash = new Image("res/slash_active.png");
				fireball = new Image("res/fireball.png");
				firestorm = new Image("res/Firestorm.png");
				iceneedle = new Image("res/iceneedle.png");
				pedobear = new Image("res/pbs4.png");
			}
		}
		if(input.isKeyDown(Input.KEY_2)){
			if(playerSkills[1] != null){
				Control.setCurrentActiveSkill(1);
				slash = new Image("res/slash.png");
				fireball = new Image("res/fireball_active.png");
				firestorm = new Image("res/Firestorm.png");
				iceneedle = new Image("res/iceneedle.png");
				pedobear = new Image("res/pbs4.png");
			}
		}
		if(input.isKeyDown(Input.KEY_3)){
			if(playerSkills[2] != null){
				Control.setCurrentActiveSkill(2);
				slash = new Image("res/slash.png");
				fireball = new Image("res/fireball.png");
				firestorm = new Image("res/firestorm_active.png");
				iceneedle = new Image("res/iceneedle.png");
				pedobear = new Image("res/pbs4.png");
			}
		}
		if(input.isKeyDown(Input.KEY_4)){
			if(playerSkills[3] != null){
				Control.setCurrentActiveSkill(3);
				slash = new Image("res/slash.png");
				fireball = new Image("res/fireball.png");
				firestorm = new Image("res/Firestorm.png");
				iceneedle = new Image("res/iceneedle_active.png");
				pedobear = new Image("res/pbs4.png");
			}
		}
		if(input.isKeyDown(Input.KEY_5)){
			if(playerSkills[4] != null){
				Control.setCurrentActiveSkill(4);
				slash = new Image("res/slash.png");
				fireball = new Image("res/fireball.png");
				firestorm = new Image("res/Firestorm.png");
				iceneedle = new Image("res/iceneedle.png");
				pedobear = new Image("res/pbs4_active.png");
			}
		}
		
		
		if((190<xPos && xPos<290) && (250<yPos && yPos<350)){
			if(input.isMouseButtonDown(0)){ // 0 = leftclick, 1 = rightclick
				sbg.enterState(1);
			}
		}
		//If left mousebutton is clicked, move the player
		if(input.isMouseButtonDown(1)){
			Control.rotate();
			Control.move();
		}
		//If right mousebutton is clicked, attack that point
		if(input.isMouseButtonDown(0)){
			if ((135<xPos && 250>xPos) && (225<yPos && 270>yPos)){
//				Control.setEnemyHP(100);
				enemy.resetHP();
			}
			Control.rotate();
			Control.attack();
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
		for(int i=0; i<playerSkills.length; i++){
			if(playerSkills[i] != null && playerSkills[i].isAttacking()){
				Control.isAttacking(playerSkills[i]);
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
		String fileName = "res/154561__ecfike__hurt-argh-1.wav";
		if(e.getActionCommand() == "Taking damage"){
			playSound("res/154561__ecfike__hurt-argh-1.wav");
		} else if(e.getActionCommand() == "Sword"){
			playSound("res/77611__joelaudio__sfx-attack-sword-001.wav");
		} else if(e.getActionCommand() == "Bow"){
			playSound("res/65733__erdie__bow01.wav");
		} else if(e.getActionCommand() == "Fireball"){
			playSound("res/77691__joelaudio__sfx-magic-fireball-001.wav");
		} else if(e.getActionCommand() == "Music"){
			playSound("res/126427__cabeeno-rossley__timer-first-half-loop.wav");
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
