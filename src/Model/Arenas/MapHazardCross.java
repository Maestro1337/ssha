package Model.Arenas;

import Model.Obstacles.Obstacle;
import Model.Obstacles.ObstacleBarricade;
import Model.Obstacles.ObstacleExplosiveBarrel;
import Model.Obstacles.ObstaclePillar;

public class MapHazardCross extends Arena {


	private Obstacle[] obstacles = new Obstacle[100];
	
	public MapHazardCross(){
		super("Hazard Cross");
		obstacles = new Obstacle [100];
		
		//Creates 8 TNTBarrels
		obstacles [1] = new ObstacleExplosiveBarrel(680,593);
		obstacles [2] = new ObstacleExplosiveBarrel(500,500);
		obstacles [3] = new ObstacleExplosiveBarrel(100,225);
		obstacles [4] = new ObstacleExplosiveBarrel(300,395);
		obstacles [5] = new ObstacleExplosiveBarrel(932,351);
		obstacles [6] = new ObstacleExplosiveBarrel(811,256);
		obstacles [7] = new ObstacleExplosiveBarrel(1033,606);
		obstacles [8] = new ObstacleExplosiveBarrel(205,590);
		//Creats 4 Barricades
		obstacles [9] = new ObstacleBarricade(623,210,1);
		obstacles [10] = new ObstacleBarricade(623,633,1);
		obstacles [11] = new ObstacleBarricade(80,440,0);
		obstacles [12] = new ObstacleBarricade(1195,440,0);
		//Creates 16 Trees
		obstacles[13] = new ObstaclePillar(630,290);
		obstacles[14] = new ObstaclePillar(630,368);
		obstacles[15] = new ObstaclePillar(630,446);
		obstacles[16] = new ObstaclePillar(630,524);
		obstacles[17] = new ObstaclePillar(630,602);		
		obstacles[18] = new ObstaclePillar(545,446);
		obstacles[19] = new ObstaclePillar(460,446);
		obstacles[20] = new ObstaclePillar(375,446);
		obstacles[21] = new ObstaclePillar(715,446);
		obstacles[22] = new ObstaclePillar(800,446);
		obstacles[23] = new ObstaclePillar(885,446);
		obstacles[24] = new ObstaclePillar(970,446);
		obstacles[25] = new ObstaclePillar(1055,446);
		obstacles[26] = new ObstaclePillar(1140,446);		
		obstacles[27] = new ObstaclePillar(290,446);
		obstacles[28] = new ObstaclePillar(205,446);
		
		super.setObstacles(obstacles);
	}
	
}
