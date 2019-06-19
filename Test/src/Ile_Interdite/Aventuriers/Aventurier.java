/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile_Interdite.Aventuriers;

/**
 *
 * @author peyrinfl
 */
import Ile_Interdite.Coordonnees;
import Ile_Interdite.Grille;
import Ile_Interdite.Tuile;
import Ile_Interdite.cartes.CarteTresor;
import Ile_Interdite.cartes.PileTresor;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public abstract class Aventurier {

    private String nom;
    private int actions = 3;
    private Tuile tuile;
    private ArrayList<CarteTresor> cartesEnMain = new ArrayList<>();
    private boolean terminer = false;
    protected static TresorsRecupere tresors = new TresorsRecupere();

    public Aventurier(String nom, Tuile tuile) {
        this.setNom(nom);
        this.tuile = tuile;
        tuile.addAventurier(this);
    }

    public String getNom() {
        return nom;
    }

    public Tuile getTuile() {
        return tuile;
    }

    public int getActions() {
        return actions;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setActions(int actions) {
        this.actions = actions;
    }

    public void deplacer(Grille g) {
        System.out.println(this.getTuile().getCoordonnee().afficherCoord());
        TreeMap<Coordonnees, Tuile> tuilesVoisines = g.getTuilesVoisines(getTuile());
        ArrayList<Tuile> Choix = new ArrayList<>();

        int numero = 0;
        for (Tuile T : tuilesVoisines.values()) {
            if (!T.getEtat().equals("Manquante")) {
                numero++;
                System.out.print(numero + " - Tuile disponible aux coordonnées : " + T.getCoordonnee().afficherCoord());
                System.out.print(" Contenant : ");
                for (Aventurier A : T.getAventuriers()) {
                    System.out.print(A.getNom() + " ");
                }
                System.out.println(" , Etat : " + T.getEtat());
                Choix.add(T);
            }
        }
        System.out.print("Sur quelle tuile de 1 à " + numero + " voulez-vous vous déplacer ? (0 pour annuler) : ");
        Scanner scn = new Scanner(System.in);

        int dir = scn.nextInt();
        while (dir < 0 || dir > numero) {
            System.out.print("Veuillez taper une tuile disponible de 1 à " + numero + " (0 pour annuler):");
            dir = scn.nextInt();
        }

        if (dir == 0) {
            System.out.println("Annulation du déplacement...");
        } else {
            // g.getTuiles().get(this.tuile.getCoordonnee()).suppAventurier(this);
            setTuile(g.getTuiles().get(Choix.get(dir - 1).getCoordonnee()));
            // g.getTuiles().get(Choix.get(dir - 1).getCoordonnee()).addAventurier(this);
            this.actions = this.actions - 1;
        }

    }

    public void assecher(Grille g) {

        System.out.println(this.getTuile().getCoordonnee().afficherCoord());
        TreeMap<Coordonnees, Tuile> tuilesVoisines = g.getTuilesVoisines(getTuile());
        ArrayList<Tuile> Choix = new ArrayList<>();
        tuilesVoisines.put(getTuile().getCoordonnee(), getTuile());
        int numero = 0;
        for (Tuile T : tuilesVoisines.values()) {
            if (!T.getEtat().equals("Manquante") && T.getEtat().equals("Innondée")) {
                numero++;
                System.out.print(numero + " - Tuile aux coordonnées : " + T.getCoordonnee().afficherCoord());
                System.out.print(" Contenant : ");
                for (Aventurier A : T.getAventuriers()) {
                    System.out.print(A.getNom() + " ");
                }
                System.out.println(" , Etat : " + T.getEtat());
                Choix.add(T);
            }

        }
        if (!Choix.isEmpty()) {
            System.out.print("Quelle tuile de 1 à " + numero + " voulez-vous assécher ? (0 pour annuler) : ");
            Scanner scn = new Scanner(System.in);

            int dir = scn.nextInt();
            while (dir < 0 || dir > numero) {
                System.out.print("Veuillez taper une tuile innonder de 1 à " + numero + " (0 pour annuler):");
                dir = scn.nextInt();
            }

            if (dir == 0) {
                System.out.println("Annulation de l'asséchement...");
            } else {
                // g.getTuiles().get(this.tuile.getCoordonnee()).suppAventurier(this);
                g.getTuiles().get(Choix.get(dir - 1).getCoordonnee()).setEtat(0);
                // g.getTuiles().get(Choix.get(dir - 1).getCoordonnee()).addAventurier(this);
                this.actions = this.actions - 1;
            }
        } else {
            System.out.println("Aucune tuile voisine peux être asséchée");
        }
    }

    public void donner(CarteTresor carte, Aventurier joueur) {
        joueur.addCarte(carte);
        removeCarte(carte);

    }

    public void removeCarte(CarteTresor carte) {
        cartesEnMain.remove(carte);

    }

    public void setTuile(Tuile tuile) {
        if (this.tuile.getAventuriers().contains(this)) {
            this.tuile.suppAventurier(this);
        }
        this.tuile = tuile;
        tuile.addAventurier(this);
    }

    public void gagnerTresor(Tuile tuile , PileTresor pilet) {
        if (tuile.getType().equalsIgnoreCase("Pierre")) {
            tresors.setPierre(true);
            ArrayList<CarteTresor> cartesRemove = new ArrayList<>();
            for (CarteTresor ct : cartesEnMain) {
                if (ct.getFonction().equalsIgnoreCase("Pierre")){
                    cartesRemove.add(ct);
                }
            }
            for (CarteTresor CT : cartesRemove){
                pilet.Defausser(CT, this);
            }
        }
        if (tuile.getType().equalsIgnoreCase("Cristal")) {
            tresors.setCristal(true);
            ArrayList<CarteTresor> cartesRemove = new ArrayList<>();
            for (CarteTresor ct : cartesEnMain) {
                if (ct.getFonction().equalsIgnoreCase("Cristal")){
                    cartesRemove.add(ct);
                }
            }
            for (CarteTresor CT : cartesRemove){
                pilet.Defausser(CT, this);
            }
        }
        if (tuile.getType().equalsIgnoreCase("Calice")) {
            tresors.setCalice(true);
            ArrayList<CarteTresor> cartesRemove = new ArrayList<>();
            
            for (CarteTresor ct : cartesEnMain) {
                if (ct.getFonction().equalsIgnoreCase("Calice")){
                    cartesRemove.add(ct);
                }
            }
            for (CarteTresor CT : cartesRemove){
                pilet.Defausser(CT, this);
            }
        }
        if (tuile.getType().equalsIgnoreCase("Statue")) {
            tresors.setStatue(true);
            ArrayList<CarteTresor> cartesRemove = new ArrayList<>();
            for (CarteTresor ct : cartesEnMain) {
                if (ct.getFonction().equalsIgnoreCase("Statue")){
                    cartesRemove.add(ct);
                }
            }
            for (CarteTresor CT : cartesRemove){
                pilet.Defausser(CT, this);
            }
        }
    }

    public boolean isTourTerminer() {
        return terminer;
    }

    public void tourTermine() {

        setTerminer(true);
        System.out.println("Tour de " + getNom() + " terminé ! ");
    }

    public void setTerminer(boolean tourTerminer) {
        this.terminer = tourTerminer;
    }

    public void Reset() {
        setActions(3);
        setTerminer(false);
    }

    public String getFonction() {
        return null;
    }

    public void faireDeplacer(Grille g, Aventurier A) {

    }

    public void addCarte(CarteTresor carte) {
        this.getCartesEnMain().add(carte);
    }

    public ArrayList<CarteTresor> getCartesEnMain() {
        return cartesEnMain;
    }

    public boolean mainIsFull() {
        return this.getCartesEnMain().size() > 5;
    }

    public boolean isCollected(Tuile tuile) {
        int nbCarte = 0;
        for (CarteTresor ct : getCartesEnMain()) {
            if (ct.getFonction().equalsIgnoreCase(tuile.getType())) {
                nbCarte++;
            }
        }
        return nbCarte >= 4;
    }

    public static TresorsRecupere getTresors() {
        return tresors;
    }

}
