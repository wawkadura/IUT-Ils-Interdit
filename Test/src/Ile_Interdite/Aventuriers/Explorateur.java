/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile_Interdite.Aventuriers;

import Ile_Interdite.Tuile;

/**
 *
 * @author peyrinfl
 */
public class Explorateur extends Aventurier {
    
    public Explorateur(String nom, Tuile tuile) {
        super(nom, tuile);
    }
    @Override
    public String getFonction() {
        return "Explorateur";
    }
} 
