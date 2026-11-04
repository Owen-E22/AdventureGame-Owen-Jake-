package application;

import javafx.scene.control.TextField;
import java.io.File;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	
	private startingLocation startingLoc;
	private Beach beach;
	private Castle castle;
	private Cave cave;
	private Cliff cliff;
	private Forest forest;
	private Hut hut;
	private Tower tower;
	private Village village;
	
	private String currentLocation;
	
    @Override
    public void start(Stage primaryStage) {

        Button btnNew = new Button("New Game");
        Button btnContinue = new Button("Continue Game");  //create buttons

        btnNew.setOnAction(e -> newGame()); //call newGame method

        btnContinue.setOnAction(e -> continueGame());  //call continueGame method

        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(btnNew, btnContinue);  //put buttons on screen

        Scene scene = new Scene(root, 400, 300);

        primaryStage.setTitle("Adventure Game");
        primaryStage.setScene(scene);             //make window
        primaryStage.show();
    }

    private void newGame() {

        TextInputDialog dialog =
                new TextInputDialog();

        dialog.setTitle("New Game");
        dialog.setHeaderText("Create Save");    //new game window layout
        dialog.setContentText("Save Name:");

        dialog.showAndWait().ifPresent(name -> {

            GameSave save =
                    new GameSave(name + ".save");  //create new game save

            System.out.println(
                    "Created " + save.getGameName());

            
            runGame();  // Launch game 
        });
    }

    private void continueGame() {

        Stage saveStage = new Stage();

        ListView<String> saveList = new ListView<>();

        File folder = new File(".");

        File[] files = folder.listFiles();

        if (files != null) {

            for (File file : files) {

                if (file.getName().endsWith(".save")) {

                    saveList.getItems()
                            .add(file.getName());
                }
            }
        }
        
        Button deleteSaveButton = new Button("Delete Save");
        
        Button loadButton =
                new Button("Load Save");
        
        
        deleteSaveButton.setOnAction(e -> {

            String selected =
                    saveList.getSelectionModel()
                            .getSelectedItem();

            if (selected != null) {

                File saveFile = new File(selected);

                if (saveFile.delete()) {

                    System.out.println(selected + " deleted.");

                    
                    saveList.getItems().remove(selected);  // Remove it from the displayed list

                } else {

                    System.out.println("Could not delete file.");
                }
            }
        });
        
        
        loadButton.setOnAction(e -> {

            String selected =
                    saveList.getSelectionModel()
                            .getSelectedItem();

            if (selected != null) {

                GameSave save =
                        new GameSave(selected);

                System.out.println(
                        "Loaded " + save.getGameName());

                saveStage.close();

                runGame();  //run game here
            }
        });

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);

        root.getChildren().addAll(
                saveList,
                loadButton,
                deleteSaveButton);

        Scene scene =
                new Scene(root, 300, 400);

        saveStage.setScene(scene);
        saveStage.setTitle("Select Save");
        saveStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public void runGame() {

        createLocations();
        
        Stage storyStage = new Stage();

        BorderPane root = new BorderPane();

        // STORY TEXT
        TextArea outputBox = new TextArea();
        outputBox.setEditable(false);
        outputBox.setWrapText(true);

        // MAP
        TextArea mapBox = new TextArea();
        mapBox.setEditable(false);
        mapBox.setPrefWidth(200);

        // MOVEMENT BUTTONS
        Button northBtn = new Button("North");
        Button southBtn = new Button("South");
        Button eastBtn = new Button("East");
        Button westBtn = new Button("West");

        // Arrange buttons like a compass
        GridPane movementPane = new GridPane();

        movementPane.add(northBtn, 1, 0);
        movementPane.add(westBtn, 0, 1);
        movementPane.add(eastBtn, 2, 1);
        movementPane.add(southBtn, 1, 2);

        movementPane.setHgap(10);
        movementPane.setVgap(10);
        movementPane.setAlignment(Pos.CENTER);

        // Place everything
        root.setTop(movementPane);
        root.setLeft(mapBox);
        root.setCenter(outputBox);

        Scene scene = new Scene(root, 800, 600);

        storyStage.setScene(scene);
        storyStage.setTitle("Awakening");
        storyStage.show();

        startingLoc.story(outputBox);  
        currentLocation = "Start";
        updateMap(mapBox);
        startingLoc.story(outputBox);

//        // temporary map
//        mapBox.setText("""
//        [Cliff] —— [Castle] —— [Hut]
//           |\t\t\t|\t\t|
//        [Beach] —— [Start]  —— [Village]
//           |\t\t\t|\t\t|
//        [Cave]  ——  [Tower] —— [Dark Forest]
//                """);
    }
    
    public void createLocations() {    //create location objects
    	startingLoc = new startingLocation();
    	beach = new Beach();
    	castle = new Castle();
    	cave = new Cave();
    	cliff = new Cliff();
    	forest = new Forest();
    	hut = new Hut();
    	tower = new Tower();
    	village = new Village();
    	
    	startingLoc.setNorth(castle);
    	startingLoc.setSouth(tower);
    	startingLoc.setEast(village);
    	startingLoc.setWest(beach);
    	
    	
    	
    	
    	
    }
    
    
    private void updateMap(TextArea mapBox) {

        switch(currentLocation) {

            case "Start":
            	mapBox.setText("""
            	        [Cliff] —— [Castle] —— [Hut]
            	           |\t\t\t|\t\t|
            	        [Beach] —— [  X  ]  —— [Village]
            	           |\t\t\t|\t\t|
            	        [Cave]  ——  [Tower] —— [Forest]
            	                """);
                break;

            case "Tower":
            	mapBox.setText("""
            	        [Cliff] —— [Castle] —— [Hut]
            	           |\t\t\t|\t\t|
            	        [Beach] —— [Start]  —— [Village]
            	           |\t\t\t|\t\t|
            	        [Cave]  ——  [  X  ] —— [Forest]
            	                """);
                break;

            case "Forest":
            	mapBox.setText("""
            	        [Cliff] —— [Castle] —— [Hut]
            	           |\t\t\t|\t\t|
            	        [Beach] —— [Start]  —— [Village]
            	           |\t\t\t|\t\t|
            	        [Cave]  ——  [Tower] —— [  X  ]
            	                """);
                break;

            case "Beach":
            	mapBox.setText("""
            	        [Cliff] —— [Castle] —— [Hut]
            	           |\t\t\t|\t\t|
            	        [  X  ] —— [Start]  —— [Village]
            	           |\t\t\t|\t\t|
            	        [Cave]  ——  [Tower] —— [Forest]
            	                """);
                break;
                
            case "Cliff":
            	mapBox.setText("""
            	        [  X  ] —— [Castle] —— [Hut]
            	           |\t\t\t|\t\t|
            	        [Beach] —— [Start]  —— [Village]
            	           |\t\t\t|\t\t|
            	        [Cave]  ——  [Tower] —— [Forest]
            	                """);
                break;
                
            case "Cave":
            	mapBox.setText("""
            	        [Cliff] —— [Castle] —— [Hut]
            	           |\t\t\t|\t\t|
            	        [Beach] —— [Start]  —— [Village]
            	           |\t\t\t|\t\t|
            	        [  X  ]  —— [Tower] —— [Forest]
            	                """);
                break;
                
            case "Hut":
            	mapBox.setText("""
            	        [Cliff] —— [Castle] —— [ X ]
            	           |\t\t\t|\t\t|
            	        [Beach] —— [Start]  —— [Village]
            	           |\t\t\t|\t\t|
            	        [Cave]  ——  [Tower] —— [Forest]
            	                """);
                break;
                
            case "Village":
            	mapBox.setText("""
            	        [Cliff] —— [Castle] —— [Hut]
            	           |\t\t\t|\t\t|
            	        [Beach] —— [Start]  —— [   X   ]
            	           |\t\t\t|\t\t|
            	        [Cave]  ——  [Tower] —— [Forest]
            	                """);
                break;
                
            case "Castle":
            	mapBox.setText("""
            	        [Cliff] —— [  X  ]  —— [Hut]
            	           |\t\t\t|\t\t|
            	        [Beach] —— [Start]  —— [Village]
            	           |\t\t\t|\t\t|
            	        [Cave]  ——  [Tower] —— [Forest]
            	                """);
                break;
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}