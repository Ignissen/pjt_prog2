import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.teszt.BinFa;

class TesztJUnit {
	
	BinFa b;
	
	
	
	@BeforeAll
	void init()
	{
		b = new BinFa();
		String s = "01111001001001000111";
		for(int i = 0; i < s.length(); i++)
		{
			b.add(s.charAt(i));
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
	@Test
	void testDeviation()
	{
		assertEquals(0.957427,b.getDeviation());
	}

}
