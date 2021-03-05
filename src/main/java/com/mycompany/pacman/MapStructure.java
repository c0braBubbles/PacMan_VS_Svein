/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    public static final String NEW_LINE = "NL";
    public static final String DEFULT_FILE_PATH = "src/main/java/default-map";
    private Object[] mapStructure;
    
    /**
     * default MapStructure
     */
    public MapStructure(){
        mapStructure = readFile(DEFULT_FILE_PATH);  
    }
    
    public Object[] getMapData(){
        return mapStructure;
    }
    
    public void genrateNewMap(){
        for(Object o:getMapData()){
            for(int i=0; i<((String[])o).length; i++){
                var rad = (String[])o;
                width =0;
                switch(rad[i]){
                    case "K": width=width+1;
                    break;
                    default:
                    break;
                }
                
            }
        }
        
    }
    
    
    
    
    /**
     *
     * @param path er filepath til kartfile
     * @return string[] som innerholder data struktur
     */
    protected final Object[] readFile(String path){
        ArrayList<String[]> al = new ArrayList<>();
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while(scanner.hasNext()) {
                
                if(scanner.hasNextLine()){
                    al.add(scanner.nextLine().split(","));
                }
                
            }
            
        }catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        return al.toArray();
    }
    
    @Override
    public String toString(){
       String s = "";
       for(Object o: getMapData()){
          s += Arrays.toString(((String[])o));
       }
       return s;
    }
}
