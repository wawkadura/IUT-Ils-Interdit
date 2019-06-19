/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile_Interdite.cartes;

/**
 *
 * @author peyrinfl
 */
import Ile_Interdite.Aventuriers.Aventurier;
import Ile_Interdite.Grille;
import java.util.ArrayList;
import java.util.Collections;

public class PileInondation {

    private ArrayList<CarteInondation> cartesInondation = new ArrayList<>();
    private ArrayList<CarteInondation> cartesInondDefaussees = new ArrayList<>();

    public PileInondation(ArrayList<CarteInondation> cartesInondation) {
        this.cartesInondation = cartesInondation;
    }

    public void piocher(Grille g) {
        if (!cartesInondation.isEmpty()) {
            CarteInondation ci = cartesInondation.get(cartesInondation.size() - 1);
            cartesInondation.remove(cartesInondation.size() - 1);
            cartesInondDefaussees.add(ci);
            ci.inonder(g);
        } else {
            melangerLesPiles();            // traitement de la pile vide
            piocher(g);
        }

    }

    public void Defausser(CarteInondation carteInondation, Aventurier aventurier) {
        this.getCartesInondation().remove(carteInondation);
        this.getCartesInondDefaussees().add(carteInondation);
    }

    public void melanger(ArrayList<CarteInondation> cartesAMelanger) {
        Collections.shuffle(cartesAMelanger);
    }

    public ArrayList<CarteInondation> getCartesInondDefaussees() {
        return cartesInondDefaussees;
    }

    public ArrayList<CarteInondation> getCartesInondation() {
        return cartesInondation;
    }
    
    public CarteInondation getCarteDuDessus() {
        return getCartesInondation().get(0);
    }
    
    
    
    

    public void melangerLesPiles() {
        melanger(cartesInondDefaussees);
        this.cartesInondation.addAll(cartesInondDefaussees);

    }

}
