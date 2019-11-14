import static org.junit.jupiter.api.Assertions.assertEquals;

//import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.teszt.BinFa;

class TesztJUnit {
	
	static BinFa b;
	
	@BeforeAll
	static void init()
	{
		b = new BinFa();
		String s = "01111001001001000111";
		for(int i = 0; i < s.length(); i++)
		{
			b.add((char)s.charAt(i));
		}
	}
	
	@Test
	void testDepth() {
		assertEquals(4,b.getDepth());
	}
	
	@Test
	void testAverage()
	{
		assertEquals(2.750000,b.getAverage());
	}
	static double round(double num, int factors)
	{
		double f = java.lang.Math.pow(10, factors);
		num *= f;
		num = java.lang.Math.round(num);
		return num/ f;
	}
	@Test
	void testDeviation()
	{
		assertEquals(0.957427,round(b.getDeviation(), 6));
	}

}
