/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile_Interdite;

/**
 *
 * @author peyrinfl
 */
        
import java.util.ArrayList;
        
public class Tuile {
    
    private String nom;
    private Coordonnees C ;
    private ArrayList<Aventurier> aventuriers = new ArrayList<>();
    
    public Tuile (Coordonnees C){
        this.C=C;
    }

    public Coordonnees getCoordonnee() {
        return C;
    }
    
}
