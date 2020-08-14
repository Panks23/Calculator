package com.pankaj.calculatorJavaAssessment.logging.fileCreator;

import com.opencsv.CSVWriter;
import com.pankaj.calculatorJavaAssessment.Operation;
import com.pankaj.calculatorJavaAssessment.notification.NotificationConstant.ConfigLoggerProperties;
import com.pankaj.calculatorJavaAssessment.operators.DefaultAdder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

public class CsvLogfileCreator implements LogfileCreator {
	
	private static Logger logger = Logger.getLogger(DefaultAdder.class.getName());
	
	public CsvLogfileCreator() {
		ConfigLoggerProperties.configLoggerProperties();
	}
	
    @Override
    public File createLogFile(List<Operation> operations) {
    	File  file = new File("./Operations.csv");
    	logger.info("Empty CSV File Created");
    	try {
    		FileWriter outputfile = new FileWriter(file); 
    		  
            CSVWriter writer = new CSVWriter(outputfile);    
            for(int i = 0; i<operations.size(); i++) {
            	String[] data =  {operations.get(i).toString()};
                writer.writeNext(data); 
            }
            writer.close();
            logger.info("Csv file for the operatations Written");
    	}catch(IOException e) {
    		logger.debug("While creating csv file threw an IO exception");
    		e.printStackTrace();
    	}
    	logger.info("Csv file successfully created");
        return file;
    }
}
