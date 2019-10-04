

class child extends parent
{
    child()
    {
        super();
    }
    @Override
    public void print()
    {
        System.out.println("Child");
    }
    public double add (double a, double b)
    {
        return a+b;
    }
}