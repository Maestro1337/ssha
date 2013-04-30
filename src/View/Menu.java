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

public class Menu extends BasicGameState implements ActionListener{

	private String mouse = "No input yet";

	/** The wav sound effect */
	private Audio wavEffect;
	
	private boolean startMusic = true;
	
	Image bg;

	Image backgroundImage;
	Image playButton;
	Image testButton;
	Image exitButton;
	Image titleText;
	
	public Menu (int state){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		
		backgroundImage = new Image("res/bg.png");
		playButton = new Image("res/playButtons.png");
		testButton = new Image("res/testButtons.png");
		exitButton = new Image("res/exitButton.png");
		titleText = new Image("res/title2.png");
		
		try {
			wavEffect = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/sounds/bg-music.wav"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		gc.setFullscreen(false);
		g.setColor(Color.black);
		g.drawImage(backgroundImage, 0, 0);

		if(startMusic){
			wavEffect.playAsSoundEffect(1.0f, 1.0f, true);
			startMusic = false;
		}
		g.drawString(mouse, 500, 20);
		
		g.drawImage(titleText, 380, 100);
		g.drawImage(playButton, 500, 300);
		g.drawImage(testButton, 500, 400);
		g.drawImage(exitButton, 500, 500);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		
		int xPos = Mouse.getX();
		int yPos = 720 - Mouse.getY();
		
		mouse = "Mouse position: (" + xPos + "," + yPos + ")";
		
		Input input = gc.getInput();  
		// Escape key quits the game
        if(input.isKeyDown(Input.KEY_ESCAPE)) gc.exit();
        
		
		if((500<xPos && xPos<750) && (300<yPos && yPos<354)){
			playButton = new Image("res/playButton_hover.png");
			if(input.isMousePressed(0)){ // 0 = leftclick, 1 = rightclick
				playButton = new Image("res/playButton_pressed.png");
				sbg.enterState(3);
			}
		} else if((500<xPos && xPos<750) && (400<yPos && yPos<454)){

			testButton = new Image("res/testButton_hover.png");
			if(input.isMousePressed(0)){ // 0 = leftclick, 1 = rightclick
				testButton = new Image("res/testButton_pressed.png");
				sbg.enterState(2);
			}
		} else if((500<xPos && xPos<750) && (500<yPos && yPos<597)){
			exitButton = new Image("res/exitButton.png");
			if(input.isMousePressed(0)){ // 0 = leftclick, 1 = rightclick
				System.exit(0);
			}
		}else{
			playButton = new Image("res/playButtons.png");
			testButton = new Image("res/testButtons.png");
		}
	}
	
	public int getID(){
		return 0;
	}
	
	public int getWidth(Image image){
		return image.getWidth();
	}
	
	public int getHeight(Image image){
		return image.getHeight();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
}
