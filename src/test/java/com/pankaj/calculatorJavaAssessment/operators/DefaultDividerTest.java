package com.pankaj.calculatorJavaAssessment.operators;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;

import com.pankaj.calculatorJavaAssessment.operators.DefaultDivider;

public class DefaultDividerTest {

	DefaultDivider defaultDivider;
	
	@Before
	public void init() {
		defaultDivider = new DefaultDivider();
	}
	
	 @Test
	    public void basicTest() {
	        Integer res = defaultDivider.divide(10, 2);
	        assertEquals("The result should be 5", 5, (int)res);
	    }
	 
	  @Test
	    public void divideByZero() {
	        try {
	        	defaultDivider.divide(10, 0);
	            fail("Dividing by zero");
	        } catch (Exception e) {
	            assertTrue("Dividing by zero threw an exception as expected", true);
	        }
	    }
	
}
