/*
 * 
 */
package com.mycompany.pacman;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author svein
 *
 */
public class MapPane extends Pane {     
    private Color backgroundColor;
    private Color mainColor;
    private Map map;
    

    public MapPane() {
        backgroundColor = Color.BLACK;
        mainColor = Color.ALICEBLUE;
    }
    
    public MapPane(Color backgroundColor, Color mainColor) {
        this.backgroundColor = backgroundColor;
        this.mainColor = mainColor;
    }
    
}