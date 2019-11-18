package com.ignissen.binfa.aspect;

import java.io.PrintWriter;

public class BinFa {
//methods
	
	public BinFa()
	{
		this.root = this.fa = new Node();
		pw = null;
	}
	
	public void add(char c) throws Exception
	{
		if(c == '0')
		{
			if(fa.left != null)
			{
				fa = fa.left;
			}
			else
			{
				fa.left = new Node('0');
				fa = root;
			}
		}
		else if(c == '1')
		{
			if(fa.right != null)
			{
				fa = fa.right;
			}
			else
			{
				fa.right = new Node('1');
				fa = root;
			}
		}
		/*else
		{
			throw new Exception("The input should be either '0' or '1'");
		}*/
	}
	
	public void preorder(PrintWriter pw)
	{
		this.pw = pw;
		prv_preorder(root, 0);
		this.pw.flush();
		//this.pw.close();
		//this.pw = null;
		//return;
	}
	
	private void prv_preorder(Node e, int depth)
	{
		if(e == null)
		{
			return;
		}
		for(int i = 0; i < depth; i++)
		{
			pw.print("--");
		}
		pw.println(e.c);
		prv_preorder(e.left, depth + 1);
		prv_preorder(e.right, depth + 1);
	}
	
	public void inorder(PrintWriter pw)
	{
		this.pw = pw;
		prv_inorder(root, 0);
		this.pw.flush();
		//this.pw.close();
		//this.pw = null;
	}
	
	private void prv_inorder(Node e, int depth)
	{
		if(e == null)
		{
			return;
		}
		for(int i = 0; i < depth; i++)
		{
			pw.print("--");
		}
		prv_inorder(e.left, depth + 1);
		pw.println(e.c);
		prv_inorder(e.right, depth + 1);
	}
	
	public void postorder(PrintWriter pw)
	{
		this.pw = pw;
		prv_postorder(root, 0);
		this.pw.flush();
		//this.pw.close();
		//this.pw = null;
	}
	
	private void prv_postorder(Node e, int depth)
	{
		if(e == null)
		{
			return;
		}
		for(int i = 0; i < depth; i++)
		{
			pw.print("--");
		}
		prv_postorder(e.left, depth + 1);
		prv_postorder(e.right, depth + 1);
		pw.println(e.c);
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
	Node fa;
	
	PrintWriter pw; //This is for printing the tree to the correct output
}
