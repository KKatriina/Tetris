/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.gui;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import tetris.peli.Logiikka;
import tetris.tetris.Suunta;


/**
 *
 * @author kkerokos
 */

/**
 * Luokka kuuntelee nuolinäppäimiä ja siirtää ja kääntää pelin palikkaa painetun nuolinäppäimen mukaisesti
 */
public class Nappaimistonkuuntelija implements KeyListener {
    private Logiikka logiikka;
    private Pelikentta kentta;
    
    public Nappaimistonkuuntelija(Logiikka logiikka, Pelikentta kentta) {
        this.logiikka = logiikka;
        this.kentta = kentta;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            logiikka.kaannaVastapaivaan();
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            logiikka.siirraPalikkaa(Suunta.ALA);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            logiikka.siirraPalikkaa(Suunta.OIKEA);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            logiikka.siirraPalikkaa(Suunta.VASEN);
        }
        
        this.kentta.paivita();
    }
    
    @Override
    public void keyTyped(KeyEvent e) {    
    }


    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
