#include <iostream>

using namespace std;

class Bird
{
public:
    Bird(){}
    void fly()
    {
        cout << "I'm flying!!" << endl;
    }
};

class Eagle : public Bird
{};
class Penguin : public Bird
{};

int main()
{
    Eagle e;
    Penguin p;
    e.fly();
    p.fly();
    return 0;
}