package Model.Obstacles;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ObstacleBarricade extends Obstacle{
	
	public ObstacleBarricade(int x, int y){
		//String type, int damage, int x, int y
		super("Barricade", 20, x, y, true);
		
		Image image = null;
		try {
			image = new Image("res/tileset/Barricade.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		super.setImage(image, image.getHeight(), image.getWidth());
	}
}