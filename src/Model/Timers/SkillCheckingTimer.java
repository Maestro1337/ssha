package Model.Timers;

import Model.Obstacles.Obstacle;
import Model.Skills.*;
import Model.*;

public class SkillCheckingTimer {

	private int ESColInterval;
	private long ESColStartTime = 0;
	private long ESColElapsedTime = 0;
	private Skill skill;
	private String playerName = null;
	private Obstacle obstacle = null;
	
	
	public SkillCheckingTimer(int interval, String playerName, Skill skill){
		ESColInterval = interval;
		this.playerName = playerName;
		this.skill = skill;
		
		resetESColTimer();
	}
	
	public SkillCheckingTimer(int interval, Obstacle obstacle, Skill skill){
		ESColInterval = interval;
		this.skill = skill;
		this.obstacle = obstacle;
		
		resetESColTimer();
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
	public String getPlayerName(){
		return playerName;
	}
	public Obstacle getObstacle(){
		return obstacle;
	}
	public int getESColInterval(){
		return ESColInterval;
	}
}
