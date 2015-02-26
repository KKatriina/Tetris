/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.peli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import tetris.gui.Pelikentta;
import static tetris.tetris.Suunta.ALA;

/**
 *
 * @author kkerokos
 */

/**
 * Luokka sisältää pelin varsinaiseen pelaamiseen liittyviä metodeja
 */
public class Ajastin extends Timer implements ActionListener {
    private Pelikentta kentta;
    private boolean jatkuu;
    private int nopeus = 1000;
    private Logiikka logiikka = null;

    
    public Ajastin() {
        super(1000, null);

        this.jatkuu = true;
    
        addActionListener(this);
        setInitialDelay(1000);
    }
    
    public void setLogiikka(Logiikka logiikka) {
        this.logiikka = logiikka;
    }
        
    public void setPelikentta(Pelikentta kentta) {
        this.kentta = kentta;
    }
    
    public void setJatkuu(boolean jatkuu) {
        this.jatkuu = jatkuu;
    }
    
    public int getNopeus() {
        return this.nopeus;
    }
    
    public void setNopeus(int n) {
        this.nopeus = n;
    }
    
    public boolean getJatkuu() {
        return this.jatkuu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!jatkuu) {
            return;
        }
                
        this.logiikka.pelikierroksenLoppu();
        this.logiikka.siirraPalikkaa(ALA);

        setDelay(nopeus);

        kentta.paivita();
    }
        
    /**
    * Metodi pienentaa pelin nopeutta
    */
    public void saadaNopeutta() {
        if (nopeus > 200) {
            nopeus -= 5;
        } else if (nopeus > 100) {
            nopeus -= 2;
        } else if (nopeus > 50) {
            nopeus -= 1;
        }
    }
    




    
    
    
    
}
