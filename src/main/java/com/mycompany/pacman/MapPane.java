/*
 * 
 */
package com.mycompany.pacman;

/**
 *
 * @author svein
 */
public class MapPane extends Map{
    MapStructure ms;
    
    public MapPane(){
       ms = new MapStructure();
    }
    
    public void generateMap() {
        for(String[] sa: ms.getMapData()){
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
}
