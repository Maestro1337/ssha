package Model;

import java.util.Random;

import main.GameEngine;
import main.MainHub;

import org.newdawn.slick.SlickException;

import Model.Obstacles.Obstacle;


public class AIModel implements PlayerControl{
	
	Player[] playerList;
	
	private PlayerModel enemyControl;
	Player enemy;
	Player targetPlayer;
	Skill[] enemySkills;
	Obstacle[] obstacles;
	
	
	
	
	public AIModel(Player player){
		enemy = player;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		playerList = MainHub.getController().getPlayers();
		obstacles = MainHub.getController().getMapSelected().getObstacles();
		enemyControl = new PlayerModel(enemy);
		updateTarget();

	//	enemy = enemyControl.getPlayer();
		enemySkills = enemy.getSkillList();
		try {
			enemyControl.ressurectPlayer();
			enemyControl.checkPlayerObstacleCollision(0, 0);
			while(true){
				AI();
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		
		
	}

	@Override
	public void killItWithFire() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changePlayer(Player player) {
		// TODO Auto-generated method stub
		
	}

	
	
	public boolean aiTimer (long delay){
		return true;
	}
		
	
	
	long time = 0;
	int dodgedirX;
	int dodgedirY;
	Obstacle currentObstacleCheck;
	
	public void AI() throws SlickException{
		if(targetPlayer == null){
			updateTarget();
		}
		Random generator = new Random();
		long delay=500;
		double dx = enemy.getX()-targetPlayer.getX();
		double dy = enemy.getY()-targetPlayer.getY();
		double distance = Math.sqrt((dx*dx)+(dy*dy));
		Skill currentSkillCheck;
		boolean collitionCaseX = false;
		boolean collitionCaseY = false;
		
		

		for(int i=0; i<MainHub.getController().getMapSelected().getObstacles().length; i++){
			currentObstacleCheck = MainHub.getController().getMapSelected().getObstacles()[i];
			if (currentObstacleCheck!=null&&currentObstacleCheck.isSolid()){

				
				if(enemyControl.isCollidingWithObstacle(currentObstacleCheck, enemy.getX()+1, enemy.getY(), enemy.getImage().getWidth(), enemy.getImage().getHeight())||
						enemyControl.isCollidingWithObstacle(currentObstacleCheck, enemy.getX()-1, enemy.getY(), enemy.getImage().getWidth(), enemy.getImage().getHeight())){
					collitionCaseX= true;
					break;
						
				}else if(enemyControl.isCollidingWithObstacle(currentObstacleCheck, enemy.getX(), enemy.getY()+1, enemy.getImage().getWidth(), enemy.getImage().getHeight())||
						enemyControl.isCollidingWithObstacle(currentObstacleCheck, enemy.getX(), enemy.getY()-1, enemy.getImage().getWidth(), enemy.getImage().getHeight())){
					collitionCaseY= true;
					break;
						
				}
			}		
		}
		// the AI moves around obstacles.
		if (collitionCaseX&&currentObstacleCheck!= null){
			if (dy>=0){
				enemyControl.move((int)enemy.getX(),(int)currentObstacleCheck.getY()-enemy.getImage().getHeight()-1);
			}else{
				enemyControl.move((int)enemy.getX(),(int)currentObstacleCheck.getY()+currentObstacleCheck.getCurrentHeight()+1);
			}	
		}else if(collitionCaseY&&currentObstacleCheck!= null){
			if (dx>=0){
				enemyControl.move((int)currentObstacleCheck.getX()-enemy.getImage().getWidth()-1,(int)enemy.getY());
			}else{
				enemyControl.move((int)currentObstacleCheck.getX()+currentObstacleCheck.getCurrentWidth()+1,(int)enemy.getY());
			}
		
		}else{
			while (System.currentTimeMillis()>time+delay){		
				currentSkillCheck = targetPlayer.getSkillList()[targetPlayer.getCurrentActiveSkillIndex()];
				// if player is attacking the AI will try to dodge.
				if(currentSkillCheck.getAttackingState()&&currentSkillCheck.getRange()>distance){
					
					if (!willCollide((int)(enemy.getX()+dy/2),(int)(enemy.getY()-dx)/2)){
						enemyControl.move((int)(enemy.getX()+dy/2),(int)(enemy.getY()-dx)/2);
					}
					else if (willCollide((int)(enemy.getX()-dy/2),(int)(enemy.getY()+dx))){
						enemyControl.move((int)(enemy.getX()-dy/2),(int)(enemy.getY()+dx));
					}
						
				}
						
					
				else if (distance>=30){
					enemyControl.move((int)targetPlayer.getX(),(int) targetPlayer.getY());
				}else if (dx<0){
					enemyControl.move(generator.nextInt((int)targetPlayer.getX()), generator.nextInt(GameEngine.screenHeight-1) + 1);
				}
				else{
					enemyControl.move(generator.nextInt(GameEngine.screenWidth-(int)targetPlayer.getX())+(int)targetPlayer.getX(), generator.nextInt(GameEngine.screenHeight-1) + 1);
				}
				time=System.currentTimeMillis();
			}
					
			
		}
		
		for(int i=0;i<enemy.getSkillList().length;i++){
			if (enemyControl.getCurrentActiveSkill().getName()!="Slash"){
				enemyControl.setCurrentActiveSkill(i);
			}
			if(enemyControl.getCurrentActiveSkill().getRange()>distance && enemyControl.getCurrentActiveSkill().getRange()>0 &&
							enemyControl.getCurrentActiveSkill().checkCooldown()==enemyControl.getCurrentActiveSkill().getCoolDown()){
				enemyControl.attack((int)targetPlayer.getX(), (int)targetPlayer.getY());
			}else if (distance<80){ // fix for using slashskill
				enemyControl.attack((int)targetPlayer.getX(), (int)targetPlayer.getY());
				enemyControl.setCurrentActiveSkill(0);
			}
		}
	}
	
	public boolean isCollidingWithObstacle(Obstacle obstacle, float x, float y, int width, int height) throws SlickException{
		if(obstacle.getX() <= x && obstacle.getX()+obstacle.getCurrentWidth() >= x){
			if(obstacle.getY() >= y && obstacle.getY() <= y+height 
					|| obstacle.getY()+obstacle.getCurrentHeight() >= y && obstacle.getY()+obstacle.getCurrentHeight() <= y+height 
					|| obstacle.getY() <= y && obstacle.getY()+obstacle.getCurrentHeight() >= y+height ){
				return true;
			}
		}else if(obstacle.getY() <= y && obstacle.getY()+obstacle.getCurrentHeight() >= y){
			if(obstacle.getX() >= x && obstacle.getX() <= x+width 
					|| obstacle.getX()+obstacle.getCurrentWidth() >= x && obstacle.getX()+obstacle.getCurrentWidth() <= x+width 
					|| obstacle.getX() <= x && obstacle.getX()+obstacle.getCurrentWidth() >= x+width ){
				return true;
			}
		}else if(obstacle.getX() <= x+width  && obstacle.getX()+obstacle.getCurrentWidth() >= x+width ){
			if(obstacle.getY() >= y && obstacle.getY() <= y+height 
					|| obstacle.getY()+obstacle.getCurrentHeight() >= y && obstacle.getY()+obstacle.getCurrentHeight() <= y+height 
					|| obstacle.getY() <= y && obstacle.getY()+obstacle.getCurrentHeight() >= y+height ){
				return true;
			}
		}else if(obstacle.getY() <= y+height  && obstacle.getY()+obstacle.getCurrentHeight() >= y+height ){
			if(obstacle.getX() >= x && obstacle.getX() <= x+width 
					|| obstacle.getX()+obstacle.getCurrentWidth() >= x && obstacle.getX()+obstacle.getCurrentWidth() <= x+width 
					|| obstacle.getX() <= x && obstacle.getX()+obstacle.getCurrentWidth() >= x+width ){
				return true;
			}
		}else if(x <= obstacle.getX() && x+width >= obstacle.getX()+obstacle.getCurrentWidth()
				&& y <= obstacle.getY() && y+width >= obstacle.getY()+obstacle.getCurrentHeight()){
			return true;
		}
		return false;
	}
	private void updateTarget(){
	/*	int targetPlayerIndex = enemy.getPlayerListIndex() != 0 ? 0 : 1;
		float targetPlayerXDir = playerList[targetPlayerIndex].getX() - enemy.getX();
		float targetPlayerYDir = playerList[targetPlayerIndex].getY() - enemy.getY();
		float targetPlayerGenDir = (float)Math.sqrt(targetPlayerXDir*targetPlayerXDir+targetPlayerYDir*targetPlayerYDir);
		
		for(int i=targetPlayerIndex; i<playerList.length; i++){
			if(playerList[i] != null && enemy.getPlayerListIndex() != i){
				float compXDir = playerList[i].getX() - enemy.getX();
				float compYDir = playerList[i].getY() - enemy.getY();
				float compGenDir = (float)Math.sqrt(compXDir*compXDir+compYDir*compYDir);
				
				if(compGenDir<targetPlayerGenDir && !playerList[i].isStealthed()){
					targetPlayerIndex = i;
					targetPlayerXDir = compXDir;
					targetPlayerYDir = compYDir;
					targetPlayerGenDir = compGenDir;
				}
			}
		}
		if(playerList[targetPlayerIndex].isStealthed() && playerList[targetPlayerIndex].isAlive()){
			targetPlayer = playerList[targetPlayerIndex];
		}else{
			targetPlayer = null;
		}*/
		targetPlayer = playerList[0];
	}
	public boolean willCollide(int gotoX,int gotoY) throws SlickException{
		float collitionVektorX = gotoX-enemy.getX();
		float collitionVektorY = gotoY-enemy.getY();
		//Checks 8 points between enemyPosition and where he wants to go for collitions
		for (int i=0;i<10;i++){
			for(int j=0; j<MainHub.getController().getMapSelected().getObstacles().length; j++){
				Obstacle currentObstacleCheck = MainHub.getController().getMapSelected().getObstacles()[j];
				if(currentObstacleCheck != null && isCollidingWithObstacle(currentObstacleCheck, enemy.getX()+collitionVektorX*i/10, enemy.getY()+collitionVektorY*i/10, enemy.getImage().getWidth(), enemy.getImage().getHeight())){
				return true;
				}
			}
		}
		return false;
	}
}
