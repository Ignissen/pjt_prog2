

class liskov
{
    public static void main(String[] args)
    {
        Eagle e = new Eagle();
        Penguin p = new Penguin();

        e.fly();
        p.fly();
    }
}