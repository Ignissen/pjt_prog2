#include <iostream>
#include <cmath>
#include <ctime>
using namespace std;

class bpp {
public:
    /** A Pi hexa kifejtésében a d+1. hexa jegytől néhány hexa jegy.*/
    string d16PiHexaJegyek;
    /**
     * Létrehoz egy <code>PiBBP</code>, a BBP algoritmust a Pi-hez
     * alkalmazó objektumot. A [BBP ALGORITMUS] David H. Bailey: The
     * BBP Algorithm for Pi. alapján a
     * {16^d Pi} = {4*{16^d S1} - 2*{16^d S4} - {16^d S5} - {16^d S6}}
     * kiszámítása, a {} a törtrészt jelöli.
     *
     * @param   d   a Pi hexa kifejtésében a d+1. hexa jegytől
     * számoljuk a hexa jegyeket
     */
    bpp(int d = 106) {
        
        double d16Pi = 0.0;
        
        double d16S1t = d16Sj(d, 1);
        double d16S4t = d16Sj(d, 4);
        double d16S5t = d16Sj(d, 5);
        double d16S6t = d16Sj(d, 6);
        
        d16Pi = 4.0*d16S1t - 2.0*d16S4t - d16S5t - d16S6t;
        
        d16Pi = d16Pi - floor(d16Pi);
        
        string sb = "";
        
        char hexaJegyek[] = {'A', 'B', 'C', 'D', 'E', 'F'};
        
        while(d16Pi != 0.0) {
            
            int jegy = (int)floor(16.0*d16Pi);
            
            if(jegy<10)
                sb += jegy;
            else
                sb += hexaJegyek[jegy-10];
            
            d16Pi = (16.0*d16Pi) - floor(16.0*d16Pi);
        }
        
        d16PiHexaJegyek = sb;
    }
    /**
     * BBP algoritmus a Pi-hez, a [BBP ALGORITMUS] David H. Bailey: The
     * BBP Algorithm for Pi. alapján a {16^d Sj} részlet kiszámítása.
     *
     * @param   d   a d+1. hexa jegytől számoljuk a hexa jegyeket
     * @param   j   Sj indexe
     */
    double d16Sj(int d, int j) {
        
        double d16Sj = 0.0;
        
        for(int k=0; k<=d; ++k)
            d16Sj += (double)n16modk(d-k, 8*k + j) / (double)(8*k + j);
        
        /* (bekapcsolva a sorozat elejen az első utáni jegyekben növeli pl.
            a pontosságot.)
        for(int k=d+1; k<=2*d; ++k)
            d16Sj += StrictMath.pow(16.0d, d-k) / (double)(8*k + j);
         */
        
        return d16Sj - floor(d16Sj);
    }
    /**
     * Bináris hatványozás mod k, a 16^n mod k kiszámítása.
     *
     * @param   n   kitevő
     * @param   k   modulus
     */
    long n16modk(int n, int k) {
        
        int t = 1;
        while(t <= n)
            t *= 2;
        
        long r = 1;
        
        while(true) {
            
            if(n >= t) {
                r = (16*r) % k;
                n = n - t;
            }
            
            t = t/2;
            
            if(t < 1)
                break;
            
            r = (r*r) % k;
            
        }
        
        return r;
    }
    /**
     * A kiszámolt néhány hexa jegy visszaadása. A használt lebegőpontos
     * aritmentia függvényében mondjuk az első 6 pontos peldául
     * d=1000000 esetén.
     *
     * @return String a kiszámolt néhány hexa jegy
     */
    string toString() {
        
        return d16PiHexaJegyek;
    }
};

int main() 
{
    clock_t delta = clock();        
    bpp* b = new bpp(1000);
    b->toString();
    delta = clock() - delta;
    
    cout << delta / CLOCKS_PER_SEC << endl;
    return 0;
}