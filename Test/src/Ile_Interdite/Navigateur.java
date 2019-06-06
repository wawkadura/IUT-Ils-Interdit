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

    @Override
    public void faireDeplacer(Grille g, Aventurier A) {

        A.deplacer(g);
        System.out.println(A.getActions());
        if (A.getActions() < 3) {
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
                super.setActions(super.getActions() - 1);
            } else {
                super.setActions(super.getActions() - 1);
            }
        }

    }

    @Override
    public String getFonction() {
        return "\u001B[33m" + "navigateur";
    }
}
