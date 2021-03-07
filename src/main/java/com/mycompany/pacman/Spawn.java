/*
 * 
 */
package com.mycompany.pacman;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.geometry.Point2D;

/**
 *
 * @author sveni
 */



public class Spawn {
    private Point2D spawnPoint;
    private ArrayList<Point2D> arraySpawn;

    public Spawn() {
       setSpawnPoint(new Point2D(0,0));
       setArraySpawn(new ArrayList<>());
       arraySpawn.add(spawnPoint);
    }

    public Spawn(Point2D spawnPoint) {
        setSpawnPoint(spawnPoint);
        setArraySpawn(new ArrayList<>());
        arraySpawn.add(spawnPoint);
    }
    public Spawn(double x, double y) {
        this(new Point2D(x,y));
    }

    public ArrayList<Point2D> getArraySpawn() {
        return arraySpawn;
    }

    public Point2D getSpawnPoint() {
        return spawnPoint;
    }

    public final void setArraySpawn(ArrayList<Point2D> arraySpawn) {
        this.arraySpawn = arraySpawn;
    }

    public final void setSpawnPoint(Point2D spawnPoint) {
        this.spawnPoint = spawnPoint;
    }
    
    public void setSpawnPoint(double x,double y){
        setSpawnPoint(new Point2D(x,y));
    }
    
    
}
