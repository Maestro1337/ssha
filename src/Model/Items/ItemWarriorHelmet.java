package Model.Items;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ItemWarriorHelmet extends Item {

	Image Image;
	public ItemWarriorHelmet(){
		
		
		//Price, Name, Description, Slot,
		super (400,"The Crusaders Defence","hej","Headwear");
		try{
			Image = new Image("res/items/Warriors Helmet.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.setImage(Image);
	} 

}
