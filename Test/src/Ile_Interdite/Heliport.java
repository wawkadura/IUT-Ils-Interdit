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
public class Heliport extends Tuile {
    
    private boolean depart;

    public Heliport(Coordonnees C) {
        super(C);
    }

    public boolean isDepart() {
        return depart;
    }

    public void setDepart(boolean depart) {
        this.depart = depart;
    }
    
    public String getType() {
        return "Héliport";
    }
    
}
