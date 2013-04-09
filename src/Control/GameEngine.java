package Control;


import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import View.MainView;
import View.Menu;

public class GameEngine extends StateBasedGame {

	private static final String gameName = "Super Slash Hose!";
	private static final int menu = 0;
	private static final int play = 1;
	
	public GameEngine(String name){
		super(name);
		this.addState(new Menu(menu));
		this.addState(new MainView(play));
	}
	
	public void initStatesList(GameContainer gc) throws SlickException{
		this.getState(menu).init(gc, this);
		this.getState(play).init(gc, this);
		this.enterState(menu);
	}
	
	public static void main(String[] args) {
		AppGameContainer agc;
		try{
			agc = new AppGameContainer(new GameEngine(gameName));
			agc.setDisplayMode(1280, 720, false);
			agc.start();
		}catch(SlickException ex){
			ex.printStackTrace();
		}
	}

}
