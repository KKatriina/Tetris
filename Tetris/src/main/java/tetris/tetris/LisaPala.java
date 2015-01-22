/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.tetris;

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
    
    public void siirra() {
        
    }
}
