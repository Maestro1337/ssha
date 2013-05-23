package Model.Items;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.StatusEffects.StatusEffectArmor;

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
		super.setPassive();
		super.setSelfAffectingStatusEffectShell(new StatusEffectArmor(this, 1.5, 0));
		super.setImage(Image);
	}

}
