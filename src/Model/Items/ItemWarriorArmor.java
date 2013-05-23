package Model.Items;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.StatusEffects.StatusEffectArmor;

public class ItemWarriorArmor extends Item {

	Image Image;
	public ItemWarriorArmor(){
		//Price, Name, Description, Slot,
		super (400,"The Righteous' Blessing","Blesses your penis. \nWorks like viagra, but the \neffect doesn't stop.","Armor");
		try{
			Image = new Image("res/items/Warrior Breastplate.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.setPassive();
		super.setSelfAffectingStatusEffectShell(new StatusEffectArmor(this, 1.5, 0));
		super.setImage(Image);
	}

}
