package com.ignissen.binfa.aspect;

import java.io.PrintWriter;
import java.util.Random;

public aspect MyAspect {

	/*pointcut callPreorder(PrintWriter pw, BinFa b) : call (void preorder(PrintWriter)) && args(pw) && target(b);
	pointcut callInorder(PrintWriter pw, BinFa b) : call (void inorder(PrintWriter)) && args(pw) && target(b);
	pointcut callPostorder(PrintWriter pw, BinFa b) : call (void postorder(PrintWriter)) && args(pw) && target(b);*/
	
	pointcut callPreorder(PrintWriter pw) : call (void preorder(PrintWriter)) && args(pw);
	pointcut callInorder(PrintWriter pw) : call (void inorder(PrintWriter)) && args(pw);
	pointcut callPostorder(PrintWriter pw) : call (void postorder(PrintWriter)) && args(pw);
	
	
	/*Object around(PrintWriter pw, BinFa b) : callPreorder(pw, b)
	{
		System.out.println("Preorder bejárás befejezve.");
		return new Object();
	}*/
	
	/*after(PrintWriter pw, BinFa b) : callPreorder(pw, b)
	{
		System.out.println("Preorder bejárás befejezve.");
	}
	
	after(PrintWriter pw, BinFa b) : callInorder(pw, b)
	{
		System.out.println("Inorder bejárás befejezve.");
	}
	
	after(PrintWriter pw, BinFa b) : callPostorder(pw, b)
	{
		System.out.println("Postorder bejárás befejezve.");
	}
	
	before(PrintWriter pw, BinFa b) : callPreorder(pw, b)
	{
		System.out.println("Preorder bejárás következik.");
	}
	
	before(PrintWriter pw, BinFa b) : callInorder(pw, b)
	{
		System.out.println("Inorder bejárás következik.");
	}
	
	before(PrintWriter pw, BinFa b) : callPostorder(pw, b)
	{
		System.out.println("Postorder bejárás következik.");
	}*/
	
	after(PrintWriter pw) : callPreorder(pw)
	{
		System.out.println("Preorder bejárás befejezve.");
	}
	
	after(PrintWriter pw) : callInorder(pw)
	{
		System.out.println("Inorder bejárás befejezve.");
	}
	
	after(PrintWriter pw) : callPostorder(pw)
	{
		System.out.println("Postorder bejárás befejezve.");
	}
	
	before(PrintWriter pw) : callPreorder(pw)
	{
		System.out.println("Preorder bejárás következik.");
	}
	
	before(PrintWriter pw) : callInorder(pw)
	{
		System.out.println("Inorder bejárás következik.");
	}
	
	before(PrintWriter pw) : callPostorder(pw)
	{
		System.out.println("Postorder bejárás következik.");
	}
	
	/*before(PrintWriter pw) : callPreorder(pw) || callInorder(pw) || callPostorder(pw)
	{
		System.out.println("Bejárás kezdődik");
	}
	after(PrintWriter pw) : callPreorder(pw) || callInorder(pw) || callPostorder(pw)
	{
		System.out.println("Bejárás kezdődik");
	}*/
	
	/*static void doStuff()
	{
		BinFa b = new BinFa();
		Random r = new Random();
		
		for(int i = 0; i < 1000; i++)
		{
			if(r.nextInt(2) == 0)
			{
				try {
					b.add('0');
				} catch (Exception e) {
					e.printStackTrace();
					System.err.println(e.getMessage());
					return;
				}
			}
			else
			{
				try {
					b.add('1');
				} catch (Exception e) {
					e.printStackTrace();
					System.err.println(e.getMessage());
					return;
				}
			}
		}
		//PrintWriter pw = new PrintWriter(System.out);
		b.preorder(new PrintWriter(System.out));
	}*/
	
	public static void main(String[] args) {
		//doStuff();
		BinFa b = new BinFa();
		Random r = new Random();
		
		for(int i = 0; i < 50; i++)
		{
			if(r.nextInt(2) == 0)
			{
				try {
					b.add('0');
				} catch (Exception e) {
					e.printStackTrace();
					System.err.println(e.getMessage());
					return;
				}
			}
			else
			{
				try {
					b.add('1');
				} catch (Exception e) {
					e.printStackTrace();
					System.err.println(e.getMessage());
					return;
				}
			}
		}
		//PrintWriter pw = new PrintWriter(System.out);
		b.preorder(new PrintWriter(System.out));
		
		b.inorder(new PrintWriter(System.out));
		
		b.postorder(new PrintWriter(System.out));
	
	}
	
}
