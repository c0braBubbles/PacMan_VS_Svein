/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pacman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

import javafx.animation.AnimationTimer;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 *
 * @author Mats Engesund
 */
public class Ghost {
    
    protected String path; 
    protected double xpos, ypos; 
    protected double speed; 
    protected Image image; 
    protected FileInputStream stream; 
    protected ImageView view;
    protected final int SIZE = 50; // alle blir like store 

    /**
     * Dette er konstruktør til spøkelse objekt
     * får inn nødvendige variabler til å legge til bilde 
     * og plassen til spøkelsene
     * legger path inn i en stream
     * 
     * @param path filsti til bilde (png)
     * @param xpos start posisjon x-kordinat
     * @param ypos start posisjon y-kordinat
     * @throws FileNotFoundException 
     */
    public Ghost(String path, double xpos, double ypos) throws FileNotFoundException {
        this.path   = path; 
        this.xpos   = xpos; 
        this.ypos   = ypos;
        stream      = new FileInputStream(path); 
        image       = new Image(stream); 
        
        setImageView(SIZE, SIZE);
    }
    
    
    protected ImageView getImageView() {
        return view; 
    }
    
    
    /**
     * kalles på i konstruktøren for å sette bilde 
     * inn i et view og posisjon
     * 
     * @param sizeX størelse x-lengde
     * @param sizeY størelse y-lengde
     */
    protected void setImageView(double sizeX, double sizeY) {
        view = new ImageView(getImage());
        view.setFitHeight(SIZE);
        view.setFitWidth(SIZE);
        view.setX(xpos);
        view.setY(ypos);
    }
    
    
    protected void setSpeed(Double speed) {
        this.speed = speed;
    }
    protected Double getSpeed() {
        return speed; 
    }
    
    
    protected Image getImage() {
        return image; 
    }
    
    
    /**
     * kalles på i App.java for å sette igang "jakten"
     * lokaliserer pacman sin posisjon og beregner avstanden
     * og jobber rundt den informasjonen for å bevege seg
     * kaller også på metode for å sjekke om pacman har spist en energitablett
     *
     * @param pacman tar inn pacman objekt for å hente variabler
     */
    protected void chase(MrPac pacman) {
        pacman.setMovement();

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double pacPosX = pacman.getMrPac().getCenterX();
                double pacPosY = pacman.getMrPac().getCenterY();

                double ghostX = view.getX();
                double ghostY = view.getY();

                double distanceX = Math.abs(pacPosX - view.getX());
                double distanceY = Math.abs(pacPosY - view.getY());

                if(distanceX > distanceY) {
                    if(ghostX > pacPosX) {
                        view.setX(ghostX - getSpeed());
                        try {
                            ohhShit(pacman);
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    } 
                    /*if(ghostX > pacPosX) {
                        if(canWalk()) {
                            view.setX(ghostX - getSpeed());
                            try {
                                ohhShit(pacman); 
                            } catch(FileNotFoundException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }*/
                    else {
                        view.setX(ghostX + getSpeed());
                        try { 
                            ohhShit(pacman);
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }
                } 
                else {
                    if(ghostY > pacPosY) {
                        view.setY(ghostY - getSpeed());
                        try {
                            ohhShit(pacman);
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }
                    
                    else {
                        view.setY(ghostY + getSpeed());
                        try {
                            ohhShit(pacman);
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }.start();
    }
    
    
    public boolean canWalk() {
        for(Rectangle rect: App.rectangles) {
            if(view.getX() > rect.getX() - rect.getWidth() && view.getX() < rect.getX() + rect.getWidth())
                if(view.getY() > rect.getY() - rect.getHeight() && view.getY() < rect.getY() + rect.getHeight())
                    return true;
        }
        
        return false; 
    }
    
    
    /**
     * sjekker om pacman har truffet en energitablett
     * bytter så bilde på spøkelset i noen sekunder
     * sletter objektet fra panelet om det blir spist
     * endrer tilbake om det ikke er spist
     * 
     * @param pacman for å få se om pacman har truffet sirkel
     * @throws FileNotFoundException nødvendig for å legge inn bilde i view
     */
    public void ohhShit(MrPac pacman) throws FileNotFoundException {
        for(Circle cir: App.circles) {
            if(pacman.hitCircle(cir) && cir.getRadius() > 15) {
                stream = new FileInputStream(App.paths[4]); 
                image = new Image(stream);
                view.setImage(image);
                if(isEaten(pacman))
                    App.root.getChildren().remove(this.view);
                changeBack(cir);
            }
        }
    }
    
    /**
     * endrer bilde tilbake til normal etter energitabletten er gått ut
     * bruker sleeper som timer for tiden den skal være et annet bilde
     * 
     * @param cir sirklene som er energitabletter
     */
    public void changeBack(Circle cir) {
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(5000);
                } catch(InterruptedException e) {
        
                }
                return null; 
            }
        };
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent t) {
                try {
                    stream = new FileInputStream(path);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                image = new Image(stream);
                view.setImage(image);
                App.circles.remove(cir);
            }
        });
        new Thread(sleeper).start();
    }
    
    
    /**
     * sjekker om pacman har spist et spøkelse
     *
     * @param pacman finner pacman sin posisjon
     * @return true eller false om posisjonene er like
     */
    public boolean isEaten(MrPac pacman) {
        if(view.getX() > pacman.getPosX()-pacman.getMrPac().getRadiusX() && view.getX() < pacman.getPosX() + pacman.getMrPac().getRadiusX()) {
            if(view.getY() > pacman.getPosY() - pacman.getMrPac().getRadiusY() && view.getY() < pacman.getPosY() + pacman.getMrPac().getRadiusY())
                return true; 
        }
        
        return false; 
    }
    
}