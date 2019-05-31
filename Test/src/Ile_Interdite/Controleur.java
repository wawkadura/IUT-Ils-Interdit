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
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Controleur {

    private Grille grille;
    private HashMap<String, Aventurier> joueurs = new HashMap<String, Aventurier>();
    private VueAventurier vueAventurier;
    private PileInondation pileInondation;
    private PileTrésor pileTresor;

    public void traiterMessage(String m) {
    }

    public void nbJoueur() {
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

    public static void main(String[] args) {
        // TODO code application logic here 
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
                grille.addTuile(tuile);
            }
            c++;
            if (c == 6) {
                c = 0;
                l++;
            }

        }
        grille.AfficherGrille();

        //test area ////////////////////////////////////////////////////////////////
        
        
        
        //test area ////////////////////////////////////////////////////////////////
        
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
        ////////////////////////////////////////COMMENCEMENT DE LA PARTIE////////////////////////////////////////////////////////
        for (Aventurier A : Joueurs) {
            while (A.getActions() > 0 && !A.isTourTerminer()) {
                System.out.println(A.getNom() + " : (" + A.getActions() + " actions restants ) ");
                System.out.println("");
                System.out.println("1- se Deplacer");
                System.out.println("2- assecher une tuile");
                System.out.println("3- terminer le tour");
                System.out.println("Veuillez choisir une action (1/2/3): ");

                Scanner scn = new Scanner(System.in);
                int rep = scn.nextInt();

                while (rep < 1 || rep > 3) {
                    System.out.println("Veuillez choisir une action (1/2/3): ");
                }
                if (rep == 1) {
                    if (A.getNom().equals("Florent")) {
                        System.out.println("Qui voulez vous dplacer : ");
                        //for blabla 
                    } else {
                        A.deplacer(grille);
                    }
                }
                if (rep == 2) {
                }
                if (rep == 3) {
                    A.tourTermine();
                }
                grille.AfficherGrille();
            }
            A.Reset();
        }

    }
}
