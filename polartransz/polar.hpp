#ifndef POLAR_H
#define POLAR_H
#include <cstdlib>
#include <ctime>
#include <cmath>

class Polar 
{
public:
    Polar()
    {
        srand(time(0));
        haveNext = false;
    }

    double nextGaussian()
    {
        if(haveNext)
        {
            haveNext = false;
            return stored; 
        }
        else
        {
            double u1 = 0, u2 = 0, v1, v2, w;
            do
            {
                u1 += (rand() % 10000) / 10000.0;
                u2 += (rand() % 10000) / 10000.0;           
                v1 = 2 * u1 -1;
                v2 = 2 * u2 -1;
                w = v1 * v1 + v2 * v2;
            }while(w > 1);

            double r = sqrt((-2 * log(w)) / w);

            stored = r * v2;
            haveNext = true;
            return r * v1;
        }
    }
private:
    bool haveNext;
    double stored;
};

#endif