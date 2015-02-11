/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tetris.tetris.Suunta;
import static tetris.tetris.Suunta.OIKEA;

/**
 *
 * @author kkerokos
 */
public class PalaTest {
    Pala pala1;
    
    public PalaTest() {
        pala1 = new Pala(2, 5);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void palaSiirtyyOikein() {
        pala1.siirra(3, 4);
        assertEquals(pala1.getX(), 3);
        assertEquals(pala1.getY(), 4);
    }
    
    @Test
    public void palaSiirtyyOikeinKunParametrinaSuunta() {
        pala1.siirra(OIKEA);
        assertEquals(pala1.getX(), 3);
        assertEquals(pala1.getY(), 5);
    }
    
    @Test
    public void osuuSeinaanToimii() {
        Pala pala2 = new Pala(11, 3);
        assertEquals(true, pala2.osuuSeinaan(Suunta.OIKEA, 10, 20));
        assertEquals(false, pala2.osuuSeinaan(Suunta.VASEN, 10, 20));
    }

}
