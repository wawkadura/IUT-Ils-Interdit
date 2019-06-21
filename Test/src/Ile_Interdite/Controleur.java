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
import Ile_Interdite.Aventuriers.Messager;
import Ile_Interdite.IHM.VueInitialisation;
import Ile_Interdite.IHM.VueNotifications;
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
    private int joueurAct = 1;
    private VueNotifications ihmNotif;
    private VueInitialisation ihmInit;
    private int no_joueurs;
    private int carteAPiocher;
    ArrayList<String> joueurs = new ArrayList<>();
    private Aventurier J1, J2, J3, J4, J5, J6;
    private String nom1, nom2, nom3, nom4;
    private int difficulte, numCarte;
    private Grille grille;
    
    ArrayList<Aventurier> aventuriers = new ArrayList<>();
    private Niveau niveau;
    
    @Override
    public void traiterMessage(Message message) {
        
        switch (message.type) {
            case DIFFICULTE:
                
                carteAPiocher = message.carteAPiocher;
                break;
            
            case DEMARRER_PARTIE:
                
                

                no_joueurs = message.nbJoueurs;
                nom1 = message.nom1;
                nom2 = message.nom2;
                nom3 = message.nom3;
                nom4 = message.nom4;
                difficulte = message.difficulte;
                
                ihm.setNbJoueurs(no_joueurs);
                ihm.setNomJoueurs(nom1, nom2, nom3, nom4);
                ihm.setRoleJoueur(aventuriers.get(0).getFonction(), aventuriers.get(1).getFonction(), aventuriers.get(2).getFonction(), aventuriers.get(3).getFonction());
                ihm.setNivEau(difficulte);
                
                ihm.creeNiv();
                ihm.mettreAJourNivEau();
                ihm.getJoueurAct(1);
                ihm.mettreAJourTuiles(grille.getTuiles().values());
                ihmInit.demarrerJeu();
                J1.setNom(nom1);
                J2.setNom(nom2);
                J3.setNom(nom3);
                J4.setNom(nom4);
                ihm.setJoueurCourant(nom1);
                joueurCourant = getJoueurCourant(nom1);
                ihm.mettreAJourCartes(joueurCourant.getCartesEnMain());
                ihm.mettreAJourActions(peuxGagnerTresor(), getJoueurTuile(), peuxAssecher());
                ihm.setCouleurJoueur();
                joueurs.add(nom1);
                joueurs.add(nom2);
                joueurs.add(nom3);
                joueurs.add(nom4);
                break;
            
            case DEPLACER:
                joueurCourant = getJoueurCourant(message.joueurCourant);
                
                tuilesVoisinesDeplacement(joueurCourant);
                
                break;
            case ASSECHER:
                joueurCourant = getJoueurCourant(message.joueurCourant);
                
                tuilesVoisinesAssechement(joueurCourant);
                
                break;
            case DONNER:
                ihm.setCartesDispo();
                
                break;
            case CHOIX_TUILE:
                if (message.deplacer) {
                    System.out.println(message.c.afficherCoord());
                    deplacement(message.c);
                    ihm.mettreAJourTuiles(grille.getTuiles().values());
                    ihm.mettreAJourActions(peuxGagnerTresor(), getJoueurTuile(), peuxAssecher());
                }
                if (message.assecher) {
                    
                    assechement(message.c);
                    ihm.mettreAJourTuiles(grille.getTuiles().values());
                    ihm.mettreAJourActions(peuxGagnerTresor(), getJoueurTuile(), peuxAssecher());
                    
                }
                break;
            case CHOIX_CARTE:
                if (message.donner) {
                    numCarte = message.numCarte;
                    ihm.mettreAJourCartes(joueurCourant.getCartesEnMain());
                    ihm.setJoueurDispo(getJoueurTuile());
                }
                if (message.defausser) {
                    numCarte = message.numCarte;
                    pileTresor.Defausser(getCarte(numCarte), joueurCourant);
                    ihm.mettreAJourCartes(joueurCourant.getCartesEnMain());
                }
                
                break;
            case CHOIX_JOUEUR:
                joueurCourant.donner(getCarte(numCarte), getJoueur(message.DonnerAJoueur));
                ihm.mettreAJourCartes(joueurCourant.getCartesEnMain());
                ihm.mettreAJourActions(peuxGagnerTresor(), getJoueurTuile(), peuxAssecher());
                break;
            case DEFAUSSER : 
                break;
            case TERMINER_TOUR:
                
                pileTresor.piocher(joueurCourant);
                pileTresor.piocher(joueurCourant);
                if (joueurCourant.mainContainMDE()) {
                    //joueurCourant.removeMDP(pileTresor);
                    ihm.setNivEau(grille.getNivEau() + 1);
                    ihm.mettreAJourNivEau();
                    grille.setNivEau(grille.getNivEau() + 1);
                }
                for (int i = 1; i <= carteAPiocher; i++) {
                    pileInondation.piocher(grille);
                }
                
                joueurAct = message.joueurAct;
                ihm.setJoueurCourant(joueurs.get(joueurAct - 1));
                joueurCourant = getJoueurCourant(joueurs.get(joueurAct - 1));
                ihm.mettreAJourActions(peuxGagnerTresor(), getJoueurTuile(), peuxAssecher());
                if(joueurCourant.mainIsFull()){ ihmNotif.mainPlein();}
                while (joueurCourant.mainIsFull()) {
                    ihm.defausserCarte();
                    ihm.mettreAJourCartes(joueurCourant.getCartesEnMain());
                    
                }
                ihm.mettreAJourCartes(joueurCourant.getCartesEnMain());
                ihm.mettreAJourTuiles(grille.getTuiles().values());
//                ihm.setNivEau(8);
//                ihm.mettreAJourNivEau();
                break;
        }
        
    }
    
    public boolean peuxAssecher() {
        ArrayList<Coordonnees> c = new ArrayList<>();
        if (joueurCourant.getTuile().getEtat().equalsIgnoreCase("Innondée")) {
            c.add(joueurCourant.getTuile().getCoordonnee());
        }
        
        if (joueurCourant.getFonction().equalsIgnoreCase("Explorateur")) {
            for (Tuile t : grille.getTuilesVoisinesAvecDiagonal(joueurCourant.getTuile()).values()) {
                if (t.getEtat().equalsIgnoreCase("Innondée")) {
                    c.add(t.getCoordonnee());
                }
            }
            joueurCourant.setActions(joueurCourant.getActions() - 1);
        } else if (joueurCourant.getFonction().equalsIgnoreCase("Ingénieur")) {
            for (Tuile t : grille.getTuilesVoisines(joueurCourant.getTuile()).values()) {
                if (t.getEtat().equalsIgnoreCase("Innondée")) {
                    c.add(t.getCoordonnee());
                }
            }
            joueurCourant.setActions(joueurCourant.getActions() - 1);
        } else {
            for (Tuile t : grille.getTuilesVoisines(joueurCourant.getTuile()).values()) {
                if (t.getEtat().equalsIgnoreCase("Innondée")) {
                    c.add(t.getCoordonnee());
                }
            }
            joueurCourant.setActions(joueurCourant.getActions() - 1);
            
        }
        return !c.isEmpty();
    }
    
    public ArrayList<String> getJoueurTuile() {
        ArrayList<String> joueur = new ArrayList<>();
        for (Aventurier j : joueurCourant.getTuile().getAventuriers()) {
            if (!j.getNom().equalsIgnoreCase(joueurCourant.getNom())) {
                joueur.add(j.getNom());
            }
        }
        return joueur;
    }
    
    public void InitialiserAventurier() {
        
    }
    
    public CarteTresor getCarte(int num) {
        return joueurCourant.getCartesEnMain().get(num);
        
    }
    
    public Aventurier getJoueur(String joueur) {
        
        for (Aventurier a : aventuriers) {
            if (a.getNom().equalsIgnoreCase(joueur)) {
                return a;
            }
        }
        return null;
    }
    
    public boolean peuxGagnerTresor() {
        int nbCristal = 0;
        int nbPierre = 0;
        int nbCalice = 0;
        int nbStatue = 0;
        if (!joueurCourant.getCartesEnMain().isEmpty()) {
            for (CarteTresor ct : joueurCourant.getCartesEnMain()) {
                if (ct.getFonction().equalsIgnoreCase("Pierre")) {
                    nbPierre++;
                }
                if (ct.getFonction().equalsIgnoreCase("Cristal")) {
                    nbCristal++;
                }
                if (ct.getFonction().equalsIgnoreCase("Statue")) {
                    nbStatue++;
                }
                if (ct.getFonction().equalsIgnoreCase("Calice")) {
                    nbCalice++;
                }
            }
            if (joueurCourant.getTuile().getType() != null && joueurCourant.getTuile().getType().equalsIgnoreCase("Pierre") && nbPierre >= 4) {
                return true;
            } else if (joueurCourant.getTuile().getType() != null && joueurCourant.getTuile().getType().equalsIgnoreCase("Cristal") && nbCristal >= 4) {
                return true;
            } else if (joueurCourant.getTuile().getType() != null && joueurCourant.getTuile().getType().equalsIgnoreCase("Calice") && nbCalice >= 4) {
                return true;
            } else if (joueurCourant.getTuile().getType() != null && joueurCourant.getTuile().getType().equalsIgnoreCase("Statue") && nbStatue >= 4) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    public Aventurier getJoueurCourant(String nomJoueur) {
        for (Aventurier a : aventuriers) {
            if (a.getNom().equalsIgnoreCase(nomJoueur)) {
                return a;
            }
        }
        return null;
    }
    
    private void deplacement(Coordonnees c) {
        for (Tuile t : grille.getTuiles().values()) {
            if (t.getCoordonnee().afficherCoord().equalsIgnoreCase(c.afficherCoord())) {
                
                joueurCourant.deplacer(t);
            }
        }
        
    }
    
    private void assechement(Coordonnees c) {
        for (Tuile t : grille.getTuiles().values()) {
            if (t.getCoordonnee().afficherCoord().equalsIgnoreCase(c.afficherCoord())) {
                joueurCourant.assecher(t);
                t.setEtat(0);
            }
        }
    }
    
    public void tuilesVoisinesDeplacement(Aventurier A) {
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
        // for (Coordonnees co : c ){System.out.println(co.afficherCoord());}
        ihm.setTuilesDispo(c);
        
    }
    
    public void tuilesVoisinesAssechement(Aventurier A) {
        ArrayList<Coordonnees> c = new ArrayList<>();
        if (A.getTuile().getEtat().equalsIgnoreCase("Innondée")) {
            c.add(A.getTuile().getCoordonnee());
        }
        
        if (A.getFonction().equalsIgnoreCase("Explorateur")) {
            for (Tuile t : grille.getTuilesVoisinesAvecDiagonal(A.getTuile()).values()) {
                if (t.getEtat().equalsIgnoreCase("Innondée")) {
                    c.add(t.getCoordonnee());
                }
            }
            A.setActions(A.getActions() - 1);
        } else if (A.getFonction().equalsIgnoreCase("Ingénieur")) {
            for (Tuile t : grille.getTuilesVoisines(A.getTuile()).values()) {
                if (t.getEtat().equalsIgnoreCase("Innondée")) {
                    c.add(t.getCoordonnee());
                }
            }
            A.setActions(A.getActions() - 1);
        } else {
            for (Tuile t : grille.getTuilesVoisines(A.getTuile()).values()) {
                if (t.getEtat().equalsIgnoreCase("Innondée")) {
                    c.add(t.getCoordonnee());
                }
            }
            A.setActions(A.getActions() - 1);
            
        }
        
        ihm.setTuilesDispo(c);
        
    }
    
    public Controleur() {
       
        ihmInit = new VueInitialisation();
        ihmInit.addObservateur(this);
        ihmInit.afficher();
        ihmNotif = new VueNotifications();
        ihmNotif.addObservateur(this);
        ihm = new VueAventurier();
        ihm.addObservateur(this);
        ihm.afficher();
        //tcommencerPartie();
        Initialisation();
    }
    
    public void afficherCarte(ArrayList<CarteTresor> c) {
        for (CarteTresor CT : c) {
            System.out.println("\t - " + CT.getFonction());
        }
    }
    
    public void Initialisation() {
        //********************************Initialisation Gille*********************************//
        int l = 0;// ligne
        int c = 0;//colonne
        int niv = 1;//demander aux joueurs
        grille = new Grille(niv);
        for (int i = 0; i < 36; i++) {// Creation de la Grille
            Coordonnees C = new Coordonnees(c, l);
            
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
            } else if (c == 2 && l == 3) {
                Heliport tuile = new Heliport(C);
                grille.addTuile(tuile);
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
        c = 0;
        l = 0;
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
        Coordonnees C = new Coordonnees(1, 1);
        Coordonnees C2 = new Coordonnees(4, 1);
        Coordonnees C3 = new Coordonnees(1, 4);
        Coordonnees C4 = new Coordonnees(4, 4);
        Coordonnees C5 = new Coordonnees(2, 2);
        Coordonnees C6 = new Coordonnees(3, 3);
        
        J1 = new Ingenieur(nom1, grille.getTuiles().get(C));
        J2 = new Explorateur(nom2, grille.getTuiles().get(C2));
        J3 = new Pilote(nom3, grille.getTuiles().get(C3));
        J4 = new Plongeur(nom4, grille.getTuiles().get(C4));
        J5 = new Navigateur("Rémi", grille.getTuiles().get(C5));
        J6 = new Messager("ilias", grille.getTuiles().get(C6));
        
        aventuriers.add(J2);
        aventuriers.add(J5);
        aventuriers.add(J3);
        aventuriers.add(J1);
        aventuriers.add(J4);

//        J1.addCarte(new CarteDeTresor("Calice"));
//        J1.addCarte(new CarteDeTresor("Calice"));
//        J1.addCarte(new CarteDeTresor("Calice"));
//        J1.addCarte(new CarteDeTresor("Calice"));
//
//        J2.addCarte(new CarteDeTresor("Statue"));
//        J2.addCarte(new CarteDeTresor("Statue"));
//        J2.addCarte(new CarteDeTresor("Statue"));
//        J2.addCarte(new CarteDeTresor("Statue"));
//
//        J3.addCarte(new CarteDeTresor("Pierre"));
//        J3.addCarte(new CarteDeTresor("Pierre"));
//        J3.addCarte(new CarteDeTresor("Pierre"));
//        J3.addCarte(new CarteDeTresor("Pierre"));
//
//        J4.addCarte(new CarteDeTresor("Cristal"));
//        J4.addCarte(new CarteDeTresor("Cristal"));
//        J4.addCarte(new CarteDeTresor("Cristal"));
//        J4.addCarte(new CarteDeTresor("Cristal"));
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
