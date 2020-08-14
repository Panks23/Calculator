package com.pankaj.calculatorJavaAssessment.operators;

import org.apache.log4j.Logger;

import com.pankaj.calculatorJavaAssessment.notification.NotificationConstant.ConfigLoggerProperties;

public class DefaultSubtractor implements Subtractor {
	private static Logger logger = Logger.getLogger(DefaultSubtractor.class.getName());
	
	public DefaultSubtractor() {
		ConfigLoggerProperties.configLoggerProperties();
	}
    @Override
    public Integer subtract(Integer a, Integer b) {
    	logger.info("Subtracting number " + b+ " from "+a);
        return a - b;
    }
}
