package Model.Obstacles;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ObstaclePillar extends Obstacle{
	
	public ObstaclePillar(int x, int y){
		//String type, int damage, int x, int y
		super("Pillar", 0, 500, x, y, true);
		
		Image[] images = new Image[1];
		try {

			images[0] = new Image("res/tileset/TreePillar.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		super.setImage(images);
	}
}
