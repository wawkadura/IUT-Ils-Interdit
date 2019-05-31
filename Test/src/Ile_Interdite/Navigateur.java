/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile_Interdite;

import java.util.Scanner;

/**
 *
 * @author peyrinfl
 */
public class Navigateur extends Aventurier {

    Navigateur(String nom, Tuile tuile) {
        super(nom, tuile);
    }

    public void faireDeplacer(Grille g, Aventurier A) {
        A.deplacer(g);
        A.tourTermine();
        System.out.println("voulez vous le deplacer encore une fois ? (oui/non)");
        Scanner scn = new Scanner(System.in);

        String rep = scn.next();
        while (!rep.equalsIgnoreCase("oui") && !rep.equalsIgnoreCase("non")) {
            System.out.println("voulez vous le deplacer encore une fois ? (oui/non)");
        }
        if (rep.equalsIgnoreCase("oui")) {
            A.deplacer(g);
            A.tourTermine();
        }
        else {}
    }
}
