/*
 * 
 */
package com.mycompany.pacman;

import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author svein
 */
public class Map extends MapStructure {
    
    public Map(){
       super();
       setMapData(dataObject);
    }
    
    public String[] getSpawnLine(int line){
        System.out.println(Arrays.toString(getMapData()[line]));
        return getMapData()[line];
    }
    
    
    
    public boolean isLegalSpawn(String data, String type) {
        throw new UnsupportedOperationException("Not supported yet.");
        /*boolean b = false;
        for(int i=0; i<Figure.values().length; i++){
            System.out.print(Figure.values()[i].valueOfLegal(data).name());
            if(
                Figure.values()[i].valueOfLegal(data)
                    .toString().equals(type)
            )
                b = true;
        }
        return b;
        */
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