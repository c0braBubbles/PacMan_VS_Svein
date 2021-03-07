/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pacman;

import java.util.HashMap;

/**
 *
 * @author sveni
 */


/**
 * Bruker Figur enum for hente lovlige spawn verdier
 * baseert på datasettet
 * see link under for kilde til hvordan det fungerer
 * @see https://www.baeldung.com/java-enum-values
 * 
 */
public enum Asset {
    PAC("A"),
    GHOST("S");
   
    public final String legal;
    
    private Asset(String legal) {
        this.legal = legal;
    }
    
    public static Asset valueOfLegal(String legal) {
    for (Asset e : values()) {
        if (e.legal.equals(legal)) {
            return e;
        }
    }
    return null;
}
}
