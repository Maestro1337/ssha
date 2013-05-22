package Model.Items;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ItemWizardRobe extends Item {

	Image Image;
	public ItemWizardRobe(){
		
		
		//Price, Name, Description, Slot,
		super (400,"The Scholars' Robe","hej","Armor");
		try{
			Image = new Image("res/items/Mages Robe.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.setImage(Image);
	}

}
