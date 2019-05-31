/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile_Interdite;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author peyrinfl
 */
public class Plongeur extends Aventurier {
    
    Plongeur(String nom, Tuile tuile) {
        super(nom, tuile);
    }
    
    @Override
    public void deplacer(Grille g) {
        System.out.println(this.getTuile().getCoordonnee().afficherCoord());
        TreeMap<Coordonnees, Tuile> tuilesVoisines = g.getTuilesVoisines(getTuile());
        ArrayList<Tuile> Choix = new ArrayList<>();
        int numero = 0;
        for (Tuile T : tuilesVoisines.values()) {
                    numero++;
                    System.out.print(numero + " - Tuile disponible au coordonnees : " + T.getCoordonnee().afficherCoord());
                    System.out.print(" Contenant : ");
                    for (Aventurier A : T.getAventuriers()) {
                        System.out.print(A.getNom()+" ");
                    } 
                    System.out.println(" , Etat : "+ T.getEtat());
                    Choix.add(T);
                
            }
        System.out.print("Sur quelle Tuile de 1 à " + numero + " voulez vous vous deplacer ? (0 pour annuler) : ");
        Scanner scn = new Scanner(System.in);
        int dir = scn.nextInt();
        while (dir < 0 || dir > numero) {
            System.out.print("Veuillez tapper une tuile Disponible de 1 à " + numero + " (0 pour annuler):");
            dir = scn.nextInt();
        }

        if (dir == 0) {
            System.out.println("Annulation du deplacement...");
        } else {
           // g.getTuiles().get(super.getTuile().getCoordonnee()).suppAventurier(this);
            super.setTuile(g.getTuiles().get(Choix.get(dir - 1).getCoordonnee()));
           // g.getTuiles().get(Choix.get(dir - 1).getCoordonnee()).addAventurier(this);
            super.setActions(super.getActions()-1);
        }

    }
    @Override
    public String getFonction() {
        return "plongeur";
    }
}
