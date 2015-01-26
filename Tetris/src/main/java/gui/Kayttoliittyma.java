/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import peli.Tetris;

/**
 *
 * @author kkerokos
 */
public class Kayttoliittyma implements Runnable {
    private JFrame frame;
    private Tetris peli;
    private int sivunPituus;
    private Pelikentta kentta;
    
    public Kayttoliittyma(Tetris peli, int sivunPituus) {
        this.peli = peli;
        this.sivunPituus = sivunPituus;
    }
    

    @Override
    public void run() {
        frame = new JFrame("Tetris!");
        int leveys = (peli.getLeveys() + 1) * sivunPituus + 10;
        int korkeus = (peli.getKorkeus() + 2) * sivunPituus + 10;
 
        frame.setPreferredSize(new Dimension(leveys, korkeus));
 
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
 
        luoKomponentit(frame.getContentPane());
 
        frame.pack();
        frame.setVisible(true);
    }
    
    public void luoKomponentit(Container container) {
        kentta = new Pelikentta(peli, sivunPituus);
        
        container.add(kentta);
        
        Nappaimistonkuuntelija nk = new Nappaimistonkuuntelija(peli.getPalikka());
        getFrame().addKeyListener(nk);
    }

    private JFrame getFrame() {
        return frame;
    }
    
}
