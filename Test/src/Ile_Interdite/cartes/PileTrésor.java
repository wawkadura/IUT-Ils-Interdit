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
    private PileInondation pile;

    
    public void piocher( Aventurier aventurier){
        Iterator itr= cartesTrésor.iterator();
        
        //while(itr.hasNext()){
        
            //int indiceauhasard = (int) (Math.random()* (cartesTrésor.size()-1)); si jamais on veut melanger les cartes 
             //aventurier.donner(cartesTrésor.get(indiceauhasard), aventurier);
        //}
        
        aventurier.addCarte(cartesTrésor.get(0));
        cartesTrésor.remove(0);
        aventurier.addCarte(cartesTrésor.get(0));
        cartesTrésor.remove(0);
        
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
