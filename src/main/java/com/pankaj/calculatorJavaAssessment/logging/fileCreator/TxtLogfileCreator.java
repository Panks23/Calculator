package com.pankaj.calculatorJavaAssessment.logging.fileCreator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.pankaj.calculatorJavaAssessment.Operation;
import com.pankaj.calculatorJavaAssessment.notification.NotificationConstant.ConfigLoggerProperties;
import com.pankaj.calculatorJavaAssessment.operators.DefaultAdder;

public class TxtLogfileCreator implements LogfileCreator {
	private static Logger logger = Logger.getLogger(DefaultAdder.class.getName());
	
	public TxtLogfileCreator() {
		ConfigLoggerProperties.configLoggerProperties();
	}
    @Override
    public File createLogFile(List<Operation> operations) {
        File file = new File("Operations.txt");
    	logger.info("Empty txt File Created");
		try {
			FileWriter outputfile = new FileWriter(file);
			for(int i = 0; i < operations.size(); i++) {
				outputfile.write(operations.get(i).toString());
				outputfile.write("\r\n");
			}
			outputfile.close();
			logger.info("Txt file for operation created");
		} catch (IOException e) {
    		logger.debug("While creating txt file threw an IO exception");
			e.printStackTrace();
		} 
    	logger.info("Txt file successfully created");
        return file;
    }
}
