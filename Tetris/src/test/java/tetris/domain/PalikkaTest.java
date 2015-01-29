/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.domain;

import java.util.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tetris.tetris.Suunta;
import static tetris.tetris.Suunta.ALA;
import static tetris.tetris.Suunta.OIKEA;
import static tetris.tetris.Suunta.VASEN;
import static tetris.tetris.Suunta.YLA;

/**
 *
 * @author kkerokos
 */
public class PalikkaTest {
    
    //miksi pit ei toimi?
    
    Palikka palikka;
    Pala pala1;
    LisaPala pala2;
    LisaPala pala3;
    List<Pala> palat;

    
    public PalikkaTest() {
    }
    

    
    @Before
    public void setUp() {
        pala1 = new Pala(2, 5);
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
        Pala pala4 = new Pala(3, 4);
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
        LisaPala pala = palikka.luoLisaPala(Suunta.ALA, pala1);
        assertEquals(pala1, pala.getPaaPala());
        assertEquals(Suunta.ALA, pala.getKiinnityssuunta());
        assertEquals(2, pala.getX());
        assertEquals(6, pala.getY());
    }
    
    @Test
    public void kaannyVastapaivaanEiTeeMitaanJosOsutaanAlareunaan() {
        Pala pala4 = new Pala(3, 20);
        LisaPala pala5 = new LisaPala(2, 20, VASEN, pala4);
        List<Pala> palat2 = new ArrayList<Pala>();
        palat2.add(pala4);
        palat2.add(pala5);
        Palikka palikka2 = new Palikka(palat2, pala4);

        
        palikka2.kaannaVastapaivaan();
        assertEquals(VASEN, pala5.getKiinnityssuunta());
        assertEquals(2, pala5.getX());
        assertEquals(20, pala5.getY()); 
    }
    
    @Test
    public void kaannyVastapaivaanToimiiJosTormataanSeiniin() {
        Pala pala4 = new Pala(10, 4);
        LisaPala pala5 = new LisaPala(10, 5, ALA, pala4);
        LisaPala pala6 = new LisaPala(9, 5, VASEN, pala5);
        List<Pala> palat2 = new ArrayList<Pala>();
        palat2.add(pala4);
        palat2.add(pala5);
        palat2.add(pala6);
        Palikka palikka2 = new Palikka(palat2, pala4);
        palikka2.kaannaVastapaivaan();
        assertEquals(OIKEA, pala5.getKiinnityssuunta());
        assertEquals(8, pala5.getX());
        assertEquals(4, pala5.getY());
        assertEquals(ALA, pala6.getKiinnityssuunta());
        assertEquals(8, pala6.getX());
        assertEquals(5, pala6.getY()); 
    }
    
    @Test
    public void osuukoToimiiOikein() {
        assertEquals(palikka.osuuko(palat, pala2), true);
        assertEquals(palikka.osuuko(palat, pala3), false);
    }
    
    
    @Test
    public void osuukoSeinaanToimiiOikein() {
        Pala pala4 = new Pala(11, 3);
        LisaPala pala5 = new LisaPala(11, 4, Suunta.ALA, pala4);
        LisaPala pala6 = new LisaPala(12, 6, Suunta.VASEN, pala5);
        List<Pala> palat3 = new ArrayList<Pala>();
        palat3.add(pala4);
        palat3.add(pala5);
        palat3.add(pala6);
        Palikka palikka3 = new Palikka(palat3, pala4);
        assertEquals(true, palikka3.osuukoSeinaan(Suunta.OIKEA));
        assertEquals(false, palikka3.osuukoSeinaan(Suunta.VASEN));
    }
    
}