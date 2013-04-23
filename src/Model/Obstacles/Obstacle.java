package Model.Obstacles;

import org.newdawn.slick.Image;

import Model.StatusEffect;

public class Obstacle {
	private String type;
	private int damage;
	private int areaOfEffect;
	private StatusEffect status;
	private int x;
	private int y;
	private Image image;
	private int currentHeight;
	private int currentWidth;
	private boolean solid;
	
	public Obstacle(String type, int damage, int x, int y, boolean solid){
		this.type = type;
		this.damage = damage;
		this.x = x;
		this.y = y;
		this.solid = solid;
	}
	
	public void setImage(Image image, int height, int width){
		if(image != null)
			this.image = image;
		
		currentHeight = height;
		currentWidth = width;
	}
	
	
	public String getType(){
		return type;
	}
	public int getDamage(){
		return damage;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public Image getImage(){
		return image;
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
