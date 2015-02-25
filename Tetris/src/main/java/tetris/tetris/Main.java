/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.tetris;

import tetris.gui.Kayttoliittyma;
import javax.swing.SwingUtilities;
import tetris.peli.Ajastin;
import tetris.peli.Logiikka;

/**
 *
 * @author kkerokos
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Logiikka logiikka = new Logiikka(10, 20);
        Ajastin ajastin = new Ajastin();
        logiikka.setAjastin(ajastin);
        ajastin.setLogiikka(logiikka);
        Kayttoliittyma kali = new Kayttoliittyma(logiikka, 20);
        SwingUtilities.invokeLater(kali);
        

        while (kali.getKentta() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Pelikenttä puuttuu!");
            }
        }
 
        ajastin.setPelikentta(kali.getKentta());
        ajastin.start();
       
       
    }
    
}
