#include <iostream>
#include "polar.hpp"
using namespace std;

int main()
{
    Polar* p = new Polar();
    for(int i = 0; i < 10; i++)
    {
        cout << i << ": " << p->nextGaussian() << endl;
        //system("sleep 2");
    }
    return 0;

}
