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
public class CarteDeTresor extends CarteTresor {
    private String typeTresor;
    private String fonction ; 
    
    public CarteDeTresor(String type){
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
    
}
