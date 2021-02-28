/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pacman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    protected Image image; 
    protected FileInputStream stream; 
    protected ImageView view;
    protected final int SIZE = 25; // alle blir like store 

    
    public Ghost(String path) throws FileNotFoundException {
        this.path = path; 
        stream = new FileInputStream(path); 
        image = new Image(stream); 
        setImageView(SIZE, SIZE);
    }
    
    
    public ImageView getImageView() {
        return view; 
    }
    
    public void setImageView(double sizeX, double sizeY) {
        view = new ImageView(getImage());
        view.setFitHeight(SIZE);
        view.setFitWidth(SIZE);
    }
    
    public Image getImage() {
        return image; 
    }
    
}
