/*
 * MandelbrotHalmazNagyító.java
 *
 * DIGIT 2005, Javat tan�tok
 * B�tfai Norbert, nbatfai@inf.unideb.hu
 *
 */
/**
 * A Mandelbrot halmazt nagy�t� �s kirajzol� oszt�ly.
 *
 * @author B�tfai Norbert, nbatfai@inf.unideb.hu
 * @version 0.0.2
 */
public class MandelbrotHalmazNagyító extends MandelbrotHalmaz {
    /** A nagy�tand� kijel�lt ter�letet bal fels� sarka. */
    private int x, y;
    /** A nagy�tand� kijel�lt ter�let szélessége �s magass�ga. */
    private int mx, my;
    /**
     * L�trehoz egy a Mandelbrot halmazt a komplex s�k
     * [a,b]x[c,d] tartom�nya felett kisz�mol� �s nyg�tani tud�
     * <code>MandelbrotHalmazNagyító</code> objektumot.
     *
     * @param      a              a [a,b]x[c,d] tartom�ny a koordin�t�ja.
     * @param      b              a [a,b]x[c,d] tartom�ny b koordin�t�ja.
     * @param      c              a [a,b]x[c,d] tartom�ny c koordin�t�ja.
     * @param      d              a [a,b]x[c,d] tartom�ny d koordin�t�ja.
     * @param      szélesség      a halmazt tartalmaz� t�mb szélessége.
     * @param      iterációsHatár a sz�m�t�s pontoss�ga.
     */
    public MandelbrotHalmazNagyító(double a, double b, double c, double d,
            int szélesség, int iterációsHatár) {
        // Az �s oszt�ly konstruktor�nak h�v�sa
        super(a, b, c, d, szélesség, iterációsHatár);
        setTitle("A Mandelbrot halmaz nagy�t�sai");
        // Eg�r kattint� esem�nyek feldolgoz�sa:
        addMouseListener(new java.awt.event.MouseAdapter() {
            // Eg�r kattint�ssal jel�lj�k ki a nagy�tand� ter�letet
            // bal fels� sark�t vagy ugyancsak eg�r kattint�ssal
            // vizsg�ljuk egy adott pont iter�ci�it:
            public void mousePressed(java.awt.event.MouseEvent m) {
                // Az eg�rmutat� poz�ci�ja
                x = m.getX();
                y = m.getY();
                // Az 1. eg�r gombbal a nagy�tand� ter�let kijel�l�s�t
                // v�gezz�k:
                if(m.getButton() == java.awt.event.MouseEvent.BUTTON1 ) {
                    // A nagy�tand� kijel�lt ter�letet bal fels� sarka: (x,y)
                    // �s szélessége (majd a vonszol�s n�veli)
                    mx = 0;
                    my = 0;
                    repaint();
                } else {
                    // Nem az 1. eg�r gombbal az eg�rmutat� mutatta c
                    // komplex sz�mb�l ind�tott iter�ci�kat vizsg�lhatjuk
                    MandelbrotIterációk iterációk =
                            new MandelbrotIterációk(
                            MandelbrotHalmazNagyító.this, 50);
                    new Thread(iterációk).start();
                }
            }
            // Vonszolva kijel�l�nk egy ter�letet...
            // Ha felengedj�k, akkor a kijel�lt ter�let
            // �jrasz�m�t�sa indul:
            public void mouseReleased(java.awt.event.MouseEvent m) {
                if(m.getButton() == java.awt.event.MouseEvent.BUTTON1 ) {
                    double dx = (MandelbrotHalmazNagyító.this.b
                            - MandelbrotHalmazNagyító.this.a)
                            /MandelbrotHalmazNagyító.this.szélesség;
                    double dy = (MandelbrotHalmazNagyító.this.d
                            - MandelbrotHalmazNagyító.this.c)
                            /MandelbrotHalmazNagyító.this.magasság;
                    // Az �j Mandelbrot nagy�t� objektum elk�sz�t�se:
                    new MandelbrotHalmazNagyító(
                            MandelbrotHalmazNagyító.this.a+x*dx,
                            MandelbrotHalmazNagyító.this.a+x*dx+mx*dx,
                            MandelbrotHalmazNagyító.this.d-y*dy-my*dy,
                            MandelbrotHalmazNagyító.this.d-y*dy,
                            600,
                            MandelbrotHalmazNagyító.this.iterációsHatár);
                }
            }
        });
        // Eg�r mozg�s esem�nyek feldolgoz�sa:
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            // Vonszol�ssal jel�lj�k ki a n�gyzetet:
            public void mouseDragged(java.awt.event.MouseEvent m) {
                // A nagy�tand� kijel�lt ter�let szélessége �s magass�ga:
                mx = m.getX() - x;
                my = m.getY() - y;
                repaint();
            }
        });
    }
    /**
     * Pillanatfelv�telek k�sz�t�se.
     */
    public void pillanatfelvétel() {
        // Az elmentend� k�p elk�sz�t�se:
        java.awt.image.BufferedImage mentKép =
                new java.awt.image.BufferedImage(szélesség, magasság,
                java.awt.image.BufferedImage.TYPE_INT_RGB);
        java.awt.Graphics g = mentKép.getGraphics();
        g.drawImage(kép, 0, 0, this);
        g.setColor(java.awt.Color.BLACK);
        g.drawString("a=" + a, 10, 15);
        g.drawString("b=" + b, 10, 30);
        g.drawString("c=" + c, 10, 45);
        g.drawString("d=" + d, 10, 60);
        g.drawString("n=" + iterációsHatár, 10, 75);
        if(számításFut) {
            g.setColor(java.awt.Color.RED);
            g.drawLine(0, sor, getWidth(), sor);
        }
        g.setColor(java.awt.Color.GREEN);
        g.drawRect(x, y, mx, my);
        g.dispose();
        // A pillanatfelv�tel k�pf�jl nev�nek k�pz�se:
        StringBuffer sb = new StringBuffer();
        sb = sb.delete(0, sb.length());
        sb.append("MandelbrotHalmazNagyitas_");
        sb.append(++pillanatfelvételSzámláló);
        sb.append("_");
        // A f�jl nev�be belevessz�k, hogy melyik tartom�nyban
        // tal�ltuk a halmazt:
        sb.append(a);
        sb.append("_");
        sb.append(b);
        sb.append("_");
        sb.append(c);
        sb.append("_");
        sb.append(d);
        sb.append(".png");
        // png form�tum� k�pet ment�nk
        try {
            javax.imageio.ImageIO.write(mentKép, "png",
                    new java.io.File(sb.toString()));
        } catch(java.io.IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * A nagy�tand� kijel�lt ter�letet jelz� n�gyzet kirajzol�sa.
     */
    public void paint(java.awt.Graphics g) {
        // A Mandelbrot halmaz kirajzol�sa
        g.drawImage(kép, 0, 0, this);
        // Ha �ppen fut a sz�m�t�s, akkor egy v�r�s
        // vonallal jel�lj�k, hogy melyik sorban tart:
        if(számításFut) {
            g.setColor(java.awt.Color.RED);
            g.drawLine(0, sor, getWidth(), sor);
        }
        // A jelz� n�gyzet kirajzol�sa:
        g.setColor(java.awt.Color.GREEN);
        g.drawRect(x, y, mx, my);
    }
    /**
     * Hol �ll az eg�rmutat�?
     * @return int a kijel�lt pont oszlop poz�ci�ja.
     */    
    public int getX() {
        return x;
    }
    /**
     * Hol �ll az eg�rmutat�?
     * @return int a kijel�lt pont sor poz�ci�ja.
     */    
    public int getY() {
        return y;
    }
    /**
     * P�ld�nyos�t egy Mandelbrot halmazt nagy�t� obektumot.
     */
    public static void main(String[] args) {
        // A kiindul� halmazt a komplex s�k [-2.0, .7]x[-1.35, 1.35]
        // tartom�ny�ban keress�k egy 600x600-as h�l�val �s az
        // aktu�lis nagy�t�si pontoss�ggal:
        new MandelbrotHalmazNagyító(-2.0, .7, -1.35, 1.35, 600, 2000);
    }
}                    
