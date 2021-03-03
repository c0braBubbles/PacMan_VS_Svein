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
    
    /* Filsti til når du jobber på stasjonær */
    /*String[] paths = {
        "C:\\Users\\mjoen\\OneDrive\\Dokumenter\\NetBeansProjects\\PacMan_VS_Svein\\src\\main\\java\\com\\mycompany\\pacman\\red.png", 
        "C:\\Users\\mjoen\\OneDrive\\Dokumenter\\NetBeansProjects\\PacMan_VS_Svein\\src\\main\\java\\com\\mycompany\\pacman\\blue.png", 
        "C:\\Users\\mjoen\\OneDrive\\Dokumenter\\NetBeansProjects\\PacMan_VS_Svein\\src\\main\\java\\com\\mycompany\\pacman\\green.png",
        "C:\\Users\\mjoen\\OneDrive\\Dokumenter\\NetBeansProjects\\PacMan_VS_Svein\\src\\main\\java\\com\\mycompany\\pacman\\yellow.png"
    };*/
    
    /* Filsti til når du jobber på laptop */
    String[] paths = {
        "C:\\Users\\Mats Engesund\\Documents\\NetBeansProjects\\PacMan\\src\\main\\java\\com\\mycompany\\pacman\\red.png", 
        "C:\\Users\\Mats Engesund\\Documents\\NetBeansProjects\\PacMan\\src\\main\\java\\com\\mycompany\\pacman\\blue.png",
        "C:\\Users\\Mats Engesund\\Documents\\NetBeansProjects\\PacMan\\src\\main\\java\\com\\mycompany\\pacman\\green.png",
        "C:\\Users\\Mats Engesund\\Documents\\NetBeansProjects\\PacMan\\src\\main\\java\\com\\mycompany\\pacman\\yellow.png"
    };
        
    
    @Override
    public void start(Stage stage) throws FileNotFoundException {
        Pane root = new Pane(); 
        root.setStyle("-fx-background-color: black;");
        
        
        /* Scene-oppsett */
        Scene scene = new Scene(root, 800, 300); 
        stage.setScene(scene);
        stage.setTitle("Pac-Man");
        
        
        MrPac pacman = new MrPac();
        root.getChildren().add(pacman.getMrPac());
                
        
        double xpos = 600.0, ypos = 200.0; 
        Red red = new Red(paths[0], xpos, ypos);
        root.getChildren().add(red.getImageView());
        
        
        stage.show();
        pacman.startAnimation();
        red.chase(pacman);
        pacman.setMovement();
    }
    

    public static void main(String[] args) {
        launch();
    }

}