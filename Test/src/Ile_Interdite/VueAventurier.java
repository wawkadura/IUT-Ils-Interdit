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
   
   
   
    
     public static void main(String [] args) {
        // Instanciation de la fenêtre 
         int l = 0;// ligne
        int c = 0;//colonne
        int niv = 1;//demander aux joueurs

        Grille grille = new Grille(niv);
        for (int i = 0; i < 36; i++) {// Creation de la Grille
            Coordonnees C = new Coordonnees(l, c);

            if (c == 2 && l == 0) {
                LieuDeTresor tuile = new LieuDeTresor(C, "coup");
                grille.addTuile(tuile);
            } //coup
            else if (c == 3 && l == 0) {
                LieuDeTresor tuile = new LieuDeTresor(C, "feu");
                grille.addTuile(tuile);
            }//feu
            else if (c == 0 && l == 2) {
                LieuDeTresor tuile = new LieuDeTresor(C, "coup");
                grille.addTuile(tuile);
            }//coup
            else if (c == 0 && l == 3) {
                LieuDeTresor tuile = new LieuDeTresor(C, "lion");
                grille.addTuile(tuile);
            }//lion
            else if (c == 5 && l == 2) {
                LieuDeTresor tuile = new LieuDeTresor(C, "feu");
                grille.addTuile(tuile);
            }//feu
            else if (c == 5 && l == 3) {
                LieuDeTresor tuile = new LieuDeTresor(C, "oeuf");
                grille.addTuile(tuile);
            }//oeuf
            else if (c == 2 && l == 5) {
                LieuDeTresor tuile = new LieuDeTresor(C, "lion");
                grille.addTuile(tuile);
            }//lion
            else if (c == 3 && l == 5) {
                LieuDeTresor tuile = new LieuDeTresor(C, "oeuf");
                grille.addTuile(tuile);
            }//oeuf
            else if (c == 0 && l == 0 || c == 1 && l == 0 || c == 0 && l == 1
                    || c == 4 && l == 0 || c == 5 && l == 0 || c == 5 && l == 1
                    || c == 0 && l == 4 || c == 0 && l == 5 || c == 1 && l == 5
                    || c == 4 && l == 5 || c == 5 && l == 4 || c == 5 && l == 5) {
            } //tuile heliport a faire aleatoirement
            else {

                Tuile tuile = new Tuile(C);
                tuile.setEtat(1);
                grille.addTuile(tuile);
            }
            c++;
            if (c == 6) {
                c = 0;
                l++;
            }

        }

        //parametrage ////////////////////////////////////////////////////////////////
        
        
        Coordonnees C = new Coordonnees(2, 3);
        Coordonnees C2 = new Coordonnees(3, 2);
        Coordonnees C3 = new Coordonnees(3, 5);
        Coordonnees C4 = new Coordonnees(4, 1);

        Ingenieur J1 = new Ingenieur("César", grille.getTuiles().get(C));
        Explorateur J2 = new Explorateur("Florent", grille.getTuiles().get(C2));
        Pilote J3 = new Pilote("Walid", grille.getTuiles().get(C3));
        Plongeur J4 = new Plongeur("Amine", grille.getTuiles().get(C4));
        
        ArrayList<Aventurier> Joueurs = new ArrayList<>();
        Joueurs.add(J1);
        Joueurs.add(J2);
        Joueurs.add(J3);
        Joueurs.add(J4);

        //parametrage ////////////////////////////////////////////////////////////////
        grille.AfficherGrille();
        ////////////////////////////////////////COMMENCEMENT DE LA PARTIE////////////////////////////////////////////////////////
        System.out.println("____________________________________________________________");
        System.out.println("                        TOUR 1                              ");
        System.out.println("____________________________________________________________");
        System.out.println("");
        for (Aventurier A : Joueurs) {
            //****************************DEBUT********************************************
            while (A.getActions() > 0 && !A.isTourTerminer()) {
                System.out.println(A.getFonction() + " " + A.getNom() + " : (" + A.getActions() + " actions restants ) ");
                System.out.println("");
                System.out.println("1- se Deplacer");
                if (A.getFonction().equalsIgnoreCase("navigateur")) {
                    System.out.println("1.2- Deplacer un autre joueur (spécial) ");
                }
                System.out.println("2- assecher une tuile");
                System.out.println("3- terminer le tour");
                System.out.print("Veuillez choisir une action (1/2/3): ");

                Scanner scn = new Scanner(System.in);
                double rep = scn.nextInt();

                while (rep < 1 || rep > 3) {
                    System.out.print("Veuillez choisir une action (1/2/3): ");
                    rep = scn.nextInt();
                }
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
        System.out.println("____________________________________________________________");
    }
}
