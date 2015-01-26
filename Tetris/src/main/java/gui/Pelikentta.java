/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Graphics;
import javax.swing.JPanel;
import peli.Tetris;

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
    }
}
