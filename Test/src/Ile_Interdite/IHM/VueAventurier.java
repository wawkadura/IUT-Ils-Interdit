/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile_Interdite.IHM;

import Ile_Interdite.Grille;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author peyrinfl
 */
public class VueAventurier extends Observe {

    private JFrame fenetre;
    private int nbJoueurs;

    public VueAventurier() {
        fenetre = new JFrame("Ile Interdite");
        JPanel milieuPanel = new JPanel(new GridLayout(6, 6));
        JPanel joueurGauche = new JPanel();
        JPanel joueurHaut = new JPanel();
        JPanel joueurDroit = new JPanel();
        JPanel joueurBas = new JPanel();

        JLabel joueurG = new JLabel("Walid");
        JLabel joueurH = new JLabel("César");
        JLabel joueurD = new JLabel("Amine");
        JLabel joueurB = new JLabel("Florent");
        
        joueurGauche.add(joueurG);
        joueurHaut.add(joueurH);
        joueurDroit.add(joueurD);
        joueurBas.add(joueurB);
        
        int l = 0;// ligne
        int c = 0;//colonne
        for (int i = 0; i < 36; i++) {

            if ((c == 0 && l == 0) || (c == 1 && l == 0) || (c == 0 && l == 1)
                    || (c == 4 && l == 0) || (c == 5 && l == 0) || (c == 5 && l == 1)
                    || (c == 0 && l == 4) || (c == 0 && l == 5) || (c == 1 && l == 5)
                    || (c == 5 && l == 4) || (c == 4 && l == 5) || (c == 5 && l == 5)) {
                JButton tuile = new JButton();
                tuile.setEnabled(false);
                tuile.setBackground(Color.PINK);
                milieuPanel.add(tuile);
            } else {
                JButton tuile = new JButton();
                milieuPanel.add(tuile);
            }

            c++;

            if (c == 6) {
                c = 0;
                l++;
            }
        }
        
        nbJoueurs = 4;
        
        fenetre.add(milieuPanel, BorderLayout.CENTER);
        if (nbJoueurs == 4 ) {
            fenetre.add(joueurGauche, BorderLayout.SOUTH);
            fenetre.add(joueurHaut, BorderLayout.SOUTH);
            fenetre.add(joueurDroit, BorderLayout.SOUTH);
            fenetre.add(joueurBas, BorderLayout.SOUTH);
        }
        if (nbJoueurs == 3 ) {
            fenetre.add(joueurGauche, BorderLayout.SOUTH);
            fenetre.add(joueurDroit, BorderLayout.SOUTH);
            fenetre.add(joueurBas, BorderLayout.SOUTH);
        }
        if (nbJoueurs == 2 ) {
            fenetre.add(joueurGauche, BorderLayout.SOUTH);
            fenetre.add(joueurDroit, BorderLayout.SOUTH);
        }
        

    }

    public void afficher() {
        //permet d'afficher la fenetre du jeu
        fenetre.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(1920, 1080);
        fenetre.setVisible(true);
    }
}
