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
    
    /*
        Dette er superklassen til spøkelsene 
        Hvert enkelt spørelse (rød, rosa, cyan og oransje) får sin 
        egen subklasse av denne. Men hvilke felles egenskaper har disse: 
        * path, filestream, image, imageView, 
        * størelse
        
        Vi har nå rød, oransje/gul, grønn og blå. ALLE ER PÅ PLASS
    */
    
    protected String path; 
    protected double xpos, ypos; 
    protected double speed; 
    protected Image image; 
    protected FileInputStream stream; 
    protected ImageView view;
    protected final int SIZE = 50; // alle blir like store 

    
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
                    /*if(ghostX > pacPosX) {
                        view.setX(ghostX - getSpeed());
                        try {
                            ohhShit(pacman);
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    } */
                    if(ghostX > pacPosX) {
                        if(canWalk(pacman)) {
                            view.setX(ghostX - getSpeed());
                            try {
                                ohhShit(pacman); 
                            } catch(FileNotFoundException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
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
    
    
    public boolean canWalk(MrPac pacman) {
        double posX = view.getX(); 
        double posY = view.getY();
        
        double rectx1 = App.rect.getX(); 
        double rectx2 = rectx1 + App.rect.getWidth();
        
        double recty1 = App.rect.getY(); 
        double recty2 = App.rect.getX(); 
        
        double distanceX = Math.abs(pacman.getMrPac().getCenterX() - view.getX());
        double distanceY = Math.abs(pacman.getMrPac().getCenterY() - view.getY());
        
        if(distanceX > distanceY) {
            if(posX - view.getFitWidth() > 0) {
                for(Rectangle rect: App.rectangles) {
                    rectx1 = rect.getX(); 
                    rectx2 = rectx1 + rect.getWidth(); 
                    
                    recty1 = rect.getY(); 
                    recty2 = recty1 + rect.getHeight();
                    
                    if((posY > recty1 && posY < recty2)) {
                        if(posX - view.getFitWidth() > rectx2)
                            continue; 
                        else if(posX - view.getFitWidth() < rectx1)
                            continue; 
                    }
                    else 
                        continue; 
                    return false; 
                }
                return true; 
            }
        }
        
        return false; 
    }
    
    
    /**
     * metode for når pacman spiser energitabletter
     * får spøkelsene til å bli "skremt"
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
    
    
    public boolean isEaten(MrPac pacman) {
        if(view.getX() > pacman.getPosX()-pacman.getMrPac().getRadiusX() && view.getX() < pacman.getPosX() + pacman.getMrPac().getRadiusX()) 
            if(view.getY() > pacman.getPosY() - pacman.getMrPac().getRadiusY() && view.getY() < pacman.getPosY() + pacman.getMrPac().getRadiusY())
                return true; 
        
        return false; 
    }
    
}