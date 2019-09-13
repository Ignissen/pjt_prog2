import java.util.Random;
import java.lang.Math;
public class Polar
{
    public Polar()
    {
        haveNextGaussian = false;
        r = new Random();
    }

    public double nextGaussian()
    {
        if(haveNextGaussian)
        {
            haveNextGaussian = false;
            return tarolt;
        }
        else
        {
            double u1, u2, v1, v2, w;
            do
            {
                u1 = r.nextDouble();
                u2 = r.nextDouble();
                v1 = 2 * u1 -1;
                v2 = 2 * u2 -1;
                w = v1 * v1 + v2 * v2;
            }while(w > 1);

            double r = Math.sqrt((-2 * Math.log(w)) / w);

            tarolt = r * v2;
            haveNextGaussian = true;
            return r * v1;
        }
    }

    private boolean haveNextGaussian;
    private double tarolt;
    private Random r;
}