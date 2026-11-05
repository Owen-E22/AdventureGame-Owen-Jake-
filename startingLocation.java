package application;

import javafx.scene.control.TextArea;


public class startingLocation extends Location{
	
	public startingLocation() {
		
		
	}
	
	
	@Override
	public void story(TextArea outputBox) {
		outputBox.setText("You wake up finding yourself lying in soft grass");
	}

	
	
}
