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
public class Navigateur extends Aventurier {

    public Navigateur(String nom, Tuile tuile) {
        super(nom, tuile);
    }

    @Override
    public void faireDeplacer(Grille g, Aventurier A) {

        A.deplacer(g);
        if (A.getActions() < 3) {
            A.Reset();
            System.out.print("Voulez-vous le déplacer encore une fois ? (oui/non)");
            Scanner scn = new Scanner(System.in);

            String rep = scn.next();
            while (!rep.equalsIgnoreCase("oui") && !rep.equalsIgnoreCase("non")) {
                System.out.print("Voulez-vous le déplacer encore une fois ? (oui/non)");
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
        return "\u001B[33m" + "Navigateur";
    }
}
