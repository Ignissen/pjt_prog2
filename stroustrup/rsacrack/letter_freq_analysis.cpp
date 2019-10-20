#include <iostream>
#include <fstream>
#include <map>
#include <vector>
#include <locale.h>
#include <string.h>

template<class T>
void swap(T &a, T &b)
{
    T tmp = a;
    a = b;
    b = tmp;
}

template<class T, class U>
void bubble(std::vector<T> &sort_by, std::vector<U> &other)
{
    for(unsigned int i = 0; i < sort_by.size(); i++)
    {
        for(int j = sort_by.size() - 1; j > 0; j--)
        {
            if(sort_by[j] > sort_by[j - 1])
            {
                swap(sort_by[j], sort_by[j - 1]);
                swap(other[j], other[j - 1]);
            }
        }
    }
}

std::string freq[26] = {
    "e",
    "t",
    "a",
    "o",
    "i",

    "n",
    "s",
    "h",
    "r",
    "d",

    "l",
    "c",
    "u",
    "m",
    "w",

    "f",
    "g",
    "y",
    "p",
    "b",

    "v",
    "k",
    "j",
    "x",
    "q",
    "z"
};

void print_help()
{
    std::cout << "Use: ./letter_freq [filename]" << '\n';
    std::cout << "You can use -int to print the characters as the decimal representation." << '\n';
}

int main(int argc, char* argv[])
{
    setlocale(LC_ALL, "LC_ALL_HU");
    if(argc < 2 || argc > 3)
    {
        print_help();
        return 1;
    }
    std::ifstream fin(argv[1]);
    std::map<char, int> num_of_chars;
    char c;
    while(!fin.eof())
    {
        fin>>c;
        num_of_chars[c]++;
    }
    fin.close();
    std::vector<char> v1;
    std::vector<int> v2;
    for(std::map<char, int>::iterator it = num_of_chars.begin(); it != num_of_chars.end(); it++)
    {
        //std::cout << it->first << " : " << it->second << '\n';
        v1.push_back(it->first);
        v2.push_back(it->second);
    }
    bubble(v2, v1);

    std::cout << "These are not surely the accurate substitution, but the most likely if the text is in english." << '\n';
    if(argc == 2)
    {
        for(unsigned i = 0; i < v1.size(); i++)
        {
            std::cout << v1[i] << " : " << v2[i];
            if(i < 26)
            {
                std::cout << " -- " << freq[i];
            }
            std::cout << '\n';
        }
    }
    else if(argc == 3 && strcmp(argv[2],"-int") == 0)
    {
        for(unsigned i = 0; i < v1.size(); i++)
        {
            std::cout <<(int) v1[i] << " : " << v2[i];
            if(i < 26)
            {
                std::cout << " -- " << freq[i];
            }
            std::cout << '\n';
        }
    }
    else
    {
        print_help();
        return 2;
    }
    
    
    
    
    return 0;
}