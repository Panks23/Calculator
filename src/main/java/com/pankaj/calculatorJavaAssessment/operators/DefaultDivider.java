package com.pankaj.calculatorJavaAssessment.operators;

import org.apache.log4j.Logger;

import com.pankaj.calculatorJavaAssessment.notification.NotificationConstant.ConfigLoggerProperties;

public class DefaultDivider implements Divider {
	private static Logger logger = Logger.getLogger(DefaultDivider.class.getName());
	
	public DefaultDivider() {
		ConfigLoggerProperties.configLoggerProperties();
	}

    @Override
    public Integer divide(Integer a, Integer b) {
        if(b!=0) {
        	logger.info("Dividing number "+a+ " by "+b);
        	return a/b;
        }else {
        	logger.debug("Dividing number by zero");
        	throw new ArithmeticException("Number is  divided by  zero is throwing an exception");
        }
    }
}
