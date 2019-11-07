#include <map>
#include <vector>
#include <algorithm>
#include <utility>
#include <iostream>

std::vector<std::pair<std::string, int>> sort_map ( std::map <std::string, int> &rank )
{
        std::vector<std::pair<std::string, int>> ordered;

        for ( auto & i : rank ) {
                if ( i.second ) {
                        std::pair<std::string, int> p {i.first, i.second};
                        ordered.push_back ( p );
                }
        }

        std::sort (
                std::begin ( ordered ), std::end ( ordered ),
        [ = ] ( auto && p1, auto && p2 ) {
                return p1.second > p2.second;
        }
        );

        return ordered;
}

int main()
{
    std::map<std::string, int> my_map;
    my_map["audi"] = 40;
    my_map["ford"] = 30;
    my_map["bmw"] = 10;
    my_map["dodge"] = 5;
    my_map["renault"] = 200;
    my_map["peugeot"] = 40;
    my_map["fiat"] = 30;

    std::cout << "Original map: \n";
    for(auto i : my_map)
    {
        std::cout << i.first << " => " << i.second << std::endl;
    }

    std::vector<std::pair<std::string, int>> v = sort_map(my_map);
    std::cout << "\nSorted map: \n";
    for(auto i : v)
    {
        std::cout << i.first << " => " << i.second << std::endl;
    }

    return 0;
}