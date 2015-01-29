/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.domain;
import tetris.domain.LisaPala;
import tetris.domain.Pala;
import java.util.*;
import tetris.tetris.Suunta;
import static tetris.tetris.Suunta.ALA;
import static tetris.tetris.Suunta.OIKEA;
import static tetris.tetris.Suunta.VASEN;
import static tetris.tetris.Suunta.YLA;


/**
 *
 * @author kkerokos
 */
public class Palikka {
    private List<Pala> palat;
    private Pala paaPala;
    private Random random;
    
   
    
    public Palikka() {
        this.palat = new ArrayList<Pala>();
        this.random = new Random();
        int lisaPaloja = random.nextInt(4);
        
        this.paaPala = new Pala(5, 0);

        luoPalat(paaPala, lisaPaloja);
    }
    
    public Palikka(List<Pala> palat, Pala paaPala) {
        this.random = new Random();
        this.palat = palat;
        this.paaPala = paaPala;
    }
    
    public boolean osuuko(List<Pala> palat, Pala pala) {
        //tää ei taida olla oikeassa luokassa
        for (Pala p : palat) {
            if (p.getX() == pala.getX() && p.getY() == pala.getY()) {
                return true;
            }
        }
        return false;
    }

    public void luoPalat(Pala pala, int lisaPaloja) {
        this.palat.add(paaPala);
        
        int i = 1;
        while (i <= lisaPaloja) {
            int suunta = random.nextInt(4);
            Suunta ksuunta = selvitaKiinnityssuunta(suunta);

            int indeksi = random.nextInt(palat.size());
            Pala kiinnitysPala = palat.get(indeksi);
            
            Pala uusiPala = luoLisaPala(ksuunta, kiinnitysPala);

            if (!osuuko(palat, uusiPala) && uusiPala.getX() >= 0) {
                palat.add(uusiPala);
                i++;
            }
        }
        

    }       
    
    public Suunta selvitaKiinnityssuunta(int suunta) {
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

    public LisaPala luoLisaPala(Suunta ksuunta, Pala pala) {
        int x = pala.getX();
        int y = pala.getY();
        if (ksuunta == OIKEA) {
            x++;
        }
        if (ksuunta == VASEN) {
            x -= 1;
        }
        if (ksuunta == YLA) {
            y -= 1;
        }
        if (ksuunta == ALA) {
            y++;
        }
        
        return new LisaPala(x, y, ksuunta, pala);
    }
    
    public void siirra(Suunta suunta) {
        if (osuukoSeinaan(suunta)) {
            return;
        }
        for (Pala p : palat) {
            p.siirra(suunta);
        }
    }
    
    public boolean osuukoSeinaan(Suunta suunta) {
        return osuukoSeinaan(suunta, this.palat);
    }

    public boolean osuukoSeinaan(Suunta suunta, List<Pala> palat) {
        for (Pala p : palat) {
            if (p.osuuSeinaan(suunta)) {
                return true;
            }
        }
        return false;
    }
    
    public void kaannaVastapaivaan() {
//        List<Pala> uudetPalat = new ArrayList<Pala>();
//        uudetPalat.add(paaPala);
//        
        for (int i = 1; i < palat.size(); i++) {
            LisaPala pala = (LisaPala) palat.get(i);
            pala.asetaUusiPaaPala(palat.get(i - 1));
            pala.kaannaVastapaivaan();
        }
        
        //jos osuu alareunaan, ei saa kaantya
        if (osuukoSeinaan(Suunta.ALA)) {
            for (int i = 1; i < palat.size(); i++) {
                LisaPala pala = (LisaPala) palat.get(i);
                pala.asetaUusiPaaPala(palat.get(i - 1));
                for (int j = 1; j <= 3; j++) {
                    pala.kaannaVastapaivaan();
                }
            }
        } else {
            while (true) {
                if (!osuukoSeinaan(YLA) && !(osuukoSeinaan(OIKEA)) && !(osuukoSeinaan(VASEN))) {
                    break;
                }
                if (osuukoSeinaan(YLA)) {
                    for (Pala p : palat) {
                        siirra(ALA);
                    }
                }
                if (osuukoSeinaan(OIKEA)) {
                    for (Pala p : palat) {
                        siirra(VASEN);
                    }
                }
                if (osuukoSeinaan(VASEN)) {
                    for (Pala p  : palat) {
                        siirra(OIKEA);
                    }
                    
                }
            }
        }
    }
    
    public Pala getPaaPala() {
        return paaPala;
    }
    
    public List<Pala> getPalat() {
        return palat;
    }
    
    
}
