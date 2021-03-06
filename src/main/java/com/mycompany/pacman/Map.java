/*
 * 
 */
package com.mycompany.pacman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author svein
 */
public class Map extends MapStructure {
    
    public Map(){
       super();
       setMapData(dataObject);
    }
    
    /**
     * Legger String[] fra dataObject super
     * in i String[][] mapData super
     * 
     * @param oa er Object[] med String[] som objekter
     */
    protected final void setMapData(Object[] oa){
        mapData = new String
            [oa.length]
            [((String[])oa[0]).length];
        for(int i=0; i<oa.length; i++){
            mapData[i] = (String[])oa[i];
        }
    }
    
    public String[][] getMapData(){
        return mapData;
    }
    
    public void genrateNewMap() throws NoSuchMethodException {
      throw new NoSuchMethodException("No methode");
    }
    
}
