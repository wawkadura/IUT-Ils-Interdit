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
import java.util.TreeMap;

public class Grille {

    private int nivEau;
    private TreeMap<Coordonnees, Tuile> tuiles = new TreeMap<>();

    public Grille(int niv) {
        this.nivEau = niv;
    }

    public void addTuile(Tuile tuile) {
        this.tuiles.put(tuile.getCoordonnee(), tuile);
    }

    public TreeMap<Coordonnees, Tuile> getTuiles() {
        return tuiles;
    }

    public TreeMap<Coordonnees, Tuile> getTuilesVoisinesAvecDiagonal(Tuile tuile) {
        TreeMap<Coordonnees, Tuile> tuilesVoisines = new TreeMap<Coordonnees, Tuile>();

        Coordonnees TuileG = new Coordonnees(tuile.getCoordonnee().getX() - 1, tuile.getCoordonnee().getY()); //gauche
        Coordonnees TuileHG = new Coordonnees(tuile.getCoordonnee().getX() - 1, tuile.getCoordonnee().getY() - 1); // en haut a gauche
        Coordonnees TuileH = new Coordonnees(tuile.getCoordonnee().getX(), tuile.getCoordonnee().getY() - 1); //haut
        Coordonnees TuileHD = new Coordonnees(tuile.getCoordonnee().getX() + 1, tuile.getCoordonnee().getY() - 1); //en haut a droite
        Coordonnees TuileD = new Coordonnees(tuile.getCoordonnee().getX() + 1, tuile.getCoordonnee().getY()); //droite
        Coordonnees TuileBD = new Coordonnees(tuile.getCoordonnee().getX() + 1, tuile.getCoordonnee().getY() + 1); // en bas a droite
        Coordonnees TuileB = new Coordonnees(tuile.getCoordonnee().getX(), tuile.getCoordonnee().getY() + 1); //bas
        Coordonnees TuileBG = new Coordonnees(tuile.getCoordonnee().getX() - 1, tuile.getCoordonnee().getY() + 1); //en bas a gauche

        if (this.getTuiles().get(TuileG) != null) {
            tuilesVoisines.put(TuileG, this.getTuiles().get(TuileG));
        }
        if (this.getTuiles().get(TuileHG) != null) {
            tuilesVoisines.put(TuileHG, this.getTuiles().get(TuileHG));
        }
        if (this.getTuiles().get(TuileH) != null) {
            tuilesVoisines.put(TuileH, this.getTuiles().get(TuileH));
        }
        if (this.getTuiles().get(TuileHD) != null) {
            tuilesVoisines.put(TuileHD, this.getTuiles().get(TuileHD));
        }
        if (this.getTuiles().get(TuileD) != null) {
            tuilesVoisines.put(TuileD, this.getTuiles().get(TuileD));
        }
        if (this.getTuiles().get(TuileBD) != null) {
            tuilesVoisines.put(TuileBD, this.getTuiles().get(TuileBD));
        }
        if (this.getTuiles().get(TuileB) != null) {
            tuilesVoisines.put(TuileB, this.getTuiles().get(TuileB));
        }
        if (this.getTuiles().get(TuileBG) != null) {
            tuilesVoisines.put(TuileBG, this.getTuiles().get(TuileBG));
        }

        return tuilesVoisines;

    }

    public TreeMap<Coordonnees, Tuile> getTuilesVoisines(Tuile tuile) {
        TreeMap<Coordonnees, Tuile> tuilesVoisines = new TreeMap<Coordonnees, Tuile>();

        Coordonnees TuileG = new Coordonnees(tuile.getCoordonnee().getX() - 1, tuile.getCoordonnee().getY()); //gauche
        Coordonnees TuileH = new Coordonnees(tuile.getCoordonnee().getX(), tuile.getCoordonnee().getY() - 1); //haut
        Coordonnees TuileD = new Coordonnees(tuile.getCoordonnee().getX() + 1, tuile.getCoordonnee().getY()); //droite
        Coordonnees TuileB = new Coordonnees(tuile.getCoordonnee().getX(), tuile.getCoordonnee().getY() + 1); //bas

        if (this.getTuiles().get(TuileG) != null) {
            tuilesVoisines.put(TuileG, this.getTuiles().get(TuileG));
        }
        if (this.getTuiles().get(TuileH) != null) {
            tuilesVoisines.put(TuileH, this.getTuiles().get(TuileH));
        }
        if (this.getTuiles().get(TuileD) != null) {
            tuilesVoisines.put(TuileD, this.getTuiles().get(TuileD));
        }
        if (this.getTuiles().get(TuileB) != null) {
            tuilesVoisines.put(TuileB, this.getTuiles().get(TuileB));
        }
        return tuilesVoisines;

    }

    public void AfficherGrille() {
        System.out.println("");
        System.out.println("///////////////////////Grille///////////////////////////// ");
        for (Tuile T : this.getTuiles().values()) {
            System.out.print("Tuile" + T.getCoordonnee().afficherCoord());
            System.out.print("Contenant : ");
            if (T.getAventuriers()==null){System.out.println("personne"); }
            for (Aventurier A : T.getAventuriers()) {
                
                System.out.print(A.getNom()+"  ");
            }
            System.out.println(" (Etat " + T.getEtat()+")");
            if(T.getType() != null) {
            System.out.print("Trésor contenu : " + T.getType());
            }

        }
        System.out.println("///////////////////////Grille///////////////////////////// ");
        System.out.println("");
    }
}
