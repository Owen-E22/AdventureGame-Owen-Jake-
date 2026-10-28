package application;

public class Location {
	
	
	String locName;
	boolean displayLoc;
	
	
	
	
	public Location(String name) {
		this.locName = name;
		displayLoc = false;
	}
	
	
	
	public void show(Location l) {
		displayLoc = true;
	}

}
