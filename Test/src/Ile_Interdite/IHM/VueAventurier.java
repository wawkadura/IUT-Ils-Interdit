/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile_Interdite.IHM;

import Ile_Interdite.Aventuriers.Aventurier;
import Ile_Interdite.Aventuriers.TresorsRecupere;
import Ile_Interdite.Coordonnees;
import Ile_Interdite.Tuile;
import Ile_Interdite.cartes.CarteTresor;
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
    private JPanel grilleMilieu;
    private int nbJoueur;
    private JPanel joueurBas;
    private JButton joueur1, joueur2, joueur3, joueur4;
    private ArrayList<String> nomsTuiles = new ArrayList<>();
    private final Font jou = new Font(Font.MONOSPACED, Font.BOLD, 30);
    private JButton eau;
    private String joueurCourant;
    private int joueurAct = 1;
    private int nbDec = 3;
    private Color none;
    private JLabel decompte;
    private JPanel monteeEauDroit;
    private JLabel monteeEau;
    private JButton tuile, seDeplacer, assecher, donnerCarte, gagnerTresor, compSpe, terminerTour;
    ArrayList<JButton> boutons = new ArrayList<>();
    ArrayList<JButton> cartes = new ArrayList<>();
    ArrayList<JButton> niveaux = new ArrayList<>();
    ArrayList<JButton> joueurs = new ArrayList<>();
    private JButton calice, pierre, statue, cristal;
    private boolean competenceUtiliser,niv1, niv2, niv3, niv4, niv5, niv6, niv7, niv8, niv9, niv10 = false;
    private boolean donner = false;
    private boolean defausser = false;
    private boolean deplacer;
    private boolean Assecher;
    private Graphics pion;
    private Pion pion1, pion2, pion3, pion4;

    public VueAventurier() {

        fenetre = new JFrame("Ile Interdite");

        ////////////////////////////////////////////////////////////JOUEURS/////////////////////////////////////////////////////////////////////////
        joueurBas = new JPanel(new GridLayout(1, 5));

        joueur1 = new JButton("");
        joueur1.setFont(jou);
        joueur1.setBorder(null);
        joueur1.setEnabled(false);
        joueur1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (donner) {

                    nbDec = nbDec - 1;
                    mettreAJourIndic();
                    decompte.setText(nbDec + "");
                    Message m = new Message();
                    m.type = TypesMessages.CHOIX_JOUEUR;
                    m.DonnerAJoueur = joueur1.getText();
                    notifierObservateur(m);
                    for (JButton jb : joueurs) {
                        jb.setEnabled(false);
                        jb.setBackground(none);
                        setCouleurJoueur();

                    }
                    for (JButton jb : cartes) {
                        if (!jb.getText().equalsIgnoreCase("Carte")) {
                            jb.setEnabled(true);
                        }
                    }
                    donner = false;
                }

            }
        });

        joueur2 = new JButton("");
        joueur2.setFont(jou);
        joueur2.setBorder(null);
        joueur2.setEnabled(false);
        joueur2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (donner) {
                    nbDec = nbDec - 1;
                    mettreAJourIndic();
                    decompte.setText(nbDec + "");
                    Message m = new Message();
                    m.type = TypesMessages.CHOIX_JOUEUR;

                    m.DonnerAJoueur = joueur2.getText();
                    notifierObservateur(m);
                    for (JButton jb : joueurs) {
                        jb.setEnabled(false);
                        jb.setBackground(none);
                        setCouleurJoueur();

                    }
                    for (JButton jb : cartes) {
                        if (!jb.getText().equalsIgnoreCase("Carte")) {
                            jb.setEnabled(true);
                        }
                    }
                    donner = false;
                }
            }
        });

        joueur3 = new JButton("");
        joueur3.setFont(jou);
        joueur3.setBorder(null);
        joueur3.setEnabled(false);
        joueur3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (donner) {
                    nbDec = nbDec - 1;
                    mettreAJourIndic();
                    decompte.setText(nbDec + "");
                    Message m = new Message();
                    m.type = TypesMessages.CHOIX_JOUEUR;
                    m.DonnerAJoueur = joueur3.getText();
                    notifierObservateur(m);
                    for (JButton jb : joueurs) {
                        jb.setEnabled(false);
                        jb.setBackground(none);
                        setCouleurJoueur();

                    }
                    for (JButton jb : cartes) {
                        if (!jb.getText().equalsIgnoreCase("Carte")) {
                            jb.setEnabled(true);
                        }
                    }
                    donner = false;
                }
            }
        });

        joueur4 = new JButton("");
        joueur4.setFont(jou);
        joueur4.setBorder(null);
        joueur4.setEnabled(false);
        joueur4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (donner) {
                    nbDec = nbDec - 1;
                    mettreAJourIndic();
                    decompte.setText(nbDec + "");
                    Message m = new Message();
                    m.type = TypesMessages.CHOIX_JOUEUR;
                    m.DonnerAJoueur = joueur4.getText();
                    notifierObservateur(m);
                    for (JButton jb : joueurs) {
                        jb.setEnabled(false);
                        jb.setBackground(none);
                        setCouleurJoueur();

                    }
                    for (JButton jb : cartes) {
                        if (!jb.getText().equalsIgnoreCase("Carte")) {
                            jb.setEnabled(true);
                        }
                    }
                    donner = false;
                }
            }
        });
        joueurs.add(joueur1);
        joueurs.add(joueur2);
        joueurs.add(joueur3);
        joueurs.add(joueur4);

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

        seDeplacer = new JButton("Se déplacer");
        seDeplacer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                deplacer = true;
                Assecher = false;

                seDeplacer.setEnabled(false);
                assecher.setEnabled(false);
                donnerCarte.setEnabled(false);
                gagnerTresor.setEnabled(false);
                compSpe.setEnabled(false);
                terminerTour.setEnabled(false);

                Message m = new Message();
                m.type = TypesMessages.DEPLACER;
                m.joueurCourant = joueurCourant;
                notifierObservateur(m);
            }
        });
        assecher = new JButton("Assécher");
        assecher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                deplacer = false;
                Assecher = true;

                seDeplacer.setEnabled(false);
                assecher.setEnabled(false);
                donnerCarte.setEnabled(false);
                gagnerTresor.setEnabled(false);
                compSpe.setEnabled(false);
                terminerTour.setEnabled(false);

                Message m = new Message();
                m.type = TypesMessages.ASSECHER;
                m.joueurCourant = joueurCourant;
                notifierObservateur(m);
            }
        });
        donnerCarte = new JButton("Donner une carte Trésor");
        donnerCarte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                donner = true;
                Message m = new Message();
                m.type = TypesMessages.DONNER;
                m.joueurCourant = joueurCourant;
                notifierObservateur(m);

                seDeplacer.setEnabled(false);
                assecher.setEnabled(false);
                donnerCarte.setEnabled(false);
                gagnerTresor.setEnabled(false);
                compSpe.setEnabled(false);
                terminerTour.setEnabled(false);
            }
        });
        gagnerTresor = new JButton("Gagner un Trésor");
        gagnerTresor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                nbDec = nbDec - 1;
                mettreAJourIndic();

                seDeplacer.setEnabled(false);
                assecher.setEnabled(false);
                donnerCarte.setEnabled(false);
                gagnerTresor.setEnabled(false);
                compSpe.setEnabled(false);
                terminerTour.setEnabled(false);
                Message m = new Message();
                m.type = TypesMessages.GAGNER;
                m.joueurCourant = joueurCourant;
                notifierObservateur(m);
            }
        });
        compSpe = new JButton("Compétence spéciale");
        compSpe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
//                if (joueurs.get(joueurAct - 1).getActionCommand().equalsIgnoreCase("Ingénieur")){}
                Message m = new Message();
                m.type = TypesMessages.COMPETENCE;
                m.joueurCourant=joueurCourant;
                notifierObservateur(m);
                competenceUtiliser=true;

            }
        });
        terminerTour = new JButton("Terminer Tour");
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
                decompte.setText(nbDec + "");
                m.joueurAct = joueurAct;
                mettreAJourIndic();

                notifierObservateur(m);
            }
        });

        actionGauche.add(nbAct);
        actionGauche.add(seDeplacer);
        actionGauche.add(assecher);
        actionGauche.add(donnerCarte);
        actionGauche.add(gagnerTresor);
        actionGauche.add(compSpe);
        actionGauche.add(terminerTour);

        fenetre.add(actionGauche, BorderLayout.WEST);
        ////////////////////////////////////////////////////////////ACTIONS/////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////CARTES///////////////////////////////////////////////////////////////////////////
        JPanel carteHaut = new JPanel(new GridLayout(2, 7));

        statue = new JButton("La Statue du Zéphyr");
        statue.setBackground(Color.LIGHT_GRAY);
        carteHaut.add(statue);
        statue.setBorder(null);
        statue.setEnabled(false);

        cristal = new JButton("Le Cristal Ardent");
        none = cristal.getBackground();
        cristal.setBackground(Color.LIGHT_GRAY);
        carteHaut.add(cristal);
        cristal.setBorder(null);
        cristal.setEnabled(false);

        JLabel espace1 = new JLabel("");
        carteHaut.add(espace1);

        JLabel cartesJoueur = new JLabel("Cartes joueur courant : ");
        carteHaut.add(cartesJoueur);

        JLabel espace2 = new JLabel("");
        carteHaut.add(espace2);

        pierre = new JButton("La Pierre Sacrée");
        pierre.setBackground(Color.LIGHT_GRAY);
        carteHaut.add(pierre);
        pierre.setBorder(null);
        pierre.setEnabled(false);

        calice = new JButton("Le Calice de Londe");
        calice.setBackground(Color.LIGHT_GRAY);
        carteHaut.add(calice);
        calice.setBorder(null);
        calice.setEnabled(false);

        for (int i = 0; i < 7; i++) {
            JButton carteJoueur = new JButton("Carte");
            int num = i;
            carteJoueur.setEnabled(false);
            carteHaut.add(carteJoueur);
            carteJoueur.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (donner) {

                        Message m = new Message();
                        m.type = TypesMessages.CHOIX_CARTE;
                        m.numCarte = num;
                        m.donner = donner;
                        notifierObservateur(m);

                        for (JButton jb : cartes) {
                            jb.setEnabled(false);
                            jb.setBackground(none);

                        }
                    } else if (defausser) {

                        Message m = new Message();
                        m.type = TypesMessages.CHOIX_CARTE;
                        m.defausser = defausser;
                        m.numCarte = num;
                        notifierObservateur(m);
                        int i = 0;

                        for (JButton jb : cartes) {
                            jb.setBackground(none);
                            defausser = false;
                        }

                    }
                }
            });

            cartes.add(carteJoueur);
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
        grilleMilieu = new JPanel(new GridLayout(6, 6));

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
                tuile.setBorder(null);
                grilleMilieu.add(tuile);
            } else {

                tuile = new JButton("(" + c + "," + l + ")");
                tuile.setEnabled(false);
                tuile.setBackground(Color.GRAY);
                //tuile.add(pionRed);
                Coordonnees C = new Coordonnees(c, l);
                boutons.add(tuile);
                tuile.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        nbDec = nbDec - 1;
                        decompte.setText(nbDec + "");

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

    public void creeNiv() {
        for (int i = 10; i >= 1; i--) {
            if (i == 1) {
                eau = new JButton();
                eau.setText("Niveau " + i + "   Piocher 2 cartes");
                eau.setEnabled(false);
                eau.setBorder(null);
                eau.setActionCommand("niv1");
                niveaux.add(eau);
                monteeEauDroit.add(eau);

            } else if (i == 2) {
                eau = new JButton();
                eau.setText("Niveau " + i + "   Piocher 2 cartes");
                eau.setEnabled(false);
                eau.setBorder(null);
                eau.setActionCommand("niv2");
                niveaux.add(eau);
                monteeEauDroit.add(eau);

            } else if (i == 3) {
                eau = new JButton();
                eau.setText("Niveau " + i + "   Piocher 3 cartes");
                eau.setEnabled(false);
                eau.setBorder(null);
                eau.setActionCommand("niv3");
                niveaux.add(eau);
                monteeEauDroit.add(eau);
                if (niv1 == true && niv2 == true && niv3 == true) {
                    eau.setBackground(Color.BLUE);
                }
            } else if (i == 4) {
                eau = new JButton();
                eau.setText("Niveau " + i + "   Piocher 3 cartes");
                eau.setEnabled(false);
                eau.setBorder(null);
                eau.setActionCommand("niv4");
                niveaux.add(eau);
                monteeEauDroit.add(eau);

            } else if (i == 5) {
                eau = new JButton();
                eau.setText("Niveau " + i + "   Piocher 3 cartes");
                eau.setEnabled(false);
                eau.setBorder(null);
                eau.setActionCommand("niv5");
                niveaux.add(eau);
                monteeEauDroit.add(eau);

            } else if (i == 6) {
                eau = new JButton();
                eau.setText("Niveau " + i + "   Piocher 4 cartes");
                eau.setEnabled(false);
                eau.setBorder(null);
                eau.setActionCommand("niv6");
                niveaux.add(eau);
                monteeEauDroit.add(eau);

            } else if (i == 7) {
                eau = new JButton();
                eau.setText("Niveau " + i + "   Piocher 4 cartes");
                eau.setEnabled(false);
                eau.setBorder(null);
                eau.setActionCommand("niv7");
                niveaux.add(eau);
                monteeEauDroit.add(eau);

            } else if (i == 8) {
                eau = new JButton();
                eau.setText("Niveau " + i + "   Piocher 5 cartes");
                eau.setEnabled(false);
                eau.setBorder(null);
                eau.setActionCommand("niv8");
                niveaux.add(eau);
                monteeEauDroit.add(eau);

            } else if (i == 9) {
                eau = new JButton();
                eau.setText("Niveau " + i + "   Piocher 5 cartes");
                eau.setEnabled(false);
                eau.setBorder(null);
                eau.setActionCommand("niv9");
                niveaux.add(eau);
                monteeEauDroit.add(eau);

            } else if (i == 10) {
                eau = new JButton();
                eau.setText("Niveau " + i + "   Mort");
                eau.setEnabled(false);
                eau.setBorder(null);
                eau.setActionCommand("niv10");
                niveaux.add(eau);
                monteeEauDroit.add(eau);

            }
        }
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

    public void setRoleJoueur(String role1, String role2, String role3, String role4) {
        joueur1.setActionCommand(role1);
        joueur2.setActionCommand(role2);
        joueur3.setActionCommand(role3);
        joueur4.setActionCommand(role4);
    }

    public void setNivEau(int difficulte) {
        if (difficulte == 0 || difficulte == 1) {
            niv1 = true;
        } else if (difficulte == 2) {
            niv1 = true;
            niv2 = true;
        } else if (difficulte == 3) {
            niv1 = true;
            niv2 = true;
            niv3 = true;
        } else if (difficulte == 4) {
            niv1 = true;
            niv2 = true;
            niv3 = true;
            niv4 = true;
        } else if (difficulte == 5) {
            niv1 = true;
            niv2 = true;
            niv3 = true;
            niv4 = true;
            niv5 = true;
        } else if (difficulte == 6) {
            niv1 = true;
            niv2 = true;
            niv3 = true;
            niv4 = true;
            niv5 = true;
            niv6 = true;
        } else if (difficulte == 7) {
            niv1 = true;
            niv2 = true;
            niv3 = true;
            niv4 = true;
            niv5 = true;
            niv6 = true;
            niv7 = true;
        } else if (difficulte == 8) {
            niv1 = true;
            niv2 = true;
            niv3 = true;
            niv4 = true;
            niv5 = true;
            niv6 = true;
            niv7 = true;
            niv8 = true;
        } else if (difficulte == 9) {
            niv1 = true;
            niv2 = true;
            niv3 = true;
            niv4 = true;
            niv5 = true;
            niv6 = true;
            niv7 = true;
            niv8 = true;
            niv9 = true;
        } else if (difficulte == 10) {
            niv1 = true;
            niv2 = true;
            niv3 = true;
            niv4 = true;
            niv5 = true;
            niv6 = true;
            niv7 = true;
            niv8 = true;
            niv9 = true;
            niv10 = true;
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

    public void setCartesDispo() {

        for (JButton J : cartes) {
            if (!J.getText().equals("Carte")) {
                J.setBackground(Color.green);
                J.setEnabled(true);
            }
        }

    }

    public void setJoueurDispo(ArrayList<String> joueur) {
        if (!joueur.isEmpty()) {
            if (joueurs.get(joueurAct - 1).getActionCommand().equals("Messager")) {
                for (JButton J : joueurs) {
                    if (!J.getText().equals(joueurCourant)) {
                        J.setBackground(Color.green);
                        J.setEnabled(true);
                    }
                }
            } else {
                for (String j : joueur) {
                    for (JButton J : joueurs) {
                        if (!J.getText().equals(joueurCourant) && J.getText().equals(j)) {
                            if (J.getForeground() == Color.GREEN) {
                                J.setForeground(Color.black);
                            }

                            J.setBackground(Color.green);
                            J.setEnabled(true);
                        }
                    }
                }
            }
        }

    }

    public void mettreAJourTresor(TresorsRecupere tr) {
        if (tr.gotCalice()) {
            calice.setEnabled(true);
            calice.setBackground(Color.CYAN);
        }
        if (tr.gotStatue()) {
            statue.setEnabled(true);
            statue.setBackground(Color.YELLOW);
        }
        if (tr.gotPierre()) {
            pierre.setEnabled(true);
            pierre.setBackground(Color.MAGENTA);
        }
        if (tr.gotCristal()) {
            cristal.setEnabled(true);
            cristal.setBackground(Color.RED);
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
                        jb.setBackground(Color.CYAN);
                    } else {
                        if (t.getType() != null) {
                            if (t.getType().equalsIgnoreCase("Héliport")) {
                                jb.setBackground(Color.LIGHT_GRAY);
                            } else {
                                jb.setBackground(Color.yellow);
                            }
                        } else {
                            jb.setBackground(Color.ORANGE);
                        }
                    }
                }
            }
        }

    }

    public void mettreAJourPions(Coordonnees oldOne, Coordonnees newOne) {
        for (JButton jb : boutons) {
            Color color = jb.getBackground();
            if (jb.getText().equalsIgnoreCase(oldOne.afficherCoord()) && joueurAct == 1) {

                jb.remove(pion1);
                jb.setBackground(Color.green);

            }
            if (jb.getText().equalsIgnoreCase(oldOne.afficherCoord()) && joueurAct == 2) {
                jb.remove(pion2);
                jb.setBackground(Color.green);
            }
            if (jb.getText().equalsIgnoreCase(oldOne.afficherCoord()) && joueurAct == 3) {
                jb.remove(pion3);
                jb.setBackground(Color.green);
            }
            if (jb.getText().equalsIgnoreCase(oldOne.afficherCoord()) && joueurAct == 4) {
                jb.remove(pion4);
                jb.setBackground(Color.green);
            }

            if (jb.getText().equalsIgnoreCase(newOne.afficherCoord()) && joueurAct == 1) {
                jb.add(pion1);

            }
            if (jb.getText().equalsIgnoreCase(newOne.afficherCoord()) && joueurAct == 2) {
                jb.add(pion2);
                jb.setBackground(color);
            }
            if (jb.getText().equalsIgnoreCase(newOne.afficherCoord()) && joueurAct == 3) {
                jb.add(pion3);
                jb.setBackground(color);
            }
            if (jb.getText().equalsIgnoreCase(newOne.afficherCoord()) && joueurAct == 4) {
                jb.add(pion4);
                jb.setBackground(color);
            }

        }

    }

    public void mettreAJourIndic() {
        decompte.setText(nbDec + "");

    }

    public void mettreAJourCartes(ArrayList<CarteTresor> cartes) {
        int i = 0;
        while (i < cartes.size()) {
            this.cartes.get(i).setText(cartes.get(i).getFonction());
            this.cartes.get(i).setEnabled(true);
            i++;
        }
        while (i < this.cartes.size()) {
            this.cartes.get(i).setText("Carte");
            this.cartes.get(i).setEnabled(false);
            i++;
        }

    }

    public void verfiCarte() {

    }

    public void mettreAJourActions(boolean GagnerTresor, ArrayList<String> joueur, boolean asseche) {
        boolean no_carte = true;
        for (JButton jb : cartes) {
            if (!jb.getText().equals("Carte")) {
                no_carte = false;
            }
        }
        terminerTour.setEnabled(true);
        if (nbDec == 0) {
            seDeplacer.setEnabled(false);
            assecher.setEnabled(false);
            donnerCarte.setEnabled(false);
            gagnerTresor.setEnabled(false);
            compSpe.setEnabled(false);
        } else {
            seDeplacer.setEnabled(true);
            assecher.setEnabled(true);
            donnerCarte.setEnabled(true);
            gagnerTresor.setEnabled(true);
            compSpe.setEnabled(true);
        }
        if (!asseche) {
            assecher.setEnabled(false);
            if (joueurs.get(joueurAct - 1).getActionCommand().equalsIgnoreCase("Ingénieur")) {
                compSpe.setEnabled(false);
            }
        }
        if (no_carte || joueur.isEmpty()) {

            donnerCarte.setEnabled(false);

        }
        if (joueurs.get(joueurAct - 1).getActionCommand().equals("Explorateur")
                || joueurs.get(joueurAct - 1).getActionCommand().equals("Plongeur")
                || joueurs.get(joueurAct - 1).getActionCommand().equals("Navigateur")
                || joueurs.get(joueurAct - 1).getActionCommand().equals("Pilote") && competenceUtiliser) {
            compSpe.setEnabled(false);
        }
        if (!GagnerTresor) {
            gagnerTresor.setEnabled(false);
        }

    }

    public void initaliserAventurier(ArrayList<String> type) {
        for (int i = 0; i < joueurs.size(); i++) {
            joueurs.get(i).setActionCommand(type.get(i));
        }

    }

    public void defausserCarte() {
        seDeplacer.setEnabled(false);
        assecher.setEnabled(false);
        donnerCarte.setEnabled(false);
        gagnerTresor.setEnabled(false);
        compSpe.setEnabled(false);
        terminerTour.setEnabled(false);
        setCartesDispo();
        defausser = true;
    }

    public void mettreAJourNivEau() {
        Message m = new Message();
        m.type = TypesMessages.DIFFICULTE;

        if (niveaux.get(9).getActionCommand().equalsIgnoreCase("niv1") && niv1) {
            niveaux.get(9).setBackground(Color.BLUE);

            m.carteAPiocher = 2;
        }
        if (niveaux.get(8).getActionCommand().equalsIgnoreCase("niv2") && niv2) {
            niveaux.get(8).setBackground(Color.BLUE);
        }
        if (niveaux.get(7).getActionCommand().equalsIgnoreCase("niv3") && niv3) {
            niveaux.get(7).setBackground(Color.BLUE);
            m.carteAPiocher = 3;
        }
        if (niveaux.get(6).getActionCommand().equalsIgnoreCase("niv4") && niv4) {
            niveaux.get(6).setBackground(Color.BLUE);
        }

        if (niveaux.get(5).getActionCommand().equalsIgnoreCase("niv5") && niv5) {
            niveaux.get(5).setBackground(Color.BLUE);
        }
        if (niveaux.get(4).getActionCommand().equalsIgnoreCase("niv6") && niv6) {
            niveaux.get(4).setBackground(Color.BLUE);
            m.carteAPiocher = 4;
        }
        if (niveaux.get(3).getActionCommand().equalsIgnoreCase("niv7") && niv7) {
            niveaux.get(3).setBackground(Color.BLUE);
        }
        if (niveaux.get(2).getActionCommand().equalsIgnoreCase("niv8") && niv8) {
            niveaux.get(2).setBackground(Color.BLUE);
            m.carteAPiocher = 5;
        }
        if (niveaux.get(1).getActionCommand().equalsIgnoreCase("niv9") && niv9) {
            niveaux.get(1).setBackground(Color.BLUE);
        }
        if (niveaux.get(0).getActionCommand().equalsIgnoreCase("niv10") && niv10) {
            niveaux.get(0).setBackground(Color.BLUE);
        }

        notifierObservateur(m);

    }

    public void getJoueurAct(int joueurAct) {
        switch (joueurAct) {

            case 1:
                joueur1.setEnabled(true);
                joueur2.setEnabled(false);
                joueur3.setEnabled(false);
                joueur4.setEnabled(false);

                break;
            case 2:
                joueur1.setEnabled(false);
                joueur2.setEnabled(true);
                joueur3.setEnabled(false);
                joueur4.setEnabled(false);
                break;
            case 3:
                joueur1.setEnabled(false);
                joueur2.setEnabled(false);
                joueur3.setEnabled(true);
                joueur4.setEnabled(false);
                break;
            case 4:
                joueur1.setEnabled(false);
                joueur2.setEnabled(false);
                joueur3.setEnabled(false);
                joueur4.setEnabled(true);
                break;
        }
    }

    public void setPions(Coordonnees j1, Coordonnees j2, Coordonnees j3, Coordonnees j4) {

        pion1 = new Pion(2, 7, Color.red);
        pion2 = new Pion(95, 7, Color.GREEN);

        if (nbJoueur >= 3) {
            pion3 = new Pion(2, 70, Color.BLUE);
        }
        if (nbJoueur == 4) {
            pion4 = new Pion(95, 70, Color.orange);
        }
        for (JButton jb : boutons) {

            if (jb.getText().equals(j1.afficherCoord())) {
                jb.add(pion1);
            }
            if (jb.getText().equals(j2.afficherCoord())) {
                jb.add(pion2);
            }
            if (jb.getText().equals(j3.afficherCoord()) && nbJoueur >= 3) {
                jb.add(pion3);
            }
            if (jb.getText().equals(j4.afficherCoord()) && nbJoueur == 4) {
                jb.add(pion4);
            }

        }
    }

    public void setCouleurJoueur() {
        for (JButton jb : joueurs) {
            if (jb.getActionCommand().equals("Explorateur")) {
                jb.setForeground(Color.GREEN);

            }
            if (jb.getActionCommand().equals("Plongeur")) {
                jb.setForeground(Color.BLACK);
            }
            if (jb.getActionCommand().equals("Pilote")) {
                jb.setForeground(Color.BLUE);
            }
            if (jb.getActionCommand().equals("Navigateur")) {
                jb.setForeground(Color.yellow);
            }
            if (jb.getActionCommand().equals("Ingénieur")) {
                jb.setForeground(Color.RED);
            }
            if (jb.getActionCommand().equals("Messager")) {
                jb.setForeground(Color.GRAY);
            }
        }

    }

    public void setNomsTuiles() {
        nomsTuiles.add("Le Pont des Abimes");
        nomsTuiles.add("La Porte de Bronze");
        nomsTuiles.add("La Caverne des Ombres");
        nomsTuiles.add("La Porte de Fer");        ///
        nomsTuiles.add("La Porte d’Or");            //
        nomsTuiles.add("Les Falaises de l’Oubli");
        nomsTuiles.add("Le Palais de Corail");
        nomsTuiles.add("La Porte d’Argent");       //   
        nomsTuiles.add("Les Dunes de l’Illusion");
        nomsTuiles.add("Heliport");                 //
        nomsTuiles.add("La Porte de Cuivre");           //
        nomsTuiles.add("Le Jardin des Hurlements");

        nomsTuiles.add("La Foret Pourpre");
        nomsTuiles.add("Le Lagon Perdu");
        nomsTuiles.add("Le Marais Brumeux");
        nomsTuiles.add("Observatoire");
        nomsTuiles.add("Le Rocher Fantome");
        nomsTuiles.add("La Caverne du Brasier");
        nomsTuiles.add("Le Temple du Soleil");
        nomsTuiles.add("Le Temple de La Lune");
        nomsTuiles.add("Le Palais des Marees");
        nomsTuiles.add("Le Val du Crepuscule");
        nomsTuiles.add("La Tour du Guet");
        nomsTuiles.add("Le Jardin des Murmures");
    }

    public void lock() {
        terminerTour.setEnabled(false);
        seDeplacer.setEnabled(false);
        assecher.setEnabled(false);
        donnerCarte.setEnabled(false);
        gagnerTresor.setEnabled(false);
        compSpe.setEnabled(false);
    }

    public class Pion extends JPanel {

        private int x = 5;
        private int y = 50;
        private Color color;

        public Pion(int x, int y, Color color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }

        @Override
        public void paintComponent(Graphics g) {
            pion = (Graphics2D) g;
            pion.setColor(Color.black);
            pion.drawOval(x, y, 20, 20);
            pion.setColor(color);
            pion.fillOval(x, y, 20, 20);

        }

    }
}
