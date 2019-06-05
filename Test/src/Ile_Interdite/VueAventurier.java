/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile_Interdite;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author peyrinfl
 */
    public class VueAventurier implements Observe {
    private Controleur C = new Controleur();

    public VueAventurier() {
        Controleur C = new Controleur(); 
    }
    public static void main(String[] args) {
        // TODO code application logic here
        VueAventurier vueAv = new VueAventurier();
    }

    private Observateur observateur;

    @Override
    public void addObservateur(Observateur o) {
        this.observateur = o;
    }

    @Override
    public void notifierObservateur(Message m) {
        if (observateur != null) {
            observateur.traiterMessage(m);
        }
    }
}
