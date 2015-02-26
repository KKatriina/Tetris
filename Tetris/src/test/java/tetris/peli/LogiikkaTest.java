/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.peli;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
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
public class LogiikkaTest {
    Logiikka logiikka;
    Pala pala1;
    Pala pala2;
    Pala pala3;
    List<Pala> palalista;
    Palikka palikka;
    List<Pala> pohjanPalat;    
    
    @Before
    public void setUp() {
        logiikka = new Logiikka(10, 20);
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
    
    @Test
    public void osuukoPohjaanAntaaOikeinFalse() {
        this.logiikka.setPohjanPalat(null);
        assertEquals(false, logiikka.osuuPohjaan());     
    }
      
    @Test
    public void osuukoPohjaanAntaaOikeinTrueKunPohjanPalatTyhja() {
        Pala pala4 = new Pala(5, 18);
        Pala pala5 = new LisaPala(5, 19, ALA, pala4);
        List<Pala> palat = new ArrayList<>();
        palat.add(pala4);
        palat.add(pala5);
        Palikka palikka2 = new Palikka(palat, pala4);
        this.logiikka.setPohjanPalat(null);
        this.logiikka.setPalikka(palikka2);
          
        assertEquals(true, logiikka.osuuPohjaan());
    }
    
    @Test
    public void osuukoPohjaanAntaaOikeinTrue() {
        Pala pala4 = new Pala(5, 18);
        Pala pala5 = new LisaPala(5, 19, ALA, pala4);
        List<Pala> palat = new ArrayList<>();
        palat.add(pala4);
        palat.add(pala5);
        Palikka palikka2 = new Palikka(palat, pala4);
        this.logiikka.setPalikka(palikka2);
          
        assertEquals(true, logiikka.osuuPohjaan());
    }
    
    @Test
    public void osuukoPohjanPaloihinAntaaOikeinTrue() {
        logiikka.setPohjanPalat(pohjanPalat);
        assertEquals(true, logiikka.osuukoPohjanPaloihin(pala2, ALA));
    }
    
    
    @Test
    public void osuukoPohjanPaloihinAntaaOikeinFalse() {
        logiikka.setPohjanPalat(pohjanPalat);
        assertEquals(false, logiikka.osuukoPohjanPaloihin(pala2, VASEN));
    }
    
    @Test
    public void osuukoPohjanPaloihinToimiiKunParametrinaSuunta() {
        logiikka.setPohjanPalat(pohjanPalat);
        logiikka.setPalikka(palikka);
        assertEquals(false, logiikka.osuukoPohjanPaloihin(VASEN));
        assertEquals(true, logiikka.osuukoPohjanPaloihin(ALA));
        assertEquals(false, logiikka.osuukoPohjanPaloihin(OIKEA));
    }
    
    @Test
    public void siirraPalikkaaSiirtaaOikein() {
        logiikka.setPalikka(palikka);
        logiikka.siirraPalikkaa(ALA);
        assertEquals(8, pala2.getX());
        assertEquals(4, pala2.getY());
    }
    
    @Test
    public void siirraPalikkaaEiSiirraJosOsuuPohjanPaloihin() {
        logiikka.setPalikka(palikka);
        Pala pala6 = new Pala(8, 4);
        List<Pala> pohjanPalat2 = new ArrayList<Pala>();
        pohjanPalat2.add(pala6);
        logiikka.setPohjanPalat(pohjanPalat2);
        logiikka.siirraPalikkaa(ALA);
        assertEquals(8, pala2.getX());
        assertEquals(3, pala2.getY());
    }
    
    @Test
    public void pohjaTaynnaAntaaOikeinFalse() {
        logiikka.setPalikka(palikka);
        logiikka.setPohjanPalat(pohjanPalat);
        assertEquals(false, logiikka.pohjaTaynna());
    }
    
    @Test
    public void pohjaTaynnaAntaaOikeinTrue() {
        for (int i = 0; i < 10; i++) {
            Pala pp = new Pala(i, 18);
            pohjanPalat.add(pp);
        }
        logiikka.setPohjanPalat(pohjanPalat);
        assertEquals(true, logiikka.pohjaTaynna());            
    }
    
    @Test
    public void alinRiviPoistuuOikein() {
        for (int i = 0; i < 10; i++) {
            Pala pp = new Pala(i, 19);
            pohjanPalat.add(pp);
        }
        logiikka.setPohjanPalat(pohjanPalat);
        logiikka.alinRiviPois();
        
        assertEquals(1, logiikka.getPohjanPalat().size());
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

        logiikka.pelikierroksenLoppu();
        
        assertEquals(5, logiikka.getPalikka().getPaaPala().getX());
        assertEquals(0, logiikka.getPalikka().getPaaPala().getY());
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
        
        logiikka.setPalikka(palikka3);
        logiikka.setPohjanPalat(pohja3);
        logiikka.kaannaVastapaivaan();
        
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
        
        logiikka.setPalikka(palikka3);
        logiikka.setPohjanPalat(pohja3);
        logiikka.kaannaVastapaivaan();
        
        assertEquals(6, pala5.getX());
        assertEquals(2, pala5.getY());
        assertEquals(6, pala6.getX());
        assertEquals(1, pala6.getY());
    }
    
    @Test
    public void osuukoPaloihinOikeallaVasemmallaJaAlhaallaAntaaOikeinTrue() {
        Pala pala4 = new Pala(5,3);
        Pala pala5 = new Pala(5,4);
        Pala pala6 = new Pala(4,3);
        Pala pala7 = new Pala(6, 3);
        List<Pala> pohja4 = new ArrayList<Pala>();
        pohja4.add(pala6);
        pohja4.add(pala5);
        pohja4.add(pala7);
        
        logiikka.setPohjanPalat(pohja4);
        
        assertEquals(true, logiikka.osuukoPaloihinVasemmalla(pala4));
        assertEquals(true, logiikka.osuukoPaloihinAlhaalla(pala4));
        assertEquals(true, logiikka.osuukoPaloihinOikealla(pala4));
        
    }
    
    @Test
    public void osuukoPaloihinOikeallaVasemmallaJaAlhaallaAntaaOikeinFalse() {
        Pala pala4 = new Pala(5,3);
        Pala pala5 = new Pala(5,5);
        Pala pala6 = new Pala(3,3);
        Pala pala7 = new Pala(7, 3);
        List<Pala> pohja4 = new ArrayList<Pala>();
        pohja4.add(pala6);
        pohja4.add(pala5);
        pohja4.add(pala7);
        
        logiikka.setPohjanPalat(pohja4);
        
        assertEquals(false, logiikka.osuukoPaloihinVasemmalla(pala4));
        assertEquals(false, logiikka.osuukoPaloihinAlhaalla(pala4));
        assertEquals(false, logiikka.osuukoPaloihinOikealla(pala4));       
    }
    
    @Test
    public void luoPeliinUusiPalikkaLuoPalikanOikein() {
        logiikka.luoPeliinUusiPalikka();
        assertEquals(5, logiikka.getPalikka().getPaaPala().getX());
        assertEquals(0, logiikka.getPalikka().getPaaPala().getY());
    }
    
    @Test
    public void peliLoppuuJosUusiPalikkaOsuuPohjanPaloihin() {
        Ajastin ajastin = new Ajastin();
        logiikka.setAjastin(ajastin);
        Pala pala5 = new Pala(5, 0);
        pohjanPalat.add(pala5);
        
        logiikka.setPohjanPalat(pohjanPalat);
        logiikka.luoPeliinUusiPalikka();
        
        assertEquals(false, logiikka.getAjastin().getJatkuu());
    }
    
}
