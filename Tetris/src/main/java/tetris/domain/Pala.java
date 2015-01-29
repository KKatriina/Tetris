/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.domain;

import tetris.tetris.Suunta;
import static tetris.tetris.Suunta.ALA;
import static tetris.tetris.Suunta.OIKEA;
import static tetris.tetris.Suunta.VASEN;
import static tetris.tetris.Suunta.YLA;

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
    
    public void siirra(Suunta suunta) {
        //tama ei osaa varoa seinia
        if (suunta == Suunta.OIKEA) {
            siirra(this.x + 1, this.y);
        } 
        if (suunta == Suunta.VASEN) {
            siirra(this.x - 1, this.y);
        }
        if (suunta == Suunta.ALA) {
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

    public boolean osuuSeinaan(Suunta suunta) {
        if (suunta == Suunta.ALA) {
            return (this.y > 20);
        } else if (suunta == Suunta.OIKEA) {
            return (this.x > 10);
        } else if (suunta == Suunta.VASEN) {
            return (this.x < 0);
        } else {
            return (this.y < 0);
        }
    }


    
}
