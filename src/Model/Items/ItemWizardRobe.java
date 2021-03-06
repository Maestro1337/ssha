package Model.Items;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.StatusEffects.StatusEffectItems;

public class ItemWizardRobe extends Item {

	Image Image;
	public ItemWizardRobe(){
		
		
		//Price, Name, Description, Slot,
		super (400,"The Scholars' Robe","Increases armor rating by a \nlarge amount","Armor");
		try{
			Image = new Image("res/items/Mages Robe.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.setPassive();
		//Item item, int damage, double movement, double armor
		super.setSelfAffectingStatusEffectShell(new StatusEffectItems(this, 0, 0, 0.4, 0));
		super.setImage(Image);
	}

}
