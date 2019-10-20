#include <iostream>
#include <png++/png.hpp>

int main(int argc, char* argv[])
{
    if(argc == 2 && strcmp(argv[1], "--help") == 0)
    {
        std::cout << "This program compares two png files based on their pixels. Any difference will result in the program telling you the two images are different." << '\n';
        std::cout << "Use: ./cmppng <file1> <file2>" << '\n';
        return 0;
    }
    if(argc != 3)
    {
        std::cout << "Start the program with --help argument to see description." << '\n';
        std::cout << "Use: ./cmppng <file1> <file2>" << '\n';
        return 1;
    }

    png::image<png::rgb_pixel> img1(argv[1]);
    png::image<png::rgb_pixel> img2(argv[2]);

    if(img1.get_width() == img2.get_width() && img1.get_height() == img2.get_height())
    {
        for(int i = 0; i < img1.get_height(); i++)
        {
            for(int j = 0; j < img1.get_width(); j++)
            {
                if(img1[i][j].red != img2[i][j].red && img1[i][j].green != img2[i][j].green && img1[i][j].blue != img2[i][j].blue)
                {
                    std::cout << "Difference found at position: X:" << j << ", Y: " << i << '\n';
                }
            }
        }
    }
    else
    {
        std::cout << "The two images cannot be the same, since their dimensions are not the same." << '\n';
    }
    
    

    return 0;
}