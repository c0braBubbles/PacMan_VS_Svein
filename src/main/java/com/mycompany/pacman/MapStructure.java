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
    protected String[][] mapData;
    
    
    /**
     * default MapStructure
     * 
     */
    public MapStructure(){
        readFile(MAP_DATA_STRUCTURE_FILE);
    }
    
    /**
     *
     * @param dataObject
     */
    public MapStructure(Object[] dataObject){
        this.dataObject = dataObject;
    }
    
    /**
     * 
     * Leser datastruktur fra fil som string
     * Datastrukturen blir lagret som Object[],
     * der objektene er String[]
     * og som String[][]
     * 
     * @param path er filepath til kartfil
     *
     */
    protected final void readFile(String path){
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
     * 
     * Data rett fra fil i objekt liste form
     * 
     * @return Objekt liste
     */
    public Object[] getDataObject() {
        return dataObject;
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
