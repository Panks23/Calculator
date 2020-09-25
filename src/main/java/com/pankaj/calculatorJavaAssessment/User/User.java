package com.pankaj.calculatorJavaAssessment.User;

import com.pankaj.calculatorJavaAssessment.Calculator;

public class User {
	
	private String name;
	private Calculator calculator;
	
	
	public User(String name, Calculator calc) {
		this.name = name;
		this.calculator = calc;
	}


	public String getName() {
		return name;
	}

	public Calculator getCalculator() {
		return calculator;
	}	

}
