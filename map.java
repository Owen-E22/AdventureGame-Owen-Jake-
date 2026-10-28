package application;

public class map {

		int numOfLocs = 9;
		
		public map() {
			Location[][] map = new Location[2][2];
			map[0][0] = new cliff("Cliff");
			map[0][1] = new castle("Castle");	//set up first row of map
			map[0][2] = new hut("hut");
			
			map[1][0] = new beach("Beach");
			map[1][1] = new startingLocation("Spawn");	//set up second row of map
			map[1][2] = new village("Village");
			
			map[2][0] = new cave("Cave");
			map[2][1] = new tower("Tower"); 	//set up third row of map
			map[2][2] = new darkForest("Dark Forest");
		}
		
		public void showLoc(Location l) {
			showLoc(l);
		}
		
		
		
		
		
		
		
}
