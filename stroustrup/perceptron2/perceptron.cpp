#include "perceptron.hpp"

#include <iostream>
#include <png++/png.hpp>


int main(int argc, char* argv[])
{
    /*for(int i = 0; i < argc; i++)
    {
        std::cout << argv[i] << '\n';
    }*/
    if(argc != 2)
    {
        std::cerr << "Use: ./perc path/to/png" << '\n';
        return 1;
    }
    
    png::image<png::rgb_pixel> png_img(argv[1]);
	int s = png_img.get_width()*png_img.get_height();

	Perceptron* p = new Perceptron(3,s,256,s);

	double* image_r = new double[s];
    double* image_g = new double[s];
    double* image_b = new double[s];

	for(int i=0;i<png_img.get_height();i++)
	{
		for(int j=0;j<png_img.get_width();j++)
		{
			image_r[i*png_img.get_width()+j] = png_img[j][i].red;
            image_g[i*png_img.get_width()+j] = png_img[j][i].green;
            image_b[i*png_img.get_width()+j] = png_img[j][i].blue;
		}
	}
    p->learning(image_r, 1, 1);
    p->learning(image_g, 1, 1);
    p->learning(image_b, 1, 1);

    p->gen_img(image_r, s);
    p->gen_img(image_g, s);
    p->gen_img(image_b, s);

	for(int i=0;i<png_img.get_height();i++)
	{
		for(int j=0;j<png_img.get_width();j++)
		{
			png_img[i][j] = png::rgb_pixel(image_r[i*png_img.get_width()+j]*255,image_g[i*png_img.get_width()+j]*255,image_b[i*png_img.get_width()+j]*255);
		}
	}
	png_img.write("generated.png");
	delete [] image_r;
    delete [] image_g;
    delete [] image_b;
	delete p;
	return 0;
}