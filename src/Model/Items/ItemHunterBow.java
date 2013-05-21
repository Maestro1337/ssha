package Model.Items;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ItemHunterBow extends Item {

	Image Image;
	public ItemHunterBow(){
		
		
		//Price, Name, Description, Bonus,
		super (400,"Cupids Demise","hej");
		try{
			Image = new Image("res/items/Hunters Bow.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.setImage(Image);
	}
}
