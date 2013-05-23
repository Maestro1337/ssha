package Model.Items;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.StatusEffects.StatusEffectArmor;
import Model.StatusEffects.StatusEffectItems;
import Model.StatusEffects.StatusEffectMovement;

public class ItemHunterCap extends Item {

		Image Image;
		public ItemHunterCap(){
			
			
			//Price, Name, Description, Slot,
			super (400,"Shadow's Sanctum","The rapist's friend.\nNoone can see what\nyou do in the dark.","Headwear");
			try{
				Image = new Image("res/items/Hunters Helmet.png");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			super.setPassive();
			super.setSelfAffectingStatusEffectShell(new StatusEffectItems(this, 0, 0.5, 1.5, 0));
			super.setImage(Image);
		}
}
