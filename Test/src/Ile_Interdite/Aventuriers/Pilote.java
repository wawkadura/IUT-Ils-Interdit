/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile_Interdite.Aventuriers;

import Ile_Interdite.Aventuriers.Aventurier;
import Ile_Interdite.Tuile;

/**
 *
 * @author peyrinfl
 */
public class Pilote extends Aventurier {

    private boolean utilise;

    public Pilote(String nom, Tuile tuile) {
        super(nom, tuile);
        setUtilise(false);
    }

    @Override
    public boolean CompetanceUtiliser() {
        return utilise;
    }

    @Override
    public void setUtilise(boolean utilise) {
        this.utilise = utilise;
    }

    @Override
    public String getFonction() {
        return "Pilote";
    }
}
