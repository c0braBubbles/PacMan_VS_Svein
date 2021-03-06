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
    
    /**
     * Bruker Figur enum for sette lovlige spawn verdier
     * see link under for kilde til hvordan det ble laget
     * @see https://www.baeldung.com/java-enum-values
     * 
     *//*
    public enum Figure {
        PAC("A"),
        GHOST("S");
        
        private static final HashMap<String, Figure> FIG_MAP = new HashMap<>();
    
        static {
            for (Figure fig: values()) {
                FIG_MAP.put(fig.legal, fig);
            }
        }
        
        public final String legal;
        
        private Figure(String legal) {
            this.legal = legal;
        }
        
    }
    */
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