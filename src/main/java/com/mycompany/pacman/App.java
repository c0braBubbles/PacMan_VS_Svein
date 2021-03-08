package com.mycompany.pacman;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
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
    public static Circle cir, cir2, cir3; 
    public static ArrayList<Circle> circles = new ArrayList<Circle>();
    
    /**
     * De store sirklene 
     * som får spøkelsene til å flykte 
     */
    public static Circle bigCir, bigCir2;
    public static ArrayList<Circle> bigCircles = new ArrayList<Circle>();
    
    public static final int SIZE_X = 600, SIZE_Y = 600;
    public static BorderPane root;
    public static MapPane mp;
    
    public static Text gameOver;
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
        
        
        gameOver = new Text(); 
        gameOver.setText("GAME OVER");
        gameOver.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        gameOver.setFill(Color.RED);
        gameOver.setX(SIZE_X/2);
        gameOver.setY(SIZE_Y/2);
        
        
        /* Scene-oppsett */
        Scene scene = new Scene(root, SIZE_X, SIZE_Y); 
        stage.setScene(scene);
        stage.setTitle("Pac-Man");
        
        
        MrPac pacman = new MrPac();
        pacman.setSpeed(2);
                

         
        double xpos = SIZE_X/2, ypos = SIZE_Y/2; 
        Red red = new Red(paths[0], xpos, ypos);
        red.setSpeed(1.0);
        Blue blue = new Blue(paths[1], xpos + 100, ypos);
        blue.setSpeed(1.0);
        Ghost green = new Ghost(paths[2], xpos - 100, ypos);
        green.setSpeed(1.0);
        Ghost yellow = new Ghost(paths[3], xpos - 200, ypos);
        yellow.setSpeed(1.0);


        
        //root.setCenter(mp); 
        mp.getChildren().addAll(pacman.getMrPac(), red.getImageView(), blue.getImageView(), green.getImageView(), yellow.getImageView()); 
        root.setCenter(mp);
        
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