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
public class SacDeSable extends CarteTrésor {
    private String fonction ="Sac de Sable"; 

    @Override
    public String getFonction() {
        return fonction;
    }

    @Override
    public void action(Grille g, Aventurier a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
