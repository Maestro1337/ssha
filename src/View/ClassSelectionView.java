package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;





import Model.MainHub;
import Model.Player;
import Model.Classes.*;
import Model.Obstacles.Obstacle;

public class ClassSelectionView extends BasicGameState implements ActionListener{

	private String mouse = "No input yet";
	
	private boolean isMultiplayer;
	
	Player player = null;
//	private String classType = null;

	Image backgroundImage;
	Image selectButton;
	Image backButton;
	Image classImage;
	
	String classDescription = "";
	String title = "";

	Obstacle[] obstacles = new Obstacle[100];
	

	Image singleplayerButton;
	Image multiplayerButton;
	Image exitButton;
	
	
	public ClassSelectionView (int state){

	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		

		singleplayerButton = new Image("res/buttons/singleplayer.png");
		multiplayerButton = new Image("res/buttons/multiplayer.png");
		exitButton = new Image("res/buttons/exitButton.png");

		backgroundImage = new Image("res/miscImages/sakura001.png");
		selectButton = new Image("res/buttons/selectButton.png");
		backButton = new Image("res/buttons/backButton.png");
		classImage = new Image("res/classImages/classes.png");
		title = "Choose your class!";
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		g.setColor(Color.black);
		g.drawImage(backgroundImage, 0, 0);
		
		g.drawString(mouse, 500, 20);
		
		g.drawImage(selectButton, 700, 550);
		g.drawImage(classImage, 336, 100);
		
		g.drawString(classDescription, 336, 450);
		g.drawString(title, 550, 75);
		
		g.drawImage(backButton, 300, 550);
		

		g.drawImage(singleplayerButton, 900, 300);
		g.drawImage(multiplayerButton, 900, 400);
		g.drawImage(exitButton, 900, 500);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		
		int xPos = Mouse.getX();
		int yPos = 720 - Mouse.getY();
		
		mouse = "Mouse position: (" + xPos + "," + yPos + ")";
		
		/*Random obsGenerator = new Random();
		for(int i=0; i<obsGenerator.nextInt(50); i++){
			obstacles[i] = new ObstaclePillar(obsGenerator.nextInt(1280), obsGenerator.nextInt(719) + 1);
		}*/
		Input input = gc.getInput();
	//	Control = new PlayerController("Player", obsGenerator.nextInt(1280), obsGenerator.nextInt(719) + 1, obstacles, "Warrior");
		if((300<xPos && xPos<550) && (550<yPos && yPos<604)){
			backButton = new Image("res/buttons/backButton_pressed.png");
			if(input.isMousePressed(0)){ // 0 = leftclick, 1 = rightclick
					
					// If you're in the Multiplayer-view and click back, the connection will close.
					if(MainHub.getController().isMulti()) {
						MainHub.getController().getSocketClient().getPlayer().setConnected(false);
						MainHub.getController().getSocketClient().closeConnection();
					}
					sbg.enterState(0);
			}
		}else{
			backButton = new Image("res/buttons/backButton.png");
		}
		if((700<xPos && xPos<950) && (550<yPos && yPos<604)){
			selectButton = new Image("res/buttons/selectButton_pressed.png");
			if(player != null && input.isMousePressed(0)){ // 0 = leftclick, 1 = rightclick
					
				/*	if(!MainHub.getController().isMulti()) {
						MainHub.getController().addPlayer(player, 0);
					} else {
						// Sets the player array-position to their server-id.
						player.setIndex(MainHub.getController().getActivePlayerIndex());
						MainHub.getController().addPlayer(player, MainHub.getController().getActivePlayerIndex());
						MainHub.getController().getSocketClient().changePlayer(player);	
					}*/
	
				if(!(MainHub.getController().isMulti())){	
					MainHub.getController().addPlayer(new ClassWarrior("Enemy", "ai", 600, 600, 1), 1);
					sbg.enterState(5);
				}else{
				//	sbg.enterState(2);
					sbg.enterState(4);
				}
			}
		}else{
			selectButton = new Image("res/buttons/selectButton.png");
		}
		if((340<xPos && xPos<517) && (106<yPos && yPos<425)){
			if(input.isMousePressed(0)){ // 0 = leftclick, 1 = rightclick
				classImage = new Image("res/classImages/warriorSelected.png");
				classDescription = "The heart of a warrior is filled with courage and strength. \n" +
						"Your skills with weapons in close combat makes you a powerful \n" +
						"force on the battlefield and a durable opponent for all who dares \n" +
						"cross your way.";
				player = new ClassWarrior(MainHub.getController().getActivePlayerName(), "player", 120, 100, 0);
		//		Control = new PlayerController("WarriorMan", obsGenerator.nextInt(1280), obsGenerator.nextInt(719) + 1, obstacles, "Warrior");
			}
		}
		if((518<xPos && xPos<719) && (106<yPos && yPos<425)){
			if(input.isMousePressed(0)){ // 0 = leftclick, 1 = rightclick
				classImage = new Image("res/classImages/hunterSelected.png");
				classDescription = "Hunters are stealthy, wealthy and wise to the ways of \n" +
						"their opponents. Able to take down tyrants without blinking an eye \n" +
						"or breaking a bowstring, you'll range far and wide with this class.";
		//		classType = "Hunter";
				player = new ClassHunter(MainHub.getController().getActivePlayerName(), "player", 120, 100, 0);
		//		Control = new PlayerController("HunterMan", obsGenerator.nextInt(1280), obsGenerator.nextInt(719) + 1, obstacles, "Hunter");
			}
		}
		if((720<xPos && xPos<938) && (106<yPos && yPos<425)){
			if(input.isMousePressed(0)){ // 0 = leftclick, 1 = rightclick
				classImage = new Image("res/classImages/mageSelected.png");
				classDescription = "Mages are the quintessential magic user. They attack from \n" +
						"a distance, able to cause great harm or restrict a targets actions using \n" +
						"their supreme knowledge of the elements.";
		//		classType = "Wizard";
				player = new ClassWizard(MainHub.getController().getActivePlayerName(), "player", 160, 300, 0);
		//		Control = new PlayerController("WizardMan", obsGenerator.nextInt(1280), obsGenerator.nextInt(719) + 1, obstacles, "Wizard");
			}
		}
		
		if((900<xPos && xPos<1150) && (300<yPos && yPos<354)){
			singleplayerButton = new Image("res/buttons/singleplayer_pressed.png");
			if(player != null && input.isMousePressed(0)){ // 0 = leftclick, 1 = rightclick
				MainHub.getController().setSingleOrMulti(false);
				MainHub.getController().addPlayer(new ClassWarrior("Enemy", "ai", 600, 600, 1), 1);
				MainHub.getController().addPlayer(player, 0);
				sbg.enterState(5);
			}
		} else if((900<xPos && xPos<1150) && (400<yPos && yPos<454)){
			multiplayerButton = new Image("res/buttons/multiplayer_pressed.png");
			if(player != null && input.isMousePressed(0)){ // 0 = leftclick, 1 = rightclick
				MainHub.getController().setSingleOrMulti(true);
				
				MainHub.getController().getSocketClient().changePlayer(player);
				
				// Try to connect to server.
				MainHub.getController().getSocketClient().getPlayer().setConnected(true);
				MainHub.getController().getSocketClient().findConnection();
				
				
				
				long oldTime = System.currentTimeMillis();
				long timeDiff = 0;
				
				// Wait ca 3 seconds
				while(timeDiff < 3000) {
					timeDiff = System.currentTimeMillis() - oldTime;
					if(MainHub.getController().getSocketClient().getPlayer().isConnected()) {
						break;
					}
				}
				
				// Enter Multiplayer state if and only if SocketClient successfully connecter to the server.
				if(MainHub.getController().getSocketClient().getPlayer().isConnected()) {
					//Setting correct playerindex
					player.setIndex(MainHub.getController().getActivePlayerIndex());
					MainHub.getController().addPlayer(player, MainHub.getController().getActivePlayerIndex());
					//MainHub.getController().getSocketClient().changePlayer(player);	
					sbg.enterState(4);
				} else {
					
				}
			}
		} else if((900<xPos && xPos<1150) && (500<yPos && yPos<597)){
			exitButton = new Image("res/buttons/exitButton_pressed.png");
			if(input.isMousePressed(0)){ // 0 = leftclick, 1 = rightclick
				System.exit(0);
			}
		}else{
			singleplayerButton = new Image("res/buttons/singleplayer.png");
			multiplayerButton = new Image("res/buttons/multiplayer.png");
			exitButton = new Image("res/buttons/exitButton.png");
		}
	}
	
	public int getID(){
		return 3;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
}
