package Control.Timers;

import Model.Skills.*;
import Model.*;

public class StatusEffectTimer {

	private int interval;
	private long startTime = 0;
	private long elapsedTime = 0;
	private boolean delay;
	
	public StatusEffectTimer(int interval, boolean delay){
		this.interval = interval;
		this.delay = delay;
		
		resetTimer();
		//If there is no delay the elapsed time will start at the interval to get the first check instantly
		if(!delay)
			elapsedTime = interval;
	}
	
	
	public void resetTimer(){
		startTime = System.currentTimeMillis();
		elapsedTime = 0;
	}
	public long checkTimer(){
		elapsedTime = System.currentTimeMillis() - startTime;
		if(elapsedTime > interval){
			elapsedTime = interval;
		}
		return elapsedTime;
	}
	public int getInterval(){
		return interval;
	}
}
