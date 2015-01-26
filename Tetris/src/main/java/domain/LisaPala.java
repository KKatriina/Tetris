/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import tetris.tetris.Kiinnityssuunta;
import static tetris.tetris.Kiinnityssuunta.ALA;
import static tetris.tetris.Kiinnityssuunta.OIKEA;
import static tetris.tetris.Kiinnityssuunta.VASEN;
import static tetris.tetris.Kiinnityssuunta.YLA;

/**
 *
 * @author kkerokos
 */
public class LisaPala extends Pala {
    private Kiinnityssuunta ksuunta;
    private Pala pala;
    
    public LisaPala(int x, int y, Kiinnityssuunta ksuunta, Pala pala) {
        super(x, y);
        this.ksuunta = ksuunta;
        this.pala = pala;
    }
    
    public Kiinnityssuunta getKiinnityssuunta() {
        return this.ksuunta;
    }
    
    public void asetaUusiPaaPala(Pala pala) {
        this.pala = pala;
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
}
