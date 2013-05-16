package Model.Obstacles;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ObstacleTestDamage extends Obstacle{
	
	public ObstacleTestDamage(int x, int y){
		//String type, int damage, int x, int y
		super("Pillar", 0, 500, x, y, true);
		
		Image[] images = new Image[5];
		try {
			images[0] = new Image("res/obstacles/testDamageObstacle/tdo1.png");
			images[1] = new Image("res/obstacles/testDamageObstacle/tdo2.png");
			images[2] = new Image("res/obstacles/testDamageObstacle/tdo3.png");
			images[3] = new Image("res/obstacles/testDamageObstacle/tdo4.png");
			images[4] = new Image("res/obstacles/testDamageObstacle/tdo5.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		super.setImage(images);
	}
}
