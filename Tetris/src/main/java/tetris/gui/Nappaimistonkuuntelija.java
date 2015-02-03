/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.gui;

import tetris.domain.Palikka;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import tetris.peli.Tetris;
import tetris.tetris.Suunta;
import static tetris.tetris.Suunta.ALA;
import static tetris.tetris.Suunta.OIKEA;
import static tetris.tetris.Suunta.VASEN;
import static tetris.tetris.Suunta.YLA;

/**
 *
 * @author kkerokos
 */
public class Nappaimistonkuuntelija implements KeyListener {

    private Tetris peli;
    private Pelikentta kentta;
    
    public Nappaimistonkuuntelija(Tetris peli, Pelikentta kentta) {
        this.peli = peli;
        this.kentta = kentta;
    }
    

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            peli.getPalikka().kaannaVastapaivaan();
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            peli.getPalikka().siirra(Suunta.ALA);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            peli.getPalikka().siirra(Suunta.OIKEA);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            peli.getPalikka().siirra(Suunta.VASEN);
        }
        
        this.kentta.paivita();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }


    
}
