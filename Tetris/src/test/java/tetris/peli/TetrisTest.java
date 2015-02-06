/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.peli;

import java.util.*;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import tetris.domain.LisaPala;
import tetris.domain.Pala;
import tetris.domain.Palikka;
import tetris.tetris.Suunta;
import static tetris.tetris.Suunta.ALA;
import static tetris.tetris.Suunta.VASEN;

/**
 *
 * @author kkerokos
 */
public class TetrisTest {
    Tetris tetris;
    Pala pala1;
    Pala pala2;
    Pala pala3;
    List<Pala> palalista;
    Palikka palikka;
    List<Pala> pohjanPalat;
    
    public TetrisTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        tetris = new Tetris(10, 20);
        pala1 = new Pala(8, 2);
        pala2 = new LisaPala(8, 3, Suunta.ALA, pala1);
        pala3 = new Pala(8, 4);
        palalista = new ArrayList<Pala>();
        palalista.add(pala1);
        palalista.add(pala2);
        pohjanPalat = new ArrayList<Pala>();
        pohjanPalat.add(pala3);
        palikka = new Palikka(palalista, pala1);
        
    }
    
    @After
    public void tearDown() {
    }
    
    
    @Test
    public void osuukoPohjaanAntaaOikeinFalse() {
        assertEquals(false, tetris.osuuPohjaan(palikka, null));
        
    }
    
//    @Test
//    public void osuukoPohjaanAntaaOikeinTrue() {
//        Pala pala4 = new Pala(5, 18);
//        Pala pala5 = new LisaPala(5, 19, ALA, pala4);
//        List<Pala> palat = new ArrayList<Pala>();
//        palat.add(pala4);
//        palat.add(pala5);
//        Palikka palikka2 = new Palikka(palat, pala4);
          
//        assertEquals(true, tetris.osuuPohjaan(palikka2, pohjanPalat));
//    }
    
    @Test
    public void osuukoPohjanPaloihinAntaaOikeinTrue() {
        tetris.setPohjanPalat(pohjanPalat);
        assertEquals(true, tetris.osuukoPohjanPaloihin(pala1, ALA));
    }
    
    @Test
    public void pysahtyykoPohjanPaloihinAntaaOikeinTrue() {
        tetris.setPohjanPalat(pohjanPalat);
        assertEquals(true, tetris.pysahtyykoPohjanPaloihin(pala2));
    }
    
    @Test
    public void osuukoPohjanPaloihinAntaaOikeinFalse() {
        tetris.setPohjanPalat(pohjanPalat);
        assertEquals(false, tetris.osuukoPohjanPaloihin(pala1, VASEN));
    }
    
    @Test
    public void siirraPalikkaaSiirtaaOikein() {
        tetris.setPalikka(palikka);
        tetris.siirraPalikkaa(ALA);
        assertEquals(8, pala2.getX());
        assertEquals(4, pala2.getY());
    }
    
    @Test
    public void siirraPalikkaaEiSiirraJosOsuuPohjanPaloihin() {
        tetris.setPalikka(palikka);
        Pala pala6 = new Pala(8, 4);
        List<Pala> pohjanPalat2 = new ArrayList<Pala>();
        pohjanPalat2.add(pala6);
        tetris.setPohjanPalat(pohjanPalat2);
        tetris.siirraPalikkaa(ALA);
        assertEquals(8, pala2.getX());
        assertEquals(3, pala2.getY());
    }

}
