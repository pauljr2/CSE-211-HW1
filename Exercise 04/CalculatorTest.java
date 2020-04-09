import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class CalculatorTest2 {
	private static Calculator calculator = new Calculator();
	@Before
	public void setUp() throws Exception {
		calculator.clear();
	}

	@Test
	public void testAdd() {
		//add code here
	}

	@Test
	public void testSubstract() {
		//add code here
	}

	//@Ignore("Multiply() Not yet implemented")
	@Test
	public void testMultiply() {
		calculator.add(3);
		calculator.multiply(1);
		assertEquals(3, calculator.getResult());
		calculator.clear();
		
		calculator.add(0);
		calculator.multiply(1);
		assertEquals(0, calculator.getResult());
		calculator.clear();
		
		calculator.add(3);
		calculator.multiply(1);
		assertEquals(3, calculator.getResult());
		calculator.clear();
		
		calculator.add(7);
		calculator.multiply(4);
		assertEquals(28, calculator.getResult());
		calculator.clear();
		
		calculator.add(4);
		calculator.multiply(2);
		assertEquals(8, calculator.getResult());
	}

	@Test
	public void testDivide() {
		//add code here
		calculator.add(3);
		calculator.divide(3);
		assertEquals(1, calculator.getResult());
		calculator.clear();
		
		calculator.add(0);
		calculator.divide(1);
		assertEquals(0, calculator.getResult());
		calculator.clear();
		
		calculator.add(7);
		calculator.divide(4);
		assertEquals(7/4, calculator.getResult());
		calculator.clear();
		
		calculator.add(8);
		calculator.divide(2);
		assertEquals(4, calculator.getResult());
	}
    
    @Test
    public void testSquare() {
        //add code here
    }

}
