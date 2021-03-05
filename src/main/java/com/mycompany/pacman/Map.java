/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pacman;

import javafx.scene.paint.Color;

/**
 *
 * @author milcunicorn
 */
public class Map {    
    protected Color backgroundColor;
    protected Color mainColor;

    public Map() {
        backgroundColor = Color.BLACK;
        mainColor = Color.ALICEBLUE;
    }
    
    public Map(Color backgroundColor, Color mainColor) {
        this.backgroundColor = backgroundColor;
        this.mainColor = mainColor;
    }
    
}
