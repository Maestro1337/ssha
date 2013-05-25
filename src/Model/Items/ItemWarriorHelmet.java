package Model.Items;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.StatusEffects.StatusEffectItems;

public class ItemWarriorHelmet extends Item {

	Image Image;
	public ItemWarriorHelmet(){
		
		
		//Price, Name, Description, Slot,
		super (400,"The Crusaders Defence","Protects against deepthroat.\nNever gag again.","Headwear");
		try{
			Image = new Image("res/items/Warriors Helmet.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.setPassive();
		super.setSelfAffectingStatusEffectShell(new StatusEffectItems(this, 0, 0.3, 0.2, 0));
		super.setImage(Image);
	} 

}
