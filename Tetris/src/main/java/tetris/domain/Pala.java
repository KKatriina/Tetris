/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.domain;

import tetris.tetris.Kiinnityssuunta;
import static tetris.tetris.Kiinnityssuunta.ALA;
import static tetris.tetris.Kiinnityssuunta.OIKEA;
import static tetris.tetris.Kiinnityssuunta.VASEN;
import static tetris.tetris.Kiinnityssuunta.YLA;

/**
 *
 * @author kkerokos
 */
public class Pala {
    private int x;
    private int y;
    
    public Pala(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void siirra(Kiinnityssuunta suunta) {
        //tama ei osaa varoa seinia
        if (suunta == OIKEA) {
            siirra(this.x + 1, this.y);
        } 
        if (suunta == VASEN) {
            siirra(this.x - 1, this.y);
        }
        if (suunta == ALA) {
            siirra(this.x, this.y + 1);
        }
    }
    
    public void siirra(int uusiX, int uusiY) {
        this.x = uusiX;
        this.y = uusiY;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
}
