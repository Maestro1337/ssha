package Control;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.*;
import Model.Obstacles.Obstacles;
import Model.Skills.*;

public class PlayerController implements ActionListener {
	
	Player player;
	//Player enemy;
	Skill[] playerSkills;
	Skill currentActiveSkill;
	
	Obstacles[] obstacles;

	Double findNaN;

	public PlayerController(String name, int x, int y, Obstacles[] obstacles){
		
		player = new Player(name, x, y);
		playerSkills = player.getSkills();
		currentActiveSkill = playerSkills[0];
		
		this.obstacles = obstacles;
		
	//	enemy = new Player(600,300);
	/*	
		try {
			enemyImage = new Image("res/awesomePinkSquare.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}
	
	public Player getPlayer(){
		return player;
	}
	/*public Player getEnemy(){
		return enemy;
	}*/
	
	public Skill[] getPlayerSkills(){
		return player.getSkills();
	}
	
	public Skill getCurrentActiveSkill(){
		return currentActiveSkill;
	}
	public void setCurrentActiveSkill(int index){
		currentActiveSkill = playerSkills[index];
	}
	
	public void isRunning() throws SlickException{
		if(!checkObstacleCollision((float)(player.getXDirMove()*player.getMoveSpeed()), (float)(player.getYDirMove()*player.getMoveSpeed()))){
			player.addX((float)(player.getXDirMove()*player.getMoveSpeed()));
			player.addY((float)(player.getYDirMove()*player.getMoveSpeed()));
			if(findNaN.isNaN()){
	//			imgX = mouseXPosMove;
	//			imgY = mouseYPosMove;
				player.setRunningState(false);
			}
			player.incMoveCounter();
			if(player.getMoveCounter()*player.getMoveSpeed() >= player.getGenDirMove())
				player.setRunningState(false);
		}else{
			player.setRunningState(false);
		}
	}
	
	public void isAttacking(Skill attackingSkill){
			if(attackingSkill != null && !attackingSkill.isEndState() && attackingSkill.isProjectile()){
				//determines which x and y the skill will have in the next render session
				attackingSkill.addAttX((float)(attackingSkill.getXDirAtt()*attackingSkill.getAttSpeed()));
				attackingSkill.addAttY((float)(attackingSkill.getYDirAtt()*attackingSkill.getAttSpeed()));
				
				attackingSkill.incAttCounter();
				
				if(attackingSkill.getAttCounter()*attackingSkill.getAttSpeed() >= attackingSkill.getGenDirAtt()){
					if(!attackingSkill.hasEndState()){
						attackingSkill.setAttackingState(false);
					}else{
						attackingSkill.activateEndState();
						System.out.println("Commencing end state with " + attackingSkill.getName());
					}
				}
				
			}else if(attackingSkill != null && !attackingSkill.isEndState() && !attackingSkill.isProjectile()){
				attackingSkill.activateEndState();
				System.out.println("Commencing end state with " + attackingSkill.getName());
			}else if(attackingSkill != null && attackingSkill.isEndState() && attackingSkill.checkEndStateTimer() == attackingSkill.getEndStateDuration()){
			
				attackingSkill.finishEndState();
				attackingSkill.setAttackingState(false);
//				attackingSkill.resetESColTimer();
				System.out.println("Finishing end state with " + attackingSkill.getName());
			}
	}
	
	public boolean isColliding(Skill skill) throws SlickException{

		if(skill.getAttX() <= player.getX() && skill.getAttX()+skill.getCurrentWidth() >= player.getX()){
			if(skill.getAttY() >= player.getY() && skill.getAttY() <= player.getY()+player.getImage().getHeight() 
					|| skill.getAttY()+skill.getCurrentHeight() >= player.getY() && skill.getAttY()+skill.getCurrentHeight() <= player.getY()+player.getImage().getHeight() 
					|| skill.getAttY() <= player.getY() && skill.getAttY()+skill.getCurrentHeight() >= player.getY()+player.getImage().getHeight() ){
				return true;
			}
		}else if(skill.getAttY() <= player.getY() && skill.getAttY()+skill.getCurrentHeight() >= player.getY()){
			if(skill.getAttX() >= player.getX() && skill.getAttX() <= player.getX()+player.getImage().getWidth() 
					|| skill.getAttX()+skill.getCurrentWidth() >= player.getX() && skill.getAttX()+skill.getCurrentWidth() <= player.getX()+player.getImage().getWidth() 
					|| skill.getAttX() <= player.getX() && skill.getAttX()+skill.getCurrentWidth() >= player.getX()+player.getImage().getWidth() ){
				return true;
			}
		}else if(skill.getAttX() <= player.getX()+player.getImage().getWidth()  && skill.getAttX()+skill.getCurrentWidth() >= player.getX()+player.getImage().getWidth() ){
			if(skill.getAttY() >= player.getY() && skill.getAttY() <= player.getY()+player.getImage().getHeight() 
					|| skill.getAttY()+skill.getCurrentHeight() >= player.getY() && skill.getAttY()+skill.getCurrentHeight() <= player.getY()+player.getImage().getHeight() 
					|| skill.getAttY() <= player.getY() && skill.getAttY()+skill.getCurrentHeight() >= player.getY()+player.getImage().getHeight() ){
				return true;
			}
		}else if(skill.getAttY() <= player.getY()+player.getImage().getHeight()  && skill.getAttY()+skill.getCurrentHeight() >= player.getY()+player.getImage().getHeight() ){
			if(skill.getAttX() >= player.getX() && skill.getAttX() <= player.getX()+player.getImage().getWidth() 
					|| skill.getAttX()+skill.getCurrentWidth() >= player.getX() && skill.getAttX()+skill.getCurrentWidth() <= player.getX()+player.getImage().getWidth() 
					|| skill.getAttX() <= player.getX() && skill.getAttX()+skill.getCurrentWidth() >= player.getX()+player.getImage().getWidth() ){
				return true;
			}
		}/*else if(skill.getAttX() <= enemyX && skill.getAttX()+skill.getCurrentWidth() >= enemyX+enemyImage.getWidth()){
			return true;
		}*/
		
	//	System.out.println("Skill: " + skill.getName() + " X: " + skill.getAttX() + " Y: " + skill.getAttY() + " W: " + skill.getCurrentWidth() + " H: " + skill.getCurrentHeight());
		
		return false;
	}

	
	
	public void move(int x, int y){
	//	mouseXPosMove = Mouse.getX();
	//	mouseYPosMove = 720 - Mouse.getY();
		rotate(x, y);
		player.setMouseXPosMove(x);
		player.setMouseYPosMove(y);
		player.setXDirMove((player.getMouseXPosMove() - player.getX()));
		player.setYDirMove((player.getMouseYPosMove() - player.getY()));
		player.setGenDirMove((float)Math.sqrt(player.getXDirMove()*player.getXDirMove()+player.getYDirMove()*player.getYDirMove()));
		findNaN = (double)player.getGenDirMove();
		player.setXDirMove(player.getXDirMove()/player.getGenDirMove());
		player.setYDirMove(player.getYDirMove()/player.getGenDirMove());
		
		player.resetMoveCounter();
		
		System.out.println("Running " + player.getGenDirMove() + " pixels");
		player.setRunningState(true);
	}
	
	public void attack(int x, int y){
		rotate(x, y);
		if(currentActiveSkill != null && currentActiveSkill.checkCooldown() == currentActiveSkill.getCoolDown()){
			
			currentActiveSkill.activateSkill();
			
			currentActiveSkill.setMouseXPos(x);
			currentActiveSkill.setMouseYPos(y);
			
			
			currentActiveSkill.resetShot(player);
			
			currentActiveSkill.setXDirAtt((currentActiveSkill.getMouseXPosAtt() - currentActiveSkill.getAttX()));
			currentActiveSkill.setYDirAtt((currentActiveSkill.getMouseYPosAtt() - currentActiveSkill.getAttY()));
			currentActiveSkill.setGenDirAtt((float)Math.sqrt(currentActiveSkill.getXDirAtt()*currentActiveSkill.getXDirAtt()+currentActiveSkill.getYDirAtt()*currentActiveSkill.getYDirAtt()));
			currentActiveSkill.setXDirAtt(currentActiveSkill.getXDirAtt()/currentActiveSkill.getGenDirAtt());
			currentActiveSkill.setYDirAtt(currentActiveSkill.getYDirAtt()/currentActiveSkill.getGenDirAtt());
			
			if(currentActiveSkill.getGenDirAtt() > currentActiveSkill.getAttackRange()){
				currentActiveSkill.setGenDirAtt(currentActiveSkill.getAttackRange());
			}
			
			currentActiveSkill.resetAttCounter();
			
			if(!currentActiveSkill.isProjectile()){
				currentActiveSkill.setNonProjectileShot();
			}
			
			System.out.println("Attacking with " + currentActiveSkill.getName() + " at the range of " + currentActiveSkill.getGenDirAtt() + " pixels");
			currentActiveSkill.setAttackingState(true);
		}
	}

	public void rotate(int x, int y){
//		mouseXPosMove = Mouse.getX();
//		mouseYPosMove = 720 - Mouse.getY();
		player.setMouseXPosMove(x);
		player.setMouseYPosMove(y);
		double rotation = Math.toDegrees(Math.atan2((player.getMouseYPosMove()-player.getY()),(player.getMouseXPosMove()-player.getX())));
//		player.getImage().setRotation(90 + (float)rotation);
		player.setRotation(90 + (float)rotation);
	}

	public void checkCollision(Skill[] playerSkills) throws SlickException{
		for(int i=0; i<playerSkills.length; i++){
			if(isColliding(playerSkills[i])){
				if(!playerSkills[i].isEndState()){
					if(!playerSkills[i].hasEndState()){
						playerSkills[i].setAttackingState(false);
						System.out.println("Target hit with " + playerSkills[i].getName());
						playerSkills[i].collidedShot();
		//				damageEnemyHP(playerSkills[i].getDamage());
						player.dealDamage(playerSkills[i].getDamage());
					}else{
						playerSkills[i].activatePreEndState();
					}
				}else{
	//				if(playerSkills[i].checkESColTimer() == playerSkills[i].getESColInterval()){
//					if(ESIT != null && ESIT.checkESColTimer() == ESIT.getESColInterval()){
					if(playerSkills[i].getESIT() != null && playerSkills[i].getESIT().checkESColTimer() == playerSkills[i].getESColInterval()){
						System.out.println("Target hit with " + playerSkills[i].getName());
	//					damageEnemyHP(playerSkills[i].getDamage());
						player.dealDamage(playerSkills[i].getDamage());
	//					playerSkills[i].resetESColTimer();
//						ESIT.resetESColTimer();
						playerSkills[i].getESIT().resetESColTimer();
					}else if(playerSkills[i].getESIT() == null){
						playerSkills[i].activateESIT(player);
					}
				}
			}
		}
	}
	
	public boolean checkObstacleCollision(float x, float y) throws SlickException{
		for(int i=0; i<obstacles.length; i++){
			if(obstacles[i] != null && isColliding(obstacles[i], x, y)){
				
				if(obstacles[i].isSolid()){
				//	player.setGenDirMove(100);
				//	player.setXDirMove(player.getXDirMove()*-1);
				//	player.setYDirMove(player.getYDirMove()*-1);
				}

				System.out.println("Target ran into " + obstacles[i].getType());
				player.dealDamage(obstacles[i].getDamage());
				return true;

			}
		}
		return false;
	}
	
	public boolean isColliding(Obstacles obstacle, float x, float y) throws SlickException{

		if(obstacle.getX() <= player.getX()+x && obstacle.getX()+obstacle.getCurrentWidth() >= player.getX()+x){
			if(obstacle.getY() >= player.getY()+y && obstacle.getY() <= player.getY()+y+player.getImage().getHeight() 
					|| obstacle.getY()+obstacle.getCurrentHeight() >= player.getY()+y && obstacle.getY()+obstacle.getCurrentHeight() <= player.getY()+y+player.getImage().getHeight() 
					|| obstacle.getY() <= player.getY()+y && obstacle.getY()+obstacle.getCurrentHeight() >= player.getY()+y+player.getImage().getHeight() ){
				return true;
			}
		}else if(obstacle.getY() <= player.getY()+y && obstacle.getY()+obstacle.getCurrentHeight() >= player.getY()+y){
			if(obstacle.getX() >= player.getX()+x && obstacle.getX() <= player.getX()+x+player.getImage().getWidth() 
					|| obstacle.getX()+obstacle.getCurrentWidth() >= player.getX()+x && obstacle.getX()+obstacle.getCurrentWidth() <= player.getX()+x+player.getImage().getWidth() 
					|| obstacle.getX() <= player.getX()+x && obstacle.getX()+obstacle.getCurrentWidth() >= player.getX()+x+player.getImage().getWidth() ){
				return true;
			}
		}else if(obstacle.getX() <= player.getX()+x+player.getImage().getWidth()  && obstacle.getX()+obstacle.getCurrentWidth() >= player.getX()+x+player.getImage().getWidth() ){
			if(obstacle.getY() >= player.getY()+y && obstacle.getY() <= player.getY()+y+player.getImage().getHeight() 
					|| obstacle.getY()+obstacle.getCurrentHeight() >= player.getY()+y && obstacle.getY()+obstacle.getCurrentHeight() <= player.getY()+y+player.getImage().getHeight() 
					|| obstacle.getY() <= player.getY()+y && obstacle.getY()+obstacle.getCurrentHeight() >= player.getY()+y+player.getImage().getHeight() ){
				return true;
			}
		}else if(obstacle.getY() <= player.getY()+y+player.getImage().getHeight()  && obstacle.getY()+obstacle.getCurrentHeight() >= player.getY()+y+player.getImage().getHeight() ){
			if(obstacle.getX() >= player.getX()+x && obstacle.getX() <= player.getX()+x+player.getImage().getWidth() 
					|| obstacle.getX()+obstacle.getCurrentWidth() >= player.getX()+x && obstacle.getX()+obstacle.getCurrentWidth() <= player.getX()+x+player.getImage().getWidth() 
					|| obstacle.getX() <= player.getX()+x && obstacle.getX()+obstacle.getCurrentWidth() >= player.getX()+x+player.getImage().getWidth() ){
				return true;
			}
		}/*else if(skill.getAttX() <= enemyX && skill.getAttX()+skill.getCurrentWidth() >= enemyX+enemyImage.getWidth()){
			return true;
		}*/
		
	//	System.out.println("Skill: " + skill.getName() + " X: " + skill.getAttX() + " Y: " + skill.getAttY() + " W: " + skill.getCurrentWidth() + " H: " + skill.getCurrentHeight());
		
		return false;
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	

}
