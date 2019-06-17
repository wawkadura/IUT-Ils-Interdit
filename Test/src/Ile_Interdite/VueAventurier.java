/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile_Interdite;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author peyrinfl
 */
public class VueAventurier extends Observe {

    private JFrame fenetre;
    private Grille grille;

    public VueAventurier() {
        fenetre = new JFrame("Ile Interdite");
        JPanel milieuPanel = new JPanel(new GridLayout(6,6));
        fenetre.add(milieuPanel, BorderLayout.CENTER);

        int l = 0;// ligne
        int c = 0;//colonne
        int niv = 1;//demander aux joueurs

        for (int i = 0; i < 36; i++) {// Creation de la Grille

            if (c == 2 && l == 0) {
                JButton tuile = new JButton("coup");
                milieuPanel.add(tuile);
            } //coup
            else if (c == 3 && l == 0) {
                JButton tuile = new JButton("feu");
                milieuPanel.add(tuile);
            }//feu
            else if (c == 0 && l == 2) {
                JButton tuile = new JButton("coup");
                milieuPanel.add(tuile);
            }//coup
            else if (c == 0 && l == 3) {
                JButton tuile = new JButton("lion");
                milieuPanel.add(tuile);
            }//lion
            else if (c == 5 && l == 2) {
                JButton tuile = new JButton("feu");
                milieuPanel.add(tuile);
            }//feu
            else if (c == 5 && l == 3) {
                JButton tuile = new JButton("oeuf");
                milieuPanel.add(tuile);
            }//oeuf
            else if (c == 2 && l == 5) {
                JButton tuile = new JButton("lion");
                milieuPanel.add(tuile);
            }//lion
            else if (c == 3 && l == 5) {
                JButton tuile = new JButton("oeuf");
                milieuPanel.add(tuile);
            }//oeuf
            else if (c == 2 && l == 2) { // tuile normal
                JButton tuile = new JButton();
                milieuPanel.add(tuile);
            } else if (c == 3 && l == 3) { // tuile normal
                JButton tuile = new JButton();
                milieuPanel.add(tuile);
            } else if (c == 2 && l == 3) { // tuile manquant
                JButton tuile = new JButton();
                milieuPanel.add(tuile);          
            } else if (c == 3 && l == 2) { // tuile manquant
                JButton tuile = new JButton();
                milieuPanel.add(tuile);
            } else if (c == 0 && l == 0 || c == 1 && l == 0 || c == 0 && l == 1
                    || c == 4 && l == 0 || c == 5 && l == 0 || c == 5 && l == 1
                    || c == 0 && l == 4 || c == 0 && l == 5 || c == 1 && l == 5
                    || c == 4 && l == 5 || c == 5 && l == 4 || c == 5 && l == 5) {
            } //tuile heliport a faire aleatoirement
            else {

                JButton tuile = new JButton();
                milieuPanel.add(tuile);
            }
            c++;
            if (c == 6) {
                c = 0;
                l++;
            }

        }

    }

    public void afficher() {
        //permet d'afficher la fenetre du jeu
        fenetre.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(1500, 900);
        fenetre.setVisible(true);
    }
}
