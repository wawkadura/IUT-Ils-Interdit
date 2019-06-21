/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile_Interdite.IHM;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author wawve
 */
public class VueNotifications extends Observe {

    private JFrame fenetre;
    public void mainPlein(){
        fenetre = new JFrame("Notification");
        JLabel info = new JLabel("Vous avez plus de 5 cartes en main ! ");
        JLabel info2 = new JLabel("veuillez retirer defausser une carte");
        JButton ok = new JButton("OK");
        fenetre.add(info) ;
        fenetre.add(info2);
        fenetre.add(ok);
        afficher();
        
    }
    public void afficher() {
        //permet d'afficher la fenetre du jeu
        fenetre.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(1500, 800);
        fenetre.setVisible(true);
    }

}
