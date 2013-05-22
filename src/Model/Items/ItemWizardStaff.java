package Model.Items;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ItemWizardStaff extends Item {

	Image Image;
	public ItemWizardStaff(){
		
		
		//Price, Name, Description, Slot,
		super (400,"Rod of Endless Power","hej","Weapon");
		try{
			Image = new Image("res/items/Mages Staff.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.setImage(Image);
	} 

}
