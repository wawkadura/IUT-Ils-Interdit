/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile_Interdite;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author peyrinfl
 */
    public class VueAventurier {
    private Controleur C = new Controleur();

    public VueAventurier(Controleur C) {
        this.C=C;
        

        /*///////////////////////////////////////COMMENCEMENT DE LA PARTIE////////////////////////////////////////////////////////
        for (Aventurier A : C.getJoueurs()) {
            //****************************DEBUT********************************************
            while (A.getActions() > 0 && !A.isTourTerminer()) {
                System.out.println(A.getFonction() + " " + A.getNom() + " : (" + A.getActions() + " actions restants ) ");
                System.out.println("");

                //**********************DEPLACEMENT***********************************************************************
                if (rep == 1) {
                    if (A.getFonction().equals("navigateur")) { //deplacement special navigateur
                        System.out.println("Aventuriers : ");
                        int select = 1;
                        for (Aventurier A2 : Joueurs) {
                            if (!A2.getFonction().equals(A.getFonction())) {
                                System.out.println(select + "- " + A2.getNom() + " " + A2.getTuile().getCoordonnee().afficherCoord());
                            }
                            select++;
                        }
                        System.out.print("Qui voulez vous deplacer ?: ");
                        Scanner selc = new Scanner(System.in);
                        int Av = selc.nextInt();
                        while (Av < 1 || Av > 3) {
                            System.out.print("Qui voulez vous deplacer ? (1 a" + select + ") :");
                            Av = selc.nextInt();
                        }
                        Joueurs.get(Av - 1).deplacer(grille);
                    } else { //deplacement normal
                        A.deplacer(grille);
                        controleur.traiterMessage("Deplacer");
                    }

                }
                //**********************DEPLACEMENT***********************************************************************

                //**********************ASSECHEMENT***********************************************************************
                if (rep == 2) {
                    A.assecher(grille);
                }
                //**********************ASSECHEMENT***********************************************************************

                if (rep == 3) {
                    A.setTerminer(true);
                }

                grille.AfficherGrille();
            }
            A.tourTermine();
            A.Reset();
            //****************************FIN********************************************
        }
        System.out.println("____________________________________________________________");
        System.out.println("                        FIN TOUR 1                              ");
        System.out.println("____________________________________________________________"); */
    }
}
