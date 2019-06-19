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
public class Niveau {
    private int nivEau;
    private int nbPiocheInond;
    
    public int getNivEau(){
        return nivEau;
    }

    public int getNbPiocheInond() {
        return nbPiocheInond;
    }

    public void setNbPiocheInond(int nbPiocheInond) {
        this.nbPiocheInond = nbPiocheInond;
    }
    
    public void setNivEau(int niv){
            this.nivEau = niv;
            if(niv >= 0 && niv <= 1) {
                this.setNbPiocheInond(2);  
            }
            else if(niv >= 2 && niv <= 4) {
                this.setNbPiocheInond(3);
            }
            else if(niv == 5 || niv == 6) {
                this.setNbPiocheInond(4);
            }
            else if (niv == 7 || niv == 8) {
                this.setNbPiocheInond(5);
            }
    }
    
    public boolean isSubmergee() {
        return (this.getNivEau() > 8);
    }
}
