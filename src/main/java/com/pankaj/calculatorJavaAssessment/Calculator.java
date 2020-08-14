package com.pankaj.calculatorJavaAssessment;

import com.pankaj.calculatorJavaAssessment.logging.OperationsLogger;
import com.pankaj.calculatorJavaAssessment.notification.NotificationConstant.ConfigLoggerProperties;
import com.pankaj.calculatorJavaAssessment.operators.Adder;
import com.pankaj.calculatorJavaAssessment.operators.DefaultAdder;
import com.pankaj.calculatorJavaAssessment.operators.DefaultDivider;
import com.pankaj.calculatorJavaAssessment.operators.DefaultMultiplier;
import com.pankaj.calculatorJavaAssessment.operators.DefaultSubtractor;
import com.pankaj.calculatorJavaAssessment.operators.Divider;
import com.pankaj.calculatorJavaAssessment.operators.Multiplier;
import com.pankaj.calculatorJavaAssessment.operators.Subtractor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.apache.log4j.Logger;

public class Calculator {
	
	private static Logger logger = Logger.getLogger(Calculator.class.getName());

    private Adder adder;
    private Subtractor subtractor;
    private Multiplier multiplier;
    private Divider divider;
    private LoggingStrategy loggingStrategy;
    private CalculationMode calculationMode;
    private List<Operation> operations = new ArrayList<>();
    private OperationsLogger operationsLogger;

    public Calculator() {
    	ConfigLoggerProperties.configLoggerProperties();
    }
    public static class Builder {

        private Calculator calculator = new Calculator();

        public Calculator build() {
            logger.info("Builded instance of calculator");
            return calculator;
        }

        public Builder adder(Adder adder) {
        	if(adder==null) {
        		logger.info("No Custom Implementation of adder provided by the user");
        	}else {
                logger.info("Custom Implementation of adder provided by the user");
        	}
            calculator.adder = adder;
            return this;
        }

        public Builder subtractor(Subtractor subtractor) {
        	if(subtractor==null) {
        		logger.info("No Custom Implementation of subtractor provided by the user");
        	}else {
                logger.info("Custom Implementation of subtractor provided by the user");
        	}            calculator.subtractor = subtractor;
            return this;
        }

        public Builder multiplier(Multiplier multiplier) {
        	if(multiplier==null) {
        		logger.info("No Custom Implementation of multiplier provided by the user");
        	}else {
                logger.info("Custom Implementation of multiplier provided by the user");
        	} 
            calculator.multiplier = multiplier;
            return this;
        }

        public Builder divider(Divider divider) {
         	if(divider==null) {
        		logger.info("No Custom Implementation of divider provided by the user");
        	}else {
                logger.info("Custom Implementation of divider provided by the user");
        	} 
            calculator.divider = divider;
            return this;
        }

        public Builder calculationMode(CalculationMode calculationMode) {
            calculator.calculationMode = calculationMode;
            return this;
        }

        public Builder loggingStrategy(LoggingStrategy loggingStrategy) {
            logger.info("User provided logging strategy");
            calculator.loggingStrategy = loggingStrategy;
            return this;
        }
    }

    public Integer add(List<Integer> numbers) {
    	if(adder!=null) {
    		logger.info("Custom addition started");
    		return adder.add(numbers);
    	}
    	logger.info("Decimal addition started");
        Adder defaultAdder = new DefaultAdder();
        Integer result = defaultAdder.add(numbers);
        addOperation(numbers);
        addResultToOperations(result);
        return result;
    }
    
    public void addOperation(List<Integer> numbers) {
    	for(int i = 0; i < numbers.size()-1; i++) {
    		operations.add(new Operation((int)numbers.get(i), (int)numbers.get(i+1), "sum"));
    	}
    	logger.info("Addition operataion is added to the operations");
    }

    public Integer subtract(Integer a, Integer b) {
    	if(subtractor!=null) {
    		logger.info("Custom subtraction started");
    		return subtractor.subtract(a, b);
    	}
		logger.info("Default subtraction started");
    	Subtractor defaultSubtract = new DefaultSubtractor();
    	Integer result  = defaultSubtract.subtract(a, b);
    	subtractOperation(a, b);
    	addResultToOperations(result);
        return result;
    }
    
    public void subtractOperation(Integer a, Integer b) {
		operations.add(new Operation((int)a, (int)b, "subtract"));
    	logger.info("Subtract operataion is added to the operations");
    }

    public Integer multiply(List<Integer> numbers) {
    	if(multiplier!=null) {
    		logger.info("Custom Multiply started");
    		return multiplier.multiply(numbers);
    	}
		logger.info("Default multiplication started");
    	DefaultMultiplier defaultMultiplier = new DefaultMultiplier();
    	Integer result = defaultMultiplier.multiply(numbers);
    	multiplyOperation(numbers);
    	addResultToOperations(result);
        return result;
    }
    
    public void multiplyOperation(List<Integer> numbers) {
    	for(int i = 0; i < numbers.size()-1; i++) {
    		operations.add(new Operation((int)numbers.get(i), (int)numbers.get(i+1), "multiply"));
    	}
    	logger.info("Multiplication operataion is added to the operations");
    }

    public Integer divide(Integer a, Integer b) {
    	if(divider!=null) {
    		logger.info("Custom Division started");
    		return divider.divide(a, b);
    	}
		logger.info("Default division started");
    	Divider defaultDivider = new DefaultDivider();
    	Integer result = defaultDivider.divide(a, b);
    	divideOperation(a, b);
    	addResultToOperations(result);
        return result;
    }
    
    public void divideOperation(Integer a, Integer b) {
		operations.add(new Operation((int)a, (int)b, "divide"));
    	logger.info("Division operataion is added to the operations");
    }

    public void sendResults() {
    	if(operationsLogger==null) {
    		logger.info("Initialising Operation Logger");
    		operationsLogger = new OperationsLogger();
    	}
    	if(loggingStrategy==null) {
    		throw new RuntimeException("There is no logging Strategy Please Provide your mail account and express your fileformat to get the resutl the way you want");
    	}
    	logger.info("Sending email of all the performed operations in the instance");
        operationsLogger.logOperations(operations, loggingStrategy);
    }


    public Integer calculate(String expression) {
    	logger.info("Creating a calculator instance to provide custom operation for calculating expression");
    	Calculator calculator = Calculator.builder()
    			.adder(adder)
    			.divider(divider)
    			.multiplier(multiplier)
    			.subtractor(subtractor)
    			.build();
    	Integer result  = EvaluateString.evaluate(expression, calculator);
    	addResultToOperations(result);
    	return result;
    }
    
//    Function to be used in Evaluate String class for the purpose of custom mode  
    public int applyOp(char op, int b, int a) 
	{ 
		switch (op) 
		{ 
		case '+': 
			return add(Arrays.asList(a, b)); 
		case '-': 
			return subtract(a, b); 
		case '*': 
			return multiply(Arrays.asList(a, b)); 
		case '/': 
			return divide(a, b); 
		} 
		return 0; 
	} 
    
    public void addResultToOperations(Integer result) {
		operations.add(new Operation((int)result, (int)result, "Result of Above all Operations"));
		logger.info("Resullt is added to the operation");
    }

    public static Builder builder() {
        return new Builder();
    }

}