package Model.Timers;

import org.newdawn.slick.Image;

public class AnimationTimer {
	
	private int interval;
	private long startTime;
	private long elapsedTime = 0;
	Image[] images;
	int counter = 0;
	
	public AnimationTimer(int duration, Image[] images){
		interval = duration/(images.length);
		this.images = images;
	}
	
	// Getters
	public Image getCurrentAnimationImage(){
		elapsedTime = System.currentTimeMillis() - startTime;
		if(elapsedTime > interval){
			resetTimer();
			counter++;
		}
		
		return counter>=0 && counter<images.length ? images[counter] : null;
	}
	public int getInterval(){
		return interval;
	}
	
	public void resetTimer(){
		startTime = System.currentTimeMillis();
		elapsedTime = 0;
	}
	public void resetCounterAndTimer(){
		counter = 0;
		resetTimer();
	}
}
