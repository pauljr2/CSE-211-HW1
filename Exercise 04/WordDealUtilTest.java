import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;


public class WordDealUtilTest {

	private static WordDealUtil wordDealUtil = new  WordDealUtil();
	@Before
	public void setUp() throws Exception {
	}
	@Before
	public void echoBefore()
	{
		System.out.println("Check Test Environment! Start the Test");
	}
	
	@After
	public void echoAfter()
	{
		System.out.println("Test Finish! Check Result");
	}
	
	// Test wordEormat4DB with normal condition
	@Test
	public void wordFormat4DBNormal()
	{
		// Q1 - test successfully passed
		String target = "employee_info";
		String result = wordDealUtil.wordFormat4DB("employeeInfo");
		assertEquals(target, result);
	}
	// Test wordEormat4DB with normal condition
	@Test
	public void wordFormat4DBNull()
	{
		// Didn't pass test successfully
		String target = null;
		String result = wordDealUtil.wordFormat4DB(target);
		assertNull(result);
	}
	
	// Test wordEormat4DB with empty string condition
	@Test
	public void wordFormat4DBEmpty()
	{
		// Q2 - test successfully passed
		String target = "";
		String result = wordDealUtil.wordFormat4DB("");
		assertEquals(target, result);
	}
	
	// Test First character with upper case
	@Test
	public void wordFormat4DBBegin()
	{
		// Q3 - test successfully passed
		String target = "employee_info";
		String result = wordDealUtil.wordFormat4DB("employeeInfo");
		assertEquals(target, result);
	}
	// Test Last character with upper case
	@Test
	public void wordFormat4DBEnd()
	{
		// Q4 - test successfully passed
		String target = "employee_info_a";
		String result = wordDealUtil.wordFormat4DB("employeeInfoA");
		assertEquals(target, result);
	}
	// Test more than one character with upper case
	@Test
	public void wordFormat4DBTogether()
	{
		// Q5 - test successfully passed
		String target = "employee_info_a";
		String result = wordDealUtil.wordFormat4DB("employeeInfoA");
		assertEquals(target, result);
	}

}
