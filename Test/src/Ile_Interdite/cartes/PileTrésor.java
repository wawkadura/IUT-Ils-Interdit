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

    public PileTrésor(ArrayList<CarteTrésor> cartesTrésor){
        this.cartesTrésor = cartesTrésor;
        melanger(cartesTrésor);
    }
    public ArrayList<CarteTrésor> getCartesTrésor() {
        return cartesTrésor;
    }
    
    public void piocher(Aventurier aventurier) {
       if (!getCartesTrésor().isEmpty()) {
        aventurier.addCarte(cartesTrésor.get(cartesTrésor.size()-1));
        cartesTrésor.remove(cartesTrésor.size()-1);
       }
       else { 
        melangerLesPiles();
        piocher(aventurier);
       }             
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
    public void melangerLesPiles(){
        melanger(cartesTrésorDefaussees);
        this.cartesTrésor.addAll(cartesTrésorDefaussees);    
    }   
}
