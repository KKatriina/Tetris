/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.Palikka;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import tetris.tetris.Kiinnityssuunta;
import static tetris.tetris.Kiinnityssuunta.ALA;
import static tetris.tetris.Kiinnityssuunta.OIKEA;
import static tetris.tetris.Kiinnityssuunta.VASEN;
import static tetris.tetris.Kiinnityssuunta.YLA;

/**
 *
 * @author kkerokos
 */
public class Nappaimistonkuuntelija implements KeyListener {

    private Palikka palikka;
    
    public Nappaimistonkuuntelija(Palikka palikka) {
        this.palikka = palikka;
    }
    
    public void setPalikka(Palikka palikka) {
        this.palikka = palikka;
    }
    

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            palikka.kaannaVastapaivaan();
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            palikka.siirra(ALA);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            palikka.siirra(OIKEA);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            palikka.siirra(VASEN);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }


    
}
