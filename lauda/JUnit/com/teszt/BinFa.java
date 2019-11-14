package com.teszt;


public class BinFa {
//methods
	
	public BinFa()
	{
		this.root = this.fa = new Node();
	}
	
	public int getDepth()
	{
		return getdepth(this.root);
	}
	
	int max(int a, int b)
	{
		return (a > b? a : b);
	}
	
	int getdepth(Node e)
	{
		if(e.left == null && e.right == null)
		{
			return 0;
		}
		else if(e.left == null && e.right != null)
		{
			return getdepth(e.right) + 1;
		}
		else if(e.left != null && e.right == null)
		{
			return getdepth(e.left) + 1;
		}
		else
		{
			return max(getdepth(e.left), getdepth(e.right));
		}
	}
	
	public double getAverage()
	{
		average = 0;
		get_average(root);
		average = sum_of_depth / num_of_leaf_elements;
		return average;
	}
	
	void
	get_average (Node fa)
	{

	  if (fa != null)
	    {
	      ++depth;
	      get_average (fa.right);
	      get_average (fa.left);
	      --depth;

	      if (fa.right == null && fa.left == null)
		{

		  ++num_of_leaf_elements;
		  sum_of_depth += depth;

		}

	    }

	}
	
	void getdeviation (Node fa)
	{
	
		if (fa != null)
		{
			++depth;
			getdeviation (fa.right);
			getdeviation (fa.left);
			--depth;
			
			if (fa.right == null && fa.left == null)
			{
				++num_of_leaf_elements;
				deviation += ((depth - average) * (depth - average));
			}
		
		}
	
	}
	
	public double getDeviation()
	{
		deviation = 0;
		average = getAverage();
		getdeviation(this.root);
		return deviation;
	}
	
	public void add(char c)
	{
		if(fa.left != null)
		{
			if(fa.c == '0')
			{
				fa = fa.left;
			}
			else
			{
				fa.left = new Node('0');
				fa = root;
			}
		}
		else if(fa.right != null)
		{
			if(fa.c == '1')
			{
				fa = fa.right;
			}
			else
			{
				fa.right = new Node('1');
				fa = root;
			}
		}
	}
	
//fields and others
	
	class Node{
		public Node()
		{
			this.left = null;
			this.right = null;
			this.c = '/';
		}
		
		public Node(char c)
		{
			this.left = null;
			this.right = null;
			this.c = c;
		}
		
		Node right;
		Node left;
		char c;
	}
	
	Node root;
	public Node fa;
	
	int sum_of_depth = 0;
	int depth = 0;
	int num_of_leaf_elements = 0;
	double deviation = 0.0;
	double average = 0.0;
}
