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
import tetris.peli.Ajastin;
import tetris.peli.Logiikka;

/**
 *
 * @author kkerokos
 */

/**
 * Luokka tarjoaa pelissä tarvittavan graafisen käyttöliittymän
 */
public class Kayttoliittyma implements Runnable {
    private JFrame frame;
    private Logiikka logiikka;
    private int sivunPituus;
    private Pelikentta kentta;
    private Nappaimistonkuuntelija nk;
    
    public Kayttoliittyma(Logiikka logiikka, int sivunPituus) {
        this.logiikka = logiikka;
        this.sivunPituus = sivunPituus;
    }

    

    @Override
    public void run() {
        frame = new JFrame("Tetris!");
        int leveys = (logiikka.getLeveys() * sivunPituus) + 10;
        int korkeus = (logiikka.getKorkeus() * sivunPituus) + 10;
 
        frame.setPreferredSize(new Dimension(leveys, korkeus));
 
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
 
        luoKomponentit(frame.getContentPane());
 
        frame.pack();
        frame.setVisible(true);
    }
    
    public void luoKomponentit(Container container) {
        kentta = new Pelikentta(this.logiikka, this.sivunPituus);
        
        container.add(kentta);
        
        nk = new Nappaimistonkuuntelija(this.logiikka, kentta);
        frame.addKeyListener(nk);
    }

    public JFrame getFrame() {
        return frame;
    }
    
    public Pelikentta getKentta() {
        return this.kentta;
    }
    
    
    
}
