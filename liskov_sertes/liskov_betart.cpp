#include <iostream>

using namespace std;

class Bird
{
public:
    Bird(){}
    virtual void move_preferred() = 0; //This stands for the most likely way that bird will change its position
};
class FlightlessBird : public Bird
{
public:
    
};
class FlyingBird : public Bird
{
public:
    void move_preferred()
    {
        cout << "I'm flying!!" <<endl;
    }
};

class Eagle : public FlyingBird
{};

class Penguin : public FlightlessBird
{
public:
    void move_preferred()
    {
        cout << "I'm swimming!" <<endl;
    }
};

class Ostrich : FlightlessBird
{
public:
    void move_preferred()
    {
        cout << "I'm running fast!" <<endl;
    }
};

int main()
{
    Eagle e;
    Penguin p;
    Ostrich o;

    e.move_preferred();
    p.move_preferred();
    o.move_preferred();
    return 0;
}