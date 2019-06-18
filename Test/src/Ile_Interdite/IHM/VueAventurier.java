/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile_Interdite.IHM;

import Ile_Interdite.Controleur;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author watrinc
 */
public class VueAventurier extends Observe {

    private JFrame fenetre;
    private int nbJoueur;
    private JPanel joueurBas;
    private JLabel joueur1, joueur2, joueur3, joueur4, caseVide;
    private Controleur controleur;
    
    public VueAventurier() {
        fenetre = new JFrame("Ile Interdite");
        
        ////////////////////////////////////////////////////////////JOUEURS/////////////////////////////////////////////////////////////////////////
        joueurBas = new JPanel(new GridLayout(1, 5));

        caseVide = new JLabel(" ");
        joueur1 = new JLabel("Walid");
        joueur2 = new JLabel("César");
        joueur3 = new JLabel("Amine");
        joueur4 = new JLabel("Florent");

        fenetre.add(joueurBas, BorderLayout.SOUTH);
        ////////////////////////////////////////////////////////////JOUEURS/////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////ACTIONS/////////////////////////////////////////////////////////////////////////
        JPanel actionGauche = new JPanel(new GridLayout(5, 1));

        JButton seDeplacer = new JButton("Se déplacer");
        JButton assecher = new JButton("Assécher");
        JButton donnerTresor = new JButton("Donner une carte Trésor");
        JButton gagnerTresor = new JButton("Gagner un Trésor");
        JButton compSpe = new JButton("Compétence spéciale");

        actionGauche.add(seDeplacer);
        actionGauche.add(assecher);
        actionGauche.add(donnerTresor);
        actionGauche.add(gagnerTresor);
        actionGauche.add(compSpe);

        fenetre.add(actionGauche, BorderLayout.WEST);
        ////////////////////////////////////////////////////////////ACTIONS/////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////CARTES///////////////////////////////////////////////////////////////////////////
        JPanel carteHaut = new JPanel(new GridLayout(2, 5));

        for (int i = 0; i < 2; i++) {
            JLabel espace = new JLabel("");
            carteHaut.add(espace);
        }
        JLabel cartesJoueur = new JLabel("Cartes joueur courant : ");
        carteHaut.add(cartesJoueur);
        for (int i = 0; i < 2; i++) {
            JLabel espace = new JLabel("");
            carteHaut.add(espace);
        }
        for (int i = 0; i < 5; i++) {
            JButton carteJoueur = new JButton("Carte");
            carteHaut.add(carteJoueur);
        }

        fenetre.add(carteHaut, BorderLayout.NORTH);
        ////////////////////////////////////////////////////////////CARTES///////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////NIVEAU EAU///////////////////////////////////////////////////////////////////////
        JPanel monteeEauDroit = new JPanel(new GridLayout(11, 1));

        JLabel monteeEau = new JLabel("Niveau d'eau : ");
        monteeEauDroit.add(monteeEau);

        for (int i = 10; i >= 1; i--) {
            if (i == 1 || i == 2) {
                JButton eau = new JButton();
                eau.setText("Niveau " + i + "   Piocher 2 cartes");
                eau.setBackground(Color.CYAN);
                eau.setEnabled(false);
                monteeEauDroit.add(eau);
            } else if (i == 3 || i == 4 || i == 5) {
                JButton eau = new JButton();
                eau.setText("Niveau " + i + "   Piocher 3 cartes");
                eau.setBackground(Color.BLUE);
                eau.setEnabled(false);
                monteeEauDroit.add(eau);
            } else if (i == 3 || i == 4 || i == 5) {
                JButton eau = new JButton();
                eau.setText("Niveau " + i + "   Piocher 3 cartes");
                eau.setEnabled(false);
                monteeEauDroit.add(eau);
            } else if (i == 6 || i == 7) {
                JButton eau = new JButton();
                eau.setText("Niveau " + i + "   Piocher 4 cartes");
                eau.setEnabled(false);
                monteeEauDroit.add(eau);
            } else if (i == 8 || i == 9) {
                JButton eau = new JButton();
                eau.setText("Niveau " + i + "   Piocher 5 cartes");
                eau.setEnabled(false);
                monteeEauDroit.add(eau);
            } else if (i == 10) {
                JButton eau = new JButton();
                eau.setText("Niveau " + i + "   Mort");
                eau.setEnabled(false);
                monteeEauDroit.add(eau);
            }
        }

        fenetre.add(monteeEauDroit, BorderLayout.EAST);
        ////////////////////////////////////////////////////////////NIVEAU EAU/////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////GRILLE/////////////////////////////////////////////////////////////////////////
        JPanel grilleMilieu = new JPanel(new GridLayout(6, 6));

        int l = 0;// ligne
        int c = 0;//colonne
        for (int i = 0; i < 36; i++) {

            if ((c == 0 && l == 0) || (c == 1 && l == 0) || (c == 0 && l == 1)
                    || (c == 4 && l == 0) || (c == 5 && l == 0) || (c == 5 && l == 1)
                    || (c == 0 && l == 4) || (c == 0 && l == 5) || (c == 1 && l == 5)
                    || (c == 5 && l == 4) || (c == 4 && l == 5) || (c == 5 && l == 5)) {
                JButton tuile = new JButton();
                tuile.setEnabled(false);
                tuile.setBackground(Color.WHITE);
                grilleMilieu.add(tuile);
            } else {
                JButton tuile = new JButton();
                grilleMilieu.add(tuile);
            }

            c++;

            if (c == 6) {
                c = 0;
                l++;
            }
        }

        fenetre.add(grilleMilieu, BorderLayout.CENTER);
        ////////////////////////////////////////////////////////////GRILLE/////////////////////////////////////////////////////////////////////////
    }

    public void afficher() {
        //permet d'afficher la fenetre du jeu
        fenetre.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(1920, 1080);
        fenetre.setVisible(false);
        
    }

    public void setNbJoueurs(int nbJoueurs) {
            nbJoueur = nbJoueurs;
            if (nbJoueur == 4) {
            joueurBas.add(caseVide);
            joueurBas.add(joueur1);
            joueurBas.add(joueur2);
            joueurBas.add(joueur3);
            joueurBas.add(joueur4);
        } else if (nbJoueur == 3) {
            joueurBas.add(caseVide);
            joueurBas.add(joueur1);
            joueurBas.add(joueur2);
            joueurBas.add(joueur3);
        } else if (nbJoueur == 2) {
            joueurBas.add(caseVide);
            joueurBas.add(joueur1);
            joueurBas.add(joueur2);
        }
            fenetre.setVisible(true);
    }
    
}
