package Model.Obstacles;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ObstacleBarricade extends Obstacle{
	
	public ObstacleBarricade(int x, int y, int type){
		//String type, int damage, int x, int y
		super("Barricade", 20,500, x, y, true);
		
		Image[] images = new Image[1];
		try {
			if (type==1){
				images[0] = new Image("res/tileset/Barricade2.png");	
			}else{
				images[0] = new Image("res/tileset/Barricade.png");
			}	
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		super.setImage(images);
	}
}