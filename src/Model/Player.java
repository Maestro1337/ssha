package Model;

public class Player {
	private int HP;
	private String name;
	private int damage;
	private int armor;
	private int kills;
	private int deaths;
	private int coins;
	private Skill[] skillList = new Skill[10];
	
	//Movement variables
	float mouseXPosMove;
	float mouseYPosMove;
	double moveSpeed = 1;
	
	int moveCounter=0;
	float xDirectionMove;
	float yDirectionMove;
	float genDirMove;
	Double findNaN;
	
	//Attack variables
	float mouseXPosAtt;
	float mouseYPosAtt;
	double attSpeed = 0.5;
	
	int attCounter=0;
	float xDirAtt;
	float yDirAtt;
	float genDirAtt;

	float attackRange = 200;
	
	//Getters for the movements
	public float getMouseXPosMove(){
		return mouseXPosMove;
	}
	public float getMouseYPosMove(){
		return mouseYPosMove;
	}
	public double getMoveSpeed(){
		return moveSpeed;
	}
	public int getMoveCounter(){
		return moveCounter;
	}
	public float getXDirectionMove(){
		return xDirectionMove;
	}
	public float getYDirectionMove(){
		return yDirectionMove;
	}
	public float getgenDirectionMove(){
		return genDirMove;
	}
	public double getFindNaN(){
		return findNaN;
	}
	//Getters for the attacks
	public float getMouseXPosAtt(){
		return mouseXPosAtt;
	}
	public float getMouseYPosAtt(){
		return mouseYPosAtt;
	}
	public double getAttSpeed(){
		return attSpeed;
	}
	public int getAttCounter(){
		return attCounter;
	}
	public float getXDirAtt(){
		return xDirAtt;
	}
	public float gettDirAtt(){
		return yDirAtt;
	}
	public float getGenDirAtt(){
		return genDirAtt;
	}
	public float getAttackRange(){
		return attackRange;
	}
}
