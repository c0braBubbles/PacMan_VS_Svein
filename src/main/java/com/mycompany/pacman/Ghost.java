/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pacman;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.animation.AnimationTimer;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

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
    protected final int SIZE = 20; // alle blir like store 

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
                        if(canWalk(pacman)) {
                            view.setX(ghostX - getSpeed());
                        }
                        try {   
                            ohhShit(pacman);
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    } 
                    else {
                        if(canWalk(pacman)) {
                        view.setX(ghostX + getSpeed());
                        }
                        try { 
                            ohhShit(pacman);
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }
                } 
                else if(distanceX < distanceY) {
                    if(ghostY > pacPosY) {
                        if(canWalk(pacman)) {
                        view.setY(ghostY - getSpeed());
                        }
                        try {
                            ohhShit(pacman);
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }
                    else {
                        if(canWalk(pacman)) {
                        view.setY(ghostY + getSpeed());
                        }
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
    
    
    /**
     * Kontraiksjons-metode for å se om spøkelsene kan gå til venstre, høyre, 
     * opp eller ned. Ser om det er en vegg i nærheten når den går en retning.
     *
     * @param pacman for å hente posisjon og lengde på arc-en
     * @return returnerer alltid false hvis ikke tilfellene treffer inn
     */
    public boolean canWalk(MrPac pacman) {
        double pacx = pacman.getMrPac().getCenterX(); 
        double pacy = pacman.getMrPac().getCenterY(); 
        
        double distanceX = Math.abs(pacx - view.getX());
        double distanceY = Math.abs(pacy - view.getY());
        
        double rectx1; 
        double rectx2; 
        
        double recty1; 
        double recty2; 
        
        double ghostx = view.getX();
        double ghosty = view.getY();
        
        if(distanceX > distanceY) {
        
            if(ghostx > pacx) {
            if(ghostx - view.getFitWidth() > 0) {
                for(Rectangle rect: App.rectangles) {
                    rectx1 = rect.getX();
                    rectx2 = rectx1 - rect.getWidth(); 
                    
                    recty1 = rect.getY();
                    recty2 = recty1 - rect.getHeight(); 
                    
                    if((ghosty > recty1 && ghosty < recty2)) {
                        if(ghostx - view.getFitWidth() > rectx2)
                            continue; 
                        else if(ghostx - view.getFitWidth() < rectx1)
                            continue; 
                    }
                    else 
                        continue; 
                    return false; 
                }
                return true;
            }
        }
        
        else if(ghostx < pacx) {
            if(ghostx + view.getFitWidth() < App.SIZE_X) {
                for(Rectangle rect: App.rectangles) {
                    rectx1 = rect.getX(); 
                    rectx2 = rectx1 + rect.getWidth(); 
                    
                    recty1 = rect.getY(); 
                    recty2 = recty1 + rect.getHeight(); 
                    
                    if(ghostx > recty1 && ghostx < recty2) {
                        if(ghostx + view.getFitWidth() < rectx1) 
                            continue; 
                        else if(ghostx + view.getFitWidth() > rectx2)
                            continue; 
                    }
                    else 
                        continue; 
                    return false; 
                }
                return true; 
            }
        }
    }
        
        else {
        
        if(ghosty > pacy) {
            if(ghosty - view.getFitHeight() > 0) {
                for(Rectangle rect: App.rectangles) {
                    rectx1 = rect.getX(); 
                    rectx1 = rectx1 + rect.getWidth(); 
                    
                    recty1 = rect.getY(); 
                    recty2 = recty1 + rect.getHeight(); 
                    
                    if(ghostx > rectx1 && ghostx < rectx1) {
                        if(ghosty - view.getFitHeight() < recty1)
                            continue; 
                        else if(ghosty - view.getFitHeight() > recty2)
                            continue; 
                    }
                    else 
                        continue; 
                    return false; 
                }
                return true; 
            }
        }
        
        else if(ghosty < pacy) {
            if(ghosty + view.getFitHeight() < App.SIZE_Y) {
                for(Rectangle rect: App.rectangles) {
                    rectx1 = rect.getX(); 
                    rectx2 = rectx1 + rect.getWidth(); 
                    
                    recty1 = rect.getY(); 
                    recty2 = recty1 + rect.getHeight(); 
                    
                    if(ghostx > rectx1 && ghostx < rectx2) {
                        if(ghosty + view.getFitHeight() < recty1) 
                            continue; 
                        else if(ghosty + view.getFitHeight() > recty2)
                            continue; 
                    }
                    else 
                        continue; 
                    return false; 
                }
                return true; 
            }
        }
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
            if(pacman.hitCircle(cir) && cir.getRadius() > 5) {
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