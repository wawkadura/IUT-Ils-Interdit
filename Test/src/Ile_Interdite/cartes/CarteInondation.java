/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile_Interdite.cartes;

import Ile_Interdite.Coordonnees;
import Ile_Interdite.Grille;
import Ile_Interdite.Tuile;

/**
 *
 * @author peyrinfl
 */
public class CarteInondation {
    private Coordonnees coordonnees ; 
    public CarteInondation (Coordonnees C){
        this.coordonnees=C;
    }

    public Coordonnees getCoordonnees() {
        return coordonnees;
    }
    
    public void inonder(Grille g) {
       for (Tuile T : g.getTuiles().values()){
           if (T.getCoordonnee()==getCoordonnees() && !T.getEtat().equalsIgnoreCase("Manquante")){
               T.setEtat(1);
           }
       }
    }
    
}
