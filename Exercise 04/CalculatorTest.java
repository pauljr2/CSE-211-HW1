
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class CalculatorTest {
	private static Calculator calculator = new Calculator();
	@Before
	public void setUp() throws Exception {
		calculator.clear();
	}

	@Test
	public void testAdd() {
		//Add up 2+3
		calculator.add(2);
		calculator.add(3);
		//We expect it to equal 5
		assertTrue(calculator.getResult() == 5);
		calculator.add(-1);
		assertTrue(calculator.getResult() == 4);
	}

	@Test
	public void testSubstract() {
		//We will be subtracting 2 from 10
		calculator.add(10);
		assertTrue(calculator.getResult() == 10);
		calculator.substract(2);
		assertTrue(calculator.getResult() == 8);
		calculator.substract(8);
		assertTrue(calculator.getResult() == 0);
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
		calculator.square(2);
		assertTrue(calculator.getResult() == 4);
		calculator.square(4);
		assertTrue(calculator.getResult()==16);
		calculator.square(0);
		assertTrue(calculator.getResult()==0);
		calculator.square(-2);
		assertTrue(calculator.getResult()==4);
	}

	@Test
	public void testSquareRoot() {
		calculator.squareRoot(16);
		assertTrue(calculator.getResult()==4);
		calculator.squareRoot(36);
		assertTrue(calculator.getResult()==6);
		calculator.squareRoot(0);
		assertTrue(calculator.getResult()==0);
	}
}
