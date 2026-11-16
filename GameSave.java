package application;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class GameSave {

    private String gameName;

    public GameSave(String gameName) {    //new game save constructor
        this.gameName = gameName;

        try {
            Files.writeString(
                Path.of(gameName), "", StandardOpenOption.CREATE);  //create file for new save
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getGameName() {  //get name of file
        return gameName;
    }
    
    public void updateSave(String command) {
        try {
            Files.writeString(
                Path.of(gameName),
                command + "\n",
                StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void death(TextArea outputBox, Button option1, Button option2, Button option3) {
    	outputBox.appendText("""
    			
    			----------------------------------------------
    			You have DIED!
    			
    			You made the wrong series of decisions
    			
    			Feel free to try again using the button at the botton of your screen!
    			
    			""");
    	option2.setVisible(false);
    	option3.setVisible(false);
    	option1.setText("Retry Aventure");
    	option1.setOnAction(e -> {
    		//TODO make function to reset save file and start over game
    	});
    }
    public void adventureComplete(TextArea outputBox, Button option1, Button option2, Button option3) {
    	
    	
    	
    	option3.setVisible(false);
    	option1.setText("Retry Aventure");
    	option2.setText("Exit Game");
    	option1.setOnAction(e -> {
    		//TODO make function to reset save file and start over game
    	});
    	option2.setOnAction(e -> {
    		//TODO exit window
    	});
    }

    
    
    
    
    
    
}