package Control;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.*;
import Model.Skills.*;

public class PlayerController implements ActionListener {
	
	Player player;
	Skill[] playerSkills;
	Skill currentActiveSkill;
	
	//TODO set ESIT to either player or skill in some way
	EndStateIntervalTimer ESIT;
	
	float mouseXPosMove;
	float mouseYPosMove;
	
	float mouseXPosAtt;
	float mouseYPosAtt;
	
	Double findNaN;
	
	//Temporary for collisionControl
	int enemyHP = 100;
	float enemyX = 600;
	float enemyY = 300;
	Image enemyImage;
	
	public int getEnemyHP(){
		return enemyHP;
	}
	public float getEnemyX(){
		return enemyX;
	}
	public float getEnemyY(){
		return enemyY;
	}
	public void damageEnemyHP(int damage){
		enemyHP -= damage;
	}
	public void setEnemyHP(int hp){
		enemyHP = hp;
	}
	public void setEnemyX(int x){
		enemyX = x;
	}
	public void setEnemyY(int y){
		enemyY = y;
	}

	public PlayerController(){
		
		player = new Player(190, 90);
		playerSkills = player.getSkills();
		currentActiveSkill = playerSkills[0];
		
		try {
			enemyImage = new Image("res/awesomePinkSquare.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public Skill[] getPlayerSkills(){
		return player.getSkills();
	}
	
	public Skill getCurrentActiveSkill(){
		return currentActiveSkill;
	}
	public void setCurrentActiveSkill(int index){
		currentActiveSkill = playerSkills[index];
	}
	
	public void isRunning(){
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
						ESIT = new EndStateIntervalTimer(attackingSkill.getESColInterval(), player, attackingSkill);
						System.out.println("Commencing end state with " + attackingSkill.getName());
					}
				}
				
			}else if(attackingSkill != null && !attackingSkill.isEndState() &&!attackingSkill.isProjectile()){
				attackingSkill.activateEndState();
				ESIT = new EndStateIntervalTimer(attackingSkill.getESColInterval(), player, attackingSkill);
				System.out.println("Commencing end state with " + attackingSkill.getName());
			}else if(attackingSkill != null && attackingSkill.isEndState() && attackingSkill.checkEndStateTimer() == attackingSkill.getEndStateDuration()){
			
				attackingSkill.finishEndState();
				attackingSkill.setAttackingState(false);
//				attackingSkill.resetESColTimer();
				System.out.println("Finishing end state with " + attackingSkill.getName());
			}
	}
	
	public boolean isColliding(Skill skill) throws SlickException{
		if(!skill.isPiercing()){
			if(skill.getAttX() <= enemyX && skill.getAttX()+skill.getCurrentWidth() >= enemyX){
				if(skill.getAttY() >= enemyY && skill.getAttY() <= enemyY+enemyImage.getHeight() 
						|| skill.getAttY()+skill.getCurrentHeight() >= enemyY && skill.getAttY()+skill.getCurrentHeight() <= enemyY+enemyImage.getHeight()
						|| skill.getAttY() <= enemyY && skill.getAttY()+skill.getCurrentHeight() >= enemyY+enemyImage.getHeight()){
					return true;
				}
			}else if(skill.getAttY() <= enemyY && skill.getAttY()+skill.getCurrentHeight() >= enemyY){
				if(skill.getAttX() >= enemyX && skill.getAttX() <= enemyX+enemyImage.getWidth() 
						|| skill.getAttX()+skill.getCurrentWidth() >= enemyX && skill.getAttX()+skill.getCurrentWidth() <= enemyX+enemyImage.getWidth()
						|| skill.getAttX() <= enemyX && skill.getAttX()+skill.getCurrentWidth() >= enemyX+enemyImage.getWidth()){
					return true;
				}
			}else if(skill.getAttX() <= enemyX+enemyImage.getWidth() && skill.getAttX()+skill.getCurrentWidth() >= enemyX+enemyImage.getWidth()){
				if(skill.getAttY() >= enemyY && skill.getAttY() <= enemyY+enemyImage.getHeight() 
						|| skill.getAttY()+skill.getCurrentHeight() >= enemyY && skill.getAttY()+skill.getCurrentHeight() <= enemyY+enemyImage.getHeight()
						|| skill.getAttY() <= enemyY && skill.getAttY()+skill.getCurrentHeight() >= enemyY+enemyImage.getHeight()){
					return true;
				}
			}else if(skill.getAttY() <= enemyY+enemyImage.getHeight() && skill.getAttY()+skill.getCurrentHeight() >= enemyY+enemyImage.getHeight()){
				if(skill.getAttX() >= enemyX && skill.getAttX() <= enemyX+enemyImage.getWidth() 
						|| skill.getAttX()+skill.getCurrentWidth() >= enemyX && skill.getAttX()+skill.getCurrentWidth() <= enemyX+enemyImage.getWidth()
						|| skill.getAttX() <= enemyX && skill.getAttX()+skill.getCurrentWidth() >= enemyX+enemyImage.getWidth()){
					return true;
				}
			}/*else if(skill.getAttX() <= enemyX && skill.getAttX()+skill.getCurrentWidth() >= enemyX+enemyImage.getWidth()){
				return true;
			}*/
			
		//	System.out.println("Skill: " + skill.getName() + " X: " + skill.getAttX() + " Y: " + skill.getAttY() + " W: " + skill.getCurrentWidth() + " H: " + skill.getCurrentHeight());
		}else{
			
		}
		return false;
	}

	
	
	public void move(){
		mouseXPosMove = Mouse.getX();
		mouseYPosMove = 720 - Mouse.getY();
		player.setXDirMove((mouseXPosMove - player.getX()));
		player.setYDirMove((mouseYPosMove - player.getY()));
		player.setGenDirMove((float)Math.sqrt(player.getXDirMove()*player.getXDirMove()+player.getYDirMove()*player.getYDirMove()));
		findNaN = (double)player.getGenDirMove();
		player.setXDirMove(player.getXDirMove()/player.getGenDirMove());
		player.setYDirMove(player.getYDirMove()/player.getGenDirMove());
		
		player.resetMoveCounter();
		
		System.out.println("Running " + player.getGenDirMove() + " pixels");
		player.setRunningState(true);
	}
	
	public void attack(){
		if(currentActiveSkill != null && currentActiveSkill.checkCooldown() == currentActiveSkill.getCoolDown()){
			
			currentActiveSkill.activateSkill();
			
			mouseXPosAtt = Mouse.getX();
			currentActiveSkill.setMouseXPos(Mouse.getX());
			mouseYPosAtt = 720 - Mouse.getY();
			currentActiveSkill.setMouseYPos(720 - Mouse.getY());
			
			
			currentActiveSkill.resetShot(player);
			if(currentActiveSkill.isProjectile()){
			
				currentActiveSkill.setXDirAtt((mouseXPosAtt - currentActiveSkill.getAttX()));
				currentActiveSkill.setYDirAtt((mouseYPosAtt - currentActiveSkill.getAttY()));
				currentActiveSkill.setGenDirAtt((float)Math.sqrt(currentActiveSkill.getXDirAtt()*currentActiveSkill.getXDirAtt()+currentActiveSkill.getYDirAtt()*currentActiveSkill.getYDirAtt()));
				currentActiveSkill.setXDirAtt(currentActiveSkill.getXDirAtt()/currentActiveSkill.getGenDirAtt());
				currentActiveSkill.setYDirAtt(currentActiveSkill.getYDirAtt()/currentActiveSkill.getGenDirAtt());
				
				if(currentActiveSkill.getGenDirAtt() > currentActiveSkill.getAttackRange()){
					currentActiveSkill.setGenDirAtt(currentActiveSkill.getAttackRange());
				}
				
				currentActiveSkill.resetAttCounter();
			}
			
			System.out.println("Attacking with " + currentActiveSkill.getName() + " at the range of " + currentActiveSkill.getGenDirAtt() + " pixels");
			currentActiveSkill.setAttackingState(true);
		}
	}

	public void rotate(){
		mouseXPosMove = Mouse.getX();
		mouseYPosMove = 720 - Mouse.getY();
		double rotation = Math.toDegrees(Math.atan2((mouseYPosMove-player.getY()),(mouseXPosMove-player.getX())));
//		player.getImage().setRotation(90 + (float)rotation);
		player.setRotation(90 + (float)rotation);
	}

	public void checkCollision() throws SlickException{
		for(int i=0; i<playerSkills.length; i++){
			if(isColliding(playerSkills[i])){
				if(!playerSkills[i].isEndState()){
					playerSkills[i].setAttackingState(false);
					System.out.println("Target hit with " + playerSkills[i].getName());
					playerSkills[i].collidedShot();
					damageEnemyHP(playerSkills[i].getDamage());
				}else{
	//				if(playerSkills[i].checkESColTimer() == playerSkills[i].getESColInterval()){
					if(ESIT != null && ESIT.checkESColTimer() == ESIT.getESColInterval()){
						System.out.println("Target hit with " + playerSkills[i].getName());
						damageEnemyHP(playerSkills[i].getDamage());
	//					playerSkills[i].resetESColTimer();
						ESIT.resetESColTimer();
					}
				}
			}
		}
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	

}
