package Model.Timers;

import org.newdawn.slick.Image;

import Model.Player;
import Model.Skills.Skill;

public class EndStateAnimationTimer {
	
	private int interval;
	private long startTime;
	private long elapsedTime = 0;
	Image[] images;
	int counter = 0;
	
	Skill skill;
	
	public EndStateAnimationTimer(int duration, Image[] images, Skill skill){
		interval = duration/(images.length);
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
	//		System.out.println("Counter: " + counter + " Length: " + images.length);
		}
		if(counter >= images.length){
			skill.finishEndState();
			skill.setAttackingState(false);
			resetCounterAndTimer();
			
			System.out.println("Finishing end state with " + skill.getName());
		}
		
		return counter>=0 && counter<images.length ? images[counter] : null;
	}
	public int getInterval(){
		return interval;
	}
}
