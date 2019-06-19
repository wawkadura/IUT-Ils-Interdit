/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile_Interdite.Aventuriers;

import Ile_Interdite.Aventuriers.Aventurier;
import Ile_Interdite.Tuile;
import Ile_Interdite.cartes.CarteTresor;

/**
 *
 * @author peyrinfl
 */
public class Messager extends Aventurier {
    
    public Messager(String nom, Tuile tuile) {
        super(nom, tuile);
    }
    
    @Override
    public void donner(CarteTresor carte, Aventurier joueur) {
        
    }
    @Override
    public String getFonction() {
        return "messager";
    }
}
