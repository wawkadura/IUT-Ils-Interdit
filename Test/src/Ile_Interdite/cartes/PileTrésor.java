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
    /**
     * @return the cartesTrésor
     */
    public  PileTrésor(ArrayList<CarteTrésor> cartesTrésor){
        
    }
    public ArrayList<CarteTrésor> getCartesTrésor() {
        return cartesTrésor;
    }

    
    public void piocher( Aventurier aventurier){
        aventurier.addCarte(cartesTrésor.get(0));
        cartesTrésor.remove(0);
        aventurier.addCarte(cartesTrésor.get(0));
        cartesTrésor.remove(0);
        
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
