package com.pankaj.calculatorJavaAssessment.operators;

import java.util.List;

import org.apache.log4j.Logger;

import com.pankaj.calculatorJavaAssessment.notification.NotificationConstant.ConfigLoggerProperties;

public class AddWithStream implements Adder{
	
	private static Logger logger = Logger.getLogger(DefaultAdder.class.getName());
	
	public AddWithStream() {
		ConfigLoggerProperties.configLoggerProperties();
	}

	@Override
	public Integer add(List<Integer> numbers) {
		
		if(numbers.size()>0) {
			logger.info("Adding number with Stream");
			int result = numbers.stream().reduce(0, (res, num) ->res+num);
			return result ;
	    }else {
	    	logger.debug("Empty List of Numbers as a input");
            throw new RuntimeException("Empty List");
	    }
		
	}
	
	

}
