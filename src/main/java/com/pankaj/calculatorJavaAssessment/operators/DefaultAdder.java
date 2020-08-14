package com.pankaj.calculatorJavaAssessment.operators;

import java.util.List;

import org.apache.log4j.Logger;

import com.pankaj.calculatorJavaAssessment.notification.NotificationConstant.ConfigLoggerProperties;






public class DefaultAdder implements Adder{
	
	private static Logger logger = Logger.getLogger(DefaultAdder.class.getName());
	
	public DefaultAdder() {
		ConfigLoggerProperties.configLoggerProperties();
	}

    @Override
    public Integer add(List<Integer> numbers) {
    	logger.info("Adding List of Numbers");
    	if(numbers.size()>0) {
	    	int sum = 0;
	        for (int i=0; i<numbers.size(); i++) {
	        	sum+=numbers.get(i);
	        }
	        return sum;
	    }else {
	    	logger.debug("Empty List of Numbers as a input");
            throw new RuntimeException("Empty List");
	    }
    }
}
