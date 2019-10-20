import java.math.BigInteger;
import java.util.Random;

public class KulcsPar2
{
    public BigInteger m;
    public BigInteger e;

    public KulcsPar2()
    {
        BigInteger p_i = new BigInteger(2100, 100, new Random());
        BigInteger q_i = new BigInteger(2100, 100, new Random());
        BigInteger z_i = new BigInteger(2100, 100, new Random());
        m = p_i.multiply(q_i);
        BigInteger d_i;
        do
        {
            do
            {
                d_i = new BigInteger(2100, 100, new Random());
            }while(d_i.equals(BigInteger.ONE));
        }while(!z_i.gcd(d_i).equals(BigInteger.ONE));
        e = d_i.modInverse(z_i);
    }
}