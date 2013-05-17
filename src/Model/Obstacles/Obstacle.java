package Model.Obstacles;

import org.newdawn.slick.Image;

import Model.StatusEffect;

public class Obstacle {
	private String type;
	private int damage;
	private int maxHealth;
	private int health;
	private StatusEffect status;
	private int x;
	private int y;
	private Image[] images;
	private Image currentImage;
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
	
	public void setImage(Image[] images){
		if(images != null)
			this.images = images;
		
		if(images[0] != null){
			currentImage = images[0];
			currentHeight = images[0].getHeight();
			currentWidth = images[0].getWidth();
		}
	}
	
	
	public String getType(){
		return type;
	}
	public int getDamage(){
		return damage;
	}
	public int getHealth(){
		return health;
	}
	public void takeDamage(int damage){
		health -= damage;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public Image getCurrentImage(){
		double divider = maxHealth/images.length;
		int index=images.length-1;
		while(divider*index > health){
			index--;
		}
	//	System.out.println(index);
		return images[index];
	//	return currentImage;
	}
	public Image[] getAllImages(){
		return images;
	}
	public int getCurrentHeight(){
		return currentHeight;
	}
	public int getCurrentWidth(){
		return currentWidth;
	}
	public boolean isSolid(){
		return solid;
	}
}
