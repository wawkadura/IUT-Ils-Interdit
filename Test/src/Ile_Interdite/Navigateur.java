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
        A.Reset();
        System.out.print("voulez vous le deplacer encore une fois ? (oui/non)");
        Scanner scn = new Scanner(System.in);

        String rep = scn.next();
        while (!rep.equalsIgnoreCase("oui") && !rep.equalsIgnoreCase("non")) {
            System.out.print("voulez vous le deplacer encore une fois ? (oui/non)");
             rep = scn.next();
        }
        if (rep.equalsIgnoreCase("oui")) {
            A.deplacer(g);
            A.Reset();
        }
        else {}
    }
    @Override
    public String getFonction() {
        return "\u001B[33m"+"navigateur";
    }
}
