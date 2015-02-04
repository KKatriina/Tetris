/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.peli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.Timer;
import tetris.domain.Pala;
import tetris.domain.Palikka;
import tetris.gui.Pelikentta;
import tetris.tetris.Suunta;
import static tetris.tetris.Suunta.ALA;
import static tetris.tetris.Suunta.OIKEA;
import static tetris.tetris.Suunta.VASEN;
import static tetris.tetris.Suunta.YLA;

/**
 *
 * @author kkerokos
 */
public class Tetris extends Timer implements ActionListener {
    private Palikka palikka;
    private List<Pala> pohjanPalat;
    private int leveys;
    private int korkeus;
    private Pelikentta kentta;

    
    public Tetris(int leveys, int korkeus) {
        super(1000, null);
        
        this.palikka = new Palikka();
        this.pohjanPalat = new ArrayList<Pala>();
        this.leveys = leveys;
        this.korkeus = korkeus;
        
        addActionListener(this);
        setInitialDelay(2000);
        

    }
    
    public void setPelikentta(Pelikentta kentta) {
        this.kentta = kentta;
    }
    
    
    public Palikka getPalikka() {
        return this.palikka;
    }
    
    public List<Pala> getPohjanPalat() {
        return this.pohjanPalat;
    }
    
    public int getLeveys() {
        return this.leveys;
    }
    
    public int getKorkeus() {
        return this.korkeus;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.palikka.siirra(Suunta.ALA);
        
        pelikierroksenLoppu();


        kentta.paivita();
    }
    
    public void pelikierroksenLoppu() {
        if (osuuPohjaan(this.palikka, this.pohjanPalat)) {
            for (Pala p : palikka.getPalat()) {
                this.pohjanPalat.add(p);
            }
            this.palikka = new Palikka();
        }
        
        
        if (pohjaTaynna()) {
            alinRiviPois();
        }
    }
    
    public void alinRiviPois() {
        ArrayList<Pala> poistettavat = new ArrayList<Pala>();
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

    public boolean osuuPohjaan(Palikka palikka, List<Pala> pohjanPalat) {
        boolean palautettava = false;
        for (Pala p : this.palikka.getPalat()) {
            if (p.getY() >= (this.korkeus - 2)) {
                palautettava = true;
            }
            
            if (pysahtyykoPohjanPaloihin(p)) {
                palautettava = true;
            }
            
        }
        return palautettava;
    }
    
    public boolean osuukoPohjanPaloihin(Suunta suunta) {
        for (Pala p : this.palikka.getPalat()) {
            if (osuukoPohjanPaloihin(p, suunta)) {
                return true;
            }
        }
        return false;
    }
    
    public void siirraPalikkaa(Suunta suunta) {
        if (!(osuukoPohjanPaloihin(suunta))) {
            this.palikka.siirra(suunta);
        }
    }
    
    public boolean osuukoPohjanPaloihin(Pala pala, Suunta suunta) {
        if (this.pohjanPalat.isEmpty()) {
            return false;
        }
        if (suunta == ALA) {
            for (Pala p : this.pohjanPalat) {
                if ((p.getY() == pala.getY() + 2) && (pala.getX() == p.getX())) {
                    return true;
                }
            }

        }
        if (suunta == YLA) {
            for (Pala p : this.pohjanPalat) {
                if ((p.getY() == pala.getY() - 1) && (pala.getX() == p.getX())) {
                    return true;
                }
            }

        } 
        if (suunta == OIKEA) {
            for (Pala p : this.pohjanPalat) {
                if ((p.getY() == pala.getY()) && (pala.getX() + 1 == p.getX())) {
                    return true;
                }
                if ((p.getY() == pala.getY() + 1) && (pala.getX() == p.getX())) {
                    return true;
                }
            }
        }
        if (suunta == VASEN) {
            for (Pala p : this.pohjanPalat) {
                if ((p.getY() == pala.getY()) && (pala.getX() - 1 == p.getX())) {
                    return true;
                }
                if ((p.getY() == pala.getY() + 1) && (pala.getX() == p.getX())) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean pohjaTaynna() {
        int pohjassaKiinni = 0;
        for (Pala p : pohjanPalat) {
            if (p.getY() > 18) {
                pohjassaKiinni++;
            }
                    
        }
        
        return (pohjassaKiinni >= 10);
    }

    public boolean pysahtyykoPohjanPaloihin(Pala pala) {
        for (Pala p : pohjanPalat) {
            if ((p.getY() == pala.getY() + 1) && (pala.getX() == p.getX())) {
                return true;
            }
        }
        return false;
    }


    
    
    
    
}
