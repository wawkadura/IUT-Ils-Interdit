/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile_Interdite.Aventuriers;

import Ile_Interdite.Aventuriers.Aventurier;
import Ile_Interdite.Tuile;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author peyrinfl
 */
public class Plongeur extends Aventurier {

    public Plongeur(String nom, Tuile tuile) {
        super(nom, tuile);
    }


    @Override
    public String getFonction() {
        return "Plongeur";
    }
}
