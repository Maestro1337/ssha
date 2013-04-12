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
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Model.*;
import Model.Skills.*;

public class MainView extends BasicGameState implements ActionListener {
	
	Image bg;
	private String mouse = "No input yet";
	
	Player player;
	Skill[] playerSkills;
	
	int enemyHP = 100;
	Image enemyImage;
	float enemyX = 600;
	float enemyY = 300;
	int eWidth;
	int eHeight;
	
	boolean isColliding=false;

	public MainView(int state) {
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		enemyImage = new Image("res/awesomePinkSquare.png");
		player = new Player(190, 90);
		playerSkills = player.getSkills();
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		bg = new Image("res/bg.png");
		
		g.drawImage(bg, 0, 0);
		g.drawImage(bg, 500, 0);
		g.drawImage(bg, 1000, 0);
		g.drawImage(bg, 0, 500);
		g.drawImage(bg, 500, 500);
		g.drawImage(bg, 1000, 500);
		
		g.drawString(mouse, 800, 10);
		
		g.drawString("Enemy HP: "+enemyHP,500,500);
		
		g.drawImage(player.getImage(), player.getX(),player.getY());
		
		
		
		for(int i=0; i<player.getSkills().length; i++){
			g.drawRect(10 + i*50, 660, 50, 50);
			if(playerSkills[i] != null){
				g.fillRect(10 + i*50, 660, 50, 50);
				g.drawString(""+playerSkills[i].checkCooldown(), 10 + i*50, 600);
			}
			
		}
		
		
		if(player.isAttacking()&&!isColliding)
			g.drawImage(player.getAttImage(), player.getAttX(),player.getAttY());
		

		if(isColliding()){
			player.setAttackingState(false);
			player.resetShot();
			enemyHP -= 10;
		}
		if(enemyHP>0){
			g.drawImage(enemyImage, enemyX, enemyY);
		}else{
			enemyX=-1000;
			enemyY=-1000;
		}
	}
	
	float mouseXPosMove;
	float mouseYPosMove;
	
	float mouseXPosAtt;
	float mouseYPosAtt;
	
	Double findNaN;
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		int xPos = Mouse.getX();
		int yPos = 720 - Mouse.getY();
		
		mouse = "Mouse position: (" + xPos + "," + yPos + ")";
		
		Input input = gc.getInput();
		if(input.isKeyDown(Input.KEY_W)){player.addY(-1);}
		if(input.isKeyDown(Input.KEY_S)){player.addY(1);}
		if(input.isKeyDown(Input.KEY_A)){player.addX(-1);}
		if(input.isKeyDown(Input.KEY_D)){player.addX(1);}
		
		if((190<xPos && xPos<290) && (250<yPos && yPos<350)){
			if(input.isMouseButtonDown(0)){ // 0 = leftclick, 1 = rightclick
				sbg.enterState(1);
			}
		}
		
		if(input.isMouseButtonDown(1)){
			move();
		}
		
		if(input.isMouseButtonDown(0)){
			if ((135<xPos && 250>xPos) && (225<yPos && 270>yPos)){
				enemyHP= 100;
				enemyX = 600;
				enemyY = 300;	
			}
			attack();
		}
		if(player.isRunning()){
			isRunning();
		}
		if(player.isAttacking()){
			isAttacking();
		}
	}
	
	private void isRunning(){
		player.addX((float)(player.getXDirMove()*player.getMoveSpeed()));
		player.addY((float)(player.getYDirMove()*player.getMoveSpeed()));
		if(findNaN.isNaN()){
//			imgX = mouseXPosMove;
//			imgY = mouseYPosMove;
			player.setRunningState(false);
		}
		player.incMoveCounter();
		if(player.getMoveCounter()*player.getMoveSpeed() >= player.getGenDirMove())
			player.setRunningState(false);
	}
	
	private void isAttacking(){
		player.addAttX((float)(player.getXDirAtt()*player.getAttSpeed()));
		player.addAttY((float)(player.getYDirAtt()*player.getAttSpeed()));
		
		player.incAttCounter();
		if(player.getAttCounter()*player.getAttSpeed() >= player.getGenDirAtt())
			player.setAttackingState(false);
	}
	
	public boolean isColliding() throws SlickException{
		if((enemyX <= player.getAttX() && player.getAttX() <= enemyX + enemyImage.getWidth()) && (enemyY <= player.getAttY() && player.getAttY() <= enemyY + enemyImage.getHeight()) ){
			return true;
		}else
		return false;
	}
	
	public void move(){
		mouseXPosMove = Mouse.getX();
		mouseYPosMove = 720 - Mouse.getY();
		player.setXDirMove((mouseXPosMove - player.getX()));
		player.setYDirMove((mouseYPosMove - player.getY()));
		player.setGenDirMove((float)Math.sqrt(player.getXDirMove()*player.getXDirMove()+player.getYDirMove()*player.getYDirMove()));
		findNaN = (double)player.getGenDirMove();
		player.setXDirMove(player.getXDirMove()/player.getGenDirMove());
		player.setYDirMove(player.getYDirMove()/player.getGenDirMove());
		
		player.resetMoveCounter();
		
		System.out.println("Running " + player.getGenDirMove() + " pixels");
		player.setRunningState(true);
	}
	
	public void attack(){
		
	//	playerSkills[0].run();
		
		
		if(!player.isAttacking() && playerSkills[0].checkCooldown() == playerSkills[0].getCoolDown()){
			
			playerSkills[0].activateSkill();
			
			mouseXPosAtt = Mouse.getX();
			mouseYPosAtt = 720 - Mouse.getY();
			
			player.resetShot();
			
			player.setXDirAtt((mouseXPosAtt - player.getAttX()));
			player.setYDirAtt((mouseYPosAtt - player.getAttY()));
			player.setGenDirAtt((float)Math.sqrt(player.getXDirAtt()*player.getXDirAtt()+player.getYDirAtt()*player.getYDirAtt()));
			player.setXDirAtt(player.getXDirAtt()/player.getGenDirAtt());
			player.setYDirAtt(player.getYDirAtt()/player.getGenDirAtt());
			
			if(player.getGenDirAtt() > player.getAttackRange()){
				player.setGenDirAtt(player.getAttackRange());
			}
			
			player.resetAttCounter();
			
			System.out.println("Attacking " + player.getGenDirAtt() + " pixels");
			player.setAttackingState(true);
		}
	}
	
	public int getID(){
		return 1;
	}
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
