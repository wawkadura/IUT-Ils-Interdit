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
import java.util.Scanner;
import java.util.TreeMap;

public abstract class Aventurier {

    private String nom;
    private int actions = 3;
    private Tuile tuile;
    private ArrayList<CarteTrésor> cartesEnMain = new ArrayList<>();

    Aventurier(String nom, Tuile tuile) {
        this.setNom(nom);
        this.setTuile(tuile);
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
                if (!T.getEtat().equals("manquant")) {
                    numero++;
                    System.out.print(numero + " - Tuile disponible au coordonnees : " + T.getCoordonnee().afficherCoord());
                    System.out.print(" Contenant : ");
                    for (Aventurier A : T.getAventuriers()) {
                        System.out.print(A.getNom()+" ");
                    } 
                    System.out.println(" , Etat : "+ T.getEtat());
                    Choix.add(T);
                }
            }
        System.out.println("Sur quelle Tuile de 1 à " + numero + " voulez vous vous deplacer ? (0 pour annuler) : ");
        Scanner scn = new Scanner(System.in);

        int dir = scn.nextInt();
        while (dir < 0 || dir > numero) {
            System.out.print("Veuillez tapper une tuile Disponible de 1 à " + numero + " (0 pour annuler):");
        }

        if (dir == 0) {
            System.out.println("Annulation du deplacement...");
        } else {
            g.getTuiles().get(this.tuile.getCoordonnee()).suppAventurier(this);
            this.tuile = g.getTuiles().get(Choix.get(dir - 1).getCoordonnee());
            g.getTuiles().get(Choix.get(dir - 1).getCoordonnee()).addAventurier(this);
            this.actions = this.actions - 1;
        }

    }

    public void assecher(Tuile tuile) {

    }

    public void donner(CarteTrésor carte, Aventurier joueur) {

    }

    public void setTuile(Tuile tuile) {
        this.tuile = tuile;
    }

    public void gagnerTresor(Tuile tuile) {

    }

    public void tourTermine() {
        setActions(3);

    }

}
