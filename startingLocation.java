package application;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;


public class startingLocation extends Location{
	
	private boolean hasGold = false;
	
	
	public startingLocation() {
		
		
	}
	
	
	@Override
	public void story(TextArea outputBox, Button option1, Button option2, Button option3, Button option4) {
		buttonReset(option1, option2, option3, option4);
		if(!hasBeenHere) {
		option1.setVisible(true);
		option2.setVisible(true);
		outputBox.setText("""
			    You wake up finding yourself lying in soft grass.

			    You sit up slowly, taking in your surroundings.

			    "Ugh, my head hurts."

			    "Where am I?"

			    You look around and see four cobblestone roads converging at an intersection.

			    "Hmmm... some roads? I wonder where they lead?"

			    As you approach the intersection, you notice a crude map drawn on a piece of wood.

			    Options:

			    - Option1: Follow one of the four roads
			    - Option2: Keep looking around
			    """);
		
		option1.setText("Follow a Road");
		option2.setText("Look Around");
		option3.setVisible(false);
		

		option1.setOnAction(e -> {
			buttonReset(option1, option2, option3, option4);
		    crossroads(outputBox, option1, option2, option3, option4);
		});

		option2.setOnAction(e -> {
			buttonReset(option1, option2, option3, option4);
		    lookAround(outputBox, option1, option2, option3, option4);
		});
		hasBeenHere = true;
		}
		else {
			buttonReset(option1, option2, option3, option4);
			returned(outputBox);
			
		}
		
	}
	
	
	
	
	
	public void crossroads(TextArea outputBox, Button option1, Button option2, Button option3, Button option4) {
		
		option1.setVisible(false);
		option2.setVisible(false);
		option3.setVisible(false);
		outputBox.setText("""
				
				Choose a direction to travel
				""");
	}
	
	public void lookAround(TextArea outputBox, Button option1, Button option2, Button option3, Button option4) {
		outputBox.setText("""
				
				You search the area more carefully and find a small leather pouch lying in the grass
				
				You reach down to pick it up
				
				As you open it, you see gold coins glinting in the sunlight
				
				"Gold!" 
				
				"Hmmmm... I wonder where I can use this
		    """);
		setHasGold(true);
		option1.setText("Continue");
		option2.setVisible(false);
		option3.setVisible(false);
		option1.setOnAction(e -> {
			crossroads(outputBox, option1, option2, option3, option4);
		});
	}
	
	
	
	public void returned(TextArea outputBox) {
		
		
		//TODO if hasGold = false prompt to look around again
		outputBox.clear();
		outputBox.setText("""
				
				You reach the crossroads
				
				Choose a direction to travel
		    """);
		
	}
	

	public void instructions(TextArea outputBox, Button option1, Button option2, Button option3) {
		
		option1.setText("Start Adventure");
		option2.setVisible(false);
		option3.setVisible(false);
		outputBox.setText("Instructions:\n\n"
				+""
				+ "- The world map is located on the left side of the screen\n"
				+ "- You may move North, South, East, and West using the buttons at the top of your screen if a location is there\n"
				+ "- When prompted, use the 'Option' buttons at the bottom of your screen to choose your path and progress\n\n"
				+ ""
				+ "Whats the goal of this game? \n"
				+ "Thats for you to find out\n"
				+ "Good luck adventurer\n\n"
				+ ""
				+ "Click 'Start Adventure' below to begin!");
		
	}


	public boolean isHasGold() {
		return hasGold;
	}


	public void setHasGold(boolean hasGold) {
		this.hasGold = hasGold;
	}
	
	
}
