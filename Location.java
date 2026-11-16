package application;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public abstract class Location {

    private Location north;
    private Location south;
    private Location east;
    private Location west;
    
    boolean hasBeenHere = false;

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

    public abstract void story(TextArea outputBox, Button option1, Button option2, Button option3 ,Button option4);
    
    public void buttonReset(Button b1, Button b2, Button b3, Button b4) {
    	b1.setText("Option1");
    	b2.setText("Option2");
    	b3.setText("Option3");
    	
    	b1.setVisible(true);
    	b2.setVisible(true);
    	b3.setVisible(true);
    	b4.setVisible(false);
    }
    
    
    
}
