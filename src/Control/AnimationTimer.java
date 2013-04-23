package Control;

import org.newdawn.slick.Image;

import Model.Player;
import Model.Skills.Skill;

public class AnimationTimer {
	
	private int interval;
	private long startTime;
	private long elapsedTime = 0;
	Image[] images;
	int counter = 0;
	
	Skill skill;
	
	public AnimationTimer(int interval, Image[] images, Skill skill){
		this.interval = interval;
		this.images = images;
		this.skill = skill;
	}
	
	
	public void resetTimer(){
		startTime = System.currentTimeMillis();
		elapsedTime = 0;
	}
	public void resetCounterAndTimer(){
		counter = 0;
		resetTimer();
	}
	public Image getCurrentAnimationImage(){
		elapsedTime = System.currentTimeMillis() - startTime;
		if(elapsedTime > interval){
			resetTimer();
			counter++;
		}
		if(counter > images.length){
			skill.setAttackingState(false);
		}
		
		return counter>=0 && counter<images.length ? images[counter] : null;
	}
	public int getInterval(){
		return interval;
	}
}