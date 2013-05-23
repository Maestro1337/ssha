package Model.Items;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

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
			super.setImage(Image);
		}
}
