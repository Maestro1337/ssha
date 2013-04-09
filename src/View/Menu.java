package View;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState{
	
	private String mouse = "No input yet";
	Image bg;
	
	Image userImage;
	float imgX = 190;
	float imgY = 90;
	
	Image enemyImage;
	float enemyX = 600;
	float enemyY = 300;
	int eWidth;
	int eHeight;
	
	Image attackImage;
	float aImgX = 0;
	float aImgY = 0;
	
	public Menu (int state){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		enemyImage = new Image("res/awesomePinkSquare.png");
		userImage = new Image("res/awesomePinkSquare.png");
		attackImage = new Image("res/awesomeGreenSquare.png");
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		bg = new Image("res/bg.png");
		
		g.drawImage(bg, 0, 0);
		g.drawImage(bg, 500, 0);
		g.drawImage(bg, 1000, 0);
		g.drawImage(bg, 0, 500);
		g.drawImage(bg, 500, 500);
		g.drawImage(bg, 1000, 500);
		
		g.drawString("Are you ready", 50, 50);
		g.drawRect(50, 75, 365, 120); //x,y,width,height
		g.drawString(mouse, 50, 200);
		
		g.drawImage(userImage, imgX,imgY);
		
		if(isAttacking)
			g.drawImage(attackImage, aImgX,aImgY);

		Image playImg = new Image("res/playNow.png");
		g.drawImage(playImg, 135, 225);
		

		if(!isColliding(eWidth, eHeight))
			g.drawImage(enemyImage, enemyX, enemyY);
	}
	
	
	boolean isRunning = false;
	boolean isAttacking = false;
	
	float mouseXPosMove;
	float mouseYPosMove;
	double moveSpeed = 1;
	
	float mouseXPosAtt;
	float mouseYPosAtt;
	double attSpeed = 0.5;
	
	int moveCounter=0;
	float xDirectionMove;
	float yDirectionMove;
	float genDirMove;
	Double findNaN;
	
	int attCounter=0;
	float xDirAtt;
	float yDirAtt;
	float genDirAtt;
	
	
	
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		
		int xPos = Mouse.getX();
		int yPos = 720 - Mouse.getY();
		
		mouse = "Mouse position: (" + xPos + "," + yPos + ")";
		
		Input input = gc.getInput();
		if(input.isKeyDown(Input.KEY_W)){imgY -= 1;}
		if(input.isKeyDown(Input.KEY_S)){imgY += 1;}
		if(input.isKeyDown(Input.KEY_A)){imgX -= 1;}
		if(input.isKeyDown(Input.KEY_D)){imgX += 1;}
		
		if((190<xPos && xPos<290) && (250<yPos && yPos<350)){
			if(input.isMouseButtonDown(0)){ // 0 = leftclick, 1 = rightclick
				sbg.enterState(1);
			}
		}
		
		if(input.isMouseButtonDown(1)){
			move();
		}
		
		if(input.isMouseButtonDown(0)){
			attack();
		}
		if(isRunning){
			isRunning();
		}
		if(isAttacking){
			isAttacking();
		}
	}
	
	private void isRunning(){
		imgX += xDirectionMove*moveSpeed;
		imgY += yDirectionMove*moveSpeed;
		if(findNaN.isNaN()){
			imgX = mouseXPosMove;
			imgY = mouseYPosMove;
			isRunning = false;
		}
		moveCounter++;
		if(moveCounter*moveSpeed >= genDirMove)
			isRunning = false;
	}
	
	private void isAttacking(){
		aImgX += xDirAtt*attSpeed;
		aImgY += yDirAtt*attSpeed;
		
		attCounter++;
		if(attCounter*attSpeed >= genDirAtt)
			isAttacking = false;
	}
	
	public boolean isColliding(int width, int height) throws SlickException{
		if((enemyX <= aImgX && aImgX <= enemyX + enemyImage.getWidth()) && (enemyY <= aImgY && aImgY <= enemyY + enemyImage.getHeight()) ){
			return true;
		}else
		return false;
	}
	
	public void move(){
		mouseXPosMove = Mouse.getX();
		mouseYPosMove = 720 - Mouse.getY();
		xDirectionMove = (mouseXPosMove - imgX);
		yDirectionMove = (mouseYPosMove - imgY);
		genDirMove = (float)Math.sqrt(xDirectionMove*xDirectionMove+yDirectionMove*yDirectionMove);
		findNaN = (double)genDirMove;
		xDirectionMove = xDirectionMove/genDirMove;
		yDirectionMove = yDirectionMove/genDirMove;
		
		moveCounter=0;
		
		isRunning = true;
	}
	
	public void attack(){
		float attackRange = 200;
		
		if(!isAttacking){
			mouseXPosAtt = Mouse.getX();
			mouseYPosAtt = 720 - Mouse.getY();
			
			aImgX = imgX;
			aImgY = imgY;
			
			xDirAtt = (mouseXPosAtt - aImgX);
			yDirAtt = (mouseYPosAtt - aImgY);
			genDirAtt = (float)Math.sqrt(xDirAtt*xDirAtt+yDirAtt*yDirAtt);
			xDirAtt = xDirAtt/genDirAtt;
			yDirAtt = yDirAtt/genDirAtt;
			
			if(genDirAtt > attackRange){
				genDirAtt = attackRange;
			}
			
			attCounter=0;
			
			isAttacking = true;
		}
	}
	
	public int getID(){
		return 0;
	}
	
	public int getWidth(Image image){
		return image.getWidth();
	}
	
	public int getHeight(Image image){
		return image.getHeight();
	}
}
