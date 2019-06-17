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
import Ile_Interdite.Aventuriers.Aventurier;
import Ile_Interdite.Aventuriers.Explorateur;
import Ile_Interdite.Aventuriers.Ingenieur;
import Ile_Interdite.Aventuriers.Navigateur;
import Ile_Interdite.Aventuriers.Pilote;
import Ile_Interdite.Aventuriers.Plongeur;
import Ile_Interdite.IHM.Message;
import Ile_Interdite.IHM.Observateur;
import Ile_Interdite.IHM.VueAventurier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Random;

public class Controleur implements Observateur {

    private VueAventurier ihm;
    private Grille grille;
    ArrayList<Aventurier> Joueurs = new ArrayList<>();
    private VueAventurier vue;

    @Override
    public void traiterMessage(Message m) {
    }

    public Controleur() {
        int l = 0;// ligne
        int c = 0;//colonne
        int niv = 1;//demander aux joueurs

        grille = new Grille(niv);

        ArrayList<Coordonnees> coordonneesPossibles = new ArrayList<Coordonnees>();
        coordonneesPossibles.add(new Coordonnees(0, 2));
        coordonneesPossibles.add(new Coordonnees(0, 3));
        coordonneesPossibles.add(new Coordonnees(1, 1));
        coordonneesPossibles.add(new Coordonnees(1, 2));
        coordonneesPossibles.add(new Coordonnees(1, 3));
        coordonneesPossibles.add(new Coordonnees(1, 4));
        coordonneesPossibles.add(new Coordonnees(2, 0));
        coordonneesPossibles.add(new Coordonnees(2, 1));
        coordonneesPossibles.add(new Coordonnees(2, 2));
        coordonneesPossibles.add(new Coordonnees(2, 3));
        coordonneesPossibles.add(new Coordonnees(2, 4));
        coordonneesPossibles.add(new Coordonnees(2, 5));
        coordonneesPossibles.add(new Coordonnees(3, 0));
        coordonneesPossibles.add(new Coordonnees(3, 1));
        coordonneesPossibles.add(new Coordonnees(3, 2));
        coordonneesPossibles.add(new Coordonnees(3, 3));
        coordonneesPossibles.add(new Coordonnees(3, 4));
        coordonneesPossibles.add(new Coordonnees(3, 5));
        coordonneesPossibles.add(new Coordonnees(4, 1));
        coordonneesPossibles.add(new Coordonnees(4, 2));
        coordonneesPossibles.add(new Coordonnees(4, 3));
        coordonneesPossibles.add(new Coordonnees(4, 4));
        coordonneesPossibles.add(new Coordonnees(5, 2));
        coordonneesPossibles.add(new Coordonnees(5, 3));

        // Placement des trésors
        Random random = new Random();

        int aleatoire = random.nextInt(coordonneesPossibles.size());
        Coordonnees coordonneesAleatoires = coordonneesPossibles.get(aleatoire);
        LieuDeTresor coup1 = new LieuDeTresor(coordonneesAleatoires, "Calice de l'Onde");
        coordonneesPossibles.remove(aleatoire);
        grille.addTuile(coup1);

        aleatoire = random.nextInt(coordonneesPossibles.size());
        coordonneesAleatoires = coordonneesPossibles.get(aleatoire);
        LieuDeTresor coup2 = new LieuDeTresor(coordonneesAleatoires, "Calice de l'Onde");
        coordonneesPossibles.remove(aleatoire);
        grille.addTuile(coup2);

        aleatoire = random.nextInt(coordonneesPossibles.size());
        coordonneesAleatoires = coordonneesPossibles.get(aleatoire);
        LieuDeTresor feu1 = new LieuDeTresor(coordonneesAleatoires, "Cristal Ardent");
        coordonneesPossibles.remove(aleatoire);
        grille.addTuile(feu1);

        aleatoire = random.nextInt(coordonneesPossibles.size());
        coordonneesAleatoires = coordonneesPossibles.get(aleatoire);
        LieuDeTresor feu2 = new LieuDeTresor(coordonneesAleatoires, "Cristal Ardent");
        coordonneesPossibles.remove(aleatoire);
        grille.addTuile(feu2);

        aleatoire = random.nextInt(coordonneesPossibles.size());
        coordonneesAleatoires = coordonneesPossibles.get(aleatoire);
        LieuDeTresor lion1 = new LieuDeTresor(coordonneesAleatoires, "Statue du Zéphir");
        coordonneesPossibles.remove(aleatoire);
        grille.addTuile(lion1);

        aleatoire = random.nextInt(coordonneesPossibles.size());
        coordonneesAleatoires = coordonneesPossibles.get(aleatoire);
        LieuDeTresor lion2 = new LieuDeTresor(coordonneesAleatoires, "Statue du Zéphir");
        coordonneesPossibles.remove(aleatoire);
        grille.addTuile(lion2);

        aleatoire = random.nextInt(coordonneesPossibles.size());
        coordonneesAleatoires = coordonneesPossibles.get(aleatoire);
        LieuDeTresor oeuf1 = new LieuDeTresor(coordonneesAleatoires, "Pierre Sacrée");
        coordonneesPossibles.remove(aleatoire);
        grille.addTuile(oeuf1);

        aleatoire = random.nextInt(coordonneesPossibles.size());
        coordonneesAleatoires = coordonneesPossibles.get(aleatoire);
        LieuDeTresor oeuf2 = new LieuDeTresor(coordonneesAleatoires, "Pierre Sacrée");
        coordonneesPossibles.remove(aleatoire);
        grille.addTuile(oeuf2);

        // Placement de l'héliport   
        aleatoire = random.nextInt(coordonneesPossibles.size());
        coordonneesAleatoires = coordonneesPossibles.get(aleatoire);
        Héliport heliport = new Héliport(coordonneesAleatoires);
        coordonneesPossibles.remove(aleatoire);
        grille.addTuile(heliport);

        // Placement des autres tuiles
        while (!coordonneesPossibles.isEmpty()) {
            aleatoire = random.nextInt(coordonneesPossibles.size());
            coordonneesAleatoires = coordonneesPossibles.get(aleatoire);
            Tuile tuile = new Tuile(coordonneesAleatoires);
            coordonneesPossibles.remove(aleatoire);
            grille.addTuile(tuile);
        }

        coordonneesPossibles.add(new Coordonnees(0, 2));
        coordonneesPossibles.add(new Coordonnees(0, 3));
        coordonneesPossibles.add(new Coordonnees(1, 1));
        coordonneesPossibles.add(new Coordonnees(1, 2));
        coordonneesPossibles.add(new Coordonnees(1, 3));
        coordonneesPossibles.add(new Coordonnees(1, 4));
        coordonneesPossibles.add(new Coordonnees(2, 0));
        coordonneesPossibles.add(new Coordonnees(2, 1));
        coordonneesPossibles.add(new Coordonnees(2, 2));
        coordonneesPossibles.add(new Coordonnees(2, 3));
        coordonneesPossibles.add(new Coordonnees(2, 4));
        coordonneesPossibles.add(new Coordonnees(2, 5));
        coordonneesPossibles.add(new Coordonnees(3, 0));
        coordonneesPossibles.add(new Coordonnees(3, 1));
        coordonneesPossibles.add(new Coordonnees(3, 2));
        coordonneesPossibles.add(new Coordonnees(3, 3));
        coordonneesPossibles.add(new Coordonnees(3, 4));
        coordonneesPossibles.add(new Coordonnees(3, 5));
        coordonneesPossibles.add(new Coordonnees(4, 1));
        coordonneesPossibles.add(new Coordonnees(4, 2));
        coordonneesPossibles.add(new Coordonnees(4, 3));
        coordonneesPossibles.add(new Coordonnees(4, 4));
        coordonneesPossibles.add(new Coordonnees(5, 2));
        coordonneesPossibles.add(new Coordonnees(5, 3));

        // Placement des joueurs
        aleatoire = random.nextInt(coordonneesPossibles.size());
        Coordonnees C = coordonneesPossibles.get(aleatoire);
        Ingenieur J1 = new Ingenieur("César", grille.getTuiles().get(C));
        coordonneesPossibles.remove(aleatoire);
        Joueurs.add(J1);

        aleatoire = random.nextInt(coordonneesPossibles.size());
        Coordonnees C2 = coordonneesPossibles.get(aleatoire);
        Explorateur J2 = new Explorateur("Florent", grille.getTuiles().get(C2));
        coordonneesPossibles.remove(aleatoire);
        Joueurs.add(J2);

        aleatoire = random.nextInt(coordonneesPossibles.size());
        Coordonnees C3 = coordonneesPossibles.get(aleatoire);
        Pilote J3 = new Pilote("Walid", grille.getTuiles().get(C3));
        coordonneesPossibles.remove(aleatoire);
        Joueurs.add(J3);

        aleatoire = random.nextInt(coordonneesPossibles.size());
        Coordonnees C4 = coordonneesPossibles.get(aleatoire);
        Plongeur J4 = new Plongeur("Amine", grille.getTuiles().get(C4));
        coordonneesPossibles.remove(aleatoire);
        Joueurs.add(J3);

        aleatoire = random.nextInt(coordonneesPossibles.size());
        Coordonnees C5 = coordonneesPossibles.get(aleatoire);
        Navigateur J5 = new Navigateur("Rémi", grille.getTuiles().get(C5));
        coordonneesPossibles.remove(aleatoire);
        Joueurs.add(J5);

        ihm = new VueAventurier();
        ihm.addObservateur(this);
        ihm.afficher();

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
                System.out.println(" " + A.getNom() + " : (" + A.getActions() + " actions restantes ) ");
                System.out.println("");
                System.out.println("1- Se Déplacer");
                if (A.getFonction().equalsIgnoreCase("\u001B[33m" + "navigateur")) {
                    System.out.println("1.2- Déplacer un autre joueur (spécial) ");
                }
                System.out.println("2- Assécher une tuile");
                System.out.println("3- Terminer le tour");
                System.out.print("Veuillez choisir une action (1/2/3): ");
                Scanner scn = new Scanner(System.in);
                String rep = scn.next();

                while (!rep.equalsIgnoreCase("1") && !rep.equalsIgnoreCase("2") && !rep.equalsIgnoreCase("3") && !rep.equalsIgnoreCase("1.2")) {
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
                                System.out.println("\u001B[30m" + select + "- " + A2.getFonction() + " " + A2.getNom() + " " + A2.getTuile().getCoordonnee().afficherCoord());

                            } else {
                                select--;
                            }
                            select++;
                        }
                        System.out.print("Qui voulez-vous déplacer ?: ");
                        Scanner selc = new Scanner(System.in);
                        int Av = selc.nextInt();
                        while (Av < 1 || Av > 4) {
                            System.out.print("Qui voulez-vous déplacer ? (1 à " + select + ") :");
                            Av = selc.nextInt();
                        }

                        A.faireDeplacer(grille, Joueurs.get(Av - 1));
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
