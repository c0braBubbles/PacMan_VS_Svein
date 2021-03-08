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
    protected final Color BACKGROUNdD_COLOR;
    protected final Color MAIN_COLOR;
    private final Map map;
    
    public MapPane() {
        map = new Map();
        BACKGROUNdD_COLOR = Color.BLACK;
        MAIN_COLOR = Color.ALICEBLUE;
    }
    
    public MapPane(Color backgroundColor, Color mainColor) {
        map = new Map();
        this.BACKGROUNdD_COLOR = backgroundColor;
        this.MAIN_COLOR = mainColor;
    }
    
    public Map getMap(){
        return map;
    }
    
    public String[] getSpawnLine(int line) {
        return map.getSpawnLine(line);
    
    }
    
    public void drawMap() {
        App.root.setStyle("-fx-background-color: "
            +BACKGROUNdD_COLOR.toString().replace("0x", "#")
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
    //@Todo fixe så methoden fungerer med scale methodene
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
        Paint color = MAIN_COLOR;
        rect.setFill(color);
        
        this.getChildren().add(rect);
        App.rectangles.add(rect);
        System.out.println("x: "+rect.getX()+" y: "+rect.getY());
        
    }
    

    private void drawFood(double x, double y, boolean notNormalFood) {
        
        double scaleY = App.SIZE_Y/map.getMapData().length;
        double scaleX = App.SIZE_X/map.getMapData()[(int)x].length;  
        
        double dx = scaleX(x, scaleX);
        
        double dy = scaleX(y, scaleY);
        
        var circle = 
            (notNormalFood)
                ?new Circle(dx+(scaleX/2),dy+(scaleY/2),10)
                :new Circle(dx+(scaleX/2),dy+(scaleY/2),5)
            ;
        
        System.out.println("x food: "+circle.getCenterX()+" y food: "+circle.getCenterY());
        
        this.getChildren().add(circle);
        if(notNormalFood){
            Paint color = Color.DARKMAGENTA;
            circle.setFill(color);
            App.circles.add(circle);
        }else if(!notNormalFood){
            Paint color = Color.DARKOLIVEGREEN;
            circle.setFill(color);
            App.circles.add(circle);
        }
    }
    
    public static double scaleX(double x, double scale) {
        double dx = 0;
        if(x!=0){
            dx=x*scale;
        }
        return dx;
    }
    
    public static double scaleY(double y, double scale) {
        double dy = 0;
        if(y!=0){
            dy=y*scale;
        }
        return dy;
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