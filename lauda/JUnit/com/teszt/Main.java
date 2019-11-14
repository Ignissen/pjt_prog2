package com.teszt;

public class Main {

	public static void main(String[] args) {
		BinFa b = new BinFa();
		String s = "01111001001001000111";
		for(int i = 0; i < s.length(); i++)
		{
			System.out.println(s.charAt(i));
			b.add((char)s.charAt(i));
		}
		
		b.Preorder();
		
		System.out.println("Average: " + b.getAverage());
		System.out.println("Depth: " + b.getDepth());
		System.out.println("Deviation: " + b.getDeviation());
	}

}
