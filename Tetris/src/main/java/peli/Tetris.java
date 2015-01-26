/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peli;

import domain.Pala;
import domain.Palikka;
import gui.Pelikentta;
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
        palikka.siirra(Kiinnityssuunta.ALA);
        
        //jos pala osuu alareunaan TAI toiseen palaan, luodaan uusi pala
        
        int pohjassaKiinni = 0;
        for (Pala p : pohjanPalat) {
            if (p.getX() == this.korkeus) {
                pohjassaKiinni++;
            }
                    
        }
        
        if (pohjassaKiinni == this.leveys) {
            for (Pala p : pohjanPalat) {
                if (p.getX() == this.korkeus) {
                    pohjanPalat.remove(p);
                } else {
                    p.siirra(Kiinnityssuunta.ALA);
                }       
            }
        }

        kentta.paivita();
    }
    
    
    
    
}
