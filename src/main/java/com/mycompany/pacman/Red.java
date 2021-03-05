/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pacman;

import java.io.FileNotFoundException;
import javafx.animation.PathTransition;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import javafx.util.Duration;

/**
 *
 * @author mjoen
 */
public class Red extends Ghost {
    
    /*
        Kan være jeg surrer, men mener det er rød som er hard på gassen når det
        kommer til å jage pacman. Gidder ikke sjekke nå, det er bestemt.
    */
    
    
    public Red(String path, double xpos, double ypos) throws FileNotFoundException {
        super(path, xpos, ypos); 
    }
}
