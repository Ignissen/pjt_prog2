import java.lang.Math;

public class bpp
{
    static <T> void print(T s)
    {
        System.out.println(s);
    }
    static double bppalg(int i)
    {
        double sum=0;
        for(int k = 0;k < i; k++)
        {
            double part1 = 1.0 / Math.pow(16, k);
            double part2 = 4.0 / (8 * k + 1);
            double part3 = 2.0 / (8 * k + 4);
            double part4 = 1.0 / (8 * k + 5);
            double part5 = 1.0 / (8 * k + 6);
            sum += part1 * (part2 - part3 - part4 - part5);
        }
        return sum;
    }
    public static void main(String[] args)
    {
        print(bppalg(100));
    }
}