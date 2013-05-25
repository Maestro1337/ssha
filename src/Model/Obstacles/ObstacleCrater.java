package Model.Obstacles;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ObstacleCrater extends Obstacle {

	public ObstacleCrater(String type, int damage, int health, int x, int y, boolean solid) {
		//String type, int damage, int x, int y
		super("Crater", 0, 50000, x, y, false);
		
		Image[] images = new Image[1];
		try {
			images[0] = new Image("res/tileset/Crater.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		super.setImage(images);
	}

}
