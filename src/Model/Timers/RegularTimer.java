package Model.Timers;

import Model.Skills.*;
import Model.*;

public class RegularTimer {

	private int interval = 1000;
	private long startTime = 0;
	private long elapsedTime = 0;
	
	public RegularTimer(int interval, int delay){
		
		this.interval = interval;
		resetTimer();
		//Setting startTime to startTime minus interval to make the first check instant and then plus delay to add delay if there is any
		startTime = startTime - interval + delay;
		elapsedTime = System.currentTimeMillis() - startTime;
	//	System.out.println(elapsedTime);
	}
	
	
	public void resetTimer(){
		startTime = System.currentTimeMillis();
		elapsedTime = 0;
	}
	public long checkTimer(){
		elapsedTime = System.currentTimeMillis() - startTime;
	//	System.out.println(elapsedTime);
		if(elapsedTime >= interval){
			elapsedTime = interval;
		}
	//	System.out.println(elapsedTime);
		return elapsedTime;
	}
	public int getInterval(){
		return interval;
	}
}
