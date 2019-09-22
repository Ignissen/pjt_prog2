
class szgy
{
    public static void main(String[] args)
    {
        parent p = new parent();
        parent c = new child();
        p.print();
        c.print();
        c.add(5.0, 2.2);
    }
}