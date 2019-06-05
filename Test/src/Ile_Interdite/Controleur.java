/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile_Interdite;

/**
 *
 * @author peyrinfl
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Controleur {

    private Grille grille;
    ArrayList<Aventurier> Joueurs = new ArrayList<>();
    private VueAventurier vue;

    public void traiterMessage(String m) {
    }


    /*public void nbJoueur() {
        int nbJoueur = 10;

        while (nbJoueur < 2 && nbJoueur > 4) {
            System.out.println("Il vous faut un minimum de 2 et maximum de 4 joueurs pour jouer !");
            System.out.println("Combien de joueurs veulent jouer ? : ");
            Scanner nb = new Scanner(System.in);
            nbJoueur = nb.nextInt();
        }
        System.out.println("Vous avez inscrit " + nbJoueur + "joueurs.");
        System.out.println("La partie peut se lancer !");
    }
     */
    public Controleur() {
        // TODO code application logic here 
        int l = 0;// ligne
        int c = 0;//colonne
        int niv = 1;//demander aux joueurs

        grille = new Grille(niv);
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
        Coordonnees C5 = new Coordonnees(3, 1);

        Ingenieur J1 = new Ingenieur("César", grille.getTuiles().get(C));
        Explorateur J2 = new Explorateur("Florent", grille.getTuiles().get(C2));
        Pilote J3 = new Pilote("Walid", grille.getTuiles().get(C3));
        Plongeur J4 = new Plongeur("Amine", grille.getTuiles().get(C4));
        Navigateur J5 = new Navigateur("Jean", grille.getTuiles().get(C5));

        Joueurs.add(J1);
        Joueurs.add(J2);
        Joueurs.add(J3);
        Joueurs.add(J4);
        Joueurs.add(J5);

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
                System.out.print(A.getFonction());
                System.out.println( " " + A.getNom() + " : (" + A.getActions() + " actions restants ) ");
                System.out.println("");
                System.out.println("1- se Deplacer");
                if (A.getFonction().equalsIgnoreCase("navigateur")) {
                    System.out.println("1.2- Deplacer un autre joueur (spécial) ");
                }
                System.out.println("2- assecher une tuile");
                System.out.println("3- terminer le tour");
                System.out.print("Veuillez choisir une action (1/2/3): ");
                Scanner scn = new Scanner(System.in);
                String rep = scn.next();

                while (!rep.equalsIgnoreCase("1")&& !rep.equalsIgnoreCase("2") && !rep.equalsIgnoreCase("3") && !rep.equalsIgnoreCase("1.2")) {
                    System.out.print("Veuillez choisir une action (1/2/3): ");
                    rep = scn.next();
                }

                //**********************DEPLACEMENT***********************************************************************
                if (rep.equalsIgnoreCase("1") || rep.equalsIgnoreCase("1.2")) {
                    if (rep.equalsIgnoreCase("1.2")) { //deplacement special navigateur
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
                if (rep.equalsIgnoreCase("2")) {
                    A.assecher(grille);
                }
                //**********************ASSECHEMENT***********************************************************************

                if (rep.equalsIgnoreCase("3")) {
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
