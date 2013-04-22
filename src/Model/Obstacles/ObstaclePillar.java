package Model.Obstacles;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ObstaclePillar extends Obstacles{
	
	public ObstaclePillar(int x, int y){
		//String type, int damage, int x, int y
		super("Pillar", 0, x, y, true);
		
		Image image = null;
		try {
			image = new Image("res/awesomePinkSquare.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		super.setImage(image, image.getHeight(), image.getWidth());
	}
}
