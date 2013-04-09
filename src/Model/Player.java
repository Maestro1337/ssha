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
}
