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
		
		g.setColor(Color.white);

		g.drawString("1", 70, 200);
		g.drawRect(50, 225, 50, 50);
		g.drawString("2", 125, 200);
		g.drawRect(105, 225, 50, 50);
		g.drawString("3", 185, 200);
		g.drawRect(160, 225, 50, 50);
		g.drawString("4", 235, 200);
		g.drawRect(215, 225, 50, 50);
		g.drawString("5", 295, 200);
		g.drawRect(270, 225, 50, 50);
		
		
		g.drawString("Off", 50, 375);
		g.drawRect(50, 400, 50, 50);
		g.drawRect(150, 400, 50, 50);
		g.drawRect(250, 400, 50, 50);
		g.drawString("Def", 150, 375);
		g.drawRect(50, 475, 50, 50);
		g.drawRect(150, 475, 50, 50);
		g.drawRect(250, 475, 50, 50);
		g.drawString("Mob", 250, 375);
		g.drawRect(50, 550, 50, 50);
		g.drawRect(150, 550, 50, 50);
		g.drawRect(250, 550, 50, 50);
		
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

