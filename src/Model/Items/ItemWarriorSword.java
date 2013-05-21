package Model.Items;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ItemWarriorSword extends Item {

	Image Image;
	public ItemWarriorSword(){
		
		
		//Price, Name, Description, Bonus,
		super (400,"The Sword of a Thousand Truths","hej");
		try{
			Image = new Image("res/items/Warriors Sword.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.setImage(Image);
	} 

}
