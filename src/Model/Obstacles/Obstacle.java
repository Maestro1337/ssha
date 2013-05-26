package Model.Obstacles;

import org.newdawn.slick.Image;

public class Obstacle {
	private String type;
	private int damage;
	private int maxHealth;
	private int health;
	private int x;
	private int y;
	private Image[] images;
	private int currentHeight;
	private int currentWidth;
	private boolean solid;
	
	public Obstacle(String type, int damage, int health, int x, int y, boolean solid){
		this.type = type;
		this.damage = damage;
		maxHealth = this.health = health;
		this.x = x;
		this.y = y;
		this.solid = solid;
	}
	
	// Getters
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public String getType(){
		return type;
	}
	public boolean isSolid(){
		return solid;
	}
	public int getDamage(){
		return damage;
	}
	public int getHealth(){
		return health;
	}
	public int getCurrentHeight(){
		return currentHeight;
	}
	public int getCurrentWidth(){
		return currentWidth;
	}
	public Image getCurrentImage(){
		double divider = maxHealth/images.length;
		int index=images.length-1;
		while(divider*index > health){
			index--;
		}
		return images[index];
	}
	public Image[] getAllImages(){
		return images;
	}
	
	// Setters
	public void setImage(Image[] images){
		// Put second if into first if
		if(images != null) {
			this.images = images;
		
			if(images[0] != null){
				currentHeight = images[0].getHeight();
				currentWidth = images[0].getWidth();
			}
		}
	}
	
	// Misc methods
	public void takeDamage(int damage){
		health -= damage;
	}
}
