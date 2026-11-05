package application;

import javafx.scene.control.TextArea;

public abstract class Location {

    private Location north;
    private Location south;
    private Location east;
    private Location west;

    public Location getNorth() {
        return north;
    }

    public void setNorth(Location north) {
        this.north = north;
    }

    public Location getSouth() {
        return south;
    }

    public void setSouth(Location south) {
        this.south = south;
    }

    public Location getEast() {
        return east;
    }

    public void setEast(Location east) {
        this.east = east;
    }

    public Location getWest() {
        return west;
    }

    public void setWest(Location west) {
        this.west = west;
    }

    public abstract void story(TextArea outputBox);
    
    
    
    
    
}
