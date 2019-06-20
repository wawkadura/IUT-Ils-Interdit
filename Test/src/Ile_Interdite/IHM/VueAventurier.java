/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile_Interdite.IHM;

import Ile_Interdite.Coordonnees;
import Ile_Interdite.Tuile;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
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
    private JLabel joueur1, joueur2, joueur3, joueur4;
    private final Font jou = new Font(Font.MONOSPACED, Font.BOLD, 30);
    private JButton eau;
    private String joueurCourant;
    private int joueurAct = 1;
    private int nbDec = 3;
    private JLabel decompte;
    private JPanel monteeEauDroit;
    private JLabel monteeEau;
    private JButton tuile;
    ArrayList<JButton> boutons = new ArrayList<>();
    private boolean val1, val2, val3, val4 = false;
    private boolean deplacer;
    private boolean Assecher;
    private Graphics pion;

    public VueAventurier() {
        fenetre = new JFrame("Ile Interdite");

        ////////////////////////////////////////////////////////////JOUEURS/////////////////////////////////////////////////////////////////////////
        joueurBas = new JPanel(new GridLayout(1, 5));

        joueur1 = new JLabel("");
        joueur1.setFont(jou);
        joueur2 = new JLabel("");
        joueur2.setFont(jou);
        joueur3 = new JLabel("");
        joueur3.setFont(jou);
        joueur4 = new JLabel("");
        joueur4.setFont(jou);

        if (joueurAct == 1) {
            joueur1.setForeground(Color.RED);
            joueur2.setForeground(Color.WHITE);
            joueur3.setForeground(Color.WHITE);
            joueur4.setForeground(Color.WHITE);
        } else if (joueurAct == 2) {
            joueur1.setForeground(Color.WHITE);
            joueur2.setForeground(Color.BLUE);
            joueur3.setForeground(Color.WHITE);
            joueur4.setForeground(Color.WHITE);
        } else if (joueurAct == 3) {
            joueur1.setForeground(Color.WHITE);
            joueur2.setForeground(Color.WHITE);
            joueur3.setForeground(Color.GREEN);
            joueur4.setForeground(Color.WHITE);
        } else if (joueurAct == 4) {
            joueur1.setForeground(Color.WHITE);
            joueur2.setForeground(Color.WHITE);
            joueur3.setForeground(Color.WHITE);
            joueur4.setForeground(Color.MAGENTA);
        } else {
            joueur1.setForeground(Color.WHITE);
            joueur2.setForeground(Color.WHITE);
            joueur3.setForeground(Color.WHITE);
            joueur4.setForeground(Color.WHITE);
        }

        fenetre.add(joueurBas, BorderLayout.SOUTH);
        ////////////////////////////////////////////////////////////JOUEURS/////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////ACTIONS/////////////////////////////////////////////////////////////////////////
        JPanel actionGauche = new JPanel(new GridLayout(7, 1));
        JPanel nbAct = new JPanel(new GridLayout(1, 2));

        JLabel nombreActions = new JLabel("Nombre d'actions restantes :          ");

        decompte = new JLabel(nbDec + "");
        decompte.setFont(jou);
        nbAct.add(nombreActions);
        nbAct.add(decompte);

        JButton seDeplacer = new JButton("Se déplacer");
        seDeplacer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                nbDec = nbDec - 1;
                setNbAct(nbDec, joueurAct);
                deplacer = true;
                Assecher = false;
                Message m = new Message();
                m.type = TypesMessages.DEPLACER;
                m.joueurCourant = joueurCourant;
                notifierObservateur(m);
            }
        });
        JButton assecher = new JButton("Assécher");
        assecher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                nbDec = nbDec - 1;
                setNbAct(nbDec, joueurAct);
                deplacer = false;
                Assecher = true;
                Message m = new Message();
                m.type = TypesMessages.ASSECHER;
                m.joueurCourant = joueurCourant;
                notifierObservateur(m);
            }
        });
        JButton donnerTresor = new JButton("Donner une carte Trésor");
        donnerTresor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                nbDec = nbDec - 1;
                setNbAct(nbDec, joueurAct);

            }
        });
        JButton gagnerTresor = new JButton("Gagner un Trésor");
        gagnerTresor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                nbDec = nbDec - 1;
                setNbAct(nbDec, joueurAct);

            }
        });
        JButton compSpe = new JButton("Compétence spéciale");
        compSpe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                nbDec = nbDec - 1;
                setNbAct(nbDec, joueurAct);

            }
        });
        JButton terminerTour = new JButton("Terminer Tour");
        terminerTour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Message m = new Message();
                m.type = TypesMessages.TERMINER_TOUR;
                if (nbJoueur == 4) {
                    if (joueurAct == 4) {
                        joueurAct = 1;
                        getJoueurAct(joueurAct);
                    } else {
                        joueurAct = joueurAct + 1;
                        getJoueurAct(joueurAct);
                    }
                } else if (nbJoueur == 3) {
                    if (joueurAct == 3) {
                        joueurAct = 1;
                        getJoueurAct(joueurAct);
                    } else {
                        joueurAct = joueurAct + 1;
                        getJoueurAct(joueurAct);
                    }
                } else if (nbJoueur == 2) {
                    if (joueurAct == 2) {
                        joueurAct = 1;
                        getJoueurAct(joueurAct);
                    } else {
                        joueurAct = joueurAct + 1;
                        getJoueurAct(joueurAct);      
                    }
                }
                nbDec = 3;
                decompte.setText(nbDec+"");
                m.joueurAct = joueurAct;
                notifierObservateur(m);
            }
        });

        actionGauche.add(nbAct);
        actionGauche.add(seDeplacer);
        actionGauche.add(assecher);
        actionGauche.add(donnerTresor);
        actionGauche.add(gagnerTresor);
        actionGauche.add(compSpe);
        actionGauche.add(terminerTour);

        fenetre.add(actionGauche, BorderLayout.WEST);
        ////////////////////////////////////////////////////////////ACTIONS/////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////CARTES///////////////////////////////////////////////////////////////////////////
        JPanel carteHaut = new JPanel(new GridLayout(2, 7));

        for (int i = 0; i < 3; i++) {
            JLabel espace = new JLabel("");
            carteHaut.add(espace);
        }

        JLabel cartesJoueur = new JLabel("Cartes joueur courant : ");
        carteHaut.add(cartesJoueur);

        for (int i = 0; i < 3; i++) {
            JLabel espace = new JLabel("");
            carteHaut.add(espace);
        }
        for (int i = 0; i < 7; i++) {
            JButton carteJoueur = new JButton("Carte");
            carteJoueur.setEnabled(false);
            carteHaut.add(carteJoueur);
        }

        fenetre.add(carteHaut, BorderLayout.NORTH);
        ////////////////////////////////////////////////////////////CARTES///////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////NIVEAU EAU///////////////////////////////////////////////////////////////////////
        monteeEauDroit = new JPanel(new GridLayout(11, 1));

        monteeEau = new JLabel("Niveau d'eau : ");
        monteeEauDroit.add(monteeEau);

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

                tuile = new JButton("(" + c + "," + l + ")");
                tuile.setEnabled(false);
                tuile.setBackground(Color.GRAY);
                Coordonnees C = new Coordonnees(c, l);
                boutons.add(tuile);
                tuile.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Message m = new Message();
                        m.type = TypesMessages.CHOIX_TUILE;
                        m.c = C;
                        m.deplacer = deplacer;
                        m.assecher = Assecher;
                        notifierObservateur(m);
                    }
                });
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

    public void paintComponent(Graphics g) {  
        pion = (Graphics2D) g;
        pion.setColor(Color.RED);
        pion.fillOval(10, 10, 10, 10);
    }

    public void afficher() {
        //permet d'afficher la fenetre du jeu
        fenetre.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(1500, 800);
        fenetre.setVisible(false);
    }

    public void setNbJoueurs(int nbJoueurs) {
        nbJoueur = nbJoueurs;
        if (nbJoueur == 4) {
            joueurBas.add(joueur1);
            joueurBas.add(joueur2);
            joueurBas.add(joueur3);
            joueurBas.add(joueur4);
        } else if (nbJoueur == 3) {
            joueurBas.add(joueur1);
            joueurBas.add(joueur2);
            joueurBas.add(joueur3);
        } else if (nbJoueur == 2) {
            joueurBas.add(joueur1);
            joueurBas.add(joueur2);
        }
        fenetre.setVisible(true);
    }

    public void setNomJoueurs(String nom1, String nom2, String nom3, String nom4) {
        joueur1.setText(nom1);
        joueur2.setText(nom2);
        joueur3.setText(nom3);
        joueur4.setText(nom4);
    }

    public void setDifficulte(int difficulte) {
        if (difficulte == 0 || difficulte == 1) {
            val1 = true;
        } else if (difficulte == 2) {
            val1 = true;
            val2 = true;
        } else if (difficulte == 3) {
            val1 = true;
            val2 = true;
            val3 = true;
        } else if (difficulte == 4) {
            val1 = true;
            val2 = true;
            val3 = true;
            val4 = true;
        }

        for (int i = 10; i >= 1; i--) {
            if (i == 1) {
                eau = new JButton();
                eau.setText("Niveau " + i + "   Piocher 2 cartes");
                eau.setEnabled(false);
                monteeEauDroit.add(eau);
                if (val1 == true) {
                    eau.setBackground(Color.BLUE);
                }
            } else if (i == 2) {
                eau = new JButton();
                eau.setText("Niveau " + i + "   Piocher 2 cartes");
                eau.setEnabled(false);
                monteeEauDroit.add(eau);
                if (val1 == true & val2 == true) {
                    eau.setBackground(Color.BLUE);
                }
            } else if (i == 3) {
                eau = new JButton();
                eau.setText("Niveau " + i + "   Piocher 3 cartes");
                eau.setEnabled(false);
                monteeEauDroit.add(eau);
                if (val1 == true && val2 == true && val3 == true) {
                    eau.setBackground(Color.BLUE);
                }
            } else if (i == 4) {
                eau = new JButton();
                eau.setText("Niveau " + i + "   Piocher 3 cartes");
                eau.setEnabled(false);
                monteeEauDroit.add(eau);
                if (val1 == true && val2 == true && val3 == true && val4 == true) {
                    eau.setBackground(Color.BLUE);
                }
            } else if (i == 5) {
                eau = new JButton();
                eau.setText("Niveau " + i + "   Piocher 3 cartes");
                eau.setEnabled(false);
                monteeEauDroit.add(eau);
            } else if (i == 6) {
                eau = new JButton();
                eau.setText("Niveau " + i + "   Piocher 4 cartes");
                eau.setEnabled(false);
                monteeEauDroit.add(eau);
            } else if (i == 7) {
                eau = new JButton();
                eau.setText("Niveau " + i + "   Piocher 4 cartes");
                eau.setEnabled(false);
                monteeEauDroit.add(eau);
            } else if (i == 8) {
                eau = new JButton();
                eau.setText("Niveau " + i + "   Piocher 5 cartes");
                eau.setEnabled(false);
                monteeEauDroit.add(eau);
            } else if (i == 9) {
                eau = new JButton();
                eau.setText("Niveau " + i + "   Piocher 5 cartes");
                eau.setEnabled(false);
                monteeEauDroit.add(eau);
            } else if (i == 10) {
                eau = new JButton();
                eau.setText("Niveau " + i + "   Mort");
                eau.setEnabled(false);
                monteeEauDroit.add(eau);
            }
        }
    }

    public void setJoueurCourant(String joueurCourant) {
        this.joueurCourant = joueurCourant;
    }

    public void setTuilesDispo(ArrayList<Coordonnees> c) {
        for (Coordonnees coord : c) {
            for (JButton J : boutons) {
                if (J.getText().equalsIgnoreCase(coord.afficherCoord())) {
                    J.setBackground(Color.green);
                    J.setEnabled(true);
                }
            }
        }
    }

    public void mettreAJourTuiles(Collection<Tuile> tuiles) {
        for (Tuile t : tuiles) {
            for (JButton jb : boutons) {
                jb.setEnabled(false);
                if (jb.getText().equalsIgnoreCase(t.getCoordonnee().afficherCoord())) {

                    if (t.getEtat().equalsIgnoreCase("Manquante")) {
                        jb.setBackground(Color.white);
                    } else if (t.getEtat().equalsIgnoreCase("Innondée")) {
                        jb.setBackground(Color.blue);
                    } else {
                        if (t.getType() != null) {
                            jb.setBackground(Color.yellow);
                        } else {
                            jb.setBackground(Color.ORANGE);
                        }
                    }
                }
            }
        }

    }

    public void mettreAJourPions() {

    }

    public void mettreAJourCartes() {

    }

    public void setNbAct(int nbDec, int joueurAct) {
        Message m = new Message();
        m.type = TypesMessages.TERMINER_TOUR;
        if (nbDec == 0) {
            if (nbJoueur == 4) {
                if (joueurAct == 4) {
                    joueurAct = 1;
                    this.joueurAct = joueurAct;
                    getJoueurAct(joueurAct);
                } else {
                    joueurAct = joueurAct + 1;
                    this.joueurAct = joueurAct;
                    getJoueurAct(joueurAct);
                }
            } else if (nbJoueur == 3) {
                if (joueurAct == 3) {
                    joueurAct = 1;
                    this.joueurAct = joueurAct;
                    getJoueurAct(joueurAct);
                } else {
                    joueurAct = joueurAct + 1;
                    this.joueurAct = joueurAct;
                    getJoueurAct(joueurAct);
                }
            } else if (nbJoueur == 2) {
                if (joueurAct == 2) {
                    joueurAct = 1;
                    this.joueurAct = joueurAct;
                    getJoueurAct(joueurAct);
                } else {
                    joueurAct = joueurAct + 1;
                    this.joueurAct = joueurAct;
                    getJoueurAct(joueurAct);
                }
            }
            nbDec = 3;
            this.nbDec = nbDec;
            decompte.setText(nbDec + "");
        } else {
            decompte.setText(nbDec + "");
        }

        m.joueurAct = joueurAct;
        notifierObservateur(m);
    }

    public void getJoueurAct(int joueurAct) {
        switch (joueurAct) {
            case 1:
                joueur1.setForeground(Color.RED);
                joueur2.setForeground(Color.WHITE);
                joueur3.setForeground(Color.WHITE);
                joueur4.setForeground(Color.WHITE);
                break;
            case 2:
                joueur1.setForeground(Color.WHITE);
                joueur2.setForeground(Color.BLUE);
                joueur3.setForeground(Color.WHITE);
                joueur4.setForeground(Color.WHITE);
                break;
            case 3:
                joueur1.setForeground(Color.WHITE);
                joueur2.setForeground(Color.WHITE);
                joueur3.setForeground(Color.GREEN);
                joueur4.setForeground(Color.WHITE);
                break;
            case 4:
                joueur1.setForeground(Color.WHITE);
                joueur2.setForeground(Color.WHITE);
                joueur3.setForeground(Color.WHITE);
                joueur4.setForeground(Color.MAGENTA);
                break;
            default:
                joueur1.setForeground(Color.WHITE);
                joueur2.setForeground(Color.WHITE);
                joueur3.setForeground(Color.WHITE);
                joueur4.setForeground(Color.WHITE);
                break;
        }
    }
}
