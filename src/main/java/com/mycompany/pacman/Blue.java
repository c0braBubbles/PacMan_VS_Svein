/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pacman;

import java.io.FileNotFoundException;

/**
 *
 * @author Mats Engesund
 */
public class Blue extends Ghost {
    
    public Blue(String path, double xpos, double ypos) throws FileNotFoundException {
        super(path, xpos, ypos); 
    }
    
    
    /*protected void chase(MrPac pacman) {
        pacman.setMovement();
        
        new AnimationTimer() {
            @Override
            public void handle(long l) {
                double pacx = pacman.getMrPac().getCenterX(); 
                double pacy = pacman.getMrPac().getCenterY(); 
                
                double ghostx = view.getX();
                double ghosty = view.getY();
                
                double distancex = Math.abs(pacx - view.getX()); 
                double distancey = Math.abs(pacy - view.getY()); 
                
                if(distancex > distancey) {
                    if(ghostx > pacx) {
                        if(canWalk(pacman))
                            view.setX(ghostx + getSpeed());
                        try { 
                            ohhShit(pacman);
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }
                    else {
                        if(canWalk(pacman)) 
                            view.setX(ghostx + getSpeed());
                        try {
                            ohhShit(pacman);
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                else if(distancex < distancey) {
                    if(ghostx > pacy) {
                        if(canWalk(pacman))
                            view.setY(ghosty - getSpeed());
                        try { 
                            ohhShit(pacman);
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }
                    else {
                        if(canWalk(pacman))
                            view.setY(ghosty - getSpeed());
                        try {
                            ohhShit(pacman);
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }.start(); 
    }*/
    
}
