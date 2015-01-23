/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import tetris.tetris.LisaPala;
import tetris.tetris.PaaPala;
import tetris.tetris.Pala;
import tetris.tetris.Palikka;

/**
 *
 * @author kkerokos
 */
public class Test1 {
    Palikka palikka;
    PaaPala pala1;
    LisaPala pala2;
    LisaPala pala3;
    List<Pala> palat;
    
    public Test1() {
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
    public void palaSiirtyyOikein() {
        pala1.siirra(3, 4);
        assertEquals(pala1.getX(), 3);
        assertEquals(pala1.getY(), 4);
    }
    
    @Test
    public void LisaPalanKaantyessaSuunnatOikein() {
        pala2.kaannaVastapaivaan();
        assertEquals(OIKEA, pala2.getKiinnityssuunta());
    }
    
    @Test
    public void LisaPalanKaantyessaKoordinaatitOikein() {
        pala2.kaannaVastapaivaan();
        assertEquals(3, pala2.getX());
        assertEquals(5, pala2.getY());
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
        //tama seuraavaksi...
    }
    
    @Test
    public void luoLisapalaToimiiOikein() {
        //tama sitten
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
    
    
    
    
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
