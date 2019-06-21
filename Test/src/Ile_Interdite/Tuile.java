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
import java.util.ArrayList;

public class Tuile {

    private String nom;
    private Coordonnees C;
    private ArrayList<Aventurier> aventuriers = new ArrayList<>();
    private int etat = 0; //0 assecher , 1 innonder, 2 manquant

    public Tuile(Coordonnees C) {
        this.C = C;
    }

    public Coordonnees getCoordonnee() {
        return C;
    }

    public int getNumEtat() {
        return this.etat;
    }

    public String getEtat() {
        if (this.etat == 2) {
            return "Manquante";
        }
        if (this.etat == 1) {
            return "Innondée";
        } else {
            return "Assechée";
        }
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public void addAventurier(Aventurier A) {
        aventuriers.add(A);
    }

    public void suppAventurier(Aventurier A) {
        if (aventuriers.contains(A)) {
            aventuriers.remove(A);
        }
    }

    public ArrayList<Aventurier> getAventuriers() {
        return aventuriers;
    }

    public String getType() {
        return null;
    }

    public void augmenterEtat() {
        if (this.etat == 0) {
            this.setEtat(1);
        } else if (this.etat == 1) {
            this.setEtat(2);
        }
    }

}
