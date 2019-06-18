/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile_Interdite.cartes;

import Ile_Interdite.Aventuriers.Aventurier;
import Ile_Interdite.cartes.PileInondation;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author peyrinfl
 */
public class PileTrésor {
    private ArrayList<CarteTrésor> cartesTrésor = new ArrayList<>();
    private ArrayList<CarteTrésor> cartesTrésorDefausser = new ArrayList<>();
    private PileInondation pile;
    /**
     * @return the cartesTrésor
     */
    public ArrayList<CarteTrésor> getCartesTrésor() {
        return cartesTrésor;
    }

    /**
     * @param cartesTrésor the cartesTrésor to set
     */
    public void setCartesTrésor(ArrayList<CarteTrésor> cartesTrésor) {
        this.cartesTrésor = cartesTrésor;
    }

    /**
     * @return the cartesTrésorDefausser
     */
    public ArrayList<CarteTrésor> getCartesTrésorDefausser() {
        return cartesTrésorDefausser;
    }

    /**
     * @param cartesTrésorDefausser the cartesTrésorDefausser to set
     */
    public void setCartesTrésorDefausser(ArrayList<CarteTrésor> cartesTrésorDefausser) {
        this.cartesTrésorDefausser = cartesTrésorDefausser;
    }

    /**
     * @return the pile
     */
    public PileInondation getPile() {
        return pile;
    }

    /**
     * @param pile the pile to set
     */
    public void setPile(PileInondation pile) {
        this.pile = pile;
    }
    
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
    
    //public PileTrésor(ArrayList<CarteTrésor> cartesTresor) {
        //this.cartesTrésor = cartesTresor;
    //}
    //public void Defausser(Carte carte){
        
    //}
}
