

public class Main
{

    public static void main(String[] args)
    {
        Polar p = new Polar();
        for(int i = 0; i < 10; i++)
        {
            System.out.println(i + ": " + p.nextGaussian());
        }
    }
}