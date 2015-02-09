/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.domain;
import java.awt.Color;
import static java.awt.Color.blue;
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

/**
 * Luokka tarjoaa metodeja paloista koostuvan palikan koordinaattien muuttamiseen
 * eli siirtämiseen ja kääntymiseen
 */
public class Palikka {
    private List<Pala> palat;
    private Pala paaPala;
    private Random random;
    private Color vari;
    
   
    
    public Palikka() {
        this.vari = blue;
        this.palat = new ArrayList<Pala>();
        this.random = new Random();
        int lisaPaloja = random.nextInt(2);
        
        this.paaPala = new Pala(5, 0);

        luoPalat(paaPala, lisaPaloja);
        kaannaVastapaivaan();
        arvoVari();
    }
    
    public Palikka(List<Pala> palat, Pala paaPala) {
        this.random = new Random();
        this.palat = palat;
        this.paaPala = paaPala;
    }
    
    /**
    * Metodi tarkistaa, ovatko parametreina annetun palan koordinaatit samat 
    * kuin jonkin parametreina annetun palalistan palan koordinaatit
    *
    * @param   palat    lista Paloista, joihin osumisesta ollaan kiinnostuneita
    * @param   pala     Pala, jonka osumisesta ollaan kiinnostuneita
    * 
    * @return           true, jos jonkin palat-listan palan koordinaatit ovat samat kuin parametrina annetun palan koordinaatit, muuten false
    * 
    */
    public boolean osuvatkoPalatPaallekkain(List<Pala> palat, Pala pala) {
        for (Pala p : palat) {
            if (p.getX() == pala.getX() && p.getY() == pala.getY()) {
                return true;
            }
        }
        return false;
    }

    /**
    * Metodi luo palalistan, jossa on parametrina annetun palan lisäksi parametrina annettu määrä lisäpaloja
    *
    * @param   pala     Pala, joka asetetaan palalistan ensimmäiseksi palaksi
    * @param   lisaPaloja   lisapalojen haluttu määrä
    * 
    */
    public void luoPalat(Pala pala, int lisaPaloja) {
        this.palat.add(paaPala);
        
        int i = 0;
        while (i <= lisaPaloja + 2) {
            int suunta = random.nextInt(4);
            Suunta ksuunta = selvitaKiinnityssuunta(suunta);

            int indeksi = random.nextInt(palat.size());
            Pala kiinnitysPala = palat.get(indeksi);
            
            Pala uusiPala = luoLisaPala(ksuunta, kiinnitysPala);

            if (!osuvatkoPalatPaallekkain(palat, uusiPala) && uusiPala.getX() >= 0) {
                palat.add(uusiPala);
                i++;
            }
        }
        

    }       
    
    /**
    * Metodi antaa parametrina annettua kokonaislukua vastaavan Suunnan
    *
    * @param   suunta   kokonaisluku väliltä 1-4
    * 
    * @return           parametrina annetun kokonaisluvun mukaan määrätty Suunta
    * 
    */
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

    /**
    * Metodi luo lisapalan, joka kiinnittyy parametrina annetun palan parametrina annetun suunnan mukaiseen reunaan
    *
    * @param   suunta   Suunta, joka kertoo, mihin parametrina annettavan palan reunaan luotava lisaPala halutaan kiinnittää
    * @param   pala     Pala, johon luotava lisaPala halutaan kiinnittää
    * 
    * @return           lisaPala, joka on kiinni halutun palan halutussa reunassa
    * 
    */
    public LisaPala luoLisaPala(Suunta suunta, Pala pala) {
        int x = pala.getX();
        int y = pala.getY();
        if (suunta == OIKEA) {
            x++;
        }
        if (suunta == VASEN) {
            x -= 1;
        }
        if (suunta == YLA) {
            y -= 1;
        }
        if (suunta == ALA) {
            y++;
        }
        
        return new LisaPala(x, y, suunta, pala);
    }
    
    /**
    * Metodi siirtää palikkaa askeleen verran haluttuun suuntaan, mikäli palikka ei osu seinään
    *
    * @param   suunta   Suunta, johon palikkaa halutaan siirtää
    * 
    */
    public void siirra(Suunta suunta) {
        if (osuukoSeinaan(suunta)) {
            return;
        } else {
            for (Pala p : palat) {
                p.siirra(suunta);
            }
        }
    }
    
    /**
    * Metodi tarkistaa, osuuko palikka koordinaatiston ulkopuolelle, jos se liikkuu askeleen parametrina annettuun suuntaan
    *
    * @param   suunta   Suunta, johon palikkaa halutaan siirtyvän
    * 
    */
    public boolean osuukoSeinaan(Suunta suunta) {
        return osuukoSeinaan(suunta, this.palat);
    }

    /**
    * Metodi tarkistaa, osuuko jokin listan paloista koordinaatiston ulkopuolelle, jos ne liikkuvat askeleen 
    * parametrina annettuun suuntaan
    *
    * @param   suunta   Suunta, johon palojen halutaan siirtyvän
    * @param   palat    lista paloista, joita halutaan siirtää
    * 
    */
    public boolean osuukoSeinaan(Suunta suunta, List<Pala> palat) {
        for (Pala p : palat) {
            if (p.osuuSeinaan(suunta)) {
                return true; 
            }
        }
        return false;
    }
    
    /**
    * Metodi kääntää palikkaa 90 astetta vastapäivään, 
    * mikäli tämä muutos ei saa siirrä yhtään palikan palaa koordinaatiston ulkopuolelle
    * 
    */
    public void kaannaVastapaivaan() {   
        for (int i = 1; i < palat.size(); i++) {
            LisaPala pala = (LisaPala) palat.get(i);
            pala.asetaUusiPaaPala(palat.get(i - 1));
            pala.kaannaVastapaivaan();
        }

        if (osuukoSeinaan(Suunta.VASEN) || osuukoSeinaan(Suunta.OIKEA) || osuukoSeinaan(Suunta.ALA)) {
            for (int i = 1; i < palat.size(); i++) {
                LisaPala pala = (LisaPala) palat.get(i);
                pala.asetaUusiPaaPala(palat.get(i - 1));
                for (int j = 1; j <= 3; j++) {
                    pala.kaannaVastapaivaan();
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
    
    public void setVari(Color vari) {
        this.vari = vari;
        for (Pala p : this.palat) {
            p.setVari(vari);
        }
    }
    
    public Color getVari() {
        return this.vari;
    }

    public void arvoVari() {
        
        int R = this.random.nextInt(256);
        int G = this.random.nextInt(256);
        int B = this.random.nextInt(256);
        Color randomVari = new Color(R, G, B);
        
        this.setVari(randomVari);
        
//        int arvottu = this.random.nextInt(7);
//        if (arvottu == 5) {
//            setVari(Color.YELLOW);
//        }
//        if (arvottu == 4) {
//            setVari(Color.RED);
//        }
//        if (arvottu == 3) {
//            setVari(Color.GREEN);
//        }
//        if (arvottu == 2) {
//            setVari(Color.PINK);
//        }
//        if (arvottu == 1) {
//            setVari(Color.orange);
//        }
    }
    
    
}
