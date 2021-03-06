/*
 * 
 */
package com.mycompany.pacman;

import javafx.scene.layout.BackgroundFill;
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
    public final int SCALE_MAP = 0;
    private final Color backgroundColor;
    private final Color mainColor;
    private final Map map;
    
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
        App.root.setStyle(
            "-fx-background-color: "
            +backgroundColor.toString().replace("0x", "#")
        );
        for(int i=0;i<map.getMapData().length;i++){
            for(int j=0;j<map.getMapData()[i].length; j++){
                switch(map.getMapData()[i][j]){
                    case "K": drawPacMap(j,i); // kant
                        break;
                    case "0": drawPacMap(j,i); // hindring inne i kartet
                        break;
                    case "A": drawFood(j,i,false);// mat for pacman
                        break;
                    case "C": drawFood(j,i,true);// "chase ability" for pacman
                        break;
                    case "Y": // ingen mat
                        break;
                    case "X": // tomt
                        break;
                    case "S": //ghostSpawn();// spøkelse spawn
                        break;
                    case "U": //noAccsessPacMan(j,i); // dør spøkelse spawn 
                        break;
                    case "B": drawPacMap(j,i);//drawGostSpawn(j,i)// spøkelse bur
                    default:
                }
            }
        }
    }
    
    // tegner pacmap
    private void drawPacMap(double dx, double dy) {
        
        double scaleY = App.SIZE_Y/map.getMapData().length;
        double scaleX = App.SIZE_X/map.getMapData()[(int)dx].length;  
        
        if(dx!=0){
            
            dx=dx*scaleX;
        }
        
        if(dy!=0){
            dy=dy*scaleY;
        }
        
        double x = dx;
        double y = dy;
        
        var rect = new Rectangle(x,y,scaleX,scaleY);
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

    private void drawFood(double dx, double dy, boolean notNormalFood) {
        
        double scaleY = App.SIZE_Y/map.getMapData().length;
        double scaleX = App.SIZE_X/map.getMapData()[(int)dx].length;  
        
        if(dx!=0){
            
            dx=dx*scaleX;
        }
        
        if(dy!=0){
            dy=dy*scaleY;
        }
        
        double x = dx;
        double y = dy;
        
        var circle = 
            (notNormalFood)
                ?new Circle(x+(scaleX/2),y+(scaleY/2),7)
                :new Circle(x+(scaleX/2),y+(scaleY/2),4)
            ;
        
        this.getChildren().add(circle);
        if(notNormalFood){
            Paint color = Color.DARKMAGENTA;
            circle.setFill(color);
            App.bigCircles.add(circle);
        }else if(!notNormalFood){
            Paint color = Color.DARKOLIVEGREEN;
            circle.setFill(color);
            App.circles.add(circle);
        }
    }

    private void ghostSpawn() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void drawGostSpawn(int j, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void noAccsessPacMan(int j, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}