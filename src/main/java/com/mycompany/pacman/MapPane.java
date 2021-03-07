/*
 * 
 */
package com.mycompany.pacman;

import java.io.FileNotFoundException;
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
    private final Color BACKGROUNdD_COLOR;
    private final Color MAIN_COLOR;
    private final Map map;
    
    public MapPane() {
        map = new Map();
        BACKGROUNdD_COLOR = Color.BLACK;
        MAIN_COLOR = Color.ALICEBLUE;
    }
    
    /**
     *
     * @param backgroundColor
     * @param mainColor
     */
    public MapPane(Color backgroundColor, Color mainColor) {
        map = new Map();
        this.BACKGROUNdD_COLOR = backgroundColor;
        this.MAIN_COLOR = mainColor;
    }
    
    /**
     *
     * @return
     */
    public Map getMap(){
        return map;
        
    }
    
    /**
     *
     * @param line
     * @return
     */
    public String[] getSpawnLine(int line) {
        return map.getSpawnLine(line);
    
    }
    
    
    /**
     *
     */
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
                    case "S": ghostSpawn(j,i);// spøkelse spawn
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
        
        double scaleY = App.SIZE_Y_GRID/map.getMapData().length;
        double scaleX = App.SIZE_X_GRID/map.getMapData()[(int)dx].length;  
        
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
    

    
    
    /**
     *
     * @param x
     * @param scale
     * @return
     */
    public static double scaleX(double x, double scale) {
        double dx = 0;
        if(x!=0){
            dx=x*scale;
        }
        return dx;
    }
    
    /**
     *
     * @param y
     * @param scale
     * @return
     */
    public static double scaleY(double y, double scale) {
        double dy = 0;
        if(y!=0){
            dy=y*scale;
        }
        return dy;
    }
    
    private void drawFood(double x, double y, boolean notNormalFood) {
        
        double scaleY = App.SIZE_Y_GRID/map.getMapData().length;
        double scaleX = App.SIZE_X_GRID/map.getMapData()[(int)x].length;  
        
        double dx = scaleX(x, scaleX);
        
        double dy = scaleY(y, scaleY);
        
        var circle = 
            (notNormalFood)
                ?new Circle(dx+(scaleX/2),dy+(scaleY/2),7)
                :new Circle(dx+(scaleX/2),dy+(scaleY/2),4)
            ;
        
        System.out.println("x food: "+dx+" y food: "+dy);
        
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
    
    private void ghostSpawn(int x, double y){
        try {
            double scaleY = App.SIZE_Y_GRID/map.getMapData().length;
            double scaleX = App.SIZE_X_GRID/map.getMapData()[(int)x].length;

            double xpos = scaleX(x,scaleX), ypos = scaleX(y,scaleY);
            Ghost blue   = new Ghost(App.paths[1], xpos + 100, ypos);
            blue.setSpeed(1.0);
            Ghost green  = new Ghost(App.paths[2], xpos - 100, ypos);
            green.setSpeed(1.0);
            Ghost yellow = new Ghost(App.paths[3], xpos - 200, ypos);
            yellow.setSpeed(1.0); 
        }catch(FileNotFoundException fNfE){
            System.out.println("No Ghost coming to you, message for you:\n"+ fNfE.getMessage());
        }
    }

    private void drawGostSpawn() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void noAccsessPacMan(int j, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}