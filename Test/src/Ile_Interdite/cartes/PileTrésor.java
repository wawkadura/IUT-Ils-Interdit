/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile_Interdite.cartes;

import Ile_Interdite.Aventuriers.Aventurier;
import Ile_Interdite.cartes.PileInondation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 *
 * @author peyrinfl
 */
public class PileTrésor {
    private ArrayList<CarteTrésor> cartesTrésor = new ArrayList<>();
    private ArrayList<CarteTrésor> cartesTrésorDefaussees = new ArrayList<>();
    
    public void piocher( Aventurier aventurier) {
        if(aventurier.getCartesEnMain().size() <= 5) {
            aventurier.addCarte(cartesTrésor.get(0));
            this.getCartesTrésor().remove(0);
        }
    }
    
    public ArrayList<CarteTrésor> getCartesTrésor() {
        return cartesTrésor;
    }
    public void Defausser(CarteTrésor carte, Aventurier aventurier) {
        aventurier.getCartesEnMain().remove(carte);
        this.getCartesTrésorDefaussees().add(carte);
    }

    public ArrayList<CarteTrésor> getCartesTrésorDefaussees() {
        return cartesTrésorDefaussees;
    }
    
    public void melanger(ArrayList<CarteTrésor> cartesAMelanger) {
        Collections.shuffle(cartesAMelanger);
    }
    
}
