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
import Ile_Interdite.Aventuriers.Navigateur;
import Ile_Interdite.Aventuriers.Plongeur;
import Ile_Interdite.Aventuriers.Pilote;
import Ile_Interdite.Aventuriers.Ingenieur;
import Ile_Interdite.Aventuriers.Aventurier;
import Ile_Interdite.Aventuriers.Explorateur;
import Ile_Interdite.IHM.VueInitialisation;
import Ile_Interdite.cartes.CarteTrésor;
import Ile_Interdite.cartes.Carte;
import Ile_Interdite.cartes.Tresor;
import Ile_Interdite.cartes.SacDeSable;
import Ile_Interdite.cartes.PileInondation;
import Ile_Interdite.cartes.PileTrésor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Random;

public class Controleur implements Observateur {

    private PileInondation pileInondation;
    private PileTrésor pileTresor;
    private VueAventurier ihm;
    private VueInitialisation ihmInit;
    private int no_joueurs;
    private Grille grille;
    ArrayList<Aventurier> Joueurs = new ArrayList<>();

    @Override
    public void traiterMessage(Message message) {


        switch (message.type) {
            case DEMARRER_PARTIE:

                no_joueurs = message.nbJoueurs;

                ihm.setNbJoueurs(no_joueurs);
                
                
                ihmInit.demarrerJeu();
                
                break;

            case QUITTER:
                
                break;
        }

    }

    public Controleur() {

        Initialisation();

        ihmInit = new VueInitialisation();
        ihmInit.addObservateur(this);
        ihmInit.afficher();
        
        ihm = new VueAventurier();
        ihm.addObservateur(this);
        ihm.afficher();
        
        //ihm = new VueAventurier();
        //ihm.addObservateur(this);
        //ihm.afficher();
//        for (int i = 0; i < 36; i++) {// Creation de la Grille
//            Coordonnees C = new Coordonnees(l, c);
//
//            if (c == 2 && l == 0) {
//                LieuDeTresor tuile = new LieuDeTresor(C, "coup");
//                grille.addTuile(tuile);
//            } //coup
//            else if (c == 3 && l == 0) {
//                LieuDeTresor tuile = new LieuDeTresor(C, "feu");
//                grille.addTuile(tuile);
//            }//feu
//            else if (c == 0 && l == 2) {
//                LieuDeTresor tuile = new LieuDeTresor(C, "coup");
//                grille.addTuile(tuile);
//            }//coup
//            else if (c == 0 && l == 3) {
//                LieuDeTresor tuile = new LieuDeTresor(C, "lion");
//                grille.addTuile(tuile);
//            }//lion
//            else if (c == 5 && l == 2) {
//                LieuDeTresor tuile = new LieuDeTresor(C, "feu");
//                grille.addTuile(tuile);
//            }//feu
//            else if (c == 5 && l == 3) {
//                LieuDeTresor tuile = new LieuDeTresor(C, "oeuf");
//                grille.addTuile(tuile);
//            }//oeuf
//            else if (c == 2 && l == 5) {
//                LieuDeTresor tuile = new LieuDeTresor(C, "lion");
//                grille.addTuile(tuile);
//            }//lion
//            else if (c == 3 && l == 5) {
//                LieuDeTresor tuile = new LieuDeTresor(C, "oeuf");
//                grille.addTuile(tuile);
//            }//oeuf
//            else if (c == 2 && l == 2) { // tuile normal
//                Tuile tuile = new Tuile(C);
//                grille.addTuile(tuile);
//            } else if (c == 3 && l == 3) { // tuile normal
//                Tuile tuile = new Tuile(C);
//                grille.addTuile(tuile);
//            } else if (c == 2 && l == 3) { // tuile manquant
//                Tuile tuile = new Tuile(C);
//                tuile.setEtat(2);
//                grille.addTuile(tuile);
//            } else if (c == 3 && l == 2) { // tuile manquant
//                Tuile tuile = new Tuile(C);
//                tuile.setEtat(2);
//                grille.addTuile(tuile);
//            } else if (c == 0 && l == 0 || c == 1 && l == 0 || c == 0 && l == 1
//                    || c == 4 && l == 0 || c == 5 && l == 0 || c == 5 && l == 1
//                    || c == 0 && l == 4 || c == 0 && l == 5 || c == 1 && l == 5
//                    || c == 4 && l == 5 || c == 5 && l == 4 || c == 5 && l == 5) {
//            } //tuile heliport a faire aleatoirement
//            else {
//
//                Tuile tuile = new Tuile(C);
//                tuile.setEtat(1);                 // le reste des tuiles sont cree inonder
//                grille.addTuile(tuile);
//            }
//            c++;
//            if (c == 6) {
//                c = 0;
//                l++;
//            }
//        }
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

    public void Initialisation() {
        //********************************Initialisation Piles Cartes*********************************//
        ArrayList<Carte> cartes = new ArrayList<>();

        for (int i = 1; i < 6; i++) {   // 5 cartes de tresor La Statue du Zéphyr
            Tresor ct = new Tresor("La Statue du Zéphyr");
            cartes.add(ct);
        }
        for (int i = 1; i < 6; i++) {   // 5 cartes de tresor La Pierre Sacrée
            Tresor ct = new Tresor("La Pierre Sacrée");
            cartes.add(ct);
        }
        for (int i = 1; i < 6; i++) {   // 5 cartes de tresor Le Cristal Ardent
            Tresor ct = new Tresor("Le Cristal Ardent");
            cartes.add(ct);
        }
        for (int i = 1; i < 6; i++) {   // 5 cartes de tresor Le Calice de L'onde
            Tresor ct = new Tresor("Le Calice de L'onde");
            cartes.add(ct);
        }
        SacDeSable SacS = new SacDeSable();    // 2 cartes Sac de sable 
        SacDeSable SacS2 = new SacDeSable();
        cartes.add(SacS);
        cartes.add(SacS2);

        //********************************Initialisation Piles *********************************//
        int l = 0;// ligne
        int c = 0;//colonne
        int niv = 1;//demander aux joueurs

        grille = new Grille(niv);

        ArrayList<Coordonnees> coordonneesPossibles = new ArrayList<Coordonnees>();

        coordonneesPossibles.add(new Coordonnees(0, 2));
        coordonneesPossibles.add(new Coordonnees(0, 3));
        coordonneesPossibles.add(new Coordonnees(1, 1));
        coordonneesPossibles.add(new Coordonnees(1, 2));
        coordonneesPossibles.add(
                new Coordonnees(1, 3));
        coordonneesPossibles.add(
                new Coordonnees(1, 4));
        coordonneesPossibles.add(
                new Coordonnees(2, 0));
        coordonneesPossibles.add(
                new Coordonnees(2, 1));
        coordonneesPossibles.add(
                new Coordonnees(2, 2));
        coordonneesPossibles.add(
                new Coordonnees(2, 3));
        coordonneesPossibles.add(
                new Coordonnees(2, 4));
        coordonneesPossibles.add(
                new Coordonnees(2, 5));
        coordonneesPossibles.add(
                new Coordonnees(3, 0));
        coordonneesPossibles.add(
                new Coordonnees(3, 1));
        coordonneesPossibles.add(
                new Coordonnees(3, 2));
        coordonneesPossibles.add(
                new Coordonnees(3, 3));
        coordonneesPossibles.add(
                new Coordonnees(3, 4));
        coordonneesPossibles.add(
                new Coordonnees(3, 5));
        coordonneesPossibles.add(
                new Coordonnees(4, 1));
        coordonneesPossibles.add(
                new Coordonnees(4, 2));
        coordonneesPossibles.add(
                new Coordonnees(4, 3));
        coordonneesPossibles.add(
                new Coordonnees(4, 4));
        coordonneesPossibles.add(
                new Coordonnees(5, 2));
        coordonneesPossibles.add(
                new Coordonnees(5, 3));

        // Placement des trésors
        Random random = new Random();

        int aleatoire = random.nextInt(coordonneesPossibles.size());
        Coordonnees coordonneesAleatoires = coordonneesPossibles.get(aleatoire);
        LieuDeTresor coup1 = new LieuDeTresor(coordonneesAleatoires, "coup1");

        coordonneesPossibles.remove(aleatoire);

        grille.addTuile(coup1);

        aleatoire = random.nextInt(coordonneesPossibles.size());
        coordonneesAleatoires = coordonneesPossibles.get(aleatoire);
        LieuDeTresor coup2 = new LieuDeTresor(coordonneesAleatoires, "coup2");

        coordonneesPossibles.remove(aleatoire);

        grille.addTuile(coup2);

        aleatoire = random.nextInt(coordonneesPossibles.size());
        coordonneesAleatoires = coordonneesPossibles.get(aleatoire);
        LieuDeTresor feu1 = new LieuDeTresor(coordonneesAleatoires, "feu1");

        coordonneesPossibles.remove(aleatoire);

        grille.addTuile(feu1);

        aleatoire = random.nextInt(coordonneesPossibles.size());
        coordonneesAleatoires = coordonneesPossibles.get(aleatoire);
        LieuDeTresor feu2 = new LieuDeTresor(coordonneesAleatoires, "feu2");

        coordonneesPossibles.remove(aleatoire);

        grille.addTuile(feu2);

        aleatoire = random.nextInt(coordonneesPossibles.size());
        coordonneesAleatoires = coordonneesPossibles.get(aleatoire);
        LieuDeTresor lion1 = new LieuDeTresor(coordonneesAleatoires, "lion1");

        coordonneesPossibles.remove(aleatoire);

        grille.addTuile(lion1);

        aleatoire = random.nextInt(coordonneesPossibles.size());
        coordonneesAleatoires = coordonneesPossibles.get(aleatoire);
        LieuDeTresor lion2 = new LieuDeTresor(coordonneesAleatoires, "lion2");

        coordonneesPossibles.remove(aleatoire);

        grille.addTuile(lion2);

        aleatoire = random.nextInt(coordonneesPossibles.size());
        coordonneesAleatoires = coordonneesPossibles.get(aleatoire);
        LieuDeTresor oeuf1 = new LieuDeTresor(coordonneesAleatoires, "oeuf1");

        coordonneesPossibles.remove(aleatoire);

        grille.addTuile(oeuf1);

        aleatoire = random.nextInt(coordonneesPossibles.size());
        coordonneesAleatoires = coordonneesPossibles.get(aleatoire);
        LieuDeTresor oeuf2 = new LieuDeTresor(coordonneesAleatoires, "oeuf2");

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
    }

}
