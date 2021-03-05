/*
 * Leser in fra fil,
 * 
 * 
 */
package com.mycompany.pacman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author sveni
 */
public class MapStructure {
    protected static final String DEFULT_FILE_PATH = "src/main/java/";
    protected static final String MAP_DATA_STRUCTURE_FILE = DEFULT_FILE_PATH +"default-map";
    protected Object[] dataObject;
    private String[][] mapData;
    
    /**
     * default MapStructure
     */
    public MapStructure(){
        setMapData(dataObject);
    }
    
    /**
     * Legger String[] fra dataObject
     * in i String[][] mapData
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
    
    /**
     * 
     * Leser datastruktur fra fil som string
     * Datastrukturen blir returnert som Object[],
     * der objektene er String[]
     * 
     * @param path er filepath til standard kartfile
     *
     */
    public void readFile(String path){
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
