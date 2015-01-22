/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.tetris;

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
        return ksuunta;
    }
    
    public void kaannaVastapaivaan() {
        int uusiX = this.pala.getX();
        int uusiY = this.pala.getY();
        
        
        //korjaa nämä - käännä palaa ja vaihda suunta
        if (ksuunta == OIKEA) {
            ksuunta = YLA;
            uusiY -= 1;
        }
        if (ksuunta == VASEN) {
            ksuunta = ALA;
            uusiY -= 1;
        }
        if (ksuunta == YLA) {
            ksuunta = VASEN;
            uusiX -= 1;
        }
        if (ksuunta == ALA) {
            ksuunta = OIKEA;
            uusiX++;
        }
        
        this.siirra(uusiX, uusiY);
    }
}
