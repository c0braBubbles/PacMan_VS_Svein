/*
 * 
 */
package com.mycompany.pacman;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author svein
 */
public class Map extends MapStructure {
    
    private static ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
    private static ArrayList<Circle> circles = new ArrayList<Circle>();
    private static ArrayList<Circle> bigCircles = new ArrayList<Circle>();
    
    
    public Map(){
       super();
       setMapData(dataObject);
    }
    
    public String[] getSpawnLine(int line){
        System.out.println(Arrays.toString(getMapData()[line]));
        return getMapData()[line];
    }

    
    /**
     * Legger String[] fra dataObject super
     * in i String[][] mapData super
     * 
     * @param oa er Object[] med String[] som objekter
     */
    public final void setMapData(Object[] oa){
        mapData = new String
            [oa.length]
            [((String[])oa[0]).length];
        for(int i=0; i<oa.length; i++){
            mapData[i] = (String[])oa[i];
        }
    }
    

    /**
     * 
     * Liste over pacmat
     * som bare gir deg litt poeng
     * 
     * @return sirkel liste
     */
    public static ArrayList<Circle> getCircles() {
        return circles;
    }

    /**
     * Liste over pac ability mat
     * 
     * @return sirkel liste
     */
    public static ArrayList<Circle> getBigCircles() {
        return bigCircles;
    }

    /**
     * 
     * Liste over
     * blokkene som kartet er bygget opp av
     * 
     * @return rektangel liste
     */
    public static ArrayList<Rectangle> getRectangles() {
        return rectangles;
    }

    /**
     * 
     * Liste over pac ability mat
     * 
     * @param bigCircles
     */
    public static void setBigCircles(ArrayList<Circle> bigCircles) {
        Map.bigCircles = bigCircles;
    }

    /**
     * 
     * Liste over pacmat
     * som bare gir deg litt poeng
     * 
     * @param circles
     */
    public static void setCircles(ArrayList<Circle> circles) {
        Map.circles = circles;
    }

    /**
     * 
     * Liste over
     * blokkene som kartet er bygget opp av
     * 
     * @param rectangles
     */
    public static void setRectangles(ArrayList<Rectangle> rectangles) {
        Map.rectangles = rectangles;
    }
    
    /**
     *
     * @return
     */
    public String[][] getMapData(){
        return mapData;
    }
    
    /**
     *
     * @throws NoSuchMethodException
     */
    void genrateNewMap() throws NoSuchMethodException {
      throw new NoSuchMethodException("No methode");
    }
    
    
    static boolean addCircle(Circle circle) {
        return circles.add(circle);
    }

    static boolean addBigCircle(Circle circle) {
        return bigCircles.add(circle);
    }

    static boolean addRectangle(Rectangle rect) {
        return rectangles.add(rect);
    }
    
}