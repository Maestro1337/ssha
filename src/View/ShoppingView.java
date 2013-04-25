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


public class ShoppingView extends BasicGameState {
	
	Image playButton;
	


	public ShoppingView (int state){
		
	}
	
	public int getID(){
		return 4;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)throws SlickException {
		
		playButton = new Image("res/playButtons.png");
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)throws SlickException {
		g.setColor(Color.black);
		g.drawImage(playButton, 500, 300);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException {
		
		int xPos = Mouse.getX();
		int yPos = 720 - Mouse.getY();
		
		Input input = gc.getInput();
		
	
		if((500<xPos && xPos<750) && (300<yPos && yPos<354)){
			playButton = new Image("res/playButton_hover.png");
			if(input.isMousePressed(0)){ // 0 = leftclick, 1 = rightclick
				playButton = new Image("res/playButton_pressed.png");
				sbg.enterState(1);
			}
		
		
		}
	}
}

