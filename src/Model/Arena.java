package Model;
import java.awt.Image;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;


public class Arena {

	private int width;
	private int height;
	private String terrain;
	
	private TiledMap background;
	
	private Image[] images;
	private int[][] mask;
	
	public Arena(){
		try {
			background = new TiledMap("res/tileset/bg.tmx");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public TiledMap getBackgroundMap(){
		return background;
	}
	
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	public String getTerrain(){
		return terrain;
	}
}
