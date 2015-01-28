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
import static tetris.tetris.Kiinnityssuunta.ALA;
import static tetris.tetris.Kiinnityssuunta.OIKEA;

/**
 *
 * @author kkerokos
 */
public class LisaPalaTest {
    Pala pala1;
    LisaPala pala2;
    
    public LisaPalaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        pala1 = new Pala(2, 5);
        pala2 = new LisaPala(2, 6, ALA, pala1);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void LisaPalanKaantyessaKoordinaatitOikein() {
        pala2.kaannaVastapaivaan();
        assertEquals(3, pala2.getX());
        assertEquals(5, pala2.getY());
    }
    
    @Test
    public void LisaPalanKaantyessaSuunnatOikein() {
        pala2.kaannaVastapaivaan();
        assertEquals(OIKEA, pala2.getKiinnityssuunta());
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
