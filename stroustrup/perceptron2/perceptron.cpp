#include "perceptron.hpp"

#include <iostream>
#include <string>
#include <png++/png.hpp>
#include <cstdlib>
#include <ctime>

void randomize(double* &array, int s)
{
    for(int i = 0; i < s; i++)
    {
        array[i] = (rand()%1000000 + 1) / 1000000.0;
    }
}

int main(int argc, char* argv[])
{
    srand(time(0));
    /*for(int i = 0; i < argc; i++)
    {
        std::cout << argv[i] << '\n';
    }*/
    if(argc < 3)
    {
        std::cerr << "Use: ./perc path/to/png [output filename]" << '\n';
        return 1;
    }
    int epochs = atoi(argv[2]);
    png::image<png::rgb_pixel> png_img(argv[1]);
	int s = png_img.get_width()*png_img.get_height();

	Perceptron* p1 = new Perceptron(3,s,256,s);

	double* image_r = new double[s];
    
    #pragma omp parallel for
	for(int i=0;i<png_img.get_height();i++)
	{
		for(int j=0;j<png_img.get_width();j++)
		{
            image_r[i*png_img.get_width()+j] =  (png_img[i][j].red + png_img[i][j].green + png_img[i][j].blue) / 3;   
		}
	}

    p1->gen_img(image_r, s);

    #pragma omp parallel for
	for(int i=0;i<png_img.get_height();i++)
	{
		for(int j=0;j<png_img.get_width();j++)
		{
			png_img[i][j] = png::rgb_pixel(image_r[i*png_img.get_width()+j]*255,image_r[i*png_img.get_width()+j]*255, image_r[i*png_img.get_width()+j]*255);
		}
	}
	if(argc == 4)
    {
        png_img.write(argv[3]);
    }
    else
    {
        png_img.write("generated.png");
    }

    delete p1;
	delete [] image_r;

	return 0;
}