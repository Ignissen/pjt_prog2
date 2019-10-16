/*
 * Szereplő.java
 *
 * DIGIT 2005, Javat tan�tok
 * B�tfai Norbert, nbatfai@inf.unideb.hu
 *
 */

/**
 * A labirintus szereplőit (kincsek, sz�rnyek, h�s) absztrah�l� oszt�ly.
 *
 * @author B�tfai Norbert, nbatfai@inf.unideb.hu
 * @version 0.0.1 
 * @see javattanitok.labirintus.Labirintus
 */
public class Szereplő {
    /** A szereplő oszlop poz�ci�ja. */
    protected int oszlop;
    /** A szereplő sor poz�ci�ja. */
    protected int sor;
    /** A labirintus, melyben a szereplő van. */
    protected Labirintus labirintus;
    /** A labirintus szélessége. */
    protected int maxSzélesség;
    /** A labirintus magassága. */
    protected int maxMagasság;
    /** V�letlensz�m gener�tor a szereplők mozgat�s�hoz. */
    protected static java.util.Random véletlenGenerátor 
            = new java.util.Random();
    /**
     * L�trehoz egy <code>Szereplő</code> objektumot.
     *
     * @param      labirintus       amibe a szereplőt helyezz�k.
     */
    public Szereplő(Labirintus labirintus) {
        this.labirintus = labirintus;
        maxSzélesség = labirintus.szélesség();
        maxMagasság = labirintus.magasság();
        // A szereplő kezd� poz�ci�ja a labirintusban
        szereplőHelyeKezdetben();
    }
    /**
     * A szereplő labirintusbeli kezd� poz�ci�j�nak meghat�roz�sa.
     */
    void szereplőHelyeKezdetben() {
        // T�bbsz�r pr�b�lkozunk elhelyezni a szereplőt a labirintusban,
        // sz�molja, hol tartunk ezekkel a pr�b�lkoz�sokkal:
        int számláló = 0;
        
        do {
            // itt +2,-2-k, hogy a bal als� sarokt�l t�vol tartsuk
            // a szereplőket, mert majd ezt akarjuk a h�s kezd� poz�ci�j�nak
            oszlop = 2+véletlenGenerátor.nextInt(maxSzélesség-2);
            sor = véletlenGenerátor.nextInt(maxMagasság-2);
            // max. 10-szer pr�b�lkozunk, de ha siker�l nem "falba tenni" a
            // szereplőt, akkor m�ris kil�p�nk:
        } while(++számláló<10 && labirintus.fal(oszlop, sor));
        
    }
    /**
     * A szereplő felfel� l�p. Ha nem tud, helyben marad.
     */
    public void lépFöl() {
        
        if(sor > 0)
            if(!labirintus.fal(oszlop, sor-1))
                --sor;        
    }
    /**
     * A szereplő lefel� l�p. Ha nem tud, helyben marad.
     */
    public void lépLe() {
        
        if(sor < maxMagasság-1)
            if(!labirintus.fal(oszlop, sor+1))
                ++sor;
        
    }
    /**
     * A szereplő balra l�p. Ha nem tud, helyben marad.
     */
    public void lépBalra() {
        
        if(oszlop > 0)
            if(!labirintus.fal(oszlop-1, sor))
                --oszlop;
        
    }
    /**
     * A szereplő jobbra l�p. Ha nem tud, helyben marad.
     */
    public void lépJobbra() {
        
        if(oszlop < maxSzélesség-1)
            if(!labirintus.fal(oszlop+1, sor))
                ++oszlop;
        
    }
    /**
     * A szereplő (Euklideszi) t�vols�ga egy m�sik szereplőt�l a labirintusban.
     *
     * @param szereplő a m�sik szereplő
     * @return int t�vols�g a m�sik szereplőt�l.
     */
    public int távolság(Szereplő szereplő) {
        
        return (int)Math.sqrt((double)
        (oszlop - szereplő.oszlop)*(oszlop - szereplő.oszlop)
        + (sor - szereplő.sor)*(sor - szereplő.sor)
        );
        
    }
    /**
     * Egy poz�ci� (Euklideszi) t�vols�ga egy m�sik szereplőt�l a 
     * labirintusban.
     *
     * @param oszlop a poz�ci� oszlop koordin�t�ja
     * @param sor a poz�ci� sor koordin�t�ja
     * @param szereplő a m�sik szereplő
     * @return int t�vols�g a m�sik szereplőt�l.
     */
    public int távolság(int oszlop, int sor, Szereplő szereplő) {
        
        if(!(oszlop >= 0 && oszlop <= maxSzélesség-1
                && sor >= 0 && sor <= maxMagasság-1))
            return Integer.MAX_VALUE;
        else
            return (int)Math.sqrt((double)
            (oszlop - szereplő.oszlop)*(oszlop - szereplő.oszlop)
            + (sor - szereplő.sor)*(sor - szereplő.sor)
            );
        
    }
    /**
     * Be�ll�tja a szereplő labirintusbeli poz�ci�j�nak oszlop 
     * koordin�t�j�t.
     *
     * @param oszlop a szereplő labirintusbeli poz�ci�j�nak oszlop 
     * koordin�t�ja.
     */
    public void oszlop(int oszlop) {
        
        this.oszlop = oszlop;
        
    }
    /**
     * Be�ll�tja a szereplő labirintusbeli poz�ci�j�nak sor koordin�t�j�t.
     *
     * @param sor a szereplő labirintusbeli poz�ci�j�nak sor koordin�t�ja.
     */
    public void sor(int sor) {
        
        this.sor = sor;
        
    }
    /**
     * Megadja a szereplő labirintusbeli poz�ci�j�nak oszlop koordin�t�j�t.
     *
     * @return int a szereplő labirintusbeli poz�ci�j�nak oszlop koordin�t�ja.
     */
    public int oszlop() {
        
        return oszlop;
        
    }
    /**
     * Megadja a szereplő labirintusbeli poz�ci�j�nak sor koordin�t�j�t.
     *
     * @return int a szereplő labirintusbeli poz�ci�j�nak sor koordin�t�ja.
     */
    public int sor() {
        
        return sor;
        
    }

    public String toString() {
    
        return "SZEREPLŐ oszlop = " 
                + oszlop
                + " sor = "
                + sor;
    }    
}                
