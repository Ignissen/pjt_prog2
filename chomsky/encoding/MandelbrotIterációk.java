/*
 * Mandelbrotiterációk.java
 *
 * DIGIT 2005, Javat tan�tok
 * B�tfai Norbert, nbatfai@inf.unideb.hu
 *
 */
/**
 * A nagy�tott Mandelbrot halmazok adott pontj�ban képes
 * nyomonk�vetni az z_{n+1} = z_n * z_n + c iterációt.
 *
 * @author B�tfai Norbert, nbatfai@inf.unideb.hu
 * @version 0.0.1
 */
public class MandelbrotIterációk implements Runnable{
    /** Mennyi id�t v�rakozzunk k�t iteráció bemutat�sa k�z�tt? */
    private int várakozás;
    // Kiss� igaz redund�nsan, s nem sz�pen, de k�nyelmesen:
    private MandelbrotHalmazNagyító nagyító;
    private int j, k;
    private double a, b, c, d;
    private  int szélesség, magasság;
    private java.awt.image.BufferedImage kép;
    /**
     * L�trehoz egy iterációkat vizsg�l� <code>Mandelbrotiterációk</code>
     * sz�l objektumot egy adott <code>MandelbrotHalmaznagyító</code>
     * objektumhoz.
     *
     * @param      nagyító      egy <code>MandelbrotHalmaznagyító</code> objektum
     * @param      várakozás    várakozási id�
     */
    public MandelbrotIterációk(MandelbrotHalmazNagyító nagyító, int várakozás) {        
        this.nagyító = nagyító;
        this.várakozás = várakozás;
        j = nagyító.getY();
        k = nagyító.getX();
        a = nagyító.getA();
        b = nagyító.getB();
        c = nagyító.getC();
        d = nagyító.getD();
        kép = nagyító.kép();
        szélesség  = nagyító.getSz();
        magasság = nagyító.getM();
    }
    /** Az vizsg�lt pontb�l indul� iterációk bemutat�sa. */
    public void run() {
        /* Az al�bbi k�d javar�szt a MandelbrotHalmaz.java sz�mol�st 
         v�gz� run() m�dszer�b�l sz�rmazik, hiszen ugyanazt csin�ljuk,
         csak most nem a h�l�n megy�nk v�gig, hanem a h�l� adott a
         p�ld�nyos�t�sunkkor az eg�rmutat� mutatta csom�pontj�ban (ennek
         felel meg a c kompelx sz�m) sz�molunk, teh�t a k�t k�ls� for 
         ciklus nem kell. */
        // A [a,b]x[c,d] tartom�nyon milyen s�r� a
        // megadott szélesség, magasság h�l�:
        double dx = (b-a)/szélesség;
        double dy = (d-c)/magasság;
        double reC, imC, reZ, imZ, ujreZ, ujimZ;
        // H�ny iterációt csin�ltunk?
        int iteráció = 0;
        // c = (reC, imC) a h�l� r�cspontjainak
        // megfelel� komplex sz�m
        reC = a+k*dx;
        imC = d-j*dy;
        // z_0 = 0 = (reZ, imZ)
        reZ = 0;
        imZ = 0;
        iteráció = 0;
        // z_{n+1} = z_n * z_n + c iterációk
        // sz�m�t�sa, am�g |z_n| < 2 vagy m�g
        // nem �rt�k el a 255 iterációt, ha
        // viszont el�rt�k, akkor �gy vessz�k,
        // hogy a kiindul�ci c komplex sz�mra
        // az iteráció konvergens, azaz a c a
        // Mandelbrot halmaz eleme
        while(reZ*reZ + imZ*imZ < 4 && iteráció < 255) {
            // z_{n+1} = z_n * z_n + c
            ujreZ = reZ*reZ - imZ*imZ + reC;
            ujimZ = 2*reZ*imZ + imC;
         
            // az iteráció (reZ, imZ) -> (ujreZ, ujimZ)
            // ezt az egyenest kell kirajzolnunk, de most
            // a komplex sz�mokat vissza kell transzform�lnunk
            // a r�cs oszlop, sor koordin�t�j�v�:
            java.awt.Graphics g = kép.getGraphics();
            g.setColor(java.awt.Color.WHITE);
            g.drawLine(
                    (int)((reZ - a)/dx),
                    (int)((d - imZ)/dy),
                    (int)((ujreZ - a)/dx),
                    (int)((d - ujimZ)/dy)
                    );
            g.dispose();
            nagyító.repaint();
            
            reZ = ujreZ;
            imZ = ujimZ;
            
            ++iteráció;
            // V�rakozunk, hogy k�zben csod�lhassuk az iteráció
            // l�tv�ny�t:
            try {
                Thread.sleep(várakozás);
            } catch (InterruptedException e) {}
        }
    }    
}                    
