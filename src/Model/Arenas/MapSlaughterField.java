package Model.Arenas;

import Model.Obstacles.Obstacle;
import Model.Obstacles.ObstacleBarricade;
import Model.Obstacles.ObstacleExplosiveBarrel;
import Model.Obstacles.ObstaclePillar;

public class MapSlaughterField extends Arena {


	private Obstacle[] obstacles = new Obstacle[100];
	
	public MapSlaughterField(){
		super("Slaughter Field");
		obstacles = new Obstacle [100];
		
		//Creates 6 TNTBarrels
		obstacles [1] = new ObstacleExplosiveBarrel(1050,530);
		obstacles [2] = new ObstacleExplosiveBarrel(1050,350);
		obstacles [3] = new ObstacleExplosiveBarrel(250,350);
		obstacles [4] = new ObstacleExplosiveBarrel(250,530);
		obstacles [5] = new ObstacleExplosiveBarrel(850,440);
		obstacles [6] = new ObstacleExplosiveBarrel(400,440);
		//Creats 12 Barricades
		obstacles [7] = new ObstacleBarricade(1100,350,1);
		obstacles [8] = new ObstacleBarricade(200,350,1);
		obstacles [9] = new ObstacleBarricade(1100,500,1);
		obstacles [10] = new ObstacleBarricade(200,500,1);
		
		obstacles [11] = new ObstacleBarricade(1020,300,0);
		obstacles [12] = new ObstacleBarricade(250,300,0);
		
		obstacles [13] = new ObstacleBarricade(608,300,0);
		obstacles [14] = new ObstacleBarricade(608,580,0);
		
		obstacles [15] = new ObstacleBarricade(250,580,0);
		obstacles [16] = new ObstacleBarricade(1020,580,0);
		
		//Creates 1 Tree
		obstacles[17] = new ObstaclePillar(630,446);

		super.setObstacles(obstacles);
	}
	
}