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
import Ile_Interdite.cartes.CarteTresor;
import Ile_Interdite.cartes.Carte;
import Ile_Interdite.cartes.CarteInondation;
import Ile_Interdite.cartes.Helicoptere;
import Ile_Interdite.cartes.MonteeDesEaux;
import Ile_Interdite.cartes.CarteDeTresor;
import Ile_Interdite.cartes.SacDeSable;
import Ile_Interdite.cartes.PileInondation;
import Ile_Interdite.cartes.PileTresor;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Controleur implements Observateur {

    private PileInondation pileInondation;
    private PileTresor pileTresor;
    private VueAventurier ihm;
    private Aventurier joueurCourant;
    private VueInitialisation ihmInit;
    private int no_joueurs;
    private Aventurier J1, J2, J3, J4;
    private String nom1, nom2, nom3, nom4;
    private int difficulte;
    private Grille grille;

    ArrayList<Aventurier> Joueurs = new ArrayList<>();
    private Niveau niveau;

    @Override
    public void traiterMessage(Message message) {

        switch (message.type) {
            case DEMARRER_PARTIE:

                no_joueurs = message.nbJoueurs;
                nom1 = message.nom1;
                nom2 = message.nom2;
                nom3 = message.nom3;
                nom4 = message.nom4;
                difficulte = message.difficulte;
                ihm.setNbJoueurs(no_joueurs);
                ihm.setNomJoueurs(nom1, nom2, nom3, nom4);
                ihm.setDifficulte(difficulte);

                ihm.mettreAJourTuiles(grille.getTuiles().values());
                ihmInit.demarrerJeu();

                J1.setNom(nom2);
                J2.setNom(nom1);
                J3.setNom(nom3);
                J4.setNom(nom4);
                ihm.setJoueurCourant(nom1);
                break;

            case DEPLACER:
                joueurCourant = getJoueurCourant(message.joueurCourant);

                tuilesVoisines(joueurCourant);

                break;
           case CHOIX_TUILE:
                deplacement(message.c);
                ihm.mettreAJourTuiles(grille.getTuiles().values());
                break;
            
        }

    }

    public Aventurier getJoueurCourant(String nomJoueur) {
        for (Aventurier a : Joueurs) {
            if (a.getNom().equalsIgnoreCase(nomJoueur)) {
                return a;
            }
        }
        return null;
    }

    private void deplacement(Coordonnees c) {
        for (Tuile t : grille.getTuiles().values()){
            if (t.getCoordonnee().afficherCoord().equalsIgnoreCase(c.afficherCoord())){
                joueurCourant.deplacer(t);
            }
        }
        
    }

    public void tuilesVoisines(Aventurier A) {
        ArrayList<Coordonnees> c = new ArrayList<>();

        if (A.getFonction().equalsIgnoreCase("Explorateur")) {
            for (Tuile t : grille.getTuilesVoisinesAvecDiagonal(A.getTuile()).values()) {
                c.add(t.getCoordonnee());
            }
            A.setActions(A.getActions() - 1);
        } else if (A.getFonction().equalsIgnoreCase("Pilot") && !A.CompetanceUtiliser()) {
            for (Tuile t : grille.getTuilesVoisinesHelicoptere().values()) {
                c.add(t.getCoordonnee());
            }
            A.setUtilise(true);
            A.setActions(A.getActions() - 1);
        }
        if (A.getFonction().equalsIgnoreCase("Plongeur")) {
            for (Tuile t : grille.getTuilesVoisinesPlongeur(A.getTuile()).values()) {
                c.add(t.getCoordonnee());
            }
            A.setActions(A.getActions() - 1);
        } else {
            for (Tuile t : grille.getTuilesVoisines(A.getTuile()).values()) {
                c.add(t.getCoordonnee());
            }
            A.setActions(A.getActions() - 1);

        }

        ihm.setTuilesDispo(c);

    }

    public Controleur() {

        ihmInit = new VueInitialisation();
        ihmInit.addObservateur(this);
        ihmInit.afficher();
        ihm = new VueAventurier();
        ihm.addObservateur(this);
        ihm.afficher();
        //tcommencerPartie();
        Initialisation();
        System.out.println(nom1 + " se trouve sur la tuile :" + J1.getTuile().getCoordonnee().afficherCoord());
    }

    public void afficherCarte(ArrayList<CarteTresor> c) {
        for (CarteTresor CT : c) {
            System.out.println("\t - " + CT.getFonction());
        }
    }

    public void commencerPartie() {

        //parametrage ////////////////////////////////////////////////////////////////
        //grille.AfficherGrille();
        //****************************************Test Area*******************************
//        if (!J1.mainIsFull()) {
//            pileTresor.piocher(J1);
//            pileTresor.piocher(J1);
//            pileTresor.piocher(J1);
//            pileTresor.piocher(J1); 
//            pileTresor.piocher(J1);
//            pileTresor.piocher(J1);
//            pileTresor.piocher(J1);
//        } else {
//            System.out.println(J1.getNom() + " a " + J1.getCartesEnMain().size() + " cartes dans les mains :");
//        }
//        ArrayList<CarteTrésor> ctr = J1.getCartesEnMain();
//        for (CarteTrésor ct : ctr) {
//            if (ct.getFonction().equals("Montée des Eaux")) {
//                //c.action();
//                pileTresor.Defausser(ct, J1);
//            }
//        }
//        System.out.println(J1.getNom() + " a " + J1.getCartesEnMain().size() + " cartes dans les mains :");
//        afficherCarte(J1.getCartesEnMain());
//        System.out.println("contenu restant de la pile carte de Tresor : (" + pileTresor.getCartesTrésor().size() + " cartes)");
//        afficherCarte(pileTresor.getCartesTrésor());
//        while (J1.mainIsFull()) {
//            int numcarte = 1;
//            System.out.println("Vous avez plus de 5 cartes dans la main !");
//            System.out.println("Defausser une carte :");
//            for (CarteTrésor ct : J1.getCartesEnMain()) {
//                System.out.println("\t" + numcarte + " - " + ct.getFonction());
//                numcarte++;
//            }
//            System.out.print("Quelle carte voulez vous defausser ? : ");
//            Scanner scn = new Scanner(System.in);
//
//            int dir = scn.nextInt();
//            pileTresor.Defausser(J1.getCartesEnMain().get(dir - 1), J1);
//
//        }
//        System.out.println(J1.getNom() + " a " + J1.getCartesEnMain().size() + " cartes dans les mains :");
//        afficherCarte(J1.getCartesEnMain());
//        J1.donner(J1.getCartesEnMain().get(0), J2);
//        System.out.println("J1 donne une carte a J2 :");
//        System.out.println("main de J1 :");
//        afficherCarte(J1.getCartesEnMain());
//        System.out.println("main de J2 :");
//        afficherCarte(J2.getCartesEnMain());
//        System.out.println("Deck defausser :");
//        afficherCarte(pileTresor.getCartesTrésorDefaussees());
//        pileTresor.melangerLesPiles();
//        System.out.println("Deck defausser :");
//        afficherCarte(pileTresor.getCartesTrésorDefaussees());
//        System.out.println("contenu restant de la pile carte de Tresor : (" + pileTresor.getCartesTrésor().size() + " cartes)");
//        afficherCarte(pileTresor.getCartesTrésor());
//        
        //****************************************Test Area*******************************
        ////////////////////////////////////////COMMENCEMENT DE LA PARTIE////////////////////////////////////////////////////////
//        System.out.println("____________________________________________________________");
//        System.out.println("                        TOUR 1                              ");
//        System.out.println("____________________________________________________________");
//        System.out.println("");
//        for (Aventurier A : Joueurs) {
//            //****************************DEBUT********************************************
//            while (A.getActions() > 0 && !A.isTourTerminer()) {
//                ihm.setJoueurCourant(A.getNom());
//                System.out.print(A.getFonction());
//                System.out.println(" " + A.getNom() + " : (" + A.getActions() + " actions restantes ) ");
//                System.out.println("");
//                System.out.println("carte en main :");
//                afficherCarte(A.getCartesEnMain());
//                System.out.println("");
//                System.out.println("1- Se Déplacer");
//                if (A.getFonction().equalsIgnoreCase("\u001B[33m" + "navigateur")) {
//                    System.out.println("1.2- Déplacer un autre joueur (spécial) ");
//                }
//
//                System.out.println("2- Assécher une tuile");
//
//                if (A.getTuile().getType() != null && A.isCollected(A.getTuile())) {
//                    System.out.println("3- Gagner Tresor");
//                }
//                System.out.println("0- Terminer le tour");
//                System.out.print("Veuillez choisir une action : ");
//                Scanner scn = new Scanner(System.in);
//                String rep = scn.next();
//
//                while (!rep.equalsIgnoreCase("1") && !rep.equalsIgnoreCase("2") && !rep.equalsIgnoreCase("3") && !rep.equalsIgnoreCase("1.2") && !rep.equalsIgnoreCase("0")) {
//                    System.out.print("Veuillez choisir une action : ");
//                    rep = scn.next();
//                }
//
//                //**********************DEPLACEMENT***********************************************************************
//                if (rep.equalsIgnoreCase("1") || rep.equalsIgnoreCase("1.2")) {
//                    if (rep.equalsIgnoreCase("1.2")) { //deplacement special navigateur
//                        System.out.println("Aventuriers : ");
//                        int select = 1;
//                        for (Aventurier A2 : Joueurs) {
//                            if (!A2.getFonction().equals(A.getFonction())) {
//                                System.out.println("\u001B[30m" + select + "- " + A2.getFonction() + " " + A2.getNom() + " " + A2.getTuile().getCoordonnee().afficherCoord());
//
//                            } else {
//                                select--;
//                            }
//                            select++;
//                        }
//                        System.out.print("Qui voulez-vous déplacer ?: ");
//                        Scanner selc = new Scanner(System.in);
//                        int Av = selc.nextInt();
//                        while (Av < 1 || Av > 4) {
//                            System.out.print("Qui voulez-vous déplacer ? (1 à " + select + ") :");
//                            Av = selc.nextInt();
//                        }
//
//                        A.faireDeplacer(grille, Joueurs.get(Av - 1));
//                    } else { //deplacement normal
//                        A.deplacer(grille);
//                    }
//                    System.out.println(nom1 + " " + nom2 + nom3 + nom4);
//                }
//                //**********************ASSECHEMENT***********************************************************************
//                if (rep.equalsIgnoreCase("2")) {
//                    A.assecher(grille);
//                }
//
//                //**********************GAGNER TRESOR***********************************************************************
//                if (rep.equalsIgnoreCase("3")) {
//                    A.gagnerTresor(A.getTuile(), pileTresor);
//
//                }
//                if (rep.equalsIgnoreCase("0")) {
//                    A.setTerminer(true);
//                }
//
//                grille.AfficherGrille();
//                A.getTresors().tresorsCollecte();
//
//            }
//            A.tourTermine();
//            A.Reset();
//            //****************************FIN********************************************
//        }
//        System.out.println("____________________________________________________________");
//        System.out.println("                        FIN TOUR 1                              ");
//        System.out.println("____________________________________________________________");
    }

    public void Initialisation() {
        //********************************Initialisation Gille*********************************//
        int l = 0;// ligne
        int c = 0;//colonne
        int niv = 1;//demander aux joueurs
        grille = new Grille(niv);
        for (int i = 0; i < 36; i++) {// Creation de la Grille
            Coordonnees C = new Coordonnees(l, c);

            if (c == 2 && l == 0) {
                LieuDeTresor tuile = new LieuDeTresor(C, "Calice");
                grille.addTuile(tuile);
            } else if (c == 0 && l == 2) {
                LieuDeTresor tuile = new LieuDeTresor(C, "Calice");
                grille.addTuile(tuile);
            } else if (c == 3 && l == 0) {
                LieuDeTresor tuile = new LieuDeTresor(C, "Cristal");
                grille.addTuile(tuile);
            } else if (c == 5 && l == 2) {
                LieuDeTresor tuile = new LieuDeTresor(C, "Cristal");
                grille.addTuile(tuile);
            } else if (c == 0 && l == 3) {
                LieuDeTresor tuile = new LieuDeTresor(C, "Statue");
                grille.addTuile(tuile);
            } else if (c == 2 && l == 5) {
                LieuDeTresor tuile = new LieuDeTresor(C, "Statue");
                grille.addTuile(tuile);
            } else if (c == 5 && l == 3) {
                LieuDeTresor tuile = new LieuDeTresor(C, "Pierre");
                grille.addTuile(tuile);
            } else if (c == 3 && l == 5) {
                LieuDeTresor tuile = new LieuDeTresor(C, "Pierre");
                grille.addTuile(tuile);
            } else if (c == 0 && l == 0 || c == 1 && l == 0 || c == 0 && l == 1
                    || c == 4 && l == 0 || c == 5 && l == 0 || c == 5 && l == 1
                    || c == 0 && l == 4 || c == 0 && l == 5 || c == 1 && l == 5
                    || c == 4 && l == 5 || c == 5 && l == 4 || c == 5 && l == 5) {
            } else {

                Tuile tuile = new Tuile(C);
                tuile.setEtat(0);
                grille.addTuile(tuile);
            }
            c++;
            if (c == 6) {
                c = 0;
                l++;
            }
        }

        ArrayList<Coordonnees> coordonneesPossibles = new ArrayList<Coordonnees>();
        for (int i = 0; i < 36; i++) {// Creation de la Grille
            Coordonnees C = new Coordonnees(l, c);
            if (c == 0 && l == 0 || c == 1 && l == 0 || c == 0 && l == 1
                    || c == 4 && l == 0 || c == 5 && l == 0 || c == 5 && l == 1
                    || c == 0 && l == 4 || c == 0 && l == 5 || c == 1 && l == 5
                    || c == 4 && l == 5 || c == 5 && l == 4 || c == 5 && l == 5) {
            } else {
                coordonneesPossibles.add(C);
            }
            c++;
            if (c == 6) {
                c = 0;
                l++;
            }
        }
        Coordonnees C = new Coordonnees(2, 3);
        Coordonnees C2 = new Coordonnees(5, 2);
        Coordonnees C3 = new Coordonnees(5, 3);
        Coordonnees C4 = new Coordonnees(3, 3);
        Coordonnees C5 = new Coordonnees(0, 3);

        J1 = new Ingenieur(nom2, grille.getTuiles().get(C));
        J2 = new Explorateur(nom1, grille.getTuiles().get(C2));
        J3 = new Pilote(nom3, grille.getTuiles().get(C3));
        J4 = new Plongeur(nom4, grille.getTuiles().get(C4));
        Navigateur J5 = new Navigateur("Rémi", grille.getTuiles().get(C5));

        Joueurs.add(J1);
        Joueurs.add(J2);
        Joueurs.add(J3);
        Joueurs.add(J4);
        Joueurs.add(J5);

        J1.addCarte(new CarteDeTresor("Calice"));
        J1.addCarte(new CarteDeTresor("Calice"));
        J1.addCarte(new CarteDeTresor("Calice"));
        J1.addCarte(new CarteDeTresor("Calice"));

        J2.addCarte(new CarteDeTresor("Statue"));
        J2.addCarte(new CarteDeTresor("Statue"));
        J2.addCarte(new CarteDeTresor("Statue"));
        J2.addCarte(new CarteDeTresor("Statue"));

        J3.addCarte(new CarteDeTresor("Pierre"));
        J3.addCarte(new CarteDeTresor("Pierre"));
        J3.addCarte(new CarteDeTresor("Pierre"));
        J3.addCarte(new CarteDeTresor("Pierre"));

        J5.addCarte(new CarteDeTresor("Cristal"));
        J5.addCarte(new CarteDeTresor("Cristal"));
        J5.addCarte(new CarteDeTresor("Cristal"));
        J5.addCarte(new CarteDeTresor("Cristal"));
/////////////////////////////////////////////PLACEMENT DES TUILES ALEATOIREMENT///////////////////////////////////////////////////////////////
        // Placement aléatoire des trésors
//        Random random = new Random();
//
//        int aleatoire = random.nextInt(coordonneesPossibles.size());
//        Coordonnees coordonneesAleatoires = coordonneesPossibles.get(aleatoire);
//        LieuDeTresor coup1 = new LieuDeTresor(coordonneesAleatoires, "Calice");
//        coordonneesPossibles.remove(aleatoire);
//        grille.addTuile(coup1);
//
//        aleatoire = random.nextInt(coordonneesPossibles.size());
//        coordonneesAleatoires = coordonneesPossibles.get(aleatoire);
//        LieuDeTresor coup2 = new LieuDeTresor(coordonneesAleatoires, "Calice");
//        coordonneesPossibles.remove(aleatoire);
//        grille.addTuile(coup2);
//
//        aleatoire = random.nextInt(coordonneesPossibles.size());
//        coordonneesAleatoires = coordonneesPossibles.get(aleatoire);
//        LieuDeTresor feu1 = new LieuDeTresor(coordonneesAleatoires, "Cristal");
//        coordonneesPossibles.remove(aleatoire);
//        grille.addTuile(feu1);
//
//        aleatoire = random.nextInt(coordonneesPossibles.size());
//        coordonneesAleatoires = coordonneesPossibles.get(aleatoire);
//        LieuDeTresor feu2 = new LieuDeTresor(coordonneesAleatoires, "Cristal");
//        coordonneesPossibles.remove(aleatoire);
//        grille.addTuile(feu2);
//
//        aleatoire = random.nextInt(coordonneesPossibles.size());
//        coordonneesAleatoires = coordonneesPossibles.get(aleatoire);
//        LieuDeTresor lion1 = new LieuDeTresor(coordonneesAleatoires, "Statue");
//        coordonneesPossibles.remove(aleatoire);
//        grille.addTuile(lion1);
//
//        aleatoire = random.nextInt(coordonneesPossibles.size());
//        coordonneesAleatoires = coordonneesPossibles.get(aleatoire);
//        LieuDeTresor lion2 = new LieuDeTresor(coordonneesAleatoires, "Statue");
//        coordonneesPossibles.remove(aleatoire);
//        grille.addTuile(lion2);
//
//        aleatoire = random.nextInt(coordonneesPossibles.size());
//        coordonneesAleatoires = coordonneesPossibles.get(aleatoire);
//        LieuDeTresor oeuf1 = new LieuDeTresor(coordonneesAleatoires, "Pierre");
//        coordonneesPossibles.remove(aleatoire);
//        grille.addTuile(oeuf1);
//
//        aleatoire = random.nextInt(coordonneesPossibles.size());
//        coordonneesAleatoires = coordonneesPossibles.get(aleatoire);
//        LieuDeTresor oeuf2 = new LieuDeTresor(coordonneesAleatoires, "Pierre");
//        coordonneesPossibles.remove(aleatoire);
//        grille.addTuile(oeuf2);
//
//        // Placement aléatoire de l'héliport   
//        aleatoire = random.nextInt(coordonneesPossibles.size());
//        coordonneesAleatoires = coordonneesPossibles.get(aleatoire);
//        Heliport heliport = new Heliport(coordonneesAleatoires);
//        coordonneesPossibles.remove(aleatoire);
//        grille.addTuile(heliport);
//
//        // Placement aléatoire des autres tuiles
//        while (!coordonneesPossibles.isEmpty()) {
//            aleatoire = random.nextInt(coordonneesPossibles.size());
//            coordonneesAleatoires = coordonneesPossibles.get(aleatoire);
//            Tuile tuile = new Tuile(coordonneesAleatoires);
//            coordonneesPossibles.remove(aleatoire);
//            grille.addTuile(tuile);
//        }
/////////////////////////////////////////////PLACEMENT DES TUILES ALEATOIREMENT///////////////////////////////////////////////////////////////

        //********************************Initialisation Piles Cartes*********************************//
        ArrayList<CarteTresor> cartes = new ArrayList<>();

        for (int i = 1; i < 6; i++) {   // 5 cartes de tresor La Statue du Zéphyr
            CarteDeTresor ct = new CarteDeTresor("Statue");
            cartes.add(ct);
        }
        for (int i = 1; i < 6; i++) {   // 5 cartes de tresor La Pierre Sacrée
            CarteDeTresor ct = new CarteDeTresor("Pierre");
            cartes.add(ct);
        }
        for (int i = 1; i < 6; i++) {   // 5 cartes de tresor Le Cristal Ardent
            CarteDeTresor ct = new CarteDeTresor("Cristal");
            cartes.add(ct);
        }
        for (int i = 1; i < 6; i++) {   // 5 cartes de tresor Le Calice de L'onde
            CarteDeTresor ct = new CarteDeTresor("Calice");
            cartes.add(ct);
        }
        SacDeSable SacS = new SacDeSable();    // 2 cartes Sac de sable 
        SacDeSable SacS2 = new SacDeSable();
        cartes.add(SacS);
        cartes.add(SacS2);

        MonteeDesEaux mde = new MonteeDesEaux();     // 3 cartes Montée Des Eaux
        MonteeDesEaux mde2 = new MonteeDesEaux();
        //MontéeDesEaux mde3 = new MontéeDesEaux();
        cartes.add(mde);
        cartes.add(mde2);
        //cartes.add(mde3);

        Helicoptere H = new Helicoptere(); // 3 cartes helicoptere
        Helicoptere H2 = new Helicoptere();
        Helicoptere H3 = new Helicoptere();
        cartes.add(H);
        cartes.add(H2);
        cartes.add(H3);

        pileTresor = new PileTresor(cartes);// inistialiser la pile de tresor

        ArrayList<CarteInondation> cartesInondation = new ArrayList<>();
        for (Coordonnees c1 : coordonneesPossibles) {
            CarteInondation carteI = new CarteInondation(c1);
            cartesInondation.add(carteI);

        }
        pileInondation = new PileInondation(cartesInondation); // inistialisation de la pile Inondation
    }

}
