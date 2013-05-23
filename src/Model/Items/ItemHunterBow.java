package Model.Items;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.StatusEffects.StatusEffectItems;

public class ItemHunterBow extends Item {

	Image Image;
	public ItemHunterBow(){
		//Price, Name, Description, Slot,
		super (400,"Cupids Demise","Makes sexbuddies become \nangry ex girlfriends.","Weapon");
		try{
			Image = new Image("res/items/Hunters Bow.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.setPassive();
		super.setSelfAffectingStatusEffectShell(new StatusEffectItems(this, 0, 0, 1, 0));
		super.setImage(Image);
	}
}
