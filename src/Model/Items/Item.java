package Model.Items;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.StatusEffect;

public class Item {
	
	private int price;
	private StatusEffect itemBonus;
	private String name;
	private String description;
	private Image itemImage;
	
	
	public Item (int price, String name,String description){
		this.price = price;
		this.name= name;
		this.description=description;
		
		
		
	}
	public int getPrice(){
		return price;
	}
	public String getName(){
		return name;
	}
	public String getDescription(){
		return description;
	}
	public void setImage(Image image){
		itemImage=image;
	}
	public Image getImage(){
		return itemImage;
	}

}
