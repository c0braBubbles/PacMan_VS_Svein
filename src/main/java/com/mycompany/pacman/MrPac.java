/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pacman;

import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Iterator;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

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
    protected Ghost ghost; 
    protected ParallelTransition animation; 
    protected RotateTransition counterclockwise;
    protected RotateTransition clockwise;
    protected Double speed;
 
    
    /**
     * Tar inn ingen parametre. Kaller bare på set-metoder 
     * Det er oppsettet til å lage et pacman objekt
     */
    public MrPac() {
        setMrPac();
        setAnimation();
    }
 
 
    private void setAnimation() {     
        /* Gaping */
        Timeline gaping = new Timeline(); 
        gaping.setCycleCount(Timeline.INDEFINITE);
        gaping.setAutoReverse(true);
 
        KeyValue angle = new KeyValue(pacman.startAngleProperty(), 10); 
        KeyValue bow = new KeyValue(pacman.lengthProperty(), 360); 
 
        KeyFrame kf = new KeyFrame(Duration.millis(200), angle, bow);
        gaping.getKeyFrames().add(kf);
 
        /* Animasjon stuff */
        animation = new ParallelTransition(); 
        animation.getChildren().add(gaping); 
    }
 
    protected void startAnimation() {
        animation.play();
    }
 
 
    protected double getPosX() {
        return pacman.getCenterX(); 
    }
    protected double getPosY() {
        return pacman.getCenterY();
    }
 
    protected double getSpeed() {
        return speed;
    }
 
    protected void setSpeed(double speed) {
        this.speed = speed;
    }
  

    /**
     * Får pacman til å bevege seg
     * Bruker keycodes 
     */
    protected void setMovement() {
        ArrayList<String> input = new ArrayList<String>();
 
        pacman.setOnKeyPressed(e -> {
            String code = e.getCode().toString();
            input.clear();
            input.add(code);
        });
 
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double pacX = pacman.getCenterX();
                double pacY = pacman.getCenterY();
 
                for (Circle cir : App.circles) {
                    if(input.contains("LEFT")) {
                        if (canWalk("LEFT")) {
                            pacman.setCenterX(pacX - getSpeed());
                            pacman.setRotate(-180);
                        }
                        if(hitCircle(cir)) {
                            App.root.getChildren().remove(cir);
                        }
                    }
 
                    if(input.contains("RIGHT")) {
                        if (canWalk("RIGHT")) {
                            pacman.setCenterX(pacX + getSpeed());
                            pacman.setRotate(0);
                        }
                        if(hitCircle(cir)) {
                            App.root.getChildren().remove(cir);
                        }
                    }
 
                    if(input.contains("UP")) {
                        if (canWalk("UP")) {
                            pacman.setCenterY(pacY - getSpeed());
                            pacman.setRotate(-90);
                        }
                        if(hitCircle(cir)) {             
                            App.root.getChildren().remove(cir); 
                        }
                    }
 
                    if(input.contains("DOWN")) {
                        if (canWalk("DOWN")) {
                            pacman.setCenterY(pacY + getSpeed());
                            pacman.setRotate(90);
                        }
                        if(hitCircle(cir)) {
                            App.root.getChildren().remove(cir);
                        }
                    }
                }
            }
        }.start();
        pacman.requestFocus();
    }
    
    
    /**
     * Sjekker om pacman treffer sirkler
     * 
     * @param cir henter sirkler fra panelet 
     * @return returnerer true eller false
     */
    public boolean hitCircle(Circle cir) {
        double pacPosX = pacman.getCenterX() + pacman.getRadiusX(); 
        double pacPosY = pacman.getCenterY() + pacman.getRadiusY(); 
        
        double cirPosX = cir.getCenterX() + cir.getRadius(); 
        double cirPosY = cir.getCenterY() + cir.getRadius(); 
        
        if(pacman.getCenterX() > cir.getCenterX()-cir.getRadius() && pacman.getCenterX() < cir.getCenterX()+cir.getRadius()) {   
            if(pacman.getCenterY() > cir.getCenterY()-cir.getRadius() && pacman.getCenterY() < cir.getCenterY()+cir.getRadius()) {
                return true; 
            }
        }
            
        return false; 
    }
    
    /**
     * sjekker om pacman kan gå eller om det er en vegg foran
     *
     * @param code tar inn keycode
     * @return returnerer true eller false
     */
    public boolean canWalk(String code) {
        double pacX = pacman.getCenterX();
        double pacY = pacman.getCenterY();
 
        double rectx1 = App.rect.getX();
        double rectx2 = rectx1 + App.rect.getWidth();
 
        double recty1 = App.rect.getY();
        double recty2 = recty1 + App.rect.getHeight();
 
        if(code.equals("LEFT")) {
            if(pacX - pacman.getRadiusX() > 0) {
                for(Rectangle rect : App.rectangles) {
                    rectx1 = rect.getX();
                    rectx2 = rectx1 + rect.getWidth();
 
                    recty1 = rect.getY();
                    recty2 = recty1 + rect.getHeight();
 
                    if((pacY > recty1 && pacY < recty2)) {
                        if(pacX - pacman.getRadiusX() > rectx2) {
                            continue;
                        } 
                        else if (pacX - pacman.getRadiusX() < rectx1) {
                            continue;
                        }
                    } 
                    else {
                        continue;
                    }
                    return false;
                } 
                return true;
            }
        } 
        
        else if(code.equals("RIGHT")) {
            if(pacX + pacman.getRadiusX() < App.SIZE_X) {
                for(Rectangle rect : App.rectangles) {
                    rectx1 = rect.getX();
                    rectx2 = rectx1 + rect.getWidth();
 
                    recty1 = rect.getY();
                    recty2 = recty1 + rect.getHeight();
 
                    if((pacY > recty1 && pacY < recty2)) {
                        if(pacX + pacman.getRadiusX() < rectx1) {
                            continue;
                        } 
                        else if(pacX + pacman.getRadiusX() > rectx2) {
                            continue;
                        }
                    } 
                    else {
                        continue;
                    }
                    return false;
                }
                return true;
            }
        } 
        
        else if(code.equals("UP")) {
            if(pacY - pacman.getRadiusY() > 0) {
                for(Rectangle rect : App.rectangles) {
                    rectx1 = rect.getX();
                    rectx2 = rectx1 + rect.getWidth();
 
                    recty1 = rect.getY();
                    recty2 = recty1 + rect.getHeight();
 
                    if(pacX > rectx1 && pacX < rectx2) {
                        if(pacY - pacman.getRadiusY() < recty1) {
                            continue;
                        } 
                        else if(pacY - pacman.getRadiusY() > recty2) {
                            continue;
                        }
                    } 
                    else {
                        continue;
                    }
 
                    return false;
                }
 
                return true;
            }
        } 
        
        else if(code.equals("DOWN")) {
            if(pacY + pacman.getRadiusY() < App.SIZE_Y) {
                for(Rectangle rect : App.rectangles) {
                    rectx1 = rect.getX();
                    rectx2 = rectx1 + rect.getWidth();
 
                    recty1 = rect.getY();
                    recty2 = recty1 + rect.getHeight();
 
                    if(pacX > rectx1 && pacX < rectx2) {
                        if(pacY + pacman.getRadiusY() < recty1) {
                            continue;
                        } 
                        else if(pacY + pacman.getRadiusY() > recty2) {
                            continue;
                        }
                    } 
                    else {
                        continue;
                    }
                    
                    return false;
                }
                
                return true;
            }
        }
        
        return false;
    }
 
 
    private void setMrPac() {
        pacman = new Arc(50, 100, 25, 25, 15, 300); 
        pacman.setStroke(Color.BLACK);
        pacman.setFill(Color.YELLOW);
        pacman.setType(ArcType.ROUND);
    }
 
    protected Arc getMrPac() {
        return pacman; 
    }
    
}