package com.mycompany.pacman;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;


/**
 * JavaFX App
 */
public class App extends Application {
    
    final int SIZE_X = 800, SIZE_Y = 300;
    String[] paths = {
        "src/main/java/com/mycompany/pacman/red.png",
        "src/main/java/com/mycompany/pacman/blue.png", 
        "src/main/java/com/mycompany/pacman/green.png", 
        "src/main/java/com/mycompany/pacman/yellow.png"
    };
        
    
    @Override
    public void start(Stage stage) throws FileNotFoundException {
        Pane root = new Pane(); 
        root.setStyle("-fx-background-color: black;");
        
        
        /* Scene-oppsett */
        Scene scene = new Scene(root, SIZE_X, SIZE_Y); 
        stage.setScene(scene);
        stage.setTitle("Pac-Man");
        
        
        MrPac pacman = new MrPac();
        pacman.setSpeed(3);
        //root.getChildren().add(pacman.getMrPac());
                
        
        double xpos = SIZE_X/2, ypos = SIZE_Y/2; 
        Ghost red    = new Ghost(paths[0], xpos, ypos);
        Ghost blue   = new Ghost(paths[1], xpos + 100, ypos);
        Ghost green  = new Ghost(paths[2], xpos - 100, ypos);
        Ghost yellow = new Ghost(paths[3], xpos - 200, ypos);
        
        
        root.getChildren().addAll(pacman.getMrPac(), red.getImageView(), blue.getImageView(), green.getImageView(), yellow.getImageView()); 
        
        
        stage.show();
        pacman.startAnimation();
        red.chase(pacman);
        blue.chase(pacman);
        green.chase(pacman);
        yellow.chase(pacman);
        pacman.setMovement();
    }
    

    public static void main(String[] args) {
        launch();
    }

}