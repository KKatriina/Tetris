/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.peli;

import java.util.ArrayList;
import java.util.List;
import tetris.domain.Pala;
import tetris.domain.Palikka;
import tetris.tetris.Suunta;
import static tetris.tetris.Suunta.ALA;
import static tetris.tetris.Suunta.OIKEA;
import static tetris.tetris.Suunta.VASEN;

/**
 *
 * @author kkerokos
 */
public class Logiikka {
    private Palikka palikka;
    private List<Pala> pohjanPalat;
    private int leveys;
    private int korkeus;
    private Ajastin ajastin = null;
    
    
    public Logiikka(int leveys, int korkeus) {
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.palikka = new Palikka();
        this.pohjanPalat = new ArrayList<Pala>();
    }

    
    public void setAjastin(Ajastin ajastin) {
        this.ajastin = ajastin;
    }
    
    public Ajastin getAjastin() {
        return this.ajastin;
    }
    
    public int getLeveys() {
        return this.leveys;
    }
    
    public int getKorkeus() {
        return this.korkeus;
    }
    

    public Palikka getPalikka() {
        return this.palikka;
    }
    
    public List<Pala> getPohjanPalat() {
        return this.pohjanPalat;
    }
    
        /**
    * Metodi tarkistaa, osuuko palikka pohjaan, ja jos osuu,
    * lisaa palikan palat pohjan paloihin, luo uuden palikan ja 
    * saataa nopeutta. Jos pelikentan alin kerros tayttyy paloista,
    * metodi poistaa alimman palakerroksen.
    */
    public void pelikierroksenLoppu() {
        if (osuuPohjaan()) {
            for (Pala p : palikka.getPalat()) {
                this.pohjanPalat.add(p);
            }
            luoPeliinUusiPalikka();
            ajastin.saadaNopeutta();
        }
        
        while (pohjaTaynna()) {
            alinRiviPois();
        }
    }
    
    /**
    * Metodi luo peliin uuden palikan, ja jos uusi palikka asettuu pohjan
    * palojen paalle, estaa palikan liikkumisen alaspain
    */
    public void luoPeliinUusiPalikka() {
        this.palikka = new Palikka();
            
        for (Pala p : this.palikka.getPalat()) {
            if (this.palikka.osuvatkoPalatPaallekkain(this.pohjanPalat, p)) {
                this.ajastin.setJatkuu(false);
            }
       }
    }
    
    /**
    * Metodi poistaa pelkentän alareunasta alimman rivin palikoita ja siirtää muita
    * pohjan palikoita yhden askeleen alaspäin
    */
    public void alinRiviPois() {
        ArrayList<Pala> poistettavat = new ArrayList<>();
        for (Pala p : pohjanPalat) {
            if (p.getY() >= (this.korkeus - 1)) {
                poistettavat.add(p);
            }
        }
        for (Pala p : poistettavat) {
            pohjanPalat.remove(p);
        }
        for (Pala p : pohjanPalat) {
            p.siirra(ALA);
        }
    }
    
    
    /**
    * Metodi kertoo, osuuko palikka koordinaatiston alareunaan tai pelikentällä oleviin
    * paloihin.
    * 
    * @return       true jos palikka osuu koordinaatiston alareunaan tai pelikentällä oleviin paloihin, muuten false
    */
    public boolean osuuPohjaan() {
        boolean palautettava = false;
        for (Pala p : this.palikka.getPalat()) {
            if (p.getY() >= (this.korkeus - 2)) {
                palautettava = true;
            }
            
            if (osuukoPohjanPaloihin(p, ALA)) {
                palautettava = true;
            }           
        }
        return palautettava;
    }
    
    /**
    * Metodi kertoo, osuuko pelin palikka siirrettäessä pohjan paloihin
    
    * @param suunta     suunta, johon palikkaa halutaan siirtää
    * @return           true, jos pelin palikka osuu siirrettäessä pohjan paloihin, muuten false
    */
    public boolean osuukoPohjanPaloihin(Suunta suunta) {
        for (Pala p : this.palikka.getPalat()) {
            if (osuukoPohjanPaloihin(p, suunta)) {
                return true;
            }
        }
        return false;
    }
    
    
    /**
    * Metodi siirtää pelin palikkaa haluttuun suuntaan, mikäli tämä ei siirrä
    * palikkaa päällekkäin pohjan palojen kanssa 
    * 
    * @param suunta     suunta, johon palikkaa halutaan siirtää
    */
    public void siirraPalikkaa(Suunta suunta) {
        if (!(osuukoPohjanPaloihin(suunta))) {
            this.palikka.siirra(suunta, this.leveys, this.korkeus);
        }
    }
    
    /**
    * Metodi tarkistaa, osuuko yksi pala päällekäin pohjan palojen kanssa, 
    * mikäli palaa siirretään yksi askel haluttuun suuntaan
    * 
    * @param    pala    Pala, jota ollaan liikuttamassa
    * @param    suunta  Suunta, johon palaa ollaan liikuttamassa 
    * @return           true, jos pala osuu siirrettäessä päällekkäin pohjan palojen kanssa,
    *                   muuten false
    */
    public boolean osuukoPohjanPaloihin(Pala pala, Suunta suunta) {
        if (this.pohjanPalat == null) {
            return false;
        }
        if (this.pohjanPalat.isEmpty()) {
            return false;
        }
        if (suunta == ALA) {
            return osuukoPaloihinAlhaalla(pala);
        }
        if (suunta == OIKEA) {
            return osuukoPaloihinOikealla(pala);
        }
        if (suunta == VASEN) {
            return osuukoPaloihinVasemmalla(pala);
        }
        return false;
    }
    
    public boolean osuukoPaloihinAlhaalla(Pala pala) {
        for (Pala p : this.pohjanPalat) {
            if ((p.getY() == pala.getY() + 1) && (pala.getX() == p.getX())) {
                return true;
            }
        }
        return false;
    }
    
    public boolean osuukoPaloihinOikealla(Pala pala) {
        for (Pala p : this.pohjanPalat) {
            if ((p.getY() == pala.getY()) && (pala.getX() + 1 == p.getX())) {
                return true;
            }
        }
        return false;
    }
    
    public boolean osuukoPaloihinVasemmalla(Pala pala) {
        for (Pala p : this.pohjanPalat) {
            if ((p.getY() == pala.getY()) && (pala.getX() - 1 == p.getX())) {
                    return true;
            }
        }
        return false;
    }
    
    
    
    /**
    * Metodi kertoo, onko pelikentän alin palarivi täynnä
    * 
    * @return   true, jos pelikentän alin palarivi on täynnä, muuten false
    */
    public boolean pohjaTaynna() {
        for (int i = 0; i < this.leveys; i++) {
            boolean temp = false;
            for (Pala p : this.pohjanPalat) {
                if (p.getX() == i && p.getY() == (this.korkeus - 2)) {
                    temp = true;
                }
            }
            if (!temp) {
                return false;
            }
        }
        
        return true; 
    }
    
    public void setPalikka(Palikka palikka) {
        this.palikka = palikka;
    }
    
    public void setPohjanPalat(List<Pala> pohjanPalat) {
        this.pohjanPalat = pohjanPalat;
    }

    /**
    * Metodi kääntää pelin palikkaa vastapäivään, mikäli tämä ei saa palikan 
    * paloja asettumaan päällekkäin pohjan palojen kanssa
    */
    public void kaannaVastapaivaan() {
        this.palikka.kaannaVastapaivaan(this.leveys, this.korkeus);
        boolean osuuko = false;
        for (Pala p : this.palikka.getPalat()) {
            if (this.palikka.osuvatkoPalatPaallekkain(this.pohjanPalat, p)) {
                osuuko = true;
            }
        }
        if (osuuko) {
            for (int i = 1; i <= 3; i++) {
                this.palikka.kaannaVastapaivaan(this.leveys, this.korkeus);
            }
        }
    }
}
