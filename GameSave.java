package application;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

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

    
    
    
    
    
    
}