/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile_Interdite.cartes;

/**
 *
 * @author peyrinfl
 */
        
import java.util.ArrayList;
        
public class PileInondation {
    private ArrayList<CarteInondation> cartesInondation = new ArrayList<>();
    private ArrayList<CarteInondation> cartesInondDefausse = new ArrayList<>();
    
    public PileInondation(ArrayList<CarteInondation> cartesInondation) {
        this.cartesInondation = cartesInondation;
    }
    public void Defausser(){}
}
