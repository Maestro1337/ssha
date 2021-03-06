package Model.Items;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.StatusEffects.StatusEffectItems;

public class ItemWizardHat extends Item {

	Image Image;
	public ItemWizardHat(){
		
		
		//Price, Name, Description, Slot,
		super (400,"Wizard's pride","A pointy hat that increases\narmor and movementspeed.","Headwear");
		try{
			Image = new Image("res/items/Mages Hat.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.setPassive();
		super.setSelfAffectingStatusEffectShell(new StatusEffectItems(this, 0, 0.3, 0.2, 0));
		super.setImage(Image);
	}

}
