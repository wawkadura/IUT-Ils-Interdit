/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile_Interdite.cartes;

import Ile_Interdite.Aventuriers.Aventurier;
import Ile_Interdite.Grille;

/**
 *
 * @author peyrinfl
 */
public abstract class CarteTresor extends Carte {
    private String fonction;
    
    public String getFonction() {
        return fonction;
    }
    public  void action(Grille g , Aventurier a){}
}
