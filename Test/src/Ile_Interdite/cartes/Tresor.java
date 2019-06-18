/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile_Interdite.cartes;

/**
 *
 * @author wawve
 */
public class Tresor extends CarteTrésor {
    private String type;
    
    public Tresor(String type){
        this.type=type;
    }

    public String getType() {
        return type;
    }
    
}
