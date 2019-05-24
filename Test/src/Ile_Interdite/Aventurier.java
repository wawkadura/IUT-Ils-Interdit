/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile_Interdite;

/**
 *
 * @author peyrinfl
 */
        
        
import java.util.ArrayList;
        
public abstract class Aventurier {
    private String nom;
    private int actions= 3;
    private Tuile tuile;
    private ArrayList<CarteTrésor> cartesEnMain = new ArrayList<>();
    
    Aventurier(String nom, Tuile tuile) {
        this.setNom(nom);
        this.setTuile(tuile);
    }

    public String getNom() {
        return nom;
    }

    public int getActions() {
        return actions;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setActions(int actions) {
        this.actions = actions;
    }
    
    public void deplacer(int x, int y) {
        
    }
    
    public void assecher(Tuile tuile) {
        
    }
    
    public void donner(CarteTrésor carte, Aventurier joueur) {
        
    }

    public void setTuile(Tuile tuile) {
        this.tuile = tuile;
    }
    
    
    public void gagnerTresor(Tuile tuile) {
        
    }
    
    public void tourTermine() {
        
    }
    
}
