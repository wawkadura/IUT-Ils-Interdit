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
public class Tresor extends CarteTrésor {
    private String typeTresor;
    private String fonction ; 
    
    public Tresor(String type){
        this.typeTresor=type;
        this.fonction=type;
    }
    @Override
    public String getFonction() {
        return fonction;
    }
    public String getType() {
        return typeTresor;
    }

    @Override
    public void action(Grille g, Aventurier a) {
    }
    
}
