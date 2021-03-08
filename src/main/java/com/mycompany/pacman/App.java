package com.mycompany.pacman;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


/**
 * JavaFX App
 * @author Mats
 * @author Svein
 */
public class App extends Application {

    
    public static final int SIZE_X = 600, SIZE_Y = 600;
    public static final int SIZE_X_GRID = 460, SIZE_Y_GRID = 560;
    static BorderPane root;
    static MapPane mp;
    
    public static String[] paths = {
        "src/main/java/com/mycompany/pacman/red.png",
        "src/main/java/com/mycompany/pacman/blue.png", 
        "src/main/java/com/mycompany/pacman/green.png", 
        "src/main/java/com/mycompany/pacman/yellow.png",
        "src/main/java/com/mycompany/pacman/shit.png"
    };
        
    
    @Override
    public void start(Stage stage) throws FileNotFoundException {
        root = new BorderPane();
        //root.setStyle("-fx-background-color: 000000ff;");
        
        // MapPane oppsett
        mp = new MapPane();
        mp.drawMap();
        mp.setPrefHeight(SIZE_Y_GRID);
        mp.setPrefWidth(SIZE_Y_GRID);
        
        
        /* Scene-oppsett */
        Scene scene = new Scene(root, SIZE_X, SIZE_Y); 
        stage.setScene(scene);
        stage.setTitle("Pac-Man");
 
        root.setCenter(mp);  
     
        stage.show();
        
    }
    

    public static void main(String[] args) {
        launch();
        
    }

}