package Model.Items;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ItemHunterCap extends Item {

		Image Image;
		public ItemHunterCap(){
			
			
			//Price, Name, Description, Slot,
			super (400,"Shadow's Sanctum","hej","Headwear");
			try{
				Image = new Image("res/items/Hunters Cap.png");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			super.setImage(Image);
		}
}
