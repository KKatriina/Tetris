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
public class LisaPala extends Pala {
    private Suunta ksuunta;
    private Pala pala;
    
    public LisaPala(int x, int y, Suunta ksuunta, Pala pala) {
        super(x, y);
        this.ksuunta = ksuunta;
        this.pala = pala;
    }
    
    public Suunta getKiinnityssuunta() {
        return this.ksuunta;
    }      
    
    public void kaannaVastapaivaan() {
        int uusiX = this.pala.getX();
        int uusiY = this.pala.getY();
        
        
        
        if (this.ksuunta == OIKEA) {
            this.ksuunta = YLA;
            uusiY -= 1;
        } else if (this.ksuunta == VASEN) {
            this.ksuunta = ALA;
            uusiY += 1;
        } else if (this.ksuunta == YLA) {
            this.ksuunta = VASEN;
            uusiX -= 1;
        } else {
            this.ksuunta = OIKEA;
            uusiX++;
        }
        
        this.siirra(uusiX, uusiY);
    }
    
    public Pala getPaaPala() {
        return this.pala;
    }

    public void asetaUusiPaaPala(Pala pala) {
        this.pala = pala;
    }
}
