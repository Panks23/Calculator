package com.pankaj.calculatorJavaAssessment;


import java.util.Stack;


public class EvaluateString 
{ 
	public static int evaluate(String expression, Calculator calculator) 
	{ 
		char[] tokens = expression.toCharArray(); 
		Stack<Integer> values = new Stack<Integer>(); 

		Stack<Character> ops = new Stack<Character>(); 

		for (int i = 0; i < tokens.length; i++) 
		{ 
			if (tokens[i] == ' ') 
				continue; 

			if (tokens[i] >= '0' && tokens[i] <= '9') 
			{ 
				StringBuffer sbuf = new StringBuffer(); 
				while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9') 
					sbuf.append(tokens[i++]); 
				values.push(Integer.parseInt(sbuf.toString())); 
			} 

			else if (tokens[i] == '(') 
				ops.push(tokens[i]); 

			else if (tokens[i] == ')') 
			{ 
				while (ops.peek() != '(') 
				values.push(calculator.applyOp(ops.pop(), values.pop(), values.pop())); 
				ops.pop(); 
			} 

			else if (tokens[i] == '+' || tokens[i] == '-' || 
					tokens[i] == '*' || tokens[i] == '/') 
			{ 
			
				while (!ops.empty() && hasPrecedence(tokens[i], ops.peek())) 
				values.push(calculator.applyOp(ops.pop(), values.pop(), values.pop())); 

				ops.push(tokens[i]); 
			} 
		} 

	
		while (!ops.empty()) 
			values.push(calculator.applyOp(ops.pop(), values.pop(), values.pop())); 

		return values.pop(); 
	} 


	public static boolean hasPrecedence(char op1, char op2) 
	{ 
		if (op2 == '(' || op2 == ')') 
			return false; 
		if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) 
			return false; 
		else
			return true; 
	} 

	
	
//	 public static void main(String[] args) 
//	    { 
//	        System.out.println(EvaluateString.evaluate("10 + 2 * 6")); 
//	        System.out.println(EvaluateString.evaluate("100 * 2 + 12")); 
//	        System.out.println(EvaluateString.evaluate("100 * ( 2 + 12 )")); 
//	        System.out.println(EvaluateString.evaluate("100 * ( 2 + 12 ) / 14")); 
//	    } 
}

