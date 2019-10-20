#include <iostream>
#include <string.h>

void print_help()
{
    std::cout << "Use: ./ceasar [encrypt/decrypt] [numbers to shift]\tThis way it will read the standard input." << '\n';
    std::cout << "If you use it manually (not attaching a file to the satndard input), you have to exit the program via CTRL+D" << '\n';
    std::cout << "Use: ./ceasar [encrypt/decrypt] [string to encrypt] [numbers to shift]\tThis way it will read the second argument as input." << '\n';
    std::cout << "encrypt can be shortened as -e and decrypt can be shortened as -d" << '\n';
}

int main(int argc, char* argv[])
{
    if(argc == 3)
    {
        int shift = atoi(argv[2]);

        if(strcmp(argv[1], "encrypt") == 0 || strcmp(argv[1], "-e") == 0)
        {
            std::string clean;
            while(std::getline(std::cin, clean))
            {
                std::string encrypted = "";
                for(unsigned int i = 0; i < clean.length(); i++)
                {
                    encrypted += clean[i]+shift;
                }
                std::cout << encrypted << '\n';
            }
        }
        else if(strcmp(argv[1], "decrypt") == 0 || strcmp(argv[1], "-d") == 0)
        {
            std::string encrypted;
            while(std::getline(std::cin, encrypted))
            {
                std::string clean = "";
                for(unsigned int i = 0; i < encrypted.length(); i++)
                {
                    clean += encrypted[i]-shift;
                }
                std::cout << clean << '\n';
            }
        }
        else
        {
            print_help();
            return 2;
        }
    }
    else if(argc == 4)
    {
        int shift = atoi(argv[3]);

        if(strcmp(argv[1], "encrypt") == 0 || strcmp(argv[1], "-e") == 0)
        {
            std::string clean = argv[2];
            std::string encrypted = "";
            for(unsigned int i = 0; i < clean.length(); i++)
            {
                encrypted += clean[i]+shift;
            }
            std::cout << encrypted << '\n';
        }
        else if(strcmp(argv[1], "decrypt") == 0 || strcmp(argv[1], "-d") == 0)
        {
            std::string encrypted = argv[2];
            std::string clean = "";
            for(unsigned int i = 0; i < encrypted.length(); i++)
            {
                clean += encrypted[i]-shift;
            }
            std::cout << clean << '\n';
        }
        else
        {
            print_help();
            return 2;
        }
    }
    else
    {
        print_help();
        return 3;
    }
    
    return 0;
}