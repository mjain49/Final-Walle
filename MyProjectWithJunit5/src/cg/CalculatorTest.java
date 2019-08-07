package cg;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class CalculatorTest {

	static Calculator ob;
	
	@BeforeAll //Junit4 @BeforeClass
	public static void beforeAllTests()
	{
		System.out.println("Before All Test");
		ob=new Calculator();
	}
	
	@AfterAll  //Junit 4 AfterClass
	public static void afterAllTests()
	{
		System.out.println("After All Test");
		ob=null;
	}
	@BeforeEach   //junit 4 has @Before
	public void beforeEachTest() {
	//Self Generated method
	System.out.println("Before Each");
	// ob= new Calculator();  //After adding Before Each and after Each the object is created only once
	}
	
	@AfterEach
	public void afterEachTest() {
		System.out.println("After Each");
	//	ob=null;
	}
	
	@RepeatedTest(5)
	//@Test  ///because repaeated test will repeat this test specifed times i.e. 5 times from this example
	void testadd() {
		
		//Calculator ob=new Calculator(); //--->Before All is used to set Object and after all is used to null that object for further use
		System.out.println("Test Case Added");
		assertEquals(9,ob.add(4, 5));
	}

	@Disabled //will disable this test case to run and test
	@Test
	void testadd1()
	{
		//Calculator ob=new Calculator();
		System.out.println("Test Case1 Added");
		assertTrue(ob.add(4, 5)>0); //Result true when the inside condition is true
		assertTrue(ob.add(3,-4)>=0); //if someone insert negative value then also result will be true because of calculator if loop check
	}
	
//	@Test
//	void testsub()
//	{
//		System.out.println("Subtraction Tested ");
//		assertEquals(5,ob.sub(10,5));
//	}
	
	@Test 
	void  testgetId()
	{
		System.out.println("Number Generation Tested");
		assertTrue(ob.getId()>0);
	}
}
