#define BOOST_NO_CXX11_SCOPED_ENUMS
#include <boost/filesystem.hpp>
#undef BOOST_NO_CXX11_SCOPED_ENUMS
#include <iostream>

using std::cout;
using std::cin;
using std::cerr;
//using std::endl;
#define endl '\n'
int sum = 0;
void list(boost::filesystem::path p, int depth=0)
{
    boost::filesystem::directory_iterator end_itr;
    for (boost::filesystem::directory_iterator itr(p); itr != end_itr; ++itr)
    {
        if(boost::filesystem::is_regular_file(itr->path()) && boost::filesystem::extension(itr->path()) == ".java")sum++;
        if(boost::filesystem::extension(itr->path()) == ".java")
        {
            std::string current_file = itr->path().string();
            for(int i = 0; i < depth; i++)
            {
                cout << "-";
            }
            cout << current_file << endl;
        }
        if(boost::filesystem::is_directory(itr->path()))
        {
            list(itr->path(), depth+1);
        }
    }
}

int main(int argc, char* argv[])
{
    if(argc != 2)
    {
        cerr << "Usage: ./boost <directory to list>" << endl;
        return -1;
    }
    boost::filesystem::path p(argv[1]);
    list(p);
    cout << endl << "Java osztályok száma az OpenJDK-ban: " << sum << endl;
    boost::filesystem::path p2("boost.cpp");
    return 0;
}