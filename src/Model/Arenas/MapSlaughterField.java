package Model.Arenas;

import Model.Obstacles.Obstacle;
import Model.Obstacles.ObstacleBarricade;
import Model.Obstacles.ObstacleExplosiveBarrel;
import Model.Obstacles.ObstaclePillar;

public class MapSlaughterField extends Arena {

	private Obstacle [] Trees;
	private Obstacle [] Barricade;
	private Obstacle [] TNT;
	private Obstacle[] obstacles = new Obstacle[100];
	
	public MapSlaughterField(){
		
		Trees = new Obstacle[100];
		Barricade = new Obstacle[100];
		TNT = new Obstacle[100];
		
		//Creates 28 Trees
		Trees[1] = new ObstaclePillar(50,100);
		Trees[2] = new ObstaclePillar(55,100);
		Trees[3] = new ObstaclePillar(55,100);
		Trees[4] = new ObstaclePillar(5,100);
		Trees[5] = new ObstaclePillar(50,100);
		Trees[6] = new ObstaclePillar(50,100);
		Trees[7] = new ObstaclePillar(50,100);
		Trees[8] = new ObstaclePillar(50,100);
		Trees[9] = new ObstaclePillar(50,100);
		Trees[10] = new ObstaclePillar(50,100);
		Trees[11] = new ObstaclePillar(50,100);
		Trees[12] = new ObstaclePillar(50,100);
		Trees[13] = new ObstaclePillar(50,100);
		Trees[14] = new ObstaclePillar(50,100);
		Trees[15] = new ObstaclePillar(50,100);
		Trees[16] = new ObstaclePillar(50,100);
		Trees[17] = new ObstaclePillar(50,100);
		Trees[18] = new ObstaclePillar(50,100);
		Trees[19] = new ObstaclePillar(50,100);
		Trees[20] = new ObstaclePillar(50,100);
		
		Barricade [1] = new ObstacleBarricade(1,1);
		Barricade [2] = new ObstacleBarricade(1,1);
		Barricade [3] = new ObstacleBarricade(1,1);
		Barricade [4] = new ObstacleBarricade(1,1);
		Barricade [5] = new ObstacleBarricade(1,1);
		Barricade [6] = new ObstacleBarricade(1,1);
		Barricade [7] = new ObstacleBarricade(1,1);
		Barricade [8] = new ObstacleBarricade(1,1);
		
		TNT [1] = new ObstacleExplosiveBarrel(500,500);
		
		super.setObstacles(Trees);
	}
	
}
