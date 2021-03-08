/*
 * 
 */
package com.mycompany.pacman;

import java.util.ArrayList;
import javafx.geometry.Point2D;

/**
 *
 * @author sveni
 */



public final class Spawn {
    private Point2D spawnPoint;
    private ArrayList<Point2D> arraySpawn;
    
    /**
     *
     */
    public Spawn() {
       setSpawnPoint(new Point2D(0,0));
       setArraySpawn(new ArrayList<>());
       arraySpawn.add(spawnPoint);
       
    }


    /**
     *
     * @return
     */
    public ArrayList<Point2D> getArraySpawn() {
        return arraySpawn;
    }

    /**
     *
     * @return
     */
    public Point2D getSpawnPoint() {
        return spawnPoint;
    }

    public final void setArraySpawn(ArrayList<Point2D> arraySpawn) {
        this.arraySpawn = arraySpawn;
    }

    /**
     *
     * @param spawnPoint
     */
    public final void setSpawnPoint(Point2D spawnPoint) {
        this.spawnPoint = spawnPoint;
    }
    
    /**
     *
     * @param x
     * @param y
     */
    public void setSpawnPoint(double x,double y){
        setSpawnPoint(new Point2D(x,y));
    }
    
    
    
    
}
