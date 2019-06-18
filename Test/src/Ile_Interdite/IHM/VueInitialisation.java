/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile_Interdite.IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author watrinc
 */
public class VueInitialisation extends Observe {

    private JFrame fenetre;
    private VueAventurier ihm;
    private int no_joueurs;
    public JTextField nom1, nom2, nom3, nom4;
    private final Font policeTitre = new Font(Font.DIALOG, Font.BOLD, 80);
    private final Font policeLabel = new Font(Font.MONOSPACED, Font.BOLD, 30);
    private final Font diff = new Font(Font.MONOSPACED, Font.BOLD, 20);

    public VueInitialisation() {
        fenetre = new JFrame("Ile Interdite Initialisation");

        //////////////////////////////////////////////////////TITRE////////////////////////////////////////////////////////////
        JPanel panelTitre = new JPanel();

        JLabel titre = new JLabel("Ile Interdite");
        titre.setFont(policeTitre);
        panelTitre.add(titre);

        fenetre.add(panelTitre, BorderLayout.NORTH);
        //////////////////////////////////////////////////////TITRE//////////////////////////////////////////////////////////// 

        //////////////////////////////////////////////////////VALEURS//////////////////////////////////////////////////////////  
        JPanel panelValeurs = new JPanel(new GridLayout(4, 2));

        JLabel joueur1 = new JLabel("Joueur 1 : ");
        joueur1.setFont(policeLabel);
        joueur1.setForeground(Color.RED);
        JLabel joueur2 = new JLabel("Joueur 2 : ");
        joueur2.setFont(policeLabel);
        joueur2.setForeground(Color.BLUE);
        JLabel joueur3 = new JLabel("Joueur 3 : ");
        joueur3.setFont(policeLabel);
        joueur3.setForeground(Color.GREEN);
        JLabel joueur4 = new JLabel("Joueur 4 : ");
        joueur4.setFont(policeLabel);
        joueur4.setForeground(Color.MAGENTA);

        nom1 = new JTextField();
        nom1.setFont(policeLabel);
        nom2 = new JTextField();
        nom2.setFont(policeLabel);
        nom3 = new JTextField();
        nom3.setFont(policeLabel);
        nom4 = new JTextField();
        nom4.setFont(policeLabel);

        panelValeurs.add(joueur1);
        panelValeurs.add(nom1);
        panelValeurs.add(joueur2);
        panelValeurs.add(nom2);
        panelValeurs.add(joueur3);
        panelValeurs.add(nom3);
        panelValeurs.add(joueur4);
        panelValeurs.add(nom4);

        fenetre.add(panelValeurs);
        //////////////////////////////////////////////////////VALEURS//////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////LANCER////////////////////////////////////////////////////////////
        JPanel panelLancer = new JPanel(new GridLayout(3, 1));

        JLabel difficulte = new JLabel("Choix de la difficulté : ");
        difficulte.setFont(diff);
        JSlider choixDifficulte = new JSlider(SwingConstants.HORIZONTAL, 1, 4, 1);
        choixDifficulte.setPaintLabels(true);
        choixDifficulte.setPaintTicks(true);
        choixDifficulte.setMajorTickSpacing(1);
        choixDifficulte.setMinorTickSpacing(1);
        choixDifficulte.setFont(diff);
        choixDifficulte.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent arg0) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        JButton commencer = new JButton(" Commencer ");
        commencer.setFont(diff);
        commencer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            
                
                Message m = new Message();
                m.type = TypesMessages.DEMARRER_PARTIE;
                m.nbJoueurs = no_joueurs;
                notifierObservateur(m);
            }
        });

        panelLancer.add(difficulte);
        panelLancer.add(choixDifficulte);
        panelLancer.add(commencer);

        fenetre.add(panelLancer, BorderLayout.SOUTH);
        //////////////////////////////////////////////////////LANCER////////////////////////////////////////////////////////////
    }

    public void afficher() {
        //permet d'afficher la fenetre d'initialisation
        fenetre.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(800, 500);
        fenetre.setVisible(true);
    }

    public void demarrerJeu() {
        fenetre.setVisible(false);
    }

}
