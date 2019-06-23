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
import Ile_Interdite.Tuile;
import Ile_Interdite.cartes.CarteTresor;
import Ile_Interdite.cartes.PileTresor;
import java.util.ArrayList;

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

    public void deplacer(Tuile tuile) {
        setTuile(tuile);

    }

    public void assecher(Tuile tuile) {
        tuile.setEtat(0);
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

    public void gagnerTresor(PileTresor pilet) {
        if (tuile.getType().equalsIgnoreCase("Pierre")) {
            tresors.setPierre(true);
            ArrayList<CarteTresor> cartesRemove = new ArrayList<>();
            for (CarteTresor ct : cartesEnMain) {
                if (ct.getFonction().equalsIgnoreCase("Pierre")) {
                    cartesRemove.add(ct);
                }
            }
            for (CarteTresor CT : cartesRemove) {
                pilet.Defausser(CT, this);
            }
        }
        if (tuile.getType().equalsIgnoreCase("Cristal")) {
            tresors.setCristal(true);
            ArrayList<CarteTresor> cartesRemove = new ArrayList<>();
            for (CarteTresor ct : cartesEnMain) {
                if (ct.getFonction().equalsIgnoreCase("Cristal")) {
                    cartesRemove.add(ct);
                }
            }
            for (CarteTresor CT : cartesRemove) {
                pilet.Defausser(CT, this);
            }
        }
        if (tuile.getType().equalsIgnoreCase("Calice")) {
            tresors.setCalice(true);
            ArrayList<CarteTresor> cartesRemove = new ArrayList<>();

            for (CarteTresor ct : cartesEnMain) {
                if (ct.getFonction().equalsIgnoreCase("Calice")) {
                    cartesRemove.add(ct);
                }
            }
            for (CarteTresor CT : cartesRemove) {
                pilet.Defausser(CT, this);
            }
        }
        if (tuile.getType().equalsIgnoreCase("Statue")) {
            tresors.setStatue(true);
            ArrayList<CarteTresor> cartesRemove = new ArrayList<>();
            for (CarteTresor ct : cartesEnMain) {
                if (ct.getFonction().equalsIgnoreCase("Statue")) {
                    cartesRemove.add(ct);
                }
            }
            for (CarteTresor CT : cartesRemove) {
                pilet.Defausser(CT, this);
            }
        }
    }

    public boolean isTourTerminer() {
        return terminer;
    }

    public String getFonction() {
        return null;
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

    public boolean CompetanceUtiliser() { 
        return false;
    }

    public void setUtilise(boolean utilise) {
    }

    public boolean mainContainMDE() {
        for (CarteTresor ct : cartesEnMain) {
            if (ct.getFonction().equals("Montée des Eaux")) {
                return true;
            }
        }
        return false;
    }

    public void removeMDE(PileTresor pt) {
        ArrayList<CarteTresor> mde = new ArrayList<>();
        for (CarteTresor ct : cartesEnMain) {
            if (ct.getFonction().equals("Montée des Eaux")) {
                mde.add(ct);
            }
        }
        for (CarteTresor ct2 : mde) {
            pt.Defausser(ct2, this);
        }

    }

}
