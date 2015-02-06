/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.gui;

import tetris.domain.Palikka;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import tetris.peli.Tetris;

/**
 *
 * @author kkerokos
 */

/**
 * Luokka tarjoaa pelissä tarvittavan graafisen käyttöliittymän
 */
public class Kayttoliittyma implements Runnable {
    private JFrame frame;
    private Tetris peli;
    private int sivunPituus;
    private Pelikentta kentta;
    private Nappaimistonkuuntelija nk;
    
    public Kayttoliittyma(Tetris peli, int sivunPituus) {
        this.peli = peli;
        this.sivunPituus = sivunPituus;
    }
    

    @Override
    public void run() {
        frame = new JFrame("Tetris!");
        int leveys = (peli.getLeveys() * sivunPituus) + 10;
        int korkeus = (peli.getKorkeus() * sivunPituus) + 10;
 
        frame.setPreferredSize(new Dimension(leveys, korkeus));
 
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
 
        luoKomponentit(frame.getContentPane());
 
        frame.pack();
        frame.setVisible(true);
    }
    
    public void luoKomponentit(Container container) {
        kentta = new Pelikentta(this.peli, this.sivunPituus);
        
        container.add(kentta);
        
        nk = new Nappaimistonkuuntelija(this.peli, kentta);
        frame.addKeyListener(nk);
    }

    public JFrame getFrame() {
        return frame;
    }
    
    public Pelikentta getKentta() {
        return this.kentta;
    }
    
    
    
}
