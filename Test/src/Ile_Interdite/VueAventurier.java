/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile_Interdite;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;

/**
 *
 * @author peyrinfl
 */
public class VueAventurier extends Observe {

    private JFrame fenetre;


    public VueAventurier() {
        fenetre = new JFrame("Ile Interdite");
        
        for (int i = 0; i < 36; i++) {
        
        
        }
        
        
    }
        
 
    
    public void afficher() {
        //permet d'afficher la fenetre du jeu
        fenetre.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(1500, 900);
        fenetre.setVisible(true);
    }
}
