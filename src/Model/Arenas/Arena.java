package Model.Arenas;
import java.util.ArrayList;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import Model.Obstacles.Obstacle;


public class Arena {

	private int width;
	private int height;
	private String terrain = "";
	private Obstacle[] obstacles;
	private ArrayList<Integer> destroyedObstacles = new ArrayList<Integer>();
	
	private TiledMap background;
	
	private String name;
	
	
	public Arena(String name){
		this.name = name;
		try {
			background = new TiledMap("res/tileset/grassBackground.tmx");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	// Getters
	public String getName(){
		return name;
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	public TiledMap getBackgroundMap(){
		return background;
	}
	public String getTerrain(){
		return terrain;
	}
	public Obstacle[] getObstacles(){
		return obstacles;
	}
	public int[] getDestroyedObstacles() {
		int[] destroyedObstacles = null;
		if(this.destroyedObstacles != null) {
			destroyedObstacles = new int[this.destroyedObstacles.size()];
			for(int i = 0; i < this.destroyedObstacles.size(); i++) {
				destroyedObstacles[i] = this.destroyedObstacles.get(i);
			}
			this.destroyedObstacles.clear();
		}
		return destroyedObstacles;
	}
	
	// Setters
	public void setObstacles(Obstacle[] obs){
		obstacles = obs;
	}
	public void addObstacle(Obstacle obs){
		for(int i = 0; i<obstacles.length; i++){
			if(obstacles[i] == null){
				obstacles[i] = obs;
				break;
			}
		}
	}
	public synchronized void removeObstacle(int index) {
		if(obstacles[index] != null){
			obstacles[index] = null;
			destroyedObstacles.add(new Integer(index));
		}
		
	}
	public synchronized void destroyObstacle(int index){
		obstacles[index] = null;
	}
}
