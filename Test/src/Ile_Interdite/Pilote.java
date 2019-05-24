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
public class Pilote extends Aventurier {
    
    private boolean utilise;
    
    Pilote(String nom, Tuile tuile) {
        super(nom, tuile);
        setUtilise(false);
    }

    public boolean isUtilise() {
        return utilise;
    }

    public void setUtilise(boolean utilise) {
        this.utilise = utilise;
    }
    
    
    @Override   
    public void deplacer(int x, int y) {
        
    }
    
    @Override
    public void tourTermine() {
        setUtilise(false);
    }
}
