package Model.Items;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.StatusEffects.StatusEffectItems;

public class ItemWarriorArmor extends Item {

	Image Image;
	public ItemWarriorArmor(){
		//Price, Name, Description, Slot,
		super (400,"The Righteous' Blessing","Increases the armor rating by \na handsome amount.","Armor");
		try{
			Image = new Image("res/items/Warrior Breastplate.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.setPassive();
		super.setSelfAffectingStatusEffectShell(new StatusEffectItems(this, 0, 0, 0.35, 0));
		super.setImage(Image);
	}

}
