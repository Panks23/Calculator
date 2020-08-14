package com.pankaj.calculatorJavaAssessment.fileCreator;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.pankaj.calculatorJavaAssessment.Operation;
import com.pankaj.calculatorJavaAssessment.logging.fileCreator.CsvLogfileCreator;
import com.pankaj.calculatorJavaAssessment.logging.fileCreator.DocxLogfileCreator;
import com.pankaj.calculatorJavaAssessment.logging.fileCreator.LogfileCreator;
import com.pankaj.calculatorJavaAssessment.logging.fileCreator.TxtLogfileCreator;

public class FileCreatorTest {
	
	LogfileCreator logfile;
	
	@Test
	public void CsvLogfileCreatorTest() {
		 logfile = new CsvLogfileCreator();
		 List<Operation> expectedListOfOperations = Arrays.asList(
	                new Operation(1, 2, "sum"),
	                new Operation(3, 5, "sum"),
	                new Operation(8, -9, "sum")
	        );
		 File file = logfile.createLogFile(expectedListOfOperations);
		 assertTrue(file.exists());
	}
	
	@Test
	public void DocxLogfileCreatorTest() {
		 logfile = new DocxLogfileCreator();
		 List<Operation> expectedListOfOperations = Arrays.asList(
	                new Operation(1, 2, "sum"),
	                new Operation(3, 5, "sum"),
	                new Operation(8, -9, "sum")
	        );
		 File file = logfile.createLogFile(expectedListOfOperations);
		 assertTrue(file.exists());
	}
	
	@Test
	public void TxtLogfileCreatorTest() {
		 logfile = new TxtLogfileCreator();
		 List<Operation> expectedListOfOperations = Arrays.asList(
	                new Operation(1, 2, "sum"),
	                new Operation(3, 5, "sum"),
	                new Operation(8, -9, "sum")
	        );
		 File file = logfile.createLogFile(expectedListOfOperations);
		 assertTrue(file.exists());
	}

}
