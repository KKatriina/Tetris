/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static tetris.tetris.Kiinnityssuunta.ALA;
import static tetris.tetris.Kiinnityssuunta.OIKEA;
import static tetris.tetris.Kiinnityssuunta.VASEN;
import static tetris.tetris.Kiinnityssuunta.YLA;
import domain.LisaPala;
import domain.PaaPala;
import domain.Pala;
import domain.Palikka;

/**
 *
 * @author kkerokos
 */
public class PalikkaTest {
    
    //miksi pit ei toimi?
    
    Palikka palikka;
    PaaPala pala1;
    LisaPala pala2;
    LisaPala pala3;
    List<Pala> palat;

    
    public PalikkaTest() {
    }
    

    
    @Before
    public void setUp() {
        pala1 = new PaaPala(2, 5);
        pala2 = new LisaPala(2, 6, ALA, pala1);
        pala3 = new LisaPala(1, 6, VASEN, pala2);
        palat = new ArrayList<Pala>();
        palat.add(pala1);
        palat.add(pala2);
        palikka = new Palikka(palat, pala1);

        
    }
    
    @Test
    public void konstruktoriAsettaaPaapalanOikein() {
        assertEquals(pala1, palikka.getPaaPala());
    }
    
    @Test
    public void konstruktoriAsettaaPalalistanOikein() {
        assertEquals(palat, palikka.getPalat());
    }
    
    @Test
    public void palojenJarjestysSailyyKaannyttaessa() {
        palikka.kaannaVastapaivaan();
        assertEquals(pala2, palikka.getPalat().get(1));
        assertEquals(pala1, palikka.getPalat().get(0));
        
    }
    
    
    @Test
    public void kahdenPalanPalikkaKaantyyOikein() {
        palikka.kaannaVastapaivaan();
        assertEquals(OIKEA, pala2.getKiinnityssuunta());
        assertEquals(3, pala2.getX());
        assertEquals(5, pala2.getY());
    }
    
    @Test
    public void osuukoAntaaOikeinTruen() {
        assertEquals(palikka.osuuko(palat, pala1), true);
    }
    
    @Test
    public void osuukoAntaaOikeinFalsen() {
        assertEquals(palikka.osuuko(palat, pala3), false);
    }
    
    @Test
    public void isompiPalikkaKaantyyOikein() {
        PaaPala pala4 = new PaaPala(3, 4);
        LisaPala pala5 = new LisaPala(3, 5, ALA, pala4);
        LisaPala pala6 = new LisaPala(2, 5, VASEN, pala5);
        List<Pala> palat2 = new ArrayList<Pala>();
        palat2.add(pala4);
        palat2.add(pala5);
        palat2.add(pala6);
        Palikka palikka2 = new Palikka(palat2, pala4);
        palikka2.kaannaVastapaivaan();
        assertEquals(OIKEA, pala5.getKiinnityssuunta());
        assertEquals(4, pala5.getX());
        assertEquals(4, pala5.getY());
        assertEquals(ALA, pala6.getKiinnityssuunta());
        assertEquals(4, pala6.getX());
        assertEquals(5, pala6.getY());        
    }
    

        
    
    @Test
    public void luoLisapalaToimiiOikein() {
        
    }
    
    @Test
    public void palaSiirtyyOikeinKunParametrinaSuunta() {
        
    }
    
    @Test
    public void kaannyVastapaivaanEiTeeMitaanJosOsutaanAlareunaan() {
        
    }
    
    @Test
    public void kaannyVastapaivaanToimiiJosTormataanSeiniin() {
        
    }
    
    
    @Test
    public void osuukoSeinaanToimiiOikein() {
        
    }
    
}