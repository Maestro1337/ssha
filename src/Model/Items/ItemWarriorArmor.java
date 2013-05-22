package Model.Items;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ItemWarriorArmor extends Item {

	Image Image;
	public ItemWarriorArmor(){
		
		
		//Price, Name, Description, Slot,
		super (400,"The Rightious' Blessing","hej","Armor");
		try{
			Image = new Image("res/items/Warrior Breastplate.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.setImage(Image);
	}

}
