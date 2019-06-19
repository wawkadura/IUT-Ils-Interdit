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
public class PileTresor {
    private ArrayList<CarteTresor> cartesTrésor = new ArrayList<>();
    private ArrayList<CarteTresor> cartesTrésorDefaussees = new ArrayList<>();
    /**
     * @return the cartesTrésor
     */
    public PileTresor(ArrayList<CarteTresor> cartesTrésor){
        this.cartesTrésor = cartesTrésor;
        melanger(cartesTrésor);
    }
    public ArrayList<CarteTresor> getCartesTrésor() {
        return cartesTrésor;
    }
    
    public void piocher(Aventurier aventurier){
       if (!cartesTrésor.isEmpty()) {
        aventurier.addCarte(cartesTrésor.get(cartesTrésor.size()-1));
        cartesTrésor.remove(cartesTrésor.size()-1);
       }
       else { 
        melangerLesPiles();
        piocher(aventurier);}             
    }
    
    public void Defausser(CarteTresor carte, Aventurier aventurier) {
        aventurier.getCartesEnMain().remove(carte);
        this.getCartesTrésorDefaussees().add(carte);
    }

    public ArrayList<CarteTresor> getCartesTrésorDefaussees() {
        return cartesTrésorDefaussees;
    }
    
    public void melanger(ArrayList<CarteTresor> cartesAMelanger) {
        Collections.shuffle(cartesAMelanger);
    }
    public void melangerLesPiles(){
        melanger(cartesTrésorDefaussees);
        this.cartesTrésor.addAll(cartesTrésorDefaussees);
        cartesTrésorDefaussees.clear();
        
    }
    
    
    
}
