package application;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class Hut extends Location{

	public Hut() {
		
		
	}


	@Override
	public void story(TextArea outputBox, Button option1, Button option2, Button option3, Button option4) {
		buttonReset(option1, option2, option3, option4);
		option1.setVisible(true);
		option2.setVisible(true);
		option3.setVisible(false);
		
		option1.setText("Knock");
		option2.setText("Snoop around");
		
		outputBox.setText("""
				
				You approach an old hut that looks a little run down
				
				"Huh I wonder if anyone is home?"
				
				""");
		
		option1.setOnAction(e -> {
			knock(outputBox, option1, option2, option3, option4);
		});
		option2.setOnAction(e -> {
			snoopAround(outputBox, option1, option2, option3, option4);
		});
		
	}
	
	
	public void knock(TextArea outputBox, Button option1, Button option2, Button option3, Button option4) {
		option1.setVisible(true);
		option2.setVisible(false);
		option3.setVisible(false);
		
		option1.setText("Continue");
		
		outputBox.setText("""
				
				You walk up to the slightly rotted door and give it a hard knock
				
				No answer
				
				You knock again
				
				No answer again
				
				"Hello is anyone home!"
				
				Suddenly the door opens a crack
				
				Strange man: "Who are you and what do you want"
				
				"Im not sure who I am and I just want to learn more about this place"
				
				Strange man: "More about this place huh?"
				"Well you don't look like your with The Dark Crown"
				"Alright come in, make it quick"
				
				He rushes you inside and quickly closes and locks the door
				
				""");
		
		option1.setOnAction(e -> {
			enterHut(outputBox, option1, option2, option3, option4);
		});
		
	
	}
	public void snoopAround(TextArea outputBox, Button option1, Button option2, Button option3, Button option4) {
		option1.setVisible(true);
		option2.setVisible(false);
		option3.setVisible(false);
		
		option1.setText("Continue");
		
		outputBox.setText("""
				
				
				""");
		
		option1.setOnAction(e -> {
			enterHut(outputBox, option1, option2, option3, option4);
		});
	}
	
	public void enterHut(TextArea outputBox, Button option1, Button option2, Button option3, Button option4) {
		
	}
	
	public void oldWarriorStory() {
		
	}
	public void findKing() {
		
	}

}
