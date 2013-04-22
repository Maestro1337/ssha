package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class ClassSelectionView extends BasicGameState implements ActionListener{

	private String mouse = "No input yet";

	Image backgroundImage;
	Image selectButton;
	Image classImage;
	
	String classDescription = "";
	String title = "";
	
	public ClassSelectionView (int state){

	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{

		backgroundImage = new Image("res/sakura001.png");
		selectButton = new Image("res/playButtons.png");
		classImage = new Image("res/classes.png");
		title = "Choose your class!";
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		g.setColor(Color.black);
		g.drawImage(backgroundImage, 0, 0);
		
		g.drawString(mouse, 500, 20);
		
		g.drawImage(selectButton, 500, 550);
		g.drawImage(classImage, 336, 100);
		
		g.drawString(classDescription, 336, 450);
		g.drawString(title, 550, 75);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		
		int xPos = Mouse.getX();
		int yPos = 720 - Mouse.getY();
		
		mouse = "Mouse position: (" + xPos + "," + yPos + ")";
		
		Input input = gc.getInput();
		
		if((500<xPos && xPos<750) && (550<yPos && yPos<604)){
			selectButton = new Image("res/playButton_hover.png");
			if(input.isMousePressed(0)){ // 0 = leftclick, 1 = rightclick
				selectButton = new Image("res/playButton_pressed.png");
				sbg.enterState(1);
			}
		}else{
			selectButton = new Image("res/playButtons.png");
		}
		if((340<xPos && xPos<517) && (106<yPos && yPos<425)){
			if(input.isMousePressed(0)){ // 0 = leftclick, 1 = rightclick
				classImage = new Image("res/warriorSelected.png");
				classDescription = "The heart of a warrior is filled with courage and strength. \n" +
						"Your skills with weapons in close combat makes you a powerful \n" +
						"force on the battlefield and a durable opponent for all who dares \n" +
						"cross your way";
			}
		}
		if((518<xPos && xPos<719) && (106<yPos && yPos<425)){
			if(input.isMousePressed(0)){ // 0 = leftclick, 1 = rightclick
				classImage = new Image("res/hunterSelected.png");
				classDescription = "Hunters are stealthy, wealthy and wise to the ways of \n" +
						"their opponents. Able to take down tyrants without blinking an eye \n" +
						"or breaking a bowstring, you'll range far and wide with this class.";
			}
		}
		if((720<xPos && xPos<938) && (106<yPos && yPos<425)){
			if(input.isMousePressed(0)){ // 0 = leftclick, 1 = rightclick
				classImage = new Image("res/mageSelected.png");
				classDescription = "Mages are the quintessential magic user. They attack from \n" +
						"a distance, able to cause great harm or restrict a targets actions using \n" +
						"their supreme knowledge of the elements.";
			}
		}
	}
	
	public int getID(){
		return 3;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
}
