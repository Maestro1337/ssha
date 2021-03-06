package Model.Items;

import org.newdawn.slick.Image;
import Model.StatusEffectShell;

public class Item {
	
	private int cost;
	private String name;
	private String description;
	private Image itemImage;
	private String itemSlot;

	private boolean isPassive = false;
	private StatusEffectShell selfAffectingSE = null;
	
	public Item (int cost, String name,String description,String itemSlot){
		this.cost = cost;
		this.name= name;
		this.description=description;
		this.itemSlot= itemSlot;
	}
	
	// Getters
	public String getName(){
		return name;
	}
	public String getDescription(){
		return description;
	}
	public int getCost(){
		return cost;
	}
	public boolean getPassive(){
		return isPassive;
	}
	public Image getImage(){
		return itemImage;
	}
	public String getItemSlot(){
		return itemSlot;
	}
	public StatusEffectShell getSelfAffectingStatusEffect(){
		return selfAffectingSE;
	}
	
	// Setters
	public void setPassive(){
		isPassive = true;
	}
	public void setImage(Image image){
		itemImage=image;
	}
	public void setSelfAffectingStatusEffectShell(StatusEffectShell SE){
		selfAffectingSE = SE;
	}
}
