/*
 * 
 */
package com.mycompany.pacman;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author svein
 *
 */
public class MapPane extends Pane {
    public static final double SCALE_MAP = 
        Math.sqrt(
            Math.pow(App.SIZE_X, 2) 
            + Math.pow(App.SIZE_Y, 2)
        )/2;
    double scale = 20;
    private Color backgroundColor;
    private Color mainColor;
    private Map map;
    
    public MapPane() {
        map = new Map();
        backgroundColor = Color.BLACK;
        mainColor = Color.ALICEBLUE;
    }
    
    public MapPane(Color backgroundColor, Color mainColor) {
        map = new Map();
        this.backgroundColor = backgroundColor;
        this.mainColor = mainColor;
    }
    
    
    public void drawMap() {
        double x = 0;
        double y = 0;
        for(int i=0;i<map.getMapData().length;i++){
            System.out.println(y);
            y++;
            y=y+scale;
            x=0;
            for(int j=0;j<map.getMapData()[i].length; j++){
                x++;
                x=x+scale;
                switch(map.getMapData()[i][j]){
                    case "K": drawEdge(x,y); // kant
                        break;
                    /*case "0": // hindring inne i kartet
                        break;
                    case "A": // mat for pacman
                        break;
                    case "C": // "chase ability" for pacman
                        break;
                    case "Y": // ingen mat
                        break;
                    case "X": // tomt
                        break;
                    case "S": // spøkelse spawn
                        break;
                    case "U": // dør spøkelse spawn 
                        break;
                    case "B": // spøkelse bur
                    default:
                    */
                }
            }
        }
    }
    
    private void drawEdge(double x, double y) {
        var rect = new Rectangle(x,y,10,10);
        Paint color = mainColor;
        rect.setFill(color);
        this.getChildren().add(rect);
        App.rectangles.add(rect);
        System.out.println("x: "+rect.getX()+" y: "+rect.getY());
        
    }
    
    /*
        this.getChildren().add();
        rectangles.add(rect);
 
        rect2 = new Rectangle(300, 50, 50, 100);
        rect2.setFill(Color.RED);
        mp.getChildren().add(rect2);
        rectangles.add(rect2);
        
        rect3 = new Rectangle(600, 100, 100, 50); 
        rect3.setFill(Color.GREEN);
        mp.getChildren().add(rect3); 
        rectangles.add(rect3);
        
        
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
    
    
}