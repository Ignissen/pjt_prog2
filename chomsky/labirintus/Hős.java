/*
 * H�s.java
 *
 * DIGIT 2005, Javat tan�tok
 * B�tfai Norbert, nbatfai@inf.unideb.hu
 *
 */

/**
 * A labirintus h�s�t le�r� oszt�ly.
 *
 * @author B�tfai Norbert, nbatfai@inf.unideb.hu
 * @version 0.0.1
 * @see javattanitok.labirintus.Labirintus
 */
public class Hős extends Szereplő {
    /** A labirintusban megtal�lt kincsek �rt�kei. */
    protected int megtaláltértékek;
    /** A h�s �leteinek maxim�lis sz�ma. */
    public static final int ÉLETEK_SZÁMA = 5;
    /** A h�s �leteinek sz�ma. */
    protected int életekSzáma = ÉLETEK_SZÁMA;
    /**
     * L�trehoz egy <code>H�s</code> objektumot.
     *
     * @param      labirintus       amelyben a h�s bolyongani fog.
     */
    public Hős(Labirintus labirintus) {
        super(labirintus);
        megtaláltértékek = 0;
    }
    /**
     * Gy�jt�geti a megtal�lt kincseket.
     *
     * @param      kincs       amit �ppen magtal�lt a h�s.
     */
    public void megtaláltam(Kincs kincs) {
        
        megtaláltértékek += kincs.érték();
        
    }
    /**
     * Jelzi, hogy �ppen megettek.
     *
     * @return true ha a h�snek m�g van �lete, ellenkez� esetben, 
     * azaz ha az �sszes �lete elfogyott m�r, akkor false.
     */
    public boolean megettek() {
        
        if(életekSzáma > 0) {
            --életekSzáma;
            return false;
        } else
            return true;
        
    }
    /**
     * megmondja, hogy �lek-e m�g?
     *
     * @return true ha a h�snek m�g van �lete, ellenkez� esetben, azaz 
     * ha az �sszes �lete elfogyott m�r, akkor false.
     */
    public boolean él() {
        
        return életekSzáma > 0;
        
    }
    /**
     * Megadja az életek sz�m�t.
     *
     * @return int az életek sz�ma.
     */
    public int életek() {
        
        return életekSzáma;
        
    }
    /**
     * Megadja a megtal�lt kincsek �sszegy�jt�getett �rt�keit.
     *
     * @return int a megtal�lt kincsek �sszegy�jt�getett �rt�kei.
     */
    public int pontszám() {
        
        return megtaláltértékek;
        
    }
   /**
     * A labirintus, amiben a h�s mozog.
     *
     * @return Labirintus a labirintus.
     */
    public Labirintus labirintus() {
        
        return labirintus;
        
    }      
}
