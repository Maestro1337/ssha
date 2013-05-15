package Control;


import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import sshaclient.Constants;
import sshaclient.SocketClient;
import sshaclient.TestController;
import sshaclient.TestView;

import Model.Player;
import Model.Classes.ClassWizard;
import View.*;

public class GameEngine extends StateBasedGame {

	private static final String gameName = "Super Slash Hose!";
	private static final int menu = 0;
	private static final int play = 1;
	private static final int multiplayer = 2;
	private static final int selection = 3;
	private static final int shop = 4;
	
	public GameEngine(String name){
		super(name);
		this.addState(new Menu(menu));
		this.addState(new MainView(play));
		this.addState(new MultiplayerView(multiplayer));
		this.addState(new ClassSelectionView(selection));
		this.addState(new ShoppingView(shop));
	}
	
	public void initStatesList(GameContainer gc) throws SlickException{
		this.getState(menu).init(gc, this);
		this.getState(play).init(gc, this);
//		this.getState(multiplayer).init(gc, this);
		this.getState(selection).init(gc, this);
		this.getState(shop).init(gc, this);
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
		
		TestView tv = new TestView();
		Player tp = new ClassWizard(Constants.name, "player", 100, 100, -1);
		SocketClient sc = new SocketClient(Constants.hostName, Constants.defaultPort, tp);
		Thread t2 = new Thread(sc);
		t2.start();
		TestController tc = new TestController(tv, tp, sc); 
		Thread t = new Thread(tc);
		t.start();
	}

}
