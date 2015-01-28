/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.domain;
import tetris.domain.LisaPala;
import tetris.domain.Pala;
import java.util.*;
import tetris.tetris.Kiinnityssuunta;
import static tetris.tetris.Kiinnityssuunta.ALA;
import static tetris.tetris.Kiinnityssuunta.OIKEA;
import static tetris.tetris.Kiinnityssuunta.VASEN;
import static tetris.tetris.Kiinnityssuunta.YLA;


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
            Kiinnityssuunta ksuunta = selvitaKiinnityssuunta(suunta);

            int indeksi = random.nextInt(palat.size());
            Pala kiinnitysPala = palat.get(indeksi);
            
            Pala uusiPala = luoLisaPala(ksuunta, kiinnitysPala);

            if (!osuuko(palat, uusiPala) && uusiPala.getX() >= 0) {
                palat.add(uusiPala);
                i++;
            }
        }
        

    }       
    
    public Kiinnityssuunta selvitaKiinnityssuunta(int suunta) {
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

    public LisaPala luoLisaPala(Kiinnityssuunta ksuunta, Pala pala) {
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
    
    public void siirra(Kiinnityssuunta suunta) {
        if (osuukoSeinaan(suunta)) {
                return;
        }
        for (Pala p : palat) {
            p.siirra(suunta);
        }
    }
    
    public boolean osuukoSeinaan(Kiinnityssuunta suunta) {
        return osuukoSeinaan(suunta, this.palat);
    }

    public boolean osuukoSeinaan(Kiinnityssuunta suunta, List<Pala> palat) {
        boolean palautettava = false;
        
        if (suunta == OIKEA) {
            for (Pala p : palat) {
                if (p.getX() > 10) {
                    palautettava = true;
                }
            }
        } else if (suunta == VASEN) {
            for (Pala p : palat) {
                if (p.getX() < 0) {
                    palautettava = true;
                }
            }
        } else if (suunta == ALA) {
            for (Pala p : palat) {
                if (p.getY() > 20) {
                    palautettava = true;
                }
            }
        } else if (suunta == YLA) {
            for (Pala p : palat) {
                if (p.getY() < 0) {
                    palautettava = true;
                }
                       
            }
        }
        return palautettava;
    }
    
    public void kaannaVastapaivaan() {
        List<Pala> uudetPalat = new ArrayList<Pala>();
        uudetPalat.add(paaPala);
        
        for (int i = 1; i < palat.size(); i++) {
            LisaPala pala = (LisaPala) palat.get(i);
            pala.asetaUusiPaaPala(uudetPalat.get(i - 1));
            pala.kaannaVastapaivaan();
            uudetPalat.add(pala);
        }
        
        //jos osuu alareunaan, ei saa kaantya
        if (osuukoSeinaan(ALA, uudetPalat)) {
            return;
        } else {
            while (true) {
                if (!osuukoSeinaan(YLA, uudetPalat) && !(osuukoSeinaan(OIKEA, uudetPalat)) && !(osuukoSeinaan(VASEN, uudetPalat))) {
                    break;
                }
                if (osuukoSeinaan(YLA, uudetPalat)) {
                    for (Pala p : uudetPalat) {
                        siirra(ALA);
                    }
                }
                if (osuukoSeinaan(OIKEA, uudetPalat)) {
                    for (Pala p : uudetPalat) {
                        siirra(VASEN);
                    }
                }
                if (osuukoSeinaan(VASEN, uudetPalat)) {
                    for (Pala p  : uudetPalat) {
                        siirra(OIKEA);
                    }
                    
                }
            }
        
            palat = uudetPalat;
        }
    }
    
    public Pala getPaaPala() {
        return paaPala;
    }
    
    public List<Pala> getPalat() {
        return palat;
    }
    
    
}
