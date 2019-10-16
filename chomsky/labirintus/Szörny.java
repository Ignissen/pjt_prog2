/*
 * Szörny.java
 *
 * DIGIT 2005, Javat tan�tok
 * B�tfai Norbert, nbatfai@inf.unideb.hu
 *
 */

/**
 * A labirintus sz�rnyeit megad� oszt�ly.
 *
 * @author B�tfai Norbert, nbatfai@inf.unideb.hu
 * @version 0.0.1
 * @see javattanitok.labirintus.Labirintus
 */
public class Szörny extends Szereplő {
    /** A megevett hős�k sz�ma. */
    int megevettHősökSzáma;
    /** A megevett kincsek sz�ma. */
    int megevettKincsekSzáma;
    /**
     * L�trehoz egy <code>Szörny</code> objektumot.
     *
     * @param      labirintus       amibe a sz�rnyet helyezz�k.
     */
    public Szörny(Labirintus labirintus) {
        super(labirintus);
    }
    /**
     * A sz�rnyek mozg�s�nak vez�rl�se, ami szerint sz�rnyek
     * a hős fel�s igyekeznek.
     *
     * @param hős aki fel� a sz�rny igyekszik
     */
    public void lép(Hős hős) {
        
        int távolság = távolság(hős);
        // Abba az ir�nyba l�v� poz�ci�ra lép, amelyben k�zelebb ker�l a hős.
        if(!labirintus.fal(oszlop, sor-1))
            if(távolság(oszlop, sor-1, hős) < távolság) {
            lépFöl();
            return;
            
            }
        
        if(!labirintus.fal(oszlop, sor+1))
            if(távolság(oszlop, sor+1, hős) < távolság) {
            lépLe();
            return;
            
            }
        
        if(!labirintus.fal(oszlop-1, sor))
            if(távolság(oszlop-1, sor, hős) < távolság) {
            lépBalra();
            return;
            
            }
        
        if(!labirintus.fal(oszlop+1, sor))
            if(távolság(oszlop+1, sor, hős) < távolság) {
            lépJobbra();
            return;
            
            }
        
    }
    /**
     * A sz�rny megette a hőst?
     *
     * @param      hős       aki bolyong a labirintusban.
     */
    public boolean megesz(Hős hős) {
        
        if(oszlop == hős.oszlop()
        && sor == hős.sor()) {
            
            ++megevettHősökSzáma;
            return true;
            
        } else
            return false;
    }    
    /**
     * Sz�molgatja a megevett kincseket.
     *
     * @param      kincs       amit �ppen megettem.
     */
    public void megtaláltam(Kincs kincs) {
        
        ++megevettKincsekSzáma;
        
    }
        /**
     * A {@code Szörny} objektum sztring reprezent�ci�j�t adja
     * meg.
     *
     * @return String az objektum sztring reprezent�ci�ja.
     */
    public String toString() {
        
        return "SZ�RNY megevett hős�k = "
                + megevettHősökSzáma
                + "megevett kincsek = "
                + megevettKincsekSzáma;
    }    
}
