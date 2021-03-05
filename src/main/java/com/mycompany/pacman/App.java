package com.mycompany.pacman;

import java.io.File;
import java.io.FileNotFoundException;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        Pane root = new Pane(); 
        root.setStyle("-fx-background-color: black;");
        
        
        /* Scene-oppsett */
        Scene scene = new Scene(root, 500, 500); 
    /*public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, 800, 300); 
        stage.setScene(scene);
        stage.setTitle("Pac-Man");
        
        
        MrPac pacman = new MrPac();
        root.getChildren().add(pacman.getMrPac());
        pacman.startAnimation();
       
 
        stage.show();
    }*/
        stage.show();
    }

    public static void main(String[] args) {
        MapStructure ms = new MapStructure();
        System.out.println(ms.toString());
        //launch();
        
    }

}