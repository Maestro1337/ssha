package View;

import java.awt.GraphicsDevice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.ResourceLoader;

public class TestView extends BasicGameState implements ActionListener{
	
	int enemyHP = 100;
	private String mouse = "No input yet";
	Image bg;
	
	TiledMap map;
	
	Image userImage;
	float imgX = 400;
	float imgY = 250;
	
	Image enemyImage;
	float enemyX = 600;
	float enemyY = 300;
	int eWidth;
	int eHeight;
	
	Image barImg;
	Image hpBackImg;
	Image hpForeImg;
	
	Image attackImage;
	Image firstSlash;
	Image secondSlash;
	Image thirdSlash;
	Image fourthSlash;
	
	float aImgX = 0;
	float aImgY = 0;

	int hpX = 500;
	int hpY = 500;
	int fillX = 514;
	int fillY = 507;
	
	boolean isColliding=false;
	
	@SuppressWarnings("unused")
	private boolean fullScreen = true;
	private boolean paused;
	
	Image user, move1, move2;
	
	Animation userAni, stand, move;
	
	
	public TestView (int state){
		
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		
		Image [] movement = {new Image("res/animations/hunter_walk1.png"), new Image("res/animations/hunter_walk2.png")};
		Image [] standing = {new Image("res/animations/hunter_stand.png"), new Image("res/animations/hunter_stand.png")};
		int [] duration = {300, 300};
		
		enemyImage = new Image("res/miscImages/awesomePinkSquare.png");
		userImage = new Image("res/animations/hunter_stand.png");
		
		firstSlash = new Image("res/animations/slash1.png");
		firstSlash.setName("slash1");
		
		secondSlash= new Image("res/animations/slash2.png");
		secondSlash.setName("slash2");
		
		thirdSlash= new Image("res/animations/slash3.png");
		thirdSlash.setName("slash3");
		
		fourthSlash= new Image("res/animations/slash4.png");
		fourthSlash.setName("slash4");
		
		attackImage = firstSlash;
		
		
		stand = new Animation(standing, duration, false);
		move = new Animation(movement, duration, false);
		
		userAni = stand;
		
		barImg = new Image("res/miscImages/bar_hp.png");
		hpBackImg = new Image("res/miscImages/gray_hp.png");
		hpForeImg = new Image("res/miscImages/red_hp.png");
		
		map = new TiledMap("res/tileset/testbackground.tmx");
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		gc.setFullscreen(false);
		map.render(0, 0);
		
		
		userAni.draw((int)imgX, (int)imgY);
		
		//user.draw(imgX,imgY);
		
		g.setColor(Color.white);

		g.drawString(mouse, 200, 500);
		
		g.drawString("Enemy HP: "+enemyHP,500,600);
		
		//g.drawImage(userImage, imgX,imgY);
		
		if(isAttacking&&!isColliding)
			g.drawImage(attackImage, aImgX,aImgY);

		//g.drawImage(userImage, shiftX,shiftY);
		
		//userImage.draw(imgX,imgY);

		if(isColliding()){
			aImgY=1000;
			aImgX=1000;
			genDirAtt=0;
			enemyHP -= 10;

			playSound("res/sounds/takingDamage.wav");
		}
		if(enemyHP>0){
			g.drawImage(enemyImage, enemyX, enemyY);
			
			g.drawImage(barImg, hpX, hpY);
			g.setColor(Color.darkGray);
			g.fillRect(fillX, fillY, 100, 16);
			g.setColor(Color.red);
			g.fillRect(fillX, fillY, enemyHP, 16);
			g.setColor(Color.black);
			
		}else{
			enemyX=-1000;
			enemyY=-1000;
		}
			
	}
	
	
	boolean isRunning = false;
	boolean isAttacking = false;
	
	
	float mouseXPosMove;
	float mouseYPosMove;
	double moveSpeed = 0.5;

	
	float mouseXPosAtt;
	float mouseYPosAtt;
	float attSpeed = (float)1;
	
	int moveCounter=0;//
	float xDirectionMove;//
	float yDirectionMove;//
	float genDirMove;//
	Double findNaN;
	
	int attCounter=0;//
	float xDirAtt;//
	float yDirAtt;//
	float genDirAtt;//

	float attackRange = 200;//
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		
		int xPos = Mouse.getX();
		int yPos = 720 - Mouse.getY();
		
		mouse = "Mouse position: (" + xPos + "," + yPos + ")";
		
		Input input = gc.getInput();  
		// Escape key quits the game
        if(input.isKeyDown(Input.KEY_ESCAPE)) gc.exit();

        // P key will pause/unpause the game but still allow Escape key to quit
        if(input.isKeyDown(Input.KEY_P)) {
            if(paused == false) paused = true;
            else paused = false;
        }
        
        // Jump over the other key presses
        if(paused == true) return;
		if(input.isKeyDown(Input.KEY_W)){
			userAni = move;
			userAni.update(delta);
			// The lower the delta the slowest the sprite will animate.
			imgY -= delta * 0.1f;;}
		if(input.isKeyDown(Input.KEY_S)){
			userAni = move;
			userAni.update(delta);
			// The lower the delta the slowest the sprite will animate.
			imgY += delta * 0.1f;;}
		if(input.isKeyDown(Input.KEY_A)){
			userAni = move;
			userAni.update(delta);
			// The lower the delta the slowest the sprite will animate.
			imgX -= delta * 0.1f;;}
		if(input.isKeyDown(Input.KEY_D)){
			userAni = move;
			userAni.update(delta);
			// The lower the delta the slowest the sprite will animate.
			imgX += delta * 0.1f;;}
		
		if(input.isMouseButtonDown(1)){
			userAni = move;
			userAni.update(delta);
			move();
		}
		
		if(input.isMouseButtonDown(0)){
			if ((135<xPos && 250<xPos) && (225<yPos && 270<yPos)){
				enemyHP= 100;
				enemyX = 600;
				enemyY = 300;	
			}
				
			attack();
		}
		if(isRunning){
			isRunning();
			if(userImage == user || userImage == move2)
				userImage = move1;
			else if(userImage == user || userImage == move1)
				userImage = move2;
		} else {
			userImage = user;
		}
		if(isAttacking){
			isAttacking();
		}
		 // Tell the player the game is paused
        if(imgX>760) {
        	imgX = 760;
        }
        if(imgY>440){
        	imgY = 440;
        }
        if(imgX<0){
        	imgX = 0;
        }
        if(imgY<0){
        	imgY = 0;
        }
		
		
	}
	
	private void isRunning(){
		imgX += xDirectionMove*moveSpeed;
		imgY += yDirectionMove*moveSpeed;
		if(findNaN.isNaN()){
			imgX = mouseXPosMove;
			imgY = mouseYPosMove;
			isRunning = false;
		}
		moveCounter++;
		if(moveCounter*moveSpeed >= genDirMove)
			isRunning = false;
	}
	
	private void isAttacking(){
		aImgX += xDirAtt*attSpeed;
		aImgY += yDirAtt*attSpeed;
		
		attCounter++;
		if(attCounter*attSpeed >= genDirAtt)
			isAttacking = false;
	}
	
	public boolean isColliding() throws SlickException{
		if((enemyX <= aImgX && aImgX <= enemyX + enemyImage.getWidth()) && (enemyY <= aImgY && aImgY <= enemyY + enemyImage.getHeight()) ){
			playSound("res/sounds/explode.wav");
			return true;
		}else
			return false;
	}
	
	public void move(){
		mouseXPosMove = Mouse.getX();
		mouseYPosMove = 720 - Mouse.getY();
		xDirectionMove = (mouseXPosMove - imgX);
		yDirectionMove = (mouseYPosMove - imgY);
		genDirMove = (float)Math.sqrt(xDirectionMove*xDirectionMove+yDirectionMove*yDirectionMove);
		findNaN = (double)genDirMove;
		xDirectionMove = xDirectionMove/genDirMove;
		yDirectionMove = yDirectionMove/genDirMove;
		
		moveCounter=0;
		
		isRunning = true;
	}
	
	public void attack(){
		
		if(!isAttacking){
			switch (attackImage.getName()) {
		
            case "slash1":attackImage = secondSlash;
                     break;
            case "slash2":attackImage = thirdSlash;
                     break;
            case "slash3":attackImage = fourthSlash;
                     break;
            case "slash4":attackImage = firstSlash;
                     break;
        }
			if(userImage == user || userImage == move2)
				userImage = move1;
			else if(userImage == user || userImage == move1)
				userImage = move2;
			
			mouseXPosAtt = Mouse.getX();
			mouseYPosAtt = 720 - Mouse.getY();
			
			aImgX = imgX;
			aImgY = imgY;
			
			xDirAtt = (mouseXPosAtt - aImgX);
			yDirAtt = (mouseYPosAtt - aImgY);
			genDirAtt = (float)Math.sqrt(xDirAtt*xDirAtt+yDirAtt*yDirAtt);
			xDirAtt = xDirAtt/genDirAtt;
			yDirAtt = yDirAtt/genDirAtt;
			
			if(genDirAtt > attackRange){
				genDirAtt = attackRange;
			}
			
			attCounter=0;
			
			isAttacking = true;
			
			playSound("res/sounds/bowShot.wav");
		}
	}
	
	public int getID(){
		return 2;
	}
	
	public int getWidth(Image image){
		return image.getWidth();
	}
	
	public int getHeight(Image image){
		return image.getHeight();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		@SuppressWarnings("unused")
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
			playSound("res/sounds/bg-music.wav");
		} else if(e.getActionCommand() == "Firestorm"){
			playSound("res/sounds/explode.wav");
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
