/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pacman;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.util.Duration;

/**
 *
 * @author mjoen
 */
public class MrPac {
    
    /*
        Dette er klassen til selve Pac-Man figuren. Vet ikke helt om det skal 
        være et bibliotek eller objekt. Vanskelig å plasere et objekt i javafx
        da det kun finnes en størelse på pacman. Kan prøve å lage en getmetode 
        i konstruktøren
    */
    
    
    protected Arc pacman; 
    protected Timeline gaping; 
    protected ParallelTransition animation; 
    
    
    public MrPac() {
        setMrPac();
        setGaping(); 
        setAnimation(); 
    }
    
    
    public void setMrPac() {
        pacman = new Arc(50, 100, 25, 25, 15, 300); 
        pacman.setStroke(Color.BLACK);
        pacman.setFill(Color.YELLOW);
        pacman.setType(ArcType.ROUND);
    }
    
    public Arc getMrPac() {
        return pacman; 
    }
    
    
    public void setGaping() {
        gaping = new Timeline(); 
        gaping.setCycleCount(Timeline.INDEFINITE);
        gaping.setAutoReverse(true);
        KeyValue vinkel = new KeyValue(pacman.startAngleProperty(), 11); 
        KeyValue bue = new KeyValue(pacman.lengthProperty(), 360); 
        KeyFrame kf = new KeyFrame(Duration.millis(300), vinkel, bue); 
        gaping.getKeyFrames().add(kf); 
    }
    
    public Timeline getGaping() {
        return gaping; 
    }
    
    
    public void setAnimation() {
        animation = new ParallelTransition(); 
        animation.getChildren().add(gaping); 
    }
    
    public void startAnimation() {
        animation.play();
    }
    
}
