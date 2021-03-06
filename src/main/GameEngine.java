package main;


import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import viewControl.*;


public class GameEngine extends StateBasedGame {

	private static final String gameName = "Super Slash Homies Arena";
	private static final int menu = 0;
	private static final int play = 1;
	private static final int selection = 3;
	private static final int shop = 4;
	private static final int options = 5;
	private static final int maxFPS = 60;
	public static final int screenHeight = 720;
	public static final int screenWidth = 1280;
	
	public GameEngine(String name){
		super(name);
		this.addState(new Menu(menu));
		this.addState(new MainView(play));
		this.addState(new ClassSelectionView(selection));
		this.addState(new ShoppingView(shop));
		this.addState(new OptionsView(options));
	}
	
	public void initStatesList(GameContainer gc) throws SlickException{
		this.getState(menu).init(gc, this);
		this.getState(play).init(gc, this);
		this.getState(selection).init(gc, this);
		this.getState(shop).init(gc, this);
		this.getState(options).init(gc,this);
		this.enterState(menu);
	}
	public static void main(String[] args) {
		AppGameContainer agc;
		try{
			agc = new AppGameContainer(new GameEngine(gameName));
			agc.setTargetFrameRate(maxFPS);
			agc.setDisplayMode(screenWidth, screenHeight, false);
			agc.start();
		}catch(SlickException ex){
			ex.printStackTrace();
		}
	}
}
