package Control;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.*;
import Model.Classes.*;
import Model.Obstacles.*;
import Model.Skills.*;

public class PlayerController implements ActionListener {
	
	Player player;
	//Player enemy;
	Skill[] playerSkills;
	Skill currentActiveSkill;
	
	Obstacle[] obstacles;

	Double findNaN;

	public PlayerController(Player player, Obstacle[] obstacles){
		
		this.player = player;
		
		playerSkills = player.getSkills();
		currentActiveSkill = playerSkills[0];
		
		this.obstacles = obstacles;
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public Skill[] getPlayerSkills(){
		return player.getSkills();
	}
	
	public void killPlayer(){
		player.setAliveState(false);
		player.setX(1000);
		player.setY(-1000);
		player.setRunningState(false);
		for(int i=0; i<playerSkills.length; i++){
			playerSkills[i].setAttX(-1000);
			playerSkills[i].setAttY(-1000);
		}
	}
	
	public void ressurectPlayer(){
		player.setAliveState(true);
		player.resetHP();
		player.setX(player.getStartX());
		player.setY(player.getStartY());
	}
	
	public Skill getCurrentActiveSkill(){
		return currentActiveSkill;
	}
	public void setCurrentActiveSkill(int index){
		
		for(int i=0; i<playerSkills.length; i++){
			if(i == index){
				currentActiveSkill = playerSkills[i];
				playerSkills[i].setChosenState(true);
			}else{
				playerSkills[i].setChosenState(false);
			}
		}
	}
	
	public void isRunning() throws SlickException{
		if(player.isAlive() && !checkObstacleCollision((float)(player.getXDirMove()*player.getMoveSpeed()), (float)(player.getYDirMove()*player.getMoveSpeed()))){
			player.addX((float)(player.getXDirMove()*player.getMoveSpeed()));
			player.addY((float)(player.getYDirMove()*player.getMoveSpeed()));
	//		if(findNaN.isNaN()){
	//			imgX = mouseXPosMove;
	//			imgY = mouseYPosMove;
	//			player.setRunningState(false);
	//		}
			player.incMoveCounter();
			if(player.getMoveCounter()*player.getMoveSpeed() >= player.getGenDirMove())
				player.setRunningState(false);
		}else{
			player.setRunningState(false);
		}
	}
	
	//Determines action depending on what state the skill is in
	public void isAttacking(Skill attackingSkill){
		if(attackingSkill != null && player.isAlive())
				if(!attackingSkill.isEndState() && attackingSkill.isProjectile()){
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
					
				}else if(!attackingSkill.isEndState() && !attackingSkill.isProjectile()){
					attackingSkill.activateEndState();					
					System.out.println("Commencing end state with " + attackingSkill.getName());
				}else if(attackingSkill.isEndState() && attackingSkill.checkEndStateTimer() == attackingSkill.getEndStateDuration()){
					attackingSkill.finishEndState();
					attackingSkill.setAttackingState(false);
	//				attackingSkill.resetESColTimer();
					System.out.println("Finishing end state with " + attackingSkill.getName());
					
					
				}else if(attackingSkill.isEndState()){
					if(attackingSkill.getAnimationTimer() != null){
						Image animationImage = attackingSkill.getAnimationTimer().getCurrentAnimationImage();
					
						if(animationImage != null)
							attackingSkill.setEndStateImage(animationImage);
					}
					
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
		
	//	System.out.println("Running " + player.getGenDirMove() + " pixels");
		player.setRunningState(true);
	}
	
	public void attack(int x, int y){
		x -= currentActiveSkill.getCurrentWidth()/2;
		y -= currentActiveSkill.getCurrentHeight()/2;
		rotate(x, y);
		if(currentActiveSkill != null && player.isAlive() && currentActiveSkill.checkCooldown() == currentActiveSkill.getCoolDown()){
			
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
		if(player.isAlive()){
			for(int i=0; i<playerSkills.length; i++){
				if(playerSkills[i] != null && isColliding(playerSkills[i])){
					
					//Checks if collided skill has a statusEffect and adds it to the player it hit
					if(playerSkills[i].getStatusEffect() != null && !playerSkills[i].getStatusEffect().hasBeenGivenTo(player.getName())){
						player.addStatusEffect(playerSkills[i].getStatusEffect().cloneTo(player));
					}
					
					if(!playerSkills[i].isEndState()){
						if(!playerSkills[i].hasEndState()){
							playerSkills[i].setAttackingState(false);
							System.out.println("Target hit with " + playerSkills[i].getName());
							playerSkills[i].collidedShot();
							player.dealDamage(playerSkills[i].getDamage());
						}else{
							playerSkills[i].activatePreEndState();
						}
					}else{
						if(playerSkills[i].getESIT() != null && playerSkills[i].getESIT().checkESColTimer() == playerSkills[i].getESColInterval()){
							System.out.println("Target hit with " + playerSkills[i].getName());
							player.dealDamage(playerSkills[i].getDamage());
							playerSkills[i].getESIT().resetESColTimer();
						}else if(playerSkills[i].getESIT() == null){
							playerSkills[i].activateESIT(player);
						}
					}
				}
			}
		}
		if(player.getHP() <= 0){
			killPlayer();
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

//				System.out.println("Target ran into " + obstacles[i].getType());
				player.dealDamage(obstacles[i].getDamage());
				return true;

			}
		}
		return false;
	}
	
	public boolean isColliding(Obstacle obstacle, float x, float y) throws SlickException{

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
	
	public void checkSpawnCollision() throws SlickException{
		for(int i=0; i<obstacles.length; i++){
			if(obstacles[i] != null){
				float x = 0;
				float y= 0;
				while(isColliding(obstacles[i], x, y)){
					player.addX(1);
				}
			}	
		}
	}
	
	public void checkStatusEffects(){
		
		//Goes through all the current StatusEffects player has
		for(int i=0; i<player.getStatusEffects().size(); i++){
			StatusEffect currentStatusEffect = player.getStatusEffects().get(i);
			
			//Checks and makes the effect if timer has not run out. If it has it will return false and remove the statusEffect from player
			if(!currentStatusEffect.checkStatusEffect()){
				player.removeStatusEffect(currentStatusEffect);
			}
		}
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	

}
