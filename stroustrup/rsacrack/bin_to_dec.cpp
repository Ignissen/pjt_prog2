#include <iostream>
#include <fstream>

int main(int argc, char* argv[])
{
    if(argc != 2)
    {
        std::cout << "Use: ./b2d [filename]" << '\n';
        return 1;
    }

    std::ifstream fin(argv[1], std::ifstream::binary);
    std::string outname = argv[1];
    outname.append(".out.dec.txt");
    std::ofstream fout(outname.c_str());
    char c;
    while(!fin.eof())
    {
        fin>>c;
        fout<< " " <<(int) c;
    }
    return 0;
}