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
import java.util.TreeMap;

public class Grille {

    private int nbPiocheInond;
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

        if (this.getTuiles().get(TuileG) != null && !this.getTuiles().get(TuileG).getEtat().equalsIgnoreCase("Manquante")) {
            tuilesVoisines.put(TuileG, this.getTuiles().get(TuileG));
        }
        if (this.getTuiles().get(TuileHG) != null && !this.getTuiles().get(TuileHG).getEtat().equalsIgnoreCase("Manquante")) {
            tuilesVoisines.put(TuileHG, this.getTuiles().get(TuileHG));
        }
        if (this.getTuiles().get(TuileH) != null && !this.getTuiles().get(TuileH).getEtat().equalsIgnoreCase("Manquante")) {
            tuilesVoisines.put(TuileH, this.getTuiles().get(TuileH));
        }
        if (this.getTuiles().get(TuileHD) != null && !this.getTuiles().get(TuileHD).getEtat().equalsIgnoreCase("Manquante")) {
            tuilesVoisines.put(TuileHD, this.getTuiles().get(TuileHD));
        }
        if (this.getTuiles().get(TuileD) != null && !this.getTuiles().get(TuileD).getEtat().equalsIgnoreCase("Manquante")) {
            tuilesVoisines.put(TuileD, this.getTuiles().get(TuileD));
        }
        if (this.getTuiles().get(TuileBD) != null && !this.getTuiles().get(TuileBD).getEtat().equalsIgnoreCase("Manquante")) {
            tuilesVoisines.put(TuileBD, this.getTuiles().get(TuileBD));
        }
        if (this.getTuiles().get(TuileB) != null && !this.getTuiles().get(TuileB).getEtat().equalsIgnoreCase("Manquante")) {
            tuilesVoisines.put(TuileB, this.getTuiles().get(TuileB));
        }
        if (this.getTuiles().get(TuileBG) != null && !this.getTuiles().get(TuileBG).getEtat().equalsIgnoreCase("Manquante")) {
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

        if (this.getTuiles().get(TuileG) != null && !this.getTuiles().get(TuileG).getEtat().equalsIgnoreCase("Manquante")) {
            tuilesVoisines.put(TuileG, this.getTuiles().get(TuileG));
        }
        if (this.getTuiles().get(TuileH) != null && !this.getTuiles().get(TuileH).getEtat().equalsIgnoreCase("Manquante")) {
            tuilesVoisines.put(TuileH, this.getTuiles().get(TuileH));
        }
        if (this.getTuiles().get(TuileD) != null && !this.getTuiles().get(TuileD).getEtat().equalsIgnoreCase("Manquante")) {
            tuilesVoisines.put(TuileD, this.getTuiles().get(TuileD));
        }
        if (this.getTuiles().get(TuileB) != null && !this.getTuiles().get(TuileB).getEtat().equalsIgnoreCase("Manquante")) {
            tuilesVoisines.put(TuileB, this.getTuiles().get(TuileB));
        }
        return tuilesVoisines;

    }

    public TreeMap<Coordonnees, Tuile> getTuilesVoisinesHelicoptere() {
        TreeMap<Coordonnees, Tuile> tuilesVoisines = new TreeMap<Coordonnees, Tuile>();
        for (Tuile t : tuiles.values()) {
            if (!t.getEtat().equalsIgnoreCase("Manquante")) {
                tuilesVoisines.put(t.getCoordonnee(), t);
            }
        }

        return tuilesVoisines;
    }

    public TreeMap<Coordonnees, Tuile> getTuilesVoisinesPlongeur(Tuile tuile) {
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
            if (T.getAventuriers() == null) {
                System.out.println("personne");
            }
            for (Aventurier A : T.getAventuriers()) {

                System.out.print(A.getNom() + "  ");
            }
            System.out.print(" (Etat " + T.getEtat() + ")");
            if (T.getType() != null) {
                if (T.getType().equals("Héliport")) {
                    System.out.print(" Case héliport");
                } else {
                    System.out.println("\u001B[33m" + " Trésor contenu : " + T.getType() + "\u001B[33m");
                }
            } else {
                System.out.println("");
            }

        }
        System.out.println("///////////////////////Grille///////////////////////////// ");
        System.out.println("");
    }

    public void setNivEau(int niv) {
        this.nivEau = niv;
        if (niv >= 0 && niv <= 1) {
            this.setNbPiocheInond(2);
        } else if (niv >= 2 && niv <= 4) {
            this.setNbPiocheInond(3);
        } else if (niv == 5 || niv == 6) {
            this.setNbPiocheInond(4);
        } else if (niv == 7 || niv == 8) {
            this.setNbPiocheInond(5);
        }
    }

    public void setNbPiocheInond(int nbPiocheInond) {
        this.nbPiocheInond = nbPiocheInond;
    }

    public int getNivEau() {
        return nivEau;
    }

    public boolean isSubmergee() {
        return (this.getNivEau() > 8);
    }

    public boolean tresorIsSubmergee() {
        int statue = 0;
        int cristal = 0;
        int pierre = 0;
        int calice = 0;
        for (Tuile t : tuiles.values()) {
            if (t.getType().equalsIgnoreCase("Statue") && t.getEtat().equalsIgnoreCase("Manquante")) {
                statue++;
            }
            if (t.getType().equalsIgnoreCase("Cristal") && t.getEtat().equalsIgnoreCase("Manquante")) {
                cristal++;
            }
            if (t.getType().equalsIgnoreCase("Pierre") && t.getEtat().equalsIgnoreCase("Manquante")) {
                pierre++;
            }
            if (t.getType().equalsIgnoreCase("Calice") && t.getEtat().equalsIgnoreCase("Manquante")) {
                calice++;
            }
        }
        return statue == 2 || cristal == 2 || pierre == 2 || calice == 2;
    }
}
