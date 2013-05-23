package Model.Items;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.StatusEffects.StatusEffectItems;

public class ItemWizardStaff extends Item {

	Image Image;
	public ItemWizardStaff(){
		
		
		//Price, Name, Description, Slot,
		super (400,"Rod of Endless Power","Rapidly increases the growth \nof the pewnitZ to the \nsize of an infant.", "Weapon");
		try{
			Image = new Image("res/items/Mages Staff.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.setPassive();
		super.setSelfAffectingStatusEffectShell(new StatusEffectItems(this, 0, 0, 1.5, 0));
		super.setImage(Image);
	} 

}
