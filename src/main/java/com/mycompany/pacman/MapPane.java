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
    
    public void drawMap() {
        for(String[] sa: map.getMapData()){
            for(int i=0;i<sa.length; i++){
                switch(sa[i]){
                    case "K": // kant
                        break;
                    case "0": // kant inne i kartet
                        break;
                    case "A": // mat for pacman
                        break;
                    case "C": // "chase ability" for pacman
                        break;
                    case "Y": // ingen mat
                        break;
                    case "X": // tomt
                        break;
                    case "S": // spøkelse spawn
                        break;
                    case "U": // dør spøkelse spawn 
                        break;
                    case "B": // spøkelse bur
                    default:
                }
            }
        }
    }

    public MapPane() {
        backgroundColor = Color.BLACK;
        mainColor = Color.ALICEBLUE;
    }
    
    public MapPane(Color backgroundColor, Color mainColor) {
        this.backgroundColor = backgroundColor;
        this.mainColor = mainColor;
    }
    
}