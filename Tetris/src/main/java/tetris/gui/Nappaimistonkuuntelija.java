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

/**
 * Luokka kuuntelee nuolinäppäimiä ja siirtää ja kääntää pelin palikkaa painetun nuolinäppäimen mukaisesti
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
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            peli.getPalikka().kaannaVastapaivaan();
            //tässä pitää katsoa, ettei käännytä pohjan palojen päälle! se on vissiin toistaiseksi mahdollista
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            peli.siirraPalikkaa(Suunta.ALA);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            peli.siirraPalikkaa(Suunta.OIKEA);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            peli.siirraPalikkaa(Suunta.VASEN);
        }
        
        peli.pelikierroksenLoppu();
        this.kentta.paivita();
    }
    

    @Override
    public void keyTyped(KeyEvent e) {
        
    }


    @Override
    public void keyReleased(KeyEvent e) {
    }


    
}
