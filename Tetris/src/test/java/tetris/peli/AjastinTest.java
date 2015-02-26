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
public class AjastinTest {
    Ajastin tetris;
    Pala pala1;
    Pala pala2;
    Pala pala3;
    List<Pala> palalista;
    Palikka palikka;
    List<Pala> pohjanPalat;
       
    @Before
    public void setUp() {
        tetris = new Ajastin();
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
    public void saadaNopeuttaToimiiIsollaSyotteella() {
        tetris.setNopeus(201);
        tetris.saadaNopeutta();
        assertEquals(196, tetris.getNopeus());
    }
    
    @Test
    public void saadaNopeuttaToimiiKeskikokoisellaSyotteella() {
        tetris.setNopeus(200);
        tetris.saadaNopeutta();
        assertEquals(198, tetris.getNopeus());
    }
    
    @Test
    public void saadaNopeuttaToimiiPienellaSyotteella() {
        tetris.setNopeus(51);
        tetris.saadaNopeutta();
        assertEquals(50, tetris.getNopeus());
        tetris.saadaNopeutta();
        assertEquals(50, tetris.getNopeus());
    }

}
