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
public class Ingenieur extends Aventurier {

    Ingenieur(String nom, Tuile tuile) {
        super(nom, tuile);
    }

    @Override
    public void assecher(Grille g) {
        int actions = getActions();
        super.assecher(g);
        if (actions > getActions()) {
            super.setActions(super.getActions() + 1);
            System.out.print("(Spécial) Voulez-vous assécher une autre tuile ?(oui/non): ");
            Scanner scn = new Scanner(System.in);

            String rep = scn.next();
            while (!rep.equalsIgnoreCase("oui") && !rep.equalsIgnoreCase("non")) {
                System.out.print("(Spécial) Voulez-vous assécher une autre tuile ?(oui/non): ");
                rep = scn.next();
            }
            if (rep.equalsIgnoreCase("oui")) {
                super.assecher(g);
            } else {
                super.setActions(super.getActions() - 1);
            }

        }
    }

    @Override
    public String getFonction() {
        return "\u001B[31m" + "Ingénieur";
    }
}
