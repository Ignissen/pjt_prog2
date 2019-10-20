import java.math.BigInteger;

public class RSAPelda
{
    public static void main(String[] args)
    {
        //System.out.println(args.length);
        if(args.length != 1)
        {
            System.out.println("Use: java RSAPelda <input string>");
            return;
        }
        KulcsPar2 jSzereplo = new KulcsPar2();
        String clean = args[0];
        byte[] buffer = clean.getBytes();
        java.math.BigInteger[] titkos = new java.math.BigInteger[buffer.length];

        for(int i = 0; i < titkos.length; i++)
        {
            titkos[i] = new java.math.BigInteger(new byte[]{buffer[i]});
            titkos[i] = titkos[i].modPow(jSzereplo.e, jSzereplo.m);
        }
        
        for(int i = 0; i < titkos.length; i++)
        {
            titkos[i] = titkos[i].modPow(jSzereplo.e, jSzereplo.m);
            buffer[i] = titkos[i].byteValue();
        }

        System.out.println(new String(buffer));
    }
}