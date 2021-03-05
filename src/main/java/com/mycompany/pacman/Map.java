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
       setMapData(dataObject);
    }
    
    /**
     * 
     * Leser datastruktur fra fil som string
     * Datastrukturen blir returnert som Object[],
     * der objektene er String[]
     * 
     * @param path er filepath til standard kartfile
     *
     */
    protected void readFile(String path){
        ArrayList<String[]> al = new ArrayList<>();
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while(scanner.hasNext()) {
                if(scanner.hasNextLine()){
                    // lager liste av hvær linje i datastruktren fra fil
                    al.add(scanner.nextLine().split(","));
                }
                
            }
            
        }catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        dataObject = al.toArray();
    }
    
    /**
     * Legger String[] fra dataObject super
     * in i String[][] mapData super
     * 
     * @param oa er Object[] med String[] som objekter
     */
    protected final void setMapData(Object[] oa){
        readFile(MAP_DATA_STRUCTURE_FILE);
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
