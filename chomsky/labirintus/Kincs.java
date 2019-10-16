/*
 * Kincs.java
 *
 * DIGIT 2005, Javat tan�tok
 * B�tfai Norbert, nbatfai@inf.unideb.hu
 *
 */

/**
 * A labirintus kincseit jellemz� oszt�ly.
 *
 * @author B�tfai Norbert, nbatfai@inf.unideb.hu
 * @version 0.0.1
 * @see javattanitok.labirintus.Labirintus
 */
public class Kincs extends Szereplő {
    /** A kincs értéke. */
    protected int érték;
    /** Megtal�ltak m�r? */
    protected boolean megtalálva;
    /**
     * L�trehoz egy {@code Kincs} objektumot.
     *
     * @param   labirintus  amibe a kincset helyezz�k.
     * @param   érték       a kincs értéke.
     */
    public Kincs(Labirintus labirintus, int érték) {
        super(labirintus);
        this.érték = érték;
    }
    /**
     * A szereplő (pl. h�s, sz�rnyek) megtal�lta a kincset?
     *
     * @param   h�s aki keresi a kincset.
     * @return true ha a kincset �ppen most megtal�lta a szereplő, 
     * ha �ppen nem, vagy m�r eleve megvan tal�lva a kincs, akkor false.
     */
    public boolean megtalált(Szereplő szereplő) {
        
        if(megtalálva)
        // mert egy kicset csak egyszer lehet megtal�lni
            return false;
        
        if(oszlop == szereplő.oszlop()
        && sor == szereplő.sor())
            megtalálva = true;
        
        return megtalálva;
    }
    /**
     * Megadja a kincs érték�t.
     *
     * @return  int a kincs értéke.
     */
    public int érték() {
        
        return érték;
        
    }
    /**
     * Megmondja, hogy megtal�lt�k-e m�r a kincset?
     *
     * @return true ha a kincset m�r megtal�lt�k, 
     * ha m�g nem akkor false.
     */
    public boolean megtalálva() {
        
        return megtalálva;
        
    }
    /**
     * A {@code Kincs} objektum sztring reprezent�ci�j�t adja
     * meg.
     *
     * @return String az objektum sztring reprezent�ci�ja.
     */
    public String toString() {
    
        return "KINCS érték = " 
                + érték
                + " megtalálva = "
                + megtalálva;
    }    
}
