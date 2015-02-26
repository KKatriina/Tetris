/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.domain;


import java.awt.Color;
import static java.awt.Color.blue;
import tetris.tetris.Suunta;


/**
 *
 * @author kkerokos
 */
/**
 * Luokka tarjoaa metodeja yhden palan koordinaattien muuttamiseen
 */
public class Pala {
    private int x;
    private int y;
    private Color vari;
    
    
    public Pala(int x, int y) {
        this.vari = blue;
        this.x = x;
        this.y = y;
    }
    
    /**
    * Metodi siirtää palaa koordinaatistossa yhden askeleen haluttuun suuntaan
    * ottamatta huomioon seiniin tai toisiin palikoihin törmäämistä
    *
    * @param   suunta   suunta, johon palaa halutaan siirtää koordinaatistossa
    */
    public void siirra(Suunta suunta) {
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
    
    /**
    * Metodi vaihtaa palan koordinaatit
    * parametreinä annettuihin koordinaatteihin
    *
    * @param   uusiX    haluttu uusi x-koordinaatti
    * @param   uusiY    haluttu uusi y-koordinaatti
    */
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

    /**
    * Metodi tarkistaa, voiko palaa siirtää haluttuun suuntaan
    * ilman että pala osuu pelikentän seinään
    *
    * @param   suunta   suunta, johon palaa aiotaan siirtää
    * @param   leveys   pelikentän leveys
    * @param   korkeus  pelikentän korkeus
    * 
    * @return           true, jos pala osuu siirrettäessä seinään, muuten false
    */
    public boolean osuuSeinaan(Suunta suunta, int leveys, int korkeus) {
        if (suunta == Suunta.ALA) {
            return (this.y >= (korkeus - 2));
        } else if (suunta == Suunta.OIKEA) {
            return (this.x >= (leveys - 1));
        } else if (suunta == Suunta.VASEN) {
            return (this.x <= 0);
        } else {
            return (this.y <= 0);
        }
    }
    
    /**
    * Metodi tarkistaa, voiko palaa siirtää haluttuun suuntaan
    * ilman että pala osuu pelikentän seinien ulkopuolelle
    *
    * @param   suunta   suunta, johon palaa aiotaan siirtää
    * @param   leveys   pelikentän leveys
    * @param   korkeus  pelikentän korkeus
    * 
    * @return           true, jos pala osuu siirrettäessä pelikentän ulkopuolelle, muuten false
    */
    public boolean meneekoSeinanLapi(Suunta suunta, int leveys, int korkeus) {
        if (suunta == Suunta.ALA) {
            return (this.y > (korkeus - 2));
        } else if (suunta == Suunta.OIKEA) {
            return (this.x > (leveys - 1));
        } else if (suunta == Suunta.VASEN) {
            return (this.x < 0);
        } else {
            return (this.y < 0);
        }
    }
    
    public void setVari(Color vari) {
        this.vari = vari;
    }
    
    public Color getVari() {
        return this.vari;
    }


    
}
