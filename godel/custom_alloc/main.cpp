#include "custom_alloc.hpp"
#include <vector>
#include <iostream>
int main()
{
    std::vector<int,custom_allocator<int>> v;
    for(int i = 0; i < 100; i++)
    {
        v.push_back(i);
    }

    for(int i = 0; i < 100; i++)
    {
        std::cout << v[i] << '\n';
    }

    v.clear();
    return 0;
}