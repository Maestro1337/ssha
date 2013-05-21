package Model.Items;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.StatusEffect;

public class ItemHunterArmor extends Item {
	Image Image;
	public ItemHunterArmor(){
		
		
		//Price, Name, Description, Bonus,
		super (400,"Leather armor of Hardened Skinn","hej");
		try{
			Image = new Image("res/items/Hunters Armor.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.setImage(Image);
	}
}
