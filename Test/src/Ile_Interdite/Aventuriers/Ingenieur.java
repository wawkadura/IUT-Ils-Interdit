/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile_Interdite.Aventuriers;

import Ile_Interdite.Aventuriers.Aventurier;
import Ile_Interdite.Grille;
import Ile_Interdite.Tuile;
import java.util.Scanner;

/**
 *
 * @author peyrinfl
 */
public class Ingenieur extends Aventurier {

    public Ingenieur(String nom, Tuile tuile) {
        super(nom, tuile);
    }

    @Override
    public String getFonction() {
        return "Ingénieur";
    }
}
