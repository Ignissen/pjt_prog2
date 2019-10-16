/*
 * Labirintus.java
 *
 * DIGIT 2005, Javat tan�tok
 * B�tfai Norbert, nbatfai@inf.unideb.hu
 *
 */

/**
 * A labirintust le�r� oszt�ly.
 *
 * @author B�tfai Norbert, nbatfai@inf.unideb.hu
 * @version 0.0.1
 */
public class Labirintus {
    /** A labirintus szélessége. */
    protected int szélesség;
    /** A labirintus magassága. */
    protected int magasság;
    /** A labirintus szerkezete: hol van fal, hol j�rat. */
    protected boolean[][] szerkezet;
    /** A falat a true �rt�k jelenti. */
    final static boolean FAL = true;
    /** Milyen állapotban van �ppen a JÁTÉK. */
    protected int játékállapot = 0;
    /** Norm�l m�k�d�s, a Hőssel id�k�zben semmi nem t�rt�nt. */
    public static final int JÁTÉK_MEGY_HŐS_RENDBEN = 0;    
    /** A Hőst �ppen megett�k, de m�g van �lete. */
    public final static int JÁTÉK_MEGY_MEGHALT_HŐS = 1;
    /** VÉGE a JÁTÉKnak, a JÁTÉKos gy�z�tt. */
    public final static int JÁTÉK_VÉGE_MINDEN_KINCS_MEGVAN = 2;
    /** VÉGE a JÁTÉKnak, a JÁTÉKos vesztett. */
    public final static int JÁTÉK_VÉGE_MEGHALT_HŐS = 3;
    /** A labirintus kincsei. */
    protected Kincs [] kincsek;
    /** A labirintus Szörnyei. */
    protected Szörny [] szörnyek;
    /**
     * L�trehoz egy megadott szerkezet�
     * {@code Labirintus} objektumot.
     */
    public Labirintus() {
        szerkezet = new boolean[][]{
            
    {false, false,  false, true,  false, true,  false, true,  true,  true },
    {false, false, false, false, false, false, false, false, false, false},
    {true,  false, true,  false, true,  false, true,  false, true,  false},
    {false, false, false, false, true,  false, true,  false, false, false},
    {false, true,  true,  false, false, false, true,  true,  false, true },
    {false, false, false, false, true,  false, false, false, false, false},
    {false,  true,  false, false,  false, true,  false, true,  true,  false},
    {false,  false, false, true,  false, true,  false, true,  false, false},
    {false, true, false, false, false, false, false, false, false, true },
    {false, false, false, false,  true,  false, false, false,  true,  true }
    
        };
        
        magasság = szerkezet.length;
        szélesség = szerkezet[0].length;
        
    }
    /**
     * L�trehoz egy param�terben kapott szerkezet� <code>Labirintus</code> 
     * objektumot.
     *
     * @param      kincsekSzáma       a kincsek sz�ma a labirintusban.
     * @param      szörnyekSzáma      a szörnyek sz�ma a labirintusban.
     * @exception  RosszLabirintusKivétel  ha a labirintust defini�l� t�mb 
     * nincs elk�sz�tve.
     */
    public Labirintus(boolean[][] szerkezet, int kincsekSzáma, int szörnyekSzáma)
    throws RosszLabirintusKivétel {
        
        if(szerkezet == null)
            throw new RosszLabirintusKivétel("A labirintust defini�l� t�mb nincs elk�sz�tve.");
        
        this.szerkezet = szerkezet;
        
        magasság = szerkezet.length;
        szélesség = szerkezet[0].length;
        
        kincsekSzörnyek(kincsekSzáma, szörnyekSzáma);
        
    }
    /**
     * L�trehoz egy megadott m�ret�, v�letlen szerkezet�
     * <code>Labirintus</code> objektumot.
     *
     * @param      szélesség          a labirintus szélessége.
     * @param      magasság           a labirintus magassága.
     * @param      kincsekSzáma       a kincsek sz�ma a labirintusban.
     * @param      szörnyekSzáma      a szörnyek sz�ma a labirintusban.
     */
    public Labirintus(int szélesség, int magasság,
            int kincsekSzáma, int szörnyekSzáma) {
        
        this.magasság = magasság;
        this.szélesség = szélesség;
        
        szerkezet = new boolean[magasság][szélesség];
        java.util.Random véletlenGenerátor = new java.util.Random();
        
        for(int i=0; i<magasság; ++i)
            for(int j=0; j<szélesség; ++j)
                if(véletlenGenerátor.nextInt()%3 == 0)
                    // a labirintus egy harmada lesz fal
                    szerkezet[magasság][szélesség] = false;
                else
                    // k�t harmada pedig j�rat
                    szerkezet[magasság][szélesség] = true;
        
        kincsekSzörnyek(kincsekSzáma, szörnyekSzáma);
        
    }
    /**
     * L�trehoz egy 10x10-es, be�p�tett szerkezet� <code>Labirintus</code>
     * objektumot.
     *
     * @param      kincsekSzáma       a kincsek sz�ma a labirintusban.
     * @param      szörnyekSzáma      a szörnyek sz�ma a labirintusban.
     */
    public Labirintus(int kincsekSzáma, int szörnyekSzáma) {
        
        this();
        
        magasság = szerkezet.length;
        szélesség = szerkezet[0].length;
        
        kincsekSzörnyek(kincsekSzáma, szörnyekSzáma);
        
    }
    /**
     * Egy megfelel� szerkezet� sz�veges �llom�nyb�l elk�sz�t egy �j a 
     * <code>Labirintus</code> objektumot.
     * A sz�veges �llom�ny szerkezete a k�vetkez�:
     * <pre>
     * // A labirintus szerkezet�t megad� �llom�ny, szerkezete a k�vetkez�:
     * // a kincsek sz�ma
     * // a szörnyek sz�ma
     * // a labirintus szélessége
     * // magassága
     * // fal=1 j�rat=0 ...
     * // .
     * // .
     * // .
     * 6
     * 3
     * 10
     * 10
     * 0 0 0 1 0 1 0 1 1 1
     * 0 0 0 0 0 0 0 0 0 0
     * 1 0 1 0 1 0 1 0 1 0
     * 0 0 0 0 1 0 1 0 0 0
     * 0 1 1 0 0 0 1 1 0 1
     * 0 0 0 0 1 0 0 0 0 0
     * 0 1 0 0 0 1 0 1 1 0
     * 0 0 0 1 0 1 0 1 0 0
     * 0 1 0 0 0 0 0 0 0 1
     * 0 0 0 0 1 0 0 0 1 1
     * </pre>
     *
     * @param      labirintusFájlnév       a labirintust defini�l�, megfelel� 
     * szerkezet� sz�veges �llom�ny neve.
     * @exception  RosszLabirintusKivétel  ha a labirintust defini�l� �llom�ny 
     * nincs meg, nem a megfelel� szerkezet�, vagy gond van az olvas�s�val.
     */
    public Labirintus(String labirintusFájlnév) throws RosszLabirintusKivétel {
        
        int kincsekSzáma = 6;  // ezeknek a kezd��rt�keknek nincs jelent�s�ge,
        int szörnyekSzáma = 3; // mert majd a f�jlb�l olvassuk be, amiben ha a 
        // n�gy f� adat hib�s, akkor nem is �p�tj�k fel a labirintust.
        
        // Csatorna a sz�veges �llom�ny olvas�s�hoz
        java.io.BufferedReader szövögesCsatorna = null;
        
        try {
            szövögesCsatorna = new java.io.BufferedReader(
                    new java.io.FileReader(labirintusFájlnév));
            
            String sor = szövögesCsatorna.readLine();
            
            while(sor.startsWith("//"))
                sor = szövögesCsatorna.readLine();
            
            try {
                
                kincsekSzáma = Integer.parseInt(sor);
                
                sor = szövögesCsatorna.readLine();
                szörnyekSzáma = Integer.parseInt(sor);
                
                sor = szövögesCsatorna.readLine();
                szélesség = Integer.parseInt(sor);
                
                sor = szövögesCsatorna.readLine();
                magasság = Integer.parseInt(sor);
                
                szerkezet = new boolean[magasság][szélesség];
                
            } catch(java.lang.NumberFormatException e) {
                
                throw new RosszLabirintusKivétel("Hib�s a kincsek, szörnyek sz�ma, szélesség, magasság megad�si r�sz.");
                
            }
            
            for(int i=0; i<magasság; ++i) {
                
                sor = szövögesCsatorna.readLine();
                
                java.util.StringTokenizer st =
                        new java.util.StringTokenizer(sor);
                
                for(int j=0; j<szélesség; ++j) {
                    String tegla = st.nextToken();
                    
                    try {
                        
                        if(Integer.parseInt(tegla) == 0)
                            szerkezet[i][j] = false;
                        else
                            szerkezet[i][j] = true;
                        
                    } catch(java.lang.NumberFormatException e) {
                        
                        System.out.println(i+". sor "+j+". oszlop "+e);
                        szerkezet[i][j] = false;
                        
                    }
                }
            }
            
        } catch(java.io.FileNotFoundException e1) {
            
            throw new RosszLabirintusKivétel("Nincs meg a f�jl: " + e1);
            
        } catch(java.io.IOException e2) {
            
            throw new RosszLabirintusKivétel("IO kiv�tel t�rt�nt: "+e2);
            
        } catch(java.util.NoSuchElementException e3) {
            
            throw new RosszLabirintusKivétel("Nem j� a labirintus szerkezete: "
                    +e3);
            
        } finally {
            
            if(szövögesCsatorna != null) {
                
                try{
                    szövögesCsatorna.close();
                } catch(Exception e) {}
                
            }
            
        }
        
        // Ha ide eljutottunk, akkor fel�p�lt a labirintus,
        // lehet ben�pes�teni:
        kincsekSzörnyek(kincsekSzáma, szörnyekSzáma);
        
    }
    /**
     * L�trehozza a kincseket �s a szörnyeket.
     *
     * @param      kincsekSzáma       a kincsek sz�ma a labirintusban.
     * @param      szörnyekSzáma      a szörnyek sz�ma a labirintusban.
     */
    private void kincsekSzörnyek(int kincsekSzáma, int szörnyekSzáma) {
        // Kincsek l�trehoz�sa
        kincsek = new Kincs[kincsekSzáma];
        for(int i=0; i<kincsek.length; ++i)
            kincsek[i] = new Kincs(this, (i+1)*100);
        // szörnyek l�trehoz�sa
        szörnyek = new Szörny[szörnyekSzáma];
        for(int i=0; i<szörnyek.length; ++i)
            szörnyek[i] = new Szörny(this);
        
    }
    /**
     * Megadja a JÁTÉK aktu�lis állapot�t.
     *
     * @return int a JÁTÉK aktu�lis állapota.
     */
    public int állapot() {
        
        return játékállapot;
        
    }
    /**
     * A labirintus mikrovil�g �let�nek egy pillanata: megn�zi, hogy a bolyong�
     * Hős r�tal�lt-e a kincsekre, vagy a szörnyek a Hősre. Ennek megfelel�en
     * megv�ltozik a JÁTÉK állapota.
     *
     * @param Hős aki a labirintusban bolyong.
     * @return int a JÁTÉK állapot�t le�r� k�d.
     */
    public int bolyong(Hős hős) {
        
        boolean mindMegvan = true;
        
        for(int i=0; i < kincsek.length; ++i) {
            
            // A Hős r�tal�lt valamelyik kincsre?
            if(kincsek[i].megtalált(hős))
                hős.megtaláltam(kincsek[i]);
            
            // ha ez egyszer is teljes�l, akkor nincs minden kincs megtal�lva
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
                játékállapot = JÁTÉK_MEGY_MEGHALT_HŐS;
                
                if(hős.megettek())
                    játékállapot = JÁTÉK_VÉGE_MEGHALT_HŐS;
                
                return játékállapot;
            }
            
        }
        
        return JÁTÉK_MEGY_HŐS_RENDBEN;
    }
    /**
     * Madadja, hogy fal-e a labirintus adott oszlop, sor poz�ci�ja.
     *
     * @param oszlop a labirintus adott oszlopa
     * @param sor a labirintus adott sora
     * @return true ha a poz�ci� fal vagy nincs a labirintusban.
     */
    public boolean fal(int oszlop, int sor) {
        
        if(!(oszlop >= 0 && oszlop <= szélesség-1
                && sor >= 0 && sor <= magasság-1))
            return FAL;
        else
            return szerkezet[sor][oszlop] == FAL;
        
    }
    /**
     * Madadja a labirintus szélesség�t.
     *
     * @return int a labirintus szélessége.
     */
    public int szélesség() {
        
        return szélesség;
        
    }
    /**
     * Madadja a labirintus magasság�t.
     *
     * @return int a labirintus magassága.
     */
    public int magasság() {
        
        return magasság;
        
    }
    /**
     * Megadja a labirintus szerkezet�t.
     *
     * @return boolean[][] a labirintus szerkezete.
     */
    public boolean[][] szerkezet() {
        
        return szerkezet;
        
    }
    /**
     * Megadja a labirintus kincseit.
     *
     * @return Kincs[] a labirintus kincsei.
     */
    public Kincs[] kincsek() {
        
        return kincsek;
        
    }
    /**
     * Megadja a labirintus Szörnyeit.
     *
     * @return Szörny[] a labirintus Szörnyei.
     */
    public Szörny[] szörnyek() {
        
        return szörnyek;
        
    }
    /**
     * Kinyomtatja a labirintus szerkezet�t a System.out-ra.
     */
    public void nyomtat() {
        
        for(int i=0; i<magasság; ++i) {
            for(int j=0; j<szélesség; ++j) {
                
                if(szerkezet[i][j])
                    System.out.print("|FAL");
                else
                    System.out.print("|   ");
                
            }
            
            System.out.println();
            
        }
        
    }
    /**
     * Kinyomtatja a labirintus szerkezet�t �s szerepl�it a System.out-ra.
     *
     * @param Hős akit szint�n belenyomtat a labirintusba.
     */
    public void nyomtat(Hős Hős) {
        
        for(int i=0; i<magasság; ++i) {
            for(int j=0; j<szélesség; ++j) {
                
                boolean vanSzörny = vanSzörny(i, j);
                boolean vanKincs = vanKincs(i, j);
                boolean vanHős = (i == Hős.sor() && j == Hős.oszlop());
                
                if(szerkezet[i][j])
                    System.out.print("|FAL");
                else if(vanSzörny && vanKincs && vanHős)
                    System.out.print("|SKH");
                else if(vanSzörny && vanKincs)
                    System.out.print("|SK ");
                else if(vanKincs && vanHős)
                    System.out.print("|KH ");
                else if(vanSzörny && vanHős)
                    System.out.print("|SH ");
                else if(vanKincs)
                    System.out.print("|K  ");
                else if(vanHős)
                    System.out.print("|H  ");
                else if(vanSzörny)
                    System.out.print("|S  ");
                else
                    System.out.print("|   ");
                
            }
            
            System.out.println();
            
        }
        
    }
    /**
     * Kinyomtatja a labirintus szerkezet�t �s szerepl�it egy
     * karakteres csatorn�ba.
     *
     * @param Hős akit szint�n belenyomtat a labirintusba.
     * @param csatorna ahova nyomtatunk.
     */
    public void nyomtat(Hős hős, java.io.PrintWriter csatorna) {
        
        for(int i=0; i<magasság; ++i) {
            for(int j=0; j<szélesség; ++j) {
                
                boolean vanSzörny = vanSzörny(i, j);
                boolean vanKincs = vanKincs(i, j);
                boolean vanHős = (i == hős.sor() && j == hős.oszlop());
                
                if(szerkezet[i][j])
                    csatorna.print("|FAL");
                else if(vanSzörny && vanKincs && vanHős)
                    csatorna.print("|SKH");
                else if(vanSzörny && vanKincs)
                    csatorna.print("|SK ");
                else if(vanKincs && vanHős)
                    csatorna.print("|KH ");
                else if(vanSzörny && vanHős)
                    csatorna.print("|SH ");
                else if(vanKincs)
                    csatorna.print("|K  ");
                else if(vanHős)
                    csatorna.print("|H  ");
                else if(vanSzörny)
                    csatorna.print("|S  ");
                else
                    csatorna.print("|   ");
                
            }
            
            csatorna.println();
            
        }
        
    }
    /**
     * Kinyomtatja a labirintus szerkezet�t �s szerepl�it egy sztringbe.
     *
     * @param Hős akit szint�n belenyomtat a labirintusba.
     * @return String a kinyomtatott labirintus
     */
    public String kinyomtat(Hős Hős) {
        
        StringBuffer stringBuffer = new StringBuffer();
        
        for(int i=0; i<magasság; ++i) {
            for(int j=0; j<szélesség; ++j) {
                
                boolean vanSzörny = vanSzörny(i, j);
                boolean vanKincs = vanKincs(i, j);
                boolean vanHős = (i == Hős.sor() && j == Hős.oszlop());
                
                if(szerkezet[i][j])
                    stringBuffer.append("|FAL");
                else if(vanSzörny && vanKincs && vanHős)
                    stringBuffer.append("|SKH");
                else if(vanSzörny && vanKincs)
                    stringBuffer.append("|SK ");
                else if(vanKincs && vanHős)
                    stringBuffer.append("|KH ");
                else if(vanSzörny && vanHős)
                    stringBuffer.append("|SH ");
                else if(vanKincs)
                    stringBuffer.append("|K  ");
                else if(vanHős)
                    stringBuffer.append("|H  ");
                else if(vanSzörny)
                    stringBuffer.append("|S  ");
                else
                    stringBuffer.append("|   ");
                
            }
            
            stringBuffer.append("\n");
            
        }
        
        return stringBuffer.toString();
    }
    /**
     * Madadja, hogy van-e megtal�lhat� kincs a labirintus
     * adott oszlop, sor poz�ci�ja.
     *
     * @param oszlop a labirintus adott oszlopa
     * @param sor a labirintus adott sora
     * @return true ha van.
     */
    boolean vanKincs(int sor, int oszlop) {
        
        boolean van = false;
        
        for(int i=0; i<kincsek.length; ++i)
            if(sor == kincsek[i].sor()
            && oszlop == kincsek[i].oszlop()
            && !kincsek[i].megtalálva()) {
            van = true;
            break;
            }
        
        return van;
    }
    /**
     * Madadja, hogy van-e Szörny a labirintus adott oszlop,
     * sor poz�ci�ja.
     *
     * @param oszlop a labirintus adott oszlopa
     * @param sor a labirintus adott sora
     * @return true ha van.
     */
    boolean vanSzörny(int sor, int oszlop) {
        
        boolean van = false;
        
        for(int i=0; i<szörnyek.length; ++i)
            if(sor == szörnyek[i].sor()
            && oszlop == szörnyek[i].oszlop()) {
            van = true;
            break;
            }
        
        return van;
    }
    /**
     * A labirintussal kapcsolatos apr�s�gok �n�ll� kipr�b�l�s�ra
     * szolg�l ez az ind�t� met�dus.
     *
     * @param args parancssor-argumentumok nincsenek.
     */
    public static void main(String[] args) {
        
        Labirintus labirintus = new Labirintus(6, 3);
        Hős hős = new Hős(labirintus);
        
        System.out.println(labirintus.getClass());
        System.out.println(hős.getClass());
        
    }
}
