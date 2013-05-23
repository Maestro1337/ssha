package Model.Items;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ItemWizardHat extends Item {

	Image Image;
	public ItemWizardHat(){
		
		
		//Price, Name, Description, Slot,
		super (400,"Gandalfs skalles balle","It's Gandalfs heads penis.","Headwear");
		try{
			Image = new Image("res/items/Mages Hat.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.setImage(Image);
	}

}
