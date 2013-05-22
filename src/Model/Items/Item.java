package Model.Items;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.StatusEffect;

public class Item {
	
	private int cost;
	private StatusEffect itemBonus;
	private String name;
	private String description;
	private Image itemImage;
	private boolean equipped;
	private String itemSlot;
	
	public Item (int cost, String name,String description,String itemSlot){
		this.cost = cost;
		this.name= name;
		this.description=description;
		this.itemSlot= itemSlot;
		
		
		
	}
	public int getCost(){
		return cost;
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
	public String getItemSlot(){
		return itemSlot;
	}

}
