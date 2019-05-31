/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile_Interdite;

import java.util.Comparator;

/**
 *
 * @author wawve
 */
public class Coordonnees implements Comparable<Coordonnees> {

    private int x;
    private int y;

    public Coordonnees(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String afficherCoord() {
        return ("("+this.x+","+this.y+")");
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    

    @Override
    public int compareTo(Coordonnees c) {
          return afficherCoord().compareTo(c.afficherCoord());
    }
}
