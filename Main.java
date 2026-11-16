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
	
	public static void main(String[] args) {    //launches program
        launch(args);
    }
	
	
	private TextArea mapBox;
	private startingLocation startingLoc;
	private Beach beach;
	private Castle castle;
	private Cave cave;
	private Cliff cliff;
	private Forest forest;
	private Hut hut;
	private Tower tower;
	private Village village;
	
	private Location currentLocation;
	
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

        ListView<String> saveList = new ListView<>();  //shows existing file in a list

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
        
        Button deleteSaveButton = new Button("Delete Save");  //delete save button
        
        Button loadButton =
                new Button("Load Save");  //load save button
        
        
        deleteSaveButton.setOnAction(e -> {

            String selected =
                    saveList.getSelectionModel()
                            .getSelectedItem();

            if (selected != null) {

                File saveFile = new File(selected);

                if (saveFile.delete()) {		//deletes file

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
                            .getSelectedItem();  //gets selected file

            if (selected != null) {

                GameSave save =
                        new GameSave(selected);  //creates new GameSave

                System.out.println(
                        "Loaded " + save.getGameName());

                saveStage.close();

                runGame();  //run game here
            }
        });

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);

        root.getChildren().addAll(
                saveList,  			//adds buttons to root to be displayed
                loadButton,
                deleteSaveButton);

        Scene scene =
                new Scene(root, 300, 400);

        saveStage.setScene(scene);
        saveStage.setTitle("Select Save");
        saveStage.show();
    }

    
    
    public void runGame() {

        createLocations();  //creates objects for locations
        
        Stage storyStage = new Stage();

        BorderPane root = new BorderPane();

        //Story
        TextArea outputBox = new TextArea();
        outputBox.setEditable(false);
        outputBox.setWrapText(true);

        //Map
        mapBox = new TextArea();
        mapBox.setEditable(false);
        mapBox.setPrefWidth(200);

        //Movement buttons
        Button northBtn = new Button("North");
        Button southBtn = new Button("South");
        Button eastBtn = new Button("East");
        Button westBtn = new Button("West");

        //Arrange buttons like a compass
        GridPane movementPane = new GridPane();

        movementPane.add(northBtn, 1, 0);
        movementPane.add(westBtn, 0, 1);
        movementPane.add(eastBtn, 2, 1);
        movementPane.add(southBtn, 1, 2);

        movementPane.setHgap(10);
        movementPane.setVgap(10);
        movementPane.setAlignment(Pos.CENTER);
        
        //User choice buttons
        HBox userChoices = new HBox(15);
        Button option1 = new Button("Option 1");
        Button option2 = new Button("Option 2");
        Button option3 = new Button("Option 3");
        Button option4 = new Button("Option 4");
        userChoices.setAlignment(Pos.CENTER);
        
        userChoices.getChildren().addAll(
        		option1, 
        		option2, 
        		option3,
        		option4
        );
        option4.setVisible(false);
        userChoices.setPrefHeight(60);
      

        // Place everything
        root.setTop(movementPane);
        root.setLeft(mapBox);
        root.setCenter(outputBox);
        root.setBottom(userChoices);
        
        
        
        //TODO set up saving commands to file and re-reading them and putting commands back in

        Scene scene = new Scene(root, 800, 600);

        storyStage.setScene(scene);
        storyStage.setTitle("Adventure");
        storyStage.show();


        currentLocation = startingLoc;
        updateMap(mapBox);      //updates map layout
          
        
        
        
        northBtn.setOnAction(e -> {
            if(currentLocation.getNorth() != null) {
                currentLocation = currentLocation.getNorth();
                currentLocation.story(outputBox, option1, option2, option3, option4);
                updateMap(mapBox);
            }
        });
        southBtn.setOnAction(e -> {
            if(currentLocation.getSouth() != null) {
                currentLocation = currentLocation.getSouth();
                currentLocation.story(outputBox, option1, option2, option3, option4);
                updateMap(mapBox);
            }
        });
        eastBtn.setOnAction(e -> {

            if(currentLocation.getEast() != null) {
                currentLocation = currentLocation.getEast();
                currentLocation.story(outputBox, option1, option2, option3, option4);
                updateMap(mapBox);
            }
        });
        westBtn.setOnAction(e -> {
            if(currentLocation.getWest() != null) {
                currentLocation = currentLocation.getWest();
                currentLocation.story(outputBox, option1, option2, option3, option4);
                updateMap(mapBox);
            }
        });
        
        outputBox.setStyle("-fx-font-style: italic;");
        
        startingLoc.instructions(outputBox, option1, option2, option3);
        
        option1.setOnAction(e -> {
        	outputBox.clear();
        	startingLoc.story(outputBox, option1, option2, option3, option4);
        });


        
        
        
    }
    
    
    //current location getter and setter for location classes to use
    public Location getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(Location currentLocation) {
		this.currentLocation = currentLocation;
	}
	public Village getVillage() {
	    return village;
	}
	public Castle getCastle() {
		return castle;
	}
	
	public startingLocation getStart() {
	    return startingLoc;
	}

	public void createLocations() {    //create location objects
    	startingLoc = new startingLocation();
    	beach = new Beach();
    	castle = new Castle(this);
    	cave = new Cave();
    	cliff = new Cliff();
    	forest = new Forest();
    	hut = new Hut();
    	tower = new Tower();
    	village = new Village(this);
    	
    	//Starting location
    	startingLoc.setNorth(castle);
    	startingLoc.setSouth(tower);
    	startingLoc.setEast(village);
    	startingLoc.setWest(beach);
    	
    	//Tower
    	tower.setNorth(startingLoc);
    	tower.setSouth(null);
    	tower.setEast(forest);
    	tower.setWest(cave);
    	
    	//Forest
    	forest.setNorth(village);
    	forest.setSouth(null);
    	forest.setEast(null);
    	forest.setWest(tower);
    	
    	//Beach
    	beach.setNorth(cliff);
    	beach.setSouth(cave);
    	beach.setEast(startingLoc);
    	beach.setWest(null);
    	
    	//Cliff
    	cliff.setNorth(null);
    	cliff.setSouth(beach);
    	cliff.setEast(castle);
    	cliff.setWest(null);
    	
    	//Cave
    	cave.setNorth(beach);
    	cave.setSouth(null);
    	cave.setEast(tower);
    	cave.setWest(null);
    	
    	//Hut
    	hut.setNorth(null);
    	hut.setSouth(village);
    	hut.setEast(null);
    	hut.setWest(castle);
    	
    	//Village
    	village.setNorth(hut);
    	village.setSouth(forest);
    	village.setEast(null);
    	village.setWest(startingLoc);
    	
    	//Castle
    	castle.setNorth(null);
    	castle.setSouth(startingLoc);
    	castle.setEast(hut);
    	castle.setWest(cliff);
    }
	
	public TextArea getMapBox() {
    	return mapBox;
    }
    
    
    public void updateMap(TextArea mapBox) {

        if (currentLocation == startingLoc) {

            mapBox.setText("""
                    [Cliff] —— [Castle] —— [Hut]
                       |\t\t\t|\t\t  |
                    [Beach] —— [  X  ] —— [Village]
                       |\t\t\t|\t\t  |
                    [Cave] —— [Tower] —— [Forest]
                    """);

        } else if (currentLocation == tower) {

            mapBox.setText("""
                    [Cliff] —— [Castle] —— [Hut]
                       |\t\t\t|\t\t  |
                    [Beach] —— [Start] —— [Village]
                       |\t\t\t|\t\t  |
                    [Cave] —— [  X  ] —— [Forest]
                    """);

        } else if (currentLocation == forest) {

            mapBox.setText("""
                    [Cliff] —— [Castle] —— [Hut]
                       |\t\t\t|\t\t  |
                    [Beach] —— [Start] —— [Village]
                       |\t\t\t|\t\t  |
                    [Cave] —— [Tower] —— [  X  ]
                    """);

        } else if (currentLocation == beach) {

            mapBox.setText("""
                    [Cliff] —— [Castle] —— [Hut]
                       |\t\t\t|\t\t  |
                    [  X  ] —— [Start] —— [Village]
                       |\t\t\t|\t\t  |
                    [Cave] —— [Tower] —— [Forest]
                    """);

        } else if (currentLocation == cliff) {

            mapBox.setText("""
                    [  X  ] —— [Castle] —— [Hut]
                       |\t\t\t|\t\t  |
                    [Beach] —— [Start] —— [Village]
                       |\t\t\t|\t\t  |
                    [Cave] —— [Tower] —— [Forest]
                    """);

        } else if (currentLocation == cave) {

            mapBox.setText("""
                    [Cliff] —— [Castle] —— [Hut]
                       |\t\t\t|\t\t  |
                    [Beach] —— [Start] —— [Village]
                       |\t\t\t|\t\t  |
                    [  X  ] —— [Tower] —— [Forest]
                    """);

        } else if (currentLocation == hut) {

            mapBox.setText("""
                    [Cliff] —— [Castle] —— [  X  ]
                       |\t\t\t|\t\t  |
                    [Beach] —— [Start] —— [Village]
                       |\t\t\t|\t\t  |
                    [Cave] —— [Tower] —— [Forest]
                    """);

        } else if (currentLocation == village) {

            mapBox.setText("""
                    [Cliff] —— [Castle] —— [Hut]
                       |\t\t\t|\t\t  |
                    [Beach] —— [Start] —— [  X  ]
                       |\t\t\t|\t\t  |
                    [Cave] —— [Tower] —— [Forest]
                    """);

        } else if (currentLocation == castle) {

            mapBox.setText("""
                    [Cliff] —— [  X  ] —— [Hut]
                       |\t\t\t|\t\t  |
                    [Beach] —— [Start] —— [Village]
                       |\t\t\t|\t\t  |
                    [Cave] —— [Tower] —— [Forest]
                    """);
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}