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
import tetris.tetris.Suunta;

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
        pala3 = new Pala(7, 5);
        palalista = new ArrayList<Pala>();
        palalista.add(pala1);
        palalista.add(pala2);
    }
    
    @After
    public void tearDown() {
    }
    
//    @Test
//    public void osuukoAntaaOikeinTrue() {
//        assertEquals(true, tetris.osuuko(palalista, pala1));
//        assertEquals(true, tetris.osuuko(palalista, pala2));
//    }
//    
//    @Test
//    public void osuukoAntaaOikeinFalse() {
//        assertEquals(false, tetris.osuuko(palalista, pala3));
//    }
    
//    @Test
//    public void osuukoPohjaanAntaaOikeinFalse() {
//        
//    }
//    
//    @Test
//    public void osuukoPohjaanAntaaOikeinTrue() {
//        
//    }

}
