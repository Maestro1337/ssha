package Model;

import java.util.Random;

import org.newdawn.slick.SlickException;

import Control.GameEngine;
import Control.PlayerControl;
import Model.Obstacles.Obstacle;
import Model.Skills.Skill;

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

	
	public boolean willCollide(){
		float collitionx;
		float collitiony;
		boolean value=false;
		Skill targetPlayerSkill;
		for (int i=1;i<targetPlayer.getSkillList()[targetPlayer.getCurrentActiveSkillIndex()].getRange();i++){
			targetPlayerSkill = targetPlayer.getSkillList()[targetPlayer.getCurrentActiveSkillIndex()];
			collitionx=targetPlayer.getX()+(i*targetPlayerSkill.getAttX());
			collitiony=targetPlayer.getY()+(i*targetPlayerSkill.getAttY());	
			if(collitionx>enemy.getX()&&collitionx<enemy.getX()+enemy.getImage().getWidth()&&collitiony>enemy.getY()&&collitiony<enemy.getY()+enemy.getImage().getHeight()){
				value = true;
			 break;
			}
		}
		return value;
	}
	public boolean aiTimer (long delay){
		return true;
	}
		
	
	
	long time = 0;
	int dodgedirX;
	int dodgedirY;
	
	
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
			Obstacle currentObstacleCheck = MainHub.getController().getMapSelected().getObstacles()[i];
			if (currentObstacleCheck!=null&&currentObstacleCheck.isSolid()){
				
				if(isCollidingWithObstacle(currentObstacleCheck, enemy.getX()+1, enemy.getY(), enemy.getImage().getWidth(), enemy.getImage().getHeight())||
						isCollidingWithObstacle(currentObstacleCheck, enemy.getX()-1, enemy.getY(), enemy.getImage().getWidth(), enemy.getImage().getHeight())){
					collitionCaseX= true;
					break;
						
				}else if(isCollidingWithObstacle(currentObstacleCheck, enemy.getX(), enemy.getY()+1, enemy.getImage().getWidth(), enemy.getImage().getHeight())||
						isCollidingWithObstacle(currentObstacleCheck, enemy.getX(), enemy.getY()-1, enemy.getImage().getWidth(), enemy.getImage().getHeight())){
					collitionCaseY= true;
					break;
						
				}
			}		
		}
		
		if (collitionCaseX){
			System.out.println("ColX");
			if (dy>=0){
				enemyControl.move((int)enemy.getX(),(int)enemy.getY()-(1+enemy.getImage().getHeight()));
			}else{
				enemyControl.move((int)enemy.getX(),(int)enemy.getY()+1);
			}	
		}if(collitionCaseY){
			System.out.println("ColY");
			if (dx>=0){
				enemyControl.move((int)enemy.getX()-(1+enemy.getImage().getWidth()),(int)enemy.getY());
			}else{
				enemyControl.move((int)enemy.getX()+1,(int)enemy.getY());
			}
		
		}else{
			System.out.println("Else");
			while (System.currentTimeMillis()>time+delay){		
				currentSkillCheck = targetPlayer.getSkillList()[targetPlayer.getCurrentActiveSkillIndex()];
				// if player is attacking the AI will try to dodge.
				if(currentSkillCheck.isAttacking()&&currentSkillCheck.getRange()>distance){
					if (enemyControl.checkPlayerObstacleCollision( (int)(enemy.getX()+dy)/2,(int)(enemy.getY()-dx)/2)){
						
					}
					else if (enemyControl.checkPlayerObstacleCollision( (int)(enemy.getX()+dy)/2,(int)(enemy.getY()-dx)/2)){
						enemyControl.move((int)(enemy.getX()+dy),(int)(enemy.getY()-dx));
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
			enemyControl.setCurrentActiveSkill(i);
			if(enemyControl.getCurrentActiveSkill().getRange()>distance &&
							enemyControl.getCurrentActiveSkill().checkCooldown()==enemyControl.getCurrentActiveSkill().getCoolDown()){
				enemyControl.attack((int)targetPlayer.getX(), (int)targetPlayer.getY());
			}
		}
	}
	
	public boolean isCollidingWithObstacle(Obstacle obstacle, float x, float y, int width, int height) throws SlickException{
		System.out.println("Checking");
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
}
