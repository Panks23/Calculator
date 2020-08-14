package com.pankaj.calculatorJavaAssessment.operators;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;

import com.pankaj.calculatorJavaAssessment.operators.DefaultSubtractor;

public class DefaultSubtractorTest {
	
	DefaultSubtractor defaultSubtractor;
	
	@Before
	public void init() {
		defaultSubtractor = new DefaultSubtractor();
		String log4j = System.getProperty("user.dir")+ "/log4j.properties";
	    PropertyConfigurator.configure(log4j);
	}
	
	 @Test
	    public void basicTest() {
	        Integer res = defaultSubtractor.subtract(10, 2);
	        assertEquals("The result should be 8", 8, (int)res);
	    }
	 
}
