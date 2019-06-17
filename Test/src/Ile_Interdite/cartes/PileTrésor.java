/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile_Interdite.cartes;

import Ile_Interdite.cartes.PileInondation;
import java.util.ArrayList;

/**
 *
 * @author peyrinfl
 */
public class PileTrésor {

    private ArrayList<CarteTrésor> cartesTrésor = new ArrayList<>();
    private ArrayList<CarteTrésor> cartesTrésorDefausser = new ArrayList<>();
    private PileInondation pile;
    
    public PileTrésor(ArrayList<CarteTrésor> cartesTresor) {
        this.cartesTrésor = cartesTresor;
    }
    public void Defausser(Carte carte){
        
    }
}
