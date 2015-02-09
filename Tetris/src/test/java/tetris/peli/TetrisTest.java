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
import static tetris.tetris.Suunta.OIKEA;
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
        this.tetris.setPohjanPalat(null);
        assertEquals(false, tetris.osuuPohjaan());
        
    }
      
    @Test
    public void osuukoPohjaanAntaaOikeinTrueKunPohjanPalatTyhja() {
        Pala pala4 = new Pala(5, 18);
        Pala pala5 = new LisaPala(5, 19, ALA, pala4);
        List<Pala> palat = new ArrayList<Pala>();
        palat.add(pala4);
        palat.add(pala5);
        Palikka palikka2 = new Palikka(palat, pala4);
        this.tetris.setPohjanPalat(null);
        this.tetris.setPalikka(palikka2);
          
        assertEquals(true, tetris.osuuPohjaan());
    }
    
    @Test
    public void osuukoPohjaanAntaaOikeinTrue() {
        Pala pala4 = new Pala(5, 18);
        Pala pala5 = new LisaPala(5, 19, ALA, pala4);
        List<Pala> palat = new ArrayList<Pala>();
        palat.add(pala4);
        palat.add(pala5);
        Palikka palikka2 = new Palikka(palat, pala4);
        this.tetris.setPalikka(palikka2);
          
        assertEquals(true, tetris.osuuPohjaan());
    }
    
    @Test
    public void osuukoPohjanPaloihinAntaaOikeinTrue() {
        tetris.setPohjanPalat(pohjanPalat);
        assertEquals(true, tetris.osuukoPohjanPaloihin(pala2, ALA));
    }
    
    
    @Test
    public void osuukoPohjanPaloihinAntaaOikeinFalse() {
        tetris.setPohjanPalat(pohjanPalat);
        assertEquals(false, tetris.osuukoPohjanPaloihin(pala2, VASEN));
    }
    
    @Test
    public void osuukoPohjanPaloihinToimiiKunParametrinaSuunta() {
        tetris.setPohjanPalat(pohjanPalat);
        tetris.setPalikka(palikka);
        assertEquals(false, tetris.osuukoPohjanPaloihin(VASEN));
        assertEquals(true, tetris.osuukoPohjanPaloihin(ALA));
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
    
    @Test
    public void pohjaTaynnaAntaaOikeinFalse() {
        tetris.setPalikka(palikka);
        tetris.setPohjanPalat(pohjanPalat);
        assertEquals(false, tetris.pohjaTaynna());
    }
    
    @Test
    public void pohjaTaynnaAntaaOikeinTrue() {
        for (int i = 0; i < 10; i++) {
            Pala pp = new Pala(i, 18);
            pohjanPalat.add(pp);
        }
        tetris.setPohjanPalat(pohjanPalat);
        assertEquals(true, tetris.pohjaTaynna());            
    }
    
    @Test
    public void alinRiviPoistuuOikein() {
        for (int i = 0; i < 10; i++) {
            Pala pp = new Pala(i, 19);
            pohjanPalat.add(pp);
        }
        tetris.setPohjanPalat(pohjanPalat);
        tetris.alinRiviPois();
        assertEquals(1, tetris.getPohjanPalat().size());
        assertEquals(5, pala3.getY());
        assertEquals(8, pala3.getX());
    }
    
    @Test
    public void pelikierroksenLoppuLuoUudenPalikanOikein() {
        Pala pala4 = new Pala(8, 17);
        Pala pala5 = new LisaPala(8, 18, ALA, pala4);
        List<Pala> palat3 = new ArrayList<Pala>();
        palat3.add(pala4);
        palat3.add(pala5);
        Palikka palikka3 = new Palikka(palat3, pala5);
        Pala pala6 = new Pala(8, 19);
        List<Pala> pohja3 = new ArrayList<Pala>();
        pohja3.add(pala6);
        tetris.pelikierroksenLoppu();
        assertEquals(5, tetris.getPalikka().getPaaPala().getX());
        assertEquals(0, tetris.getPalikka().getPaaPala().getY());
    }
    
    @Test
    public void palikkaEiKaannyJosOsuuPohjanPaloihin() {
        Pala pala4 = new Pala(5, 2);
        Pala pala5 = new LisaPala(5, 3, ALA, pala4);
        Pala pala6 = new LisaPala(6, 3, OIKEA, pala5);
        List<Pala> palat3 = new ArrayList<Pala>();
        palat3.add(pala4);
        palat3.add(pala5);
        palat3.add(pala6);
        Palikka palikka3 = new Palikka(palat3, pala5);
        Pala pala7 = new Pala(6, 2);
        List<Pala> pohja3 = new ArrayList<Pala>();
        pohja3.add(pala7);
        tetris.setPalikka(palikka3);
        tetris.setPohjanPalat(pohja3);
        tetris.kaannaVastapaivaan();
        assertEquals(5, pala5.getX());
        assertEquals(3, pala5.getY());
        assertEquals(6, pala6.getX());
        assertEquals(3, pala6.getY());
    }
    
    @Test
    public void palikkaKaantyyJosEiOsutaPohjanPaloihin() {
        Pala pala4 = new Pala(5, 2);
        Pala pala5 = new LisaPala(5, 3, ALA, pala4);
        Pala pala6 = new LisaPala(6, 3, OIKEA, pala5);
        List<Pala> palat3 = new ArrayList<Pala>();
        palat3.add(pala4);
        palat3.add(pala5);
        palat3.add(pala6);
        Palikka palikka3 = new Palikka(palat3, pala5);
        Pala pala7 = new Pala(7, 2);
        List<Pala> pohja3 = new ArrayList<Pala>();
        pohja3.add(pala7);
        tetris.setPalikka(palikka3);
        tetris.setPohjanPalat(pohja3);
        tetris.kaannaVastapaivaan();
        assertEquals(6, pala5.getX());
        assertEquals(2, pala5.getY());
        assertEquals(6, pala6.getX());
        assertEquals(1, pala6.getY());
    }
    
    @Test
    public void osuukoPaloihinOikeallaVasemmallaJaAlhaallaToimii() {
        Pala pala4 = new Pala(5,3);
        Pala pala5 = new Pala(5,4);
        Pala pala6 = new Pala(4,3);
        List<Pala> pohja4 = new ArrayList<Pala>();
        pohja4.add(pala6);
        pohja4.add(pala5);
        tetris.setPohjanPalat(pohja4);
        assertEquals(true, tetris.osuukoPaloihinVasemmalla(pala4));
        assertEquals(true, tetris.osuukoPaloihinAlhaalla(pala4));
        assertEquals(false, tetris.osuukoPaloihinOikealla(pala4));
        
    }
            

}
