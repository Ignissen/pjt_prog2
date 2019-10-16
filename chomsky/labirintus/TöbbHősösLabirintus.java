/*
 * TöbbHősösLabirintus.java
 *
 * DIGIT 2005, Javat tan�tok
 * B�tfai Norbert, nbatfai@inf.unideb.hu
 *
 */

/**
 * A t�bb hős�s labirintust le�r� oszt�ly, ahol egy hős hal�la
 * m�r nem jelenti a labirintus j�t�k v�g�t. A j�t�k �llapot�t
 * a kor�bbi j�t�kokban a labirintushoz kapcsoltuk, most, hogy
 * olyan tov�bbfejlesztett labirintust akarunk, amiben t�bb hős
 * is bolyonghat, �gy �rezz�k, hogy a j�t�k v�ge ink�bb a hősh�z
 * tartozik, semmint a labirintushoz. Mindkett� igaz: mert, ha a
 * kincsek fogynak el, akkor a labirintus oldal�r�l van v�ge a
 * j�t�knak.
 *
 * @author B�tfai Norbert, nbatfai@inf.unideb.hu
 * @version 0.0.1
 */
public class TöbbHősösLabirintus extends Labirintus {
    /**
     * Argumentum n�lk�li konstruktor, gyerekek implicit super()-�hez.
     */
    public TöbbHősösLabirintus() {}
    /**
     * A <code>TöbbHősösLabirintus</code> objektum elk�sz�t�se.
     *
     * @param      labirintusFájlNév       a labirintust defini�l�, megfelel� 
     * szerkezet� sz�veges �llom�ny neve.
     * @exception  RosszLabirintusKivétel  ha a labirintust defini�l� �llom�ny nem 
     * a megfelel� szerkezet�
     */
    public TöbbHősösLabirintus(String labirintusFájlNév) throws 
            RosszLabirintusKivétel {
        
        super(labirintusFájlNév);
        
    }
    /**
     * Az �s megfelel� met�dus�nak elfed�se, mert ez a J�T�K_V�GE_MEGHALT_H�S
     * csak a hős v�g�t jelenti, a labirintus�t nem!
     *
     * @see Labirintus#bolyong(Hős hős)
     * @param hős aki a labirintusban bolyong.
     * @return int a j�t�k �llapot�t le�r� k�d.
     */
    public int bolyong(Hős hős) {
        
        boolean mindMegvan = true;
        
        for(int i=0; i < kincsek.length; ++i) {
            
            // A hős r�tal�lt valamelyik kincsre?
            if(kincsek[i].megtalált(hős))
                hős.megtaláltam(kincsek[i]);
            
            // ha ez egyszer is teljes�l, akkor nincs minden kincs megtalálva
            if(!kincsek[i].megtalálva())
                mindMegvan = false;
            
        }
        
        if(mindMegvan) {
            
            játékállapot = JÁTÉK_VÉGE_MINDEN_KINCS_MEGVAN;
            return játékállapot;
            
        }
        
        for(int i=0; i < szörnyek.length; ++i) {
            
            szörnyek[i].lép(hős);
            
            if(szörnyek[i].megesz(hős))  {
                
                if(hős.megettek())
                    // De ez a j�t�k v�ge csak a hős v�g�t
                    // jelenti, a labirintus�t nem!
                    return JÁTÉK_VÉGE_MEGHALT_HŐS;
                else
                    return JÁTÉK_MEGY_MEGHALT_HŐS;
                
            }            
        }
        
        return JÁTÉK_MEGY_HŐS_RENDBEN;
    }
}               
