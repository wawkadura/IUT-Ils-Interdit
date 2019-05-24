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
        
import java.util.HashMap;     
        
public class Grille {

    
    
    private int nivEau;
    private HashMap<Coordonnees, Tuile >  tuiles = new HashMap<Coordonnees, Tuile>();
    public Grille (int niv){
        this.nivEau=niv;
    }
    
    public void addTuile(Tuile tuile) {
     this.tuiles.put(tuile.getCoordonnee(), tuile);
    }
}
