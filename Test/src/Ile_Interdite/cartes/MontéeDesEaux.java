package Ile_Interdite.cartes;


import Ile_Interdite.CarteTrésor;
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
public class MontéeDesEaux extends CarteTrésor {
  private int nb;
    public void action(Grille grille,PileInondation pileInondation){
        grille.setNivEau(grille.getNivEau()+1);
        pileInondation.Defausser();
        
        
        
        
    }
}
