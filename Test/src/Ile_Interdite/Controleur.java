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
import java.util.HashMap;
import java.util.Scanner;

public class Controleur {

    private Grille grille;
    private HashMap<String, Aventurier> joueurs = new HashMap<String, Aventurier>();
    private VueAventurier vueAventurier;
    private PileInondation pileInondation;
    private PileTrésor pileTresor;

    public void traiterMessage(String m) {
    }
    public void nbJoueur() { 
            int nbJoueur = 10;
        
            while (nbJoueur < 2 && nbJoueur > 4) {
                System.out.println("Il vous faut un minimum de 2 et maximum de 4 joueurs pour jouer !");
                System.out.println("Combien de joueurs veulent jouer ? : ");
                Scanner nb = new Scanner(System.in);
                nbJoueur = nb.nextInt();
            }
            System.out.println("Vous avez inscrit " + nbJoueur + "joueurs.");
            System.out.println("La partie peut se lancer !");   
        }

    public static void main(String[] args) {
        // TODO code application logic here 
        int l = 0;// ligne
        int c = 0;//colonne
        int niv = 1;//demander aux joueurs

        Grille grille = new Grille(niv);
        for (int i = 0; i < 36; i++) {// Creation de la Grille
            Coordonnees C = new Coordonnees(l, c);
            if (c == 2 && l == 0) {
                LieuDeTresor tuile = new LieuDeTresor(C, "coup");
                grille.addTuile(tuile);
            } //coup
            else if (c == 3 && l == 0) {
                LieuDeTresor tuile = new LieuDeTresor(C, "feu");
                grille.addTuile(tuile);
            }//feu
            else if (c == 0 && l == 2) {
                LieuDeTresor tuile = new LieuDeTresor(C, "coup");
                grille.addTuile(tuile);
            }//coup
            else if (c == 0 && l == 3) {
                LieuDeTresor tuile = new LieuDeTresor(C, "lion");
                grille.addTuile(tuile);
            }//lion
            else if (c == 5 && l == 2) {
                LieuDeTresor tuile = new LieuDeTresor(C, "feu");
                grille.addTuile(tuile);
            }//feu
            else if (c == 5 && l == 3) {
                LieuDeTresor tuile = new LieuDeTresor(C, "oeuf");
                grille.addTuile(tuile);
            }//oeuf
            else if (c == 2 && l == 5) {
                LieuDeTresor tuile = new LieuDeTresor(C, "lion");
                grille.addTuile(tuile);
            }//lion
            else if (c == 3 && l == 5) {
                LieuDeTresor tuile = new LieuDeTresor(C, "oeuf");
                grille.addTuile(tuile);
            }//oeuf
            
            //tuile heliport a faire aleatoirement
            else {

                Tuile tuile = new Tuile(C);
                grille.addTuile(tuile);
            }
            if (c == 6) {
                c = 0;
                l++;
            }
            
        }
    
        //demander combien de joueur veux jouer de 2 a 4
        
        // donner aleatoirement des roles
        // donner aleatoirement des tuile
        Coordonnees C = new Coordonnees(2,2);
        Coordonnees C2 = new Coordonnees(3,2);
        Coordonnees C3 = new Coordonnees(4,2);
        Coordonnees C4 = new Coordonnees(0,2);
        Tuile tuile = grille.getTuiles().get(C);
        Tuile tuile2 = grille.getTuiles().get(C2);
        Tuile tuile3 = grille.getTuiles().get(C3);
        Tuile tuile4 = grille.getTuiles().get(C4);
        
        Ingenieur Joueur1 = new Ingenieur("Joueur1",grille.getTuiles().get(C) );
        System.out.println("depart"+Joueur1.getTuile().getCoordonnee().afficherCoord()); // nullpointerException a corriger
        Explorateur Joueur2 = new Explorateur("Joueur2", tuile2);
        Pilote Joueur3 = new Pilote("Joueur3", tuile3);
        Plongeur Joueur4 = new Plongeur("Joueur4", tuile4);
        Joueur1.deplacer(grille);
        Joueur1.tourTermine();
        System.out.println("fin"+Joueur1.getTuile().getCoordonnee().afficherCoord()); 
        // loup du jeu (tant que personne est mort et que les tresor sont tous disponible et que les joueur ont pas gagnier )
        //loup du tour selon les action while action >0
        //demander quelle action faire :
            //assecher 
            //deplacer 
            //rien faire 
            //donner (si il est sur la meme cas d'un autre joueur )
            //chercher (si il est sur une tuile a tresor )
            //Activer Competence
            //activer une carte (si il en a)
        //faire
        //faire piocher les joueur dans la pile a tresor 
        
        

    }
}


