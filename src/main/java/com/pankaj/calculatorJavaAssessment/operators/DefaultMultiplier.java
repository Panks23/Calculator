package com.pankaj.calculatorJavaAssessment.operators;

import java.util.List;

import org.apache.log4j.Logger;

import com.pankaj.calculatorJavaAssessment.notification.NotificationConstant.ConfigLoggerProperties;

public class DefaultMultiplier implements Multiplier {
	private static Logger logger = Logger.getLogger(DefaultMultiplier.class.getName());

	public DefaultMultiplier() {
		ConfigLoggerProperties.configLoggerProperties();
	}
	
    @Override
    public Integer multiply(List<Integer> numbers) {
    	if(numbers.size()>0) {
	        int result = 1;
	        logger.info("Multiplying List of numbers");
	        for(int index = 0; index<numbers.size(); index++) {
	        	result *= numbers.get(index);
	        }
	        return result;
    }else {
    	logger.debug("Empty List Provided");
        throw new RuntimeException("Empty List");

    }
    }
}
