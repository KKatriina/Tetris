/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.gui;

import tetris.domain.Pala;
import java.awt.Graphics;
import javax.swing.JPanel;
import tetris.peli.Tetris;

/**
 *
 * @author kkerokos
 */

/**
 * Luokka luo piirtoalustan ja piirtää sille pelissä tarvittavat palat ja palikan
 */
public class Pelikentta extends JPanel {
    private Tetris peli;
    private int sivunPituus;
    
    public Pelikentta(Tetris peli, int sivunPituus) {
        this.peli = peli;
        this.sivunPituus = sivunPituus;
    }
    
    /**
    * Metodi piirtää pelikentän uudestaan
    * 
    */
    public void paivita() {
        this.repaint();
    }
    
    /**
    * Metodi piirtää pelikentälle palikkaan ja pohjan paloihin 
    * kuuluvat palat samankokoisina neliöinä
    *
    * @param   g    Graphics
    * 
    */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        
        for (Pala p : this.peli.getPalikka().getPalat()) {
            g.setColor(p.getVari());
            g.fill3DRect(sivunPituus * p.getX(), sivunPituus * p.getY(), sivunPituus, sivunPituus, true);
        }
        
        for (Pala p : this.peli.getPohjanPalat()) {
            g.setColor(p.getVari());
            g.fill3DRect(sivunPituus * p.getX(), sivunPituus * p.getY(), sivunPituus, sivunPituus, true);
        }
        
    
    }
}
