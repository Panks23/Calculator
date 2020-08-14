package com.pankaj.calculatorJavaAssessment;

import com.pankaj.calculatorJavaAssessment.CalculationMode;
import com.pankaj.calculatorJavaAssessment.Calculator;
import com.pankaj.calculatorJavaAssessment.FileFormat;
import com.pankaj.calculatorJavaAssessment.LoggingStrategy;
import com.pankaj.calculatorJavaAssessment.Operation;
import com.pankaj.calculatorJavaAssessment.logging.OperationsLogger;
import com.pankaj.calculatorJavaAssessment.operators.Adder;
import com.pankaj.calculatorJavaAssessment.operators.Multiplier;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorBasicTest {

    @Mock
    OperationsLogger operationsLogger;

    @InjectMocks
    Calculator calculator;
    

    @Test
    public void basicTestInDefaultMode() {

        LoggingStrategy loggingStrategy = new LoggingStrategy("anyMail", FileFormat.csv);

        Calculator calculator = Calculator.builder()
                .loggingStrategy(loggingStrategy).build();

        List<Integer> listOfNumbers = Arrays.asList(1, 2, 5, -9);

        List<Operation> expectedListOfOperations = Arrays.asList(
                new Operation(1, 2, "sum"),
                new Operation(3, 5, "sum"),
                new Operation(8, -9, "sum")
        );
        Integer sum = calculator.add(listOfNumbers);
        assertEquals("The calculator should return usual sum", -1, (int)sum);

    }

    @Test
    public void customiseAdditionWhenAdderIsProvidedTest() {

        LoggingStrategy loggingStrategy = new LoggingStrategy("anyMail", FileFormat.csv);
        Adder myAdder = numbers -> numbers.size(); // A lambda which means that myAdder is an instance of Adder with given behavior.
        List<Integer> listOfNumbers = Arrays.asList(1, 2, 5, -9);

        Calculator calculator = Calculator.builder()
                .adder(myAdder)
                .calculationMode(CalculationMode.CUSTOM)
                .loggingStrategy(loggingStrategy).build();

        // Addition behavior should be as specified by myAdder
        Integer sum = calculator.add(listOfNumbers);
        assertEquals("The calculator should return size of numbers passed as specified by myAdder", 4, (int)sum);

        // Multiplication behavior should remain same
        Integer product = calculator.multiply(listOfNumbers);
        assertEquals("The multiplication behavior should remain same", -90, (int)product);

    }
    
    @Test
    public void calculateExpressionTest() {
        LoggingStrategy loggingStrategy = new LoggingStrategy("anyMail", FileFormat.csv);
        String expression = "3 + 4 * 7 - 50 / 5";
        
        Calculator calculator = Calculator.builder().loggingStrategy(loggingStrategy).build();
        Integer result = calculator.calculate(expression);
        assertEquals("The calculation should return 21", 21, (int)result);
    }
    
    @Test
    public void customiseAdderForTheExpressionEvaluation() {
    	  LoggingStrategy loggingStrategy = new LoggingStrategy("anyMail", FileFormat.csv);
          Adder myAdder = numbers -> numbers.size(); // A lambda which means that myAdder is an instance of Adder with given behavior.
          String expression = "3 + 4 * 7 - 50 / 5";

          Calculator calculator = Calculator.builder()
                  .adder(myAdder)
                  .calculationMode(CalculationMode.CUSTOM)
                  .loggingStrategy(loggingStrategy).build();

          // Addition behavior should be as specified by myAdder
          Integer sum = calculator.calculate(expression);
          assertEquals("The calculator should return size of numbers passed as specified by myAdder", -8, (int)sum);
    }
    
    @Test
    public void sendResultTest() {
        LoggingStrategy loggingStrategy = new LoggingStrategy("atlharry46@gmail.com", FileFormat.docx);
        Calculator calculator = Calculator.builder()
                .loggingStrategy(loggingStrategy).build();

        List<Integer> listOfNumbers = Arrays.asList(1, 2, 5, -9);

        Integer sum = calculator.add(listOfNumbers);
        calculator.sendResults();
        assertEquals("The calculator should return usual sum", -1, (int)sum);
    }
    
    
    @Test
    public void sendResultWithoutLoggingStrategyThrowException() {
    	 Calculator calculator = Calculator.builder().build();

         List<Integer> listOfNumbers = Arrays.asList(1, 2, 5, -9);

         Integer sum = calculator.add(listOfNumbers);
         try {
             calculator.sendResults();
             fail("Should Throw Exception for no logging Strategy");
         } catch (Exception e) {
             assertTrue("Passing an emptyList to defaultAdder threw exception as expected", true);
         }      
    }
    
    
    @Test
    public void customizeMultiplyWhentMultiplierIsProvided() {
    	
    	   LoggingStrategy loggingStrategy = new LoggingStrategy("anyMail", FileFormat.csv);
           Multiplier myMultiplier = numbers -> numbers.size(); // A lambda which means that myAdder is an instance of Adder with given behavior.
           List<Integer> listOfNumbers = Arrays.asList(1, 2, 5, -9);

           Calculator calculator = Calculator.builder()
                   .multiplier(myMultiplier)
                   .calculationMode(CalculationMode.CUSTOM)
                   .loggingStrategy(loggingStrategy).build();

           // Multiplication behavior should be as specified in myMultiplier
           Integer product = calculator.multiply(listOfNumbers);
           assertEquals("The multiplication behavior should remain same", 4, (int)product);
           
           // Addition behavior should be as same
           Integer sum = calculator.add(listOfNumbers);
           assertEquals("The calculator should return size of numbers passed as specified by myAdder", -1, (int)sum);
    	
    }
    
}
