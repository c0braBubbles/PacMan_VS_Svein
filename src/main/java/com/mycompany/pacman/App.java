package com.mycompany.pacman;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * JavaFX App
 */
public class App extends Application {
    
    public static final int SIZE_X = 800, SIZE_Y = 300;
    public static Rectangle rect; 
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
        red.setSpeed(1.0);
        Ghost blue   = new Ghost(paths[1], xpos + 100, ypos);
        blue.setSpeed(1.0);
        Ghost green  = new Ghost(paths[2], xpos - 100, ypos);
        green.setSpeed(1.0);
        Ghost yellow = new Ghost(paths[3], xpos - 200, ypos);
        yellow.setSpeed(1.0);
        
        
        root.getChildren().addAll(pacman.getMrPac(), red.getImageView(), blue.getImageView(), green.getImageView(), yellow.getImageView()); 
        
        
        rect = new Rectangle(100, 100, 100, 100);
        rect.setFill(Color.BLUE);
        root.getChildren().add(rect);
        
        
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