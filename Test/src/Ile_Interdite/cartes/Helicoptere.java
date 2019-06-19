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
 * @author wawve
 */
public class Helicoptere extends CarteTresor {
   
    private String fonction ="Helicoptere"; 

    @Override
    public String getFonction() {
        return fonction;
    }

    @Override
    public void action(Grille g, Aventurier a) {
        
    }

    
}
