/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile_Interdite.IHM;

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
    private final Font jou = new Font(Font.MONOSPACED, Font.BOLD, 30);
    private JButton eau;
    private String joueurCourant;
    private int joueurAct = 1;
    private int nbDec = 3;
    private JLabel decompte;
    private JPanel monteeEauDroit;
    private JLabel monteeEau;
    private JButton tuile, seDeplacer, assecher, donnerCarte, gagnerTresor, compSpe, terminerTour;
    ArrayList<JButton> boutons = new ArrayList<>();
    ArrayList<JButton> cartes = new ArrayList<>();
    ArrayList<JButton> niveaux = new ArrayList<>();
    private boolean niv1, niv2, niv3, niv4, niv5, niv6, niv7, niv8, niv9, niv10 = false;
    private boolean deplacer;
    private boolean Assecher;
    private Graphics pion;

    public VueAventurier() {
        fenetre = new JFrame("Ile Interdite");

        ////////////////////////////////////////////////////////////JOUEURS/////////////////////////////////////////////////////////////////////////
        joueurBas = new JPanel(new GridLayout(1, 5));

        joueur1 = new JButton("");
        joueur1.setFont(jou);
        joueur1.setBorder(null);
        joueur2 = new JButton("");
        joueur2.setFont(jou);
        joueur2.setBorder(null);
        joueur3 = new JButton("");
        joueur3.setFont(jou);
        joueur3.setBorder(null);
        joueur4 = new JButton("");
        joueur4.setFont(jou);
        joueur4.setBorder(null);

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
                nbDec = nbDec - 1;
                setNbAct(nbDec, joueurAct);
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
                nbDec = nbDec - 1;
                setNbAct(nbDec, joueurAct);

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
                setNbAct(nbDec, joueurAct);

                seDeplacer.setEnabled(false);
                assecher.setEnabled(false);
                donnerCarte.setEnabled(false);
                gagnerTresor.setEnabled(false);
                compSpe.setEnabled(false);
                terminerTour.setEnabled(false);
            }
        });
        compSpe = new JButton("Compétence spéciale");
        compSpe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
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

        JButton statue = new JButton("La Statue du Zéphyr");
        statue.setBackground(Color.ORANGE);
        carteHaut.add(statue);
        statue.setBorder(null);
        statue.setEnabled(false);

        JButton cristal = new JButton("Le Cristal Ardent");
        cristal.setBackground(Color.RED);
        carteHaut.add(cristal);
        cristal.setBorder(null);
        cristal.setEnabled(false);

        JLabel espace1 = new JLabel("");
        carteHaut.add(espace1);

        JLabel cartesJoueur = new JLabel("Cartes joueur courant : ");
        carteHaut.add(cartesJoueur);

        JLabel espace2 = new JLabel("");
        carteHaut.add(espace2);

        JButton pierre = new JButton("La Pierre Sacrée");
        pierre.setBackground(Color.GRAY);
        carteHaut.add(pierre);
        pierre.setBorder(null);
        pierre.setEnabled(false);

        JButton calice = new JButton("Le Calice de Londe");
        calice.setBackground(Color.CYAN);
        carteHaut.add(calice);
        calice.setBorder(null);
        calice.setEnabled(false);

        for (int i = 0; i < 7; i++) {
            JButton carteJoueur = new JButton("Carte");
            carteJoueur.setEnabled(false);
            carteHaut.add(carteJoueur);
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
                Coordonnees C = new Coordonnees(c, l);
                boutons.add(tuile);
                tuile.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        nbDec = nbDec - 1;
                        setNbAct(nbDec, joueurAct);

                        Message m = new Message();
                        m.type = TypesMessages.CHOIX_TUILE;
                        m.c = C;
                        m.deplacer = deplacer;
                        m.assecher = Assecher;
                        notifierObservateur(m);
                        seDeplacer.setEnabled(true);
                        assecher.setEnabled(true);
                        donnerCarte.setEnabled(true);
                        gagnerTresor.setEnabled(true);
                        compSpe.setEnabled(true);
                        terminerTour.setEnabled(true);
                        mettreAJourActions();
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

    public void afficherNiv() {
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

    public void mettreAJourPions() {

    }

    public void mettreAJourCartes(ArrayList<CarteTresor> cartes) {
        int i = 0;
        while (i < cartes.size()) {
            this.cartes.get(i).setText(cartes.get(i).getFonction());
            this.cartes.get(i).setEnabled(true);
            i++;
        }

    }

    public void mettreAJourActions() {
        terminerTour.setEnabled(true);

    }

    public void mettreAJourNivEau() {
        Message m = new Message();
        m.type = TypesMessages.DIFFICULTE;
        for (JButton jb : niveaux) {
            if (jb.getActionCommand().equalsIgnoreCase("niv1") && niv1) {
                jb.setBackground(Color.BLUE);

                m.carteAPiocher = 2;
            }
            if (jb.getActionCommand().equalsIgnoreCase("niv2") && niv2) {
                jb.setBackground(Color.BLUE);
            }
            if (jb.getActionCommand().equalsIgnoreCase("niv3") && niv3) {
                jb.setBackground(Color.BLUE);
                m.carteAPiocher = 3;
            }
            if (jb.getActionCommand().equalsIgnoreCase("niv4") && niv4) {
                jb.setBackground(Color.BLUE);
            }

            if (jb.getActionCommand().equalsIgnoreCase("niv5") && niv5) {
                jb.setBackground(Color.BLUE);
            }
            if (jb.getActionCommand().equalsIgnoreCase("niv6") && niv6) {
                jb.setBackground(Color.BLUE);
                m.carteAPiocher = 4;
            }
            if (jb.getActionCommand().equalsIgnoreCase("niv7") && niv7) {
                jb.setBackground(Color.BLUE);
            }
            if (jb.getActionCommand().equalsIgnoreCase("niv8") && niv8) {
                jb.setBackground(Color.BLUE);
                m.carteAPiocher = 5;
            }
            if (jb.getActionCommand().equalsIgnoreCase("niv9") && niv9) {
                jb.setBackground(Color.BLUE);
            }
            if (jb.getActionCommand().equalsIgnoreCase("niv10") && niv10) {
                jb.setBackground(Color.BLUE);
            }
        }
        notifierObservateur(m);

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
//                nbDec = nbDec - 1;
//                setNbAct(nbDec, joueurAct);
//
//                seDeplacer.setEnabled(false);
//                assecher.setEnabled(false);
//                donnerCarte.setEnabled(false);
//                gagnerTresor.setEnabled(false);
//                compSpe.setEnabled(false);
//                terminerTour.setEnabled(false);
