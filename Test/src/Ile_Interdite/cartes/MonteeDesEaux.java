package Ile_Interdite.cartes;

import Ile_Interdite.Aventuriers.Aventurier;
import Ile_Interdite.Grille;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author benmansa
 */
public class MonteeDesEaux extends CarteTresor {

    
    private String fonction = "Montée des Eaux";

    @Override
    public String getFonction() {
        return fonction;
    }
    
    

    @Override
    public void action(Grille g, Aventurier a) {
        g.setNivEau(g.getNivEau() + 1);
    }
}
