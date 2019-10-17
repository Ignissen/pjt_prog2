#include <iostream>
#include <map>
#include <vector>
#include <cstdlib>
#include <ctime>
#include <boost/algorithm/string.hpp>

using std::cout;
using std::cin;
using std::cerr;
#define endl '\n'

class Leet
{
public:
    Leet(){
        initDict();
        srand(time(0));
    }
    std::string l337ify(const std::string& s)
    {
        std::string output = "";
        for(char c : s)
        {
            auto search = dict.find(c);
            if(search != dict.end())
            {
                output.append(dict[c][rand()%dict[c].size()]);
            }
            else
            {
                output += c;
            }
        }
        return output;
    }

private:
    std::map<char, std::vector<std::string>> dict;
    void initDict()
    {
        dict['a'] = std::vector<std::string>({"4","/-\\","a","/\\","a","a","a"});
        dict['b'] = std::vector<std::string>({"!3","|3","8","ß","b","b","b"});
        dict['c'] = std::vector<std::string>({"[","<","{","c","c","c","c"});
        dict['d'] = std::vector<std::string>({"|}","d","|>","T)","d","d","d"});
        dict['e'] = std::vector<std::string>({"3","&","e","€","e","e","e"});
        dict['f'] = std::vector<std::string>({"|=","|#","/=","f","f","f","f"});
        dict['g'] = std::vector<std::string>({"&","6","g","(_+","g","g","g"});
        dict['h'] = std::vector<std::string>({"|-|",")-(","[-]","h","h","h","h"});
        dict['i'] = std::vector<std::string>({"1","[]","!","|","i","i","i"});
        dict['j'] = std::vector<std::string>({"_|",";","1","j","j","j","j"});

        dict['k'] = std::vector<std::string>({">|","1<","|c","k","k","k","k"});
        dict['l'] = std::vector<std::string>({"1","|_","l","|","l","l","l"});
        dict['m'] = std::vector<std::string>({"/\\/\\","/V\\","[V]","m","m","m","m"});
        dict['n'] = std::vector<std::string>({"</>","n","|\\|","^/","n","n","n"});
        dict['o'] = std::vector<std::string>({"0","Q","o","<>","o","o","o"});
        dict['p'] = std::vector<std::string>({"|*","|>","p","|7","p","p","p"});
        dict['q'] = std::vector<std::string>({"(_,)","q","9","&","q","q","q"});
        dict['r'] = std::vector<std::string>({"I2","|?","Iz","r","r","r","r"});
        dict['s'] = std::vector<std::string>({"s","5","z","§","s","s","s"});
        dict['t'] = std::vector<std::string>({"4","-|-","7","t","t","t","t"});

        dict['u'] = std::vector<std::string>({"(_)","u","v","L|","u","u","u"});
        dict['v'] = std::vector<std::string>({"v","\\/","|/","\\|","v","v","v"});
        dict['w'] = std::vector<std::string>({"\\/\\/","w","\\x/","\\\\//\\\\//","w","w","w"});
        dict['x'] = std::vector<std::string>({"4","}{","><","x","x","x","x"});
        dict['y'] = std::vector<std::string>({"y","j","`/","\\|/","y","y","y"});
        dict['z'] = std::vector<std::string>({"2","-/_","z",">_","z","z","z"});

        dict['1'] = std::vector<std::string>({"I","1","L","I"});
        dict['2'] = std::vector<std::string>({"R","2","2","Z"});
        dict['3'] = std::vector<std::string>({"E","3","E","3"});
        dict['4'] = std::vector<std::string>({"4","A","A","4"});
        dict['5'] = std::vector<std::string>({"S","5","S","5"});
        dict['6'] = std::vector<std::string>({"b","6","G","6"});
        dict['7'] = std::vector<std::string>({"7","7","L","T"});
        dict['8'] = std::vector<std::string>({"8","B","8","B"});
        dict['9'] = std::vector<std::string>({"g","q","9","9"});
        dict['0'] = std::vector<std::string>({"0","()","[]","0"});
    }
};

int main()
{
    //cout << "Please use english letters and numbers only!" << endl;
    Leet l;
    std::string s;
    do
    {
        cout << '>';
        getline(cin,s);
        boost::algorithm::to_lower(s);
        cout << l.l337ify(s) << endl;
    }
    while(s != "stop");
    return 0;
}