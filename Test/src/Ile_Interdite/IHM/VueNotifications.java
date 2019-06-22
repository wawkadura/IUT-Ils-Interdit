/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile_Interdite.IHM;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author wawve
 */
public class VueNotifications extends Observe {

    private JFrame fenetre1;
    private JFrame fenetre2;
    private JFrame fenetre3;

    public void mainPlein() {
        fenetre1 = new JFrame("Attention");
        JPanel haut = new JPanel(new GridLayout(3, 1));
        JPanel bas = new JPanel();
        JLabel info1 = new JLabel("                            ATTENTION !");
        JLabel info2 = new JLabel("     Vous avez plus de 5 cartes en main ! ");
        JLabel info3 = new JLabel("         Veuillez défausser une carte");
        haut.add(info1);
        haut.add(info2);
        haut.add(info3);
        JButton ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Message m = new Message();
                m.type = TypesMessages.DEFAUSSER;
                notifierObservateur(m);
                fenetre1.setVisible(false);

            }
        });
        bas.add(ok);
        fenetre1.add(haut, BorderLayout.CENTER);
        fenetre1.add(bas, BorderLayout.SOUTH);
        afficher1();
    }

    public void vueGagner() {
        fenetre2 = new JFrame("Partie gagnée");
        JPanel haut = new JPanel(new GridLayout(3, 1));
        JPanel bas = new JPanel();
        JLabel info1 = new JLabel("                       FÉLICITATION !");
        JLabel info2 = new JLabel("     Vous venez de remporter la partie ! ");
        haut.add(info1);
        haut.add(info2);
        JButton quitter = new JButton("QUITTER");
        quitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Message m = new Message();
                m.type = TypesMessages.QUITTER;
                notifierObservateur(m);
                fenetre2.setVisible(false);
            }
        });
        JButton recommencer = new JButton("RECOMMENCER");
        recommencer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Message m = new Message();
                m.type = TypesMessages.RECOMMENCER;
                notifierObservateur(m);
                fenetre2.setVisible(false);
            }
        });
        bas.add(quitter);
        bas.add(recommencer);
        fenetre2.add(haut, BorderLayout.CENTER);
        fenetre2.add(bas, BorderLayout.SOUTH);
        afficher2();
    }

    public void vuePerdu() {
        fenetre3 = new JFrame("Partie perdu");
        JPanel haut = new JPanel(new GridLayout(3, 1));
        JPanel bas = new JPanel();
        JLabel info1 = new JLabel("                       DOMMAGE !");
        JLabel info2 = new JLabel("         Vous avez perdu la partie ! ");
        haut.add(info1);
        haut.add(info2);
        JButton quitter = new JButton("QUITTER");
        quitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Message m = new Message();
                m.type = TypesMessages.QUITTER;
                notifierObservateur(m);
                fenetre3.setVisible(false);
            }
        });
        JButton recommencer = new JButton("RECOMMENCER");
        recommencer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Message m = new Message();
                m.type = TypesMessages.RECOMMENCER;
                notifierObservateur(m);
                fenetre3.setVisible(false);
            }
        });
        bas.add(quitter);
        bas.add(recommencer);
        fenetre3.add(haut, BorderLayout.CENTER);
        fenetre3.add(bas, BorderLayout.SOUTH);
        afficher3();
    }

    public void afficher1() {
        //permet d'afficher la fenetre du jeu
        fenetre1.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        fenetre1.setSize(300, 200);
        fenetre1.setVisible(true);
    }

    public void afficher2() {
        //permet d'afficher la fenetre du jeu
        fenetre2.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        fenetre2.setSize(300, 200);
        fenetre2.setVisible(true);
    }

    public void afficher3() {
        //permet d'afficher la fenetre du jeu
        fenetre3.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        fenetre3.setSize(300, 200);
        fenetre3.setVisible(true);
    }

}
