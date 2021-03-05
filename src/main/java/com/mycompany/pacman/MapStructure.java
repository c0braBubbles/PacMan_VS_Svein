/*
 * Leser in fra fil,
 * 
 * 
 */
package com.mycompany.pacman;

import java.util.Arrays;

/**
 *
 * @author sveni
 */
public class MapStructure {
    protected static final String DEFULT_FILE_PATH = "src/main/java/";
    protected static final String MAP_DATA_STRUCTURE_FILE = DEFULT_FILE_PATH +"default-map";
    protected Object[] dataObject;
    protected String[][] mapData;
    
    public MapStructure(){
        dataObject = null;
        mapData = null;
    }
    
    /**
     * default MapStructure
     * @param dataObject
     */
    
    public MapStructure(Object[] dataObject){
        this.dataObject = dataObject;
    }
    
    
    
    @Override
    public String toString(){
       String s = "";
       for(String[] sa: mapData){
          s += Arrays.toString(sa)
                .replace("[", "")
                .replace("]", "")
                +"\n";
       }
       return s;
    }
}
