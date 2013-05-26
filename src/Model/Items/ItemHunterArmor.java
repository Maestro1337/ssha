package Model.Items;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import Model.StatusEffects.StatusEffectItems;

public class ItemHunterArmor extends Item {
	Image Image;
	public ItemHunterArmor(){
		//Price, Name, Description, Slot,
		super (400,"Leather armor of Hardened Skin","Increases armor rating","Armor");
		try{
			Image = new Image("res/items/Hunters Armor.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.setPassive();
		super.setSelfAffectingStatusEffectShell(new StatusEffectItems(this, 0, 0, 0.5, 0));
		super.setImage(Image);
	}
}
