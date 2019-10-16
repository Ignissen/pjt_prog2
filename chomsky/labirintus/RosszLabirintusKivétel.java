/*
 * RosszLabirintusKiv�tel.java
 *
 * DIGIT 2005, Javat tan�tok
 * B�tfai Norbert, nbatfai@inf.unideb.hu
 *
 */

/**
 * Ha �llom�ny alapj�n k�sz�tj�k a labirintust, akkor az �llom�ny szerkezet�nek
 * hib�it jelzi ez az oszt�ly.
 *
 * @author B�tfai Norbert, nbatfai@inf.unideb.hu
 * @version 0.0.1
 * @see javattanitok.labirintus.Labirintus
 */
public class RosszLabirintusKivétel extends java.lang.Exception {    
    /**
     * Elk�sz�t egy <code>RosszLabirintusKiv�tel</code> kiv�tel objektumot.
     *
     * @param hiba a hiba le�r�sa
     */
    public RosszLabirintusKivétel(String hiba) {
        super(hiba);
    }
}
