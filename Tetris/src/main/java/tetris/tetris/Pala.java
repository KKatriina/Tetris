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
public class Pala {
    private int x;
    private int y;
    
    public Pala(int x, int y) {
        this.x = x;
        this.y = y;
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
