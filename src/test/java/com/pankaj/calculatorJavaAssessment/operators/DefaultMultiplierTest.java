package com.pankaj.calculatorJavaAssessment.operators;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;

import com.pankaj.calculatorJavaAssessment.operators.DefaultMultiplier;

public class DefaultMultiplierTest {
	
	DefaultMultiplier defaultMultiplier;

	    @Before
	    public void init() {
	    	defaultMultiplier = new DefaultMultiplier();
	    	String log4j = System.getProperty("user.dir")+ "/log4j.properties";
	        PropertyConfigurator.configure(log4j);
	    }

	    @Test
	    public void basicTest() {
	        List<Integer> numbersToBeAdded = Arrays.asList(5, 10, -6, 1);
	        Integer sum = defaultMultiplier.multiply(numbersToBeAdded);
	        assertEquals("The sum should be -300", -300, (int)sum);
	    }

	    @Test
	    public void notEnoughNumbersShouldThrowExceptionTest() {
	        List<Integer> emptyList = new ArrayList<>();
	        try {
	        	defaultMultiplier.multiply(emptyList);
	            fail("Adding an empty list should throw exception");
	        } catch (Exception e) {
	            assertTrue("Passing an emptyList to defaultAdder threw exception as expected", true);
	        }
	    }

}
