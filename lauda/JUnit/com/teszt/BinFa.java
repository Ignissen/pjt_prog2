package com.teszt;


public class BinFa {
//methods
	
	public BinFa()
	{
		this.root = this.tree_ptr = new Node();
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
			return max(getdepth(e.left), getdepth(e.right)) + 1;
		}
	}
	
	public double getAverage()
	{
		average = 0;
		num_of_leaf_elements = 0;
		sum_of_depth = 0;
		get_average(root,0);
		average = sum_of_depth / num_of_leaf_elements;
		return (float)sum_of_depth / num_of_leaf_elements;
	}
	
	void Preorder()
	{
		preorder(root, 0);
	}
	
	void preorder(Node e, int depth)
	{
		if(e != null)
		{
			for(int i = 0; i < depth; i++)
			{
				System.out.print("--");
			}
			System.out.println(e.c);
			preorder(e.left, depth + 1);
			preorder(e.right, depth + 1);
		}
	}
	
	void get_average (Node e, int depth)
	{

	  if (e != null)
	    {
	      //++depth;
	      get_average (e.right, depth + 1);
	      get_average (e.left, depth + 1);
	      //--depth;

	      if (e.right == null && e.left == null)
		{

		  ++num_of_leaf_elements;
		  sum_of_depth += depth;

		}

	    }

	}
	
	
	public double getDeviation()
	{
        average = getAverage();
        
        deviation = 0.0;
        num_of_leaf_elements = depth = 0;
        
        getdeviation(root);
        
        if(num_of_leaf_elements-1 > 0)
        	deviation = Math.sqrt(deviation/(num_of_leaf_elements-1));
        else
        	deviation = Math.sqrt(deviation);
            
        return deviation;
    }
    
    private void getdeviation(Node rhs){
       if(rhs != null){ 
        ++depth;
        getdeviation(rhs.left);
        getdeviation(rhs.right);
        --depth;

             
        if(rhs.left == null && rhs.right == null)
        {
        	num_of_leaf_elements ++;
            deviation += Math.pow((depth-average), 2);
        }
       }
    }	
	
	public void add(char c)
	{
		if(c == '0')
		{
			if(tree_ptr.left != null)
			{
				tree_ptr = tree_ptr.left;
			}
			else
			{
				tree_ptr.left = new Node('0');
				tree_ptr = root;
			}
		}
		else if(c == '1')
		{
			if(tree_ptr.right != null)
			{
				tree_ptr = tree_ptr.right;
			}
			else
			{
				tree_ptr.right = new Node('1');
				tree_ptr = root;
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
	Node tree_ptr;
	
	int sum_of_depth = 0;
	int depth = 0;
	int num_of_leaf_elements = 0;
	double deviation = 0.0;
	double average = 0.0;
}
