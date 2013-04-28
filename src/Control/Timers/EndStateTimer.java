package Control.Timers;

import Model.Skills.*;
import Model.*;

public class EndStateTimer {

	private int ESColInterval;
	private long ESColStartTime = 0;
	private long ESColElapsedTime = 0;
	private Skill skill;
	private Player player;
	
	public EndStateTimer(int interval, Player player, Skill skill){
		ESColInterval = interval;
		this.player = player;
		this.skill = skill;
	}
	
	
	public void resetESColTimer(){
		ESColStartTime = System.currentTimeMillis();
		ESColElapsedTime = 0;
	}
	public long checkESColTimer(){
		ESColElapsedTime = System.currentTimeMillis() - ESColStartTime;
		if(ESColElapsedTime > ESColInterval){
			ESColElapsedTime = ESColInterval;
		}
		return ESColElapsedTime;
	}
	public int getESColInterval(){
		return ESColInterval;
	}
}
