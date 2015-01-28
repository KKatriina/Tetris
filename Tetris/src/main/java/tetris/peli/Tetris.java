/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.peli;

import tetris.domain.Pala;
import tetris.domain.Palikka;
import tetris.gui.Pelikentta;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import tetris.tetris.Kiinnityssuunta;

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
        this.palikka.siirra(Kiinnityssuunta.ALA);
        
        //jos pala osuu alareunaan TAI toiseen palaan, luodaan uusi pala
        if (osuuPohjaan(this.palikka, this.pohjanPalat)) {
            for (Pala p : palikka.getPalat()) {
                this.pohjanPalat.add(p);
            }
            this.palikka = new Palikka();
            
        }
        
        
        if (pohjaTaynna()) {
            for (Pala p : pohjanPalat) {
                if (p.getX() == this.korkeus - 1) {
                    pohjanPalat.remove(p);
                } else {
                    p.siirra(Kiinnityssuunta.ALA);
                }       
            }
        }

        kentta.paivita();
    }

    public boolean osuuPohjaan(Palikka palikka, List<Pala> pohjanPalat) {
       
        
        for (Pala p : this.palikka.getPalat()) {
            if (p.getX() == (this.korkeus - 1)) {
                return true;
            }
            
            if (this.palikka.osuuko(pohjanPalat, p)) {
                return true;
            }
            
        }
        return false;
    }
    
    public boolean osuuko(List<Pala> palat, Pala pala) {
        //tää ei taida olla oikeassa luokassa
        for (Pala p : palat) {
            if (p.getX() == pala.getX() && p.getY() == pala.getY()) {
                return true;
            }
        }
        return false;
    }
    
    public boolean pohjaTaynna() {
        int pohjassaKiinni = 0;
        for (Pala p : pohjanPalat) {
            if (p.getX() == (this.korkeus - 1)) {
                pohjassaKiinni++;
            }
                    
        }
        
        if (pohjassaKiinni == this.leveys) {
            return true;
        } else {
            return false;
        }
    }


    
    
    
    
}
