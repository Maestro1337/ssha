package Model.StatusEffects;

import org.newdawn.slick.Image;

public class MainSkillChange {
	
	private Image[] newSkillAnimation = null;
	private Image[] oldSkillAnimation = null;
	private int newSkillDamage = 0;
	private int oldSkillDamage = 0;
	
	public MainSkillChange(Image[] newImages, int newSkillDamage){
		newSkillAnimation = newImages;
		this.newSkillDamage = newSkillDamage;
	}
	
	public Image[] getNewSkillImages(){
		return newSkillAnimation;
	}
	public Image[] getOldSkillImages(){
		return oldSkillAnimation;
	}
	public int getNewSkillDamage(){
		return newSkillDamage;
	}
	public int getOldSkillDamage(){
		return oldSkillDamage;
	}
	
	public void setOldSkill(Image[] images, int damage){
		oldSkillAnimation = images;
		oldSkillDamage = damage;
	}

}
