/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.tetris;

import java.util.ArrayList;
import java.util.List;
import tetris.domain.Palikka;
import tetris.gui.Kayttoliittyma;
import javax.swing.SwingUtilities;
import tetris.domain.LisaPala;
import tetris.domain.Pala;
import tetris.peli.Tetris;
import static tetris.tetris.Suunta.ALA;
import static tetris.tetris.Suunta.OIKEA;
import static tetris.tetris.Suunta.VASEN;

/**
 *
 * @author kkerokos
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Tetris peli = new Tetris(10, 20);
        Kayttoliittyma kali = new Kayttoliittyma(peli, 20);
        SwingUtilities.invokeLater(kali);
        

        while (kali.getKentta() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Pelikenttä puuttuu!");
            }
        }
 
        peli.setPelikentta(kali.getKentta());
        peli.start();
       
       
    }
    
}
