import java.util.Random;

public class Main
{
    public static void main(String[] args) 
    {
        Random r = new Random();
        Binfa b = new Binfa();
        for(int i=0; i<100;i++)
        {
            if(r.nextInt(2) == 1)
            {
                b.add('1');
            }
            else
            {
                b.add('0');
            }
        }
        b.inorder(b.getRoot());
    }
}