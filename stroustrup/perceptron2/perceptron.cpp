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
        std::cerr << "Use: ./perc path/to/png epochs [output filename]" << '\n';
        return 1;
    }
    int epochs = atoi(argv[2]);
    png::image<png::rgb_pixel> png_img(argv[1]);
	int s = png_img.get_width()*png_img.get_height();

	Perceptron* p1 = new Perceptron(3,s,256,s);

	double* image_r = new double[s];
    double* image_g = new double[s];
    double* image_b = new double[s];

    double* random_array = new double[s];

	for(int i=0;i<png_img.get_height();i++)
	{
		for(int j=0;j<png_img.get_width();j++)
		{
			/*image_r[i*png_img.get_width()+j] = p1->sigmoid(png_img[j][i].red);
            image_g[i*png_img.get_width()+j] = p1->sigmoid(png_img[j][i].green);
            image_b[i*png_img.get_width()+j] = p1->sigmoid(png_img[j][i].blue);*/

            image_r[i*png_img.get_width()+j] = png_img[j][i].red;
            image_g[i*png_img.get_width()+j] = png_img[j][i].green;
            image_b[i*png_img.get_width()+j] = png_img[j][i].blue;
		}
	}
    //p->learning(image_r, 1, 1);
    //p->learning(image_g, 1, 1);
    //p->learning(image_b, 1, 1);

    for(int i = 0; i < epochs; i++)
    {
        std::cout << "Epoch " << i << " started." << '\n';
        randomize(random_array, s);
        p1->learning(image_r, random_array);
        randomize(random_array, s);
        p1->learning(image_g, random_array);
        randomize(random_array, s);
        p1->learning(image_b, random_array);
        std::cout << "Epoch " << i << " finished." << '\n';
    }

    p1->gen_img(image_r, s);
    p1->gen_img(image_g, s);
    p1->gen_img(image_b, s);

	for(int i=0;i<png_img.get_height();i++)
	{
		for(int j=0;j<png_img.get_width();j++)
		{
			png_img[i][j] = png::rgb_pixel(image_r[i*png_img.get_width()+j]*255,image_g[i*png_img.get_width()+j]*255,image_b[i*png_img.get_width()+j]*255);
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
    
	delete [] image_r;
    delete [] image_g;
    delete [] image_b;
	delete p1;
    //delete p2;
    //delete p3;
	return 0;
}