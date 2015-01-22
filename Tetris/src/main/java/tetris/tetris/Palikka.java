/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.tetris;
import java.util.*;
import static tetris.tetris.Kiinnityssuunta.ALA;
import static tetris.tetris.Kiinnityssuunta.OIKEA;
import static tetris.tetris.Kiinnityssuunta.VASEN;
import static tetris.tetris.Kiinnityssuunta.YLA;
import tetris.tetris.PaaPala;

/**
 *
 * @author kkerokos
 */
public class Palikka {
    private List<Pala> palat;
    private PaaPala paaPala;
    private Random random;
    
    public Palikka() {
        this.palat = new ArrayList<Pala>();
        this.random = new Random();
        int lisaPaloja = random.nextInt(4);
        
        this.paaPala = new PaaPala(5, 0);

        luoPalat(paaPala, lisaPaloja);
    }
    
    public boolean osuuko(List<Pala> palat, Pala pala) {
        for (Pala p : palat) {
            if (p.getX() == pala.getX() && p.getY() == pala.getY()) {
                return true;
            }
        }
        return false;
    }

    private void luoPalat(Pala paaPala, int lisaPaloja) {
        this.palat.add(paaPala);
        
        int i = 1;
        while (i <= lisaPaloja) {
            int suunta = random.nextInt(4);
            Kiinnityssuunta ksuunta = selvitaKiinnityssuunta(suunta);

            int indeksi = random.nextInt(palat.size() - 1);
            Pala kiinnitysPala = palat.get(indeksi);
            
            Pala uusiPala = luoLisaPala(ksuunta, kiinnitysPala);

            if (!osuuko(palat, uusiPala) && uusiPala.getX() >= 0) {
                palat.add(uusiPala);
                i++;
            }
        }
        

    }       
    
    private Kiinnityssuunta selvitaKiinnityssuunta(int suunta) {
        if (suunta == 1) {
            return OIKEA;
        }
        if (suunta == 2) {
            return YLA;
        }
        if (suunta == 3) {
            return VASEN; 
        }
        return ALA;
    }

    private Pala luoLisaPala(Kiinnityssuunta ksuunta, Pala pala) {
        int x = pala.getX();
        int y = pala.getY();
        if (ksuunta == OIKEA) {
            x++;
        }
        if (ksuunta == VASEN) {
            x -= 1;
        }
        if (ksuunta == YLA) {
            y++;
        }
        if (ksuunta == ALA) {
            y -= 1;
        }
        
        return new LisaPala(x, y, ksuunta, pala);
    }
    
    private void kaannaVastapaivaan() {
        //uusi lista paloja, lisää pääpala
        for (int i = 1; i < palat.size() - 1; i++) {
            LisaPala pala = (LisaPala) palat.get(i);
            pala.kaannaVastapaivaan();
            //lisää uuteen listaan
        }
        //palat = uudetPalat;
                
        
    }
}
