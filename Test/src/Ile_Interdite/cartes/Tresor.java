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
    private String fonction ; 
    
    public Tresor(String fonction){
        this.fonction = fonction;
    }
    @Override
    public String getFonction() {
        return fonction;
    }
    
}
