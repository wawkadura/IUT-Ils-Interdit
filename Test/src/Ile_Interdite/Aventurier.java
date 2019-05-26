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
        Boolean droite = false, haut = false, gauche = false, bas = false; // pour l'affectation aprés le choix de l'utilisateur 
        int num = 0; // nombre de tuiles disponible
        Coordonnees TuileGauche = new Coordonnees(this.tuile.getCoordonnee().getX() - 1, this.tuile.getCoordonnee().getY()); //gauche
        Coordonnees TuileDroite = new Coordonnees(this.tuile.getCoordonnee().getX() + 1, this.tuile.getCoordonnee().getY()); //droite
        Coordonnees TuileEnHaut = new Coordonnees(this.tuile.getCoordonnee().getX() - 1, this.tuile.getCoordonnee().getY() - 1); //haut
        Coordonnees TuileEnBas = new Coordonnees(this.tuile.getCoordonnee().getX(), this.tuile.getCoordonnee().getY() + 1); //bas
        if (g.getTuiles().get(TuileGauche) != null) {
            if (g.getTuiles().get(TuileGauche).getEtat() != 2) {
                System.out.println(num + " - " + "Tuile de droite disponible de coordonnees : " + TuileDroite.afficherCoord());
                num++;
                gauche = true;
            }
        }
        if (g.getTuiles().get(TuileDroite) != null) {
            if (g.getTuiles().get(TuileDroite).getEtat() != 2) {
                System.out.println(num + " - " + "Tuile de droite disponible de coordonnees : " + TuileDroite.afficherCoord());
                num++;
                droite = true;
            }
        }
        if (g.getTuiles().get(TuileEnHaut) != null) {
            if (g.getTuiles().get(TuileEnHaut).getEtat() != 2) {
                System.out.println(num + " - " + "Tuile au dessus disponible de coordonnees : " + TuileEnHaut.afficherCoord());
                num++;
                haut = true;
            }
        }
        if (g.getTuiles().get(TuileEnBas) != null) {
            if (g.getTuiles().get(TuileEnBas).getEtat() != 2) {
                System.out.println(num + " - " + "Tuile en dessous disponible de coordonnees : " + TuileEnBas.afficherCoord());
                num++;
                bas = true;
            }
        }
        System.out.println("Sur quelle Tuile de 1 à " + num + " voulez vous vous deplacer ? (0 pour annuler) : ");
        Scanner scn = new Scanner(System.in);

        int dir = scn.nextInt();
        while (dir < 0 || dir > num) {
            System.out.println("Veuillez tapper une tuile Disponible de 1 à " + num + " (0 pour annuler):");
        }
        if (dir != 0) {
            if (gauche && dir == 1) {
                this.tuile = g.getTuiles().get(TuileGauche);
            }
            if (droite && !gauche && dir == 1
                    || droite && dir == 2) {
                this.tuile = g.getTuiles().get(TuileDroite);
            }
            if (haut && !droite && !gauche && dir == 1
                    || haut && (!droite || !gauche) && dir == 2
                    || haut && dir == 3) {
                this.tuile = g.getTuiles().get(TuileEnHaut);
            }
            if (bas && dir == 4
                    || bas && (!haut || !droite || !gauche) && dir == 3
                    || bas && ((!haut && !gauche) || (!haut && !droite) || (!gauche && !droite)) && dir == 2
                    || bas && !haut && !gauche && !droite && dir == 1) {
                this.tuile = g.getTuiles().get(TuileEnBas);
                g.getTuiles().get(TuileEnBas).addAventurier(this);
                g.getTuiles().get(this.tuile).suppAventurier(this);
                this.actions=this.actions -1;
            }
        } else {
            System.out.println("Annulation du deplacement...");
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
