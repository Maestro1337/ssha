package Model.Items;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.StatusEffects.StatusEffectItems;

public class ItemWarriorSword extends Item {

	Image Image;
	public ItemWarriorSword(){
		
		
		//Price, Name, Description, Slot,
		super (400,"The Sword of a Thousand Truths","Stored on a USB.","Weapon");
		try{
			Image = new Image("res/items/Warriors Sword.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.setPassive();
		super.setSelfAffectingStatusEffectShell(new StatusEffectItems(this, 0, 0, 0, 0));
		super.setImage(Image);
	} 

}
