package Model.Timers;

import Model.Skills.*;
import Model.*;

public class StatusEffectTimer {

	private int interval = 1000;
	private long startTime = 0;
	private long elapsedTime = 0;
	
	public StatusEffectTimer(int delay){
		
		resetTimer();
		//Setting startTime to startTime minus interval to make the first check instant and then plus delay to add delay if there is any
		startTime = startTime - interval + delay;
	}
	
	
	public void resetTimer(){
		startTime = System.currentTimeMillis();
		elapsedTime = 0;
	}
	public long checkTimer(){
		elapsedTime = System.currentTimeMillis() - startTime;
		
		if(elapsedTime >= interval){
			elapsedTime = interval;
		}
		return elapsedTime;
	}
	public int getInterval(){
		return interval;
	}
}
