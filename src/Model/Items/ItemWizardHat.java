package Model.Items;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.StatusEffects.StatusEffectItems;

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
		super.setPassive();
		super.setSelfAffectingStatusEffectShell(new StatusEffectItems(this, 0, 0.3, 1.2, 0));
		super.setImage(Image);
	}

}
