/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.gui;

import tetris.domain.Pala;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import tetris.peli.Tetris;

/**
 *
 * @author kkerokos
 */
public class Pelikentta extends JPanel {
    private Tetris peli;
    private int sivunPituus;
    
    public Pelikentta(Tetris peli, int sivunPituus) {
        this.peli = peli;
        this.sivunPituus = sivunPituus;
    }
    
    public void paivita() {
        this.repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        //olisi hauskaa, jos palat eri värisiä - staattinen muuttuja "Vari"? katsotaan myöhemmin
        
        g.setColor(Color.blue);
        
        for (Pala p : this.peli.getPalikka().getPalat()) {
            g.fill3DRect(sivunPituus * p.getX(), sivunPituus * p.getY(), sivunPituus, sivunPituus, true);
        }
        
        for (Pala p : this.peli.getPohjanPalat()) {
            g.fill3DRect(sivunPituus * p.getX(), sivunPituus * p.getY(), sivunPituus, sivunPituus, true);
        }
    }
}
