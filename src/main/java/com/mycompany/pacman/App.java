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
    /*
    public static Rectangle rect;
    public static Rectangle rect2;
    public static Rectangle rect3;
    */
    public static ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
    
    /**
     * De små sirklene
     * som bare gir deg litt poeng
     */
    public static ArrayList<Circle> circles = new ArrayList<Circle>();
    
    /**
     * De store sirklene 
     * som får spøkelsene til å flykte 
     */
    public static Circle bigCir, bigCir2;
    public static ArrayList<Circle> bigCircles = new ArrayList<Circle>();
    
    public static final int SIZE_X = 600, SIZE_Y = 600;
    public static final int SIZE_X_GRID = 460, SIZE_Y_GRID = 560;
    public static BorderPane root;
    public static Pane wrapperPane;
    public static MapPane mp;
    
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
        
        
        MrPac pacman = new MrPac();
        pacman.setSpeed(2);
                

        
        


        
        root.setCenter(mp);  
            mp.getChildren().addAll(
                pacman.getMrPac(), 
                red.getImageView(), 
                blue.getImageView(), 
                green.getImageView(), 
                yellow.getImageView()
            );
        
        stage.show();
        pacman.startAnimation();
        pacman.setMovement();
        red.chase(pacman);
        blue.chase(pacman);
        green.chase(pacman);
        yellow.chase(pacman);
    }
    

    public static void main(String[] args) {
        launch();
        
    }

}