package com.mycompany.pacman;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * JavaFX App
 */
public class App extends Application {
    
    public static Rectangle rect;
    public static Rectangle rect2;
    public static Rectangle rect3;
    public static Rectangle rect4;
    public static ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
    
    /**
     * De små sirklene
     * som bare gir deg litt poeng
     */
    public static Circle cir, cir2, cir3; 
    public static ArrayList<Circle> circles = new ArrayList<Circle>();
    
    /**
     * De store sirklene 
     * som får spøkelsene til å flykte 
     */
    public static Circle bigCir, bigCir2;
    
    public static final int SIZE_X = 800, SIZE_Y = 300;
    public static BorderPane root;
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
        root.setStyle("-fx-background-color: black;");
        
        // MapPane oppsett
        mp = new MapPane();
        
        
        /* Scene-oppsett */
        Scene scene = new Scene(root, SIZE_X, SIZE_Y); 
        stage.setScene(scene);
        stage.setTitle("Pac-Man");
        
        
        MrPac pacman = new MrPac();
        pacman.setSpeed(3);
                
        
        double xpos = SIZE_X/2, ypos = SIZE_Y/2; 
        Red red    = new Red(paths[0], xpos, ypos);
        red.setSpeed(1.0);
        Ghost blue   = new Ghost(paths[1], xpos + 100, ypos);
        blue.setSpeed(1.0);
        Ghost green  = new Ghost(paths[2], xpos - 100, ypos);
        green.setSpeed(1.0);
        Ghost yellow = new Ghost(paths[3], xpos - 200, ypos);
        yellow.setSpeed(1.0);
        Ghost shit = new Ghost(paths[4], xpos + 200, ypos);
        
        
        root.setCenter(mp); 
        mp.getChildren().addAll(pacman.getMrPac(), red.getImageView(), blue.getImageView(), green.getImageView(), yellow.getImageView(), shit.getImageView()); 
        
        
        /* Teste kræsj med rektangler *//*
        rect = new Rectangle(100, 100, 100, 100);
        rect.setFill(Color.BLUE);
        mp.getChildren().add(rect);
        rectangles.add(rect);
 

        rect2 = new Rectangle(300, 50, 50, 100);
        rect2.setFill(Color.RED);
        mp.getChildren().add(rect2);
        rectangles.add(rect2);
        
        rect3 = new Rectangle(600, 100, 100, 50); 
        rect3.setFill(Color.GREEN);
        mp.getChildren().add(rect3); 
        rectangles.add(rect3);
        
        rect4 = new Rectangle(200, 100, 60, 80);
        rect4.setFill(Color.GREY);
        root.getChildren().add(rect4); 
        rectangles.add(rect4);
        
        
        /* Teste sirkler *//*
        cir = new Circle(200, 250, 10); 
        cir.setFill(Color.GRAY);
        mp.getChildren().add(cir); 
        circles.add(cir); 
        
        cir2 = new Circle(250, 250, 10); 
        cir2.setFill(Color.GRAY);
        mp.getChildren().add(cir2); 
        circles.add(cir2); 
        
        cir3 = new Circle(300, 250, 10); 
        cir3.setFill(Color.GRAY);
        mp.getChildren().add(cir3); 
        circles.add(cir3); 
        
        /* Teste med store sirkler *//*
        bigCir = new Circle(350, 250, 20); 
        bigCir.setFill(Color.GRAY);
        mp.getChildren().add(bigCir); 
        circles.add(bigCir);
        */
        
        stage.show();
        pacman.startAnimation();
        red.chase(pacman);
        blue.chase(pacman);
        green.chase(pacman);
        yellow.chase(pacman);
    }
    

    public static void main(String[] args) {
        MapStructure ms = new MapStructure();
        System.out.println(ms.toString());
        //launch();
        
    }

}