package com.pankaj.calculatorJavaAssessment.operators;

import org.junit.Before;
import org.junit.Test;

import com.pankaj.calculatorJavaAssessment.operators.DefaultAdder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class DefaultAdderTest {


    DefaultAdder defaultAdder;
    

    @Before
    public void init() {
        defaultAdder = new DefaultAdder();
    }

    @Test
    public void basicTest() {
        List<Integer> numbersToBeAdded = Arrays.asList(5, 10, -6, 0);
        Integer sum = defaultAdder.add(numbersToBeAdded);
        assertEquals("The sum should be 9", 9, (int)sum);
    }

    @Test
    public void notEnoughNumbersShouldThrowExceptionTest() {
        List<Integer> emptyList = new ArrayList<>();
        try {
            defaultAdder.add(emptyList);
            fail("Adding an empty list should throw exception");
        } catch (RuntimeException e) {
            assertTrue("Passing an emptyList to defaultAdder threw exception as expected", true);
        }
    }
    
    @Test
    public void lamdaAdderTest() {
    	Adder add = number ->{
    		if(number.size()>0) {
    		int sum = 0;
    		for(int i=0; i<number.size(); i++) {
    			sum+=number.get(i);
    		}
    		return sum;
    		}else {
    			throw new RuntimeException("Empty List");
    		}
    	};
        List<Integer> numbersToBeAdded = Arrays.asList(5, 10, -6, 0);
    	assertEquals((int)add.add(numbersToBeAdded), 9);
    }
    
    @Test
    public void lambdaAdderForEmptyListTest() {
    	Adder add = number ->{
    		if(number.size()>0) {
    		int sum = 0;
    		for(int i=0; i<number.size(); i++) {
    			sum+=number.get(i);
    		}
    		return sum;
    		}else {
    			throw new RuntimeException("Empty List");
    		}
    	};
        List<Integer> numbersToBeAdded = new ArrayList<>();
        try {
            defaultAdder.add(numbersToBeAdded);
            fail("Adding an empty list should throw exception");
        } catch (Exception e) {
            assertTrue("Passing an emptyList to defaultAdder threw exception as expected", true);
        }
    }
}
