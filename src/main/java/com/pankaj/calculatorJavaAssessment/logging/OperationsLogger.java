package com.pankaj.calculatorJavaAssessment.logging;

import com.pankaj.calculatorJavaAssessment.FileFormat;
import com.pankaj.calculatorJavaAssessment.LoggingStrategy;
import com.pankaj.calculatorJavaAssessment.Operation;
import com.pankaj.calculatorJavaAssessment.logging.fileCreator.LogfileCreator;
import com.pankaj.calculatorJavaAssessment.logging.fileCreator.LogfileCreatorFactory;
import com.pankaj.calculatorJavaAssessment.logging.notification.MailSender;
import com.pankaj.calculatorJavaAssessment.logging.notification.Notification;
import com.pankaj.calculatorJavaAssessment.logging.notification.NotificationsSender;
import com.pankaj.calculatorJavaAssessment.notification.NotificationConstant.ConfigLoggerProperties;

import static com.pankaj.calculatorJavaAssessment.notification.NotificationConstant.NotificationConstants.*;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

public class OperationsLogger {
	private static Logger logger = Logger.getLogger(OperationsLogger.class.getName());
	
	LogfileCreator logfileCreator;

    NotificationsSender notificationsSender;

    public OperationsLogger() {
    	ConfigLoggerProperties.configLoggerProperties();
    }

    private LogfileCreator getLogfileCreator(FileFormat fileFormat) {
        if (logfileCreator == null) {
            logfileCreator = LogfileCreatorFactory.create(fileFormat);
        }
        return logfileCreator;
    }

    private NotificationsSender getNotificationsSender() {
        if (notificationsSender == null) {
            notificationsSender = new MailSender();
        }
        return notificationsSender;
    }

    public void logOperations(List<Operation> operations, LoggingStrategy loggingStrategy) {
    	
        File file = createLogFileWithGivenFileFormat(operations, loggingStrategy);
        Optional<File> optionalFile = Optional.ofNullable(file);
        if(optionalFile.isEmpty()) {
        	logger.error("Failed to create log file");
        }else {
	    	Notification notification = createNotification(loggingStrategy, optionalFile.get());
	    	notificationsSender = getNotificationsSender();
	    	notificationsSender.sendNotification(notification);
        }
    }
    
    public File createLogFileWithGivenFileFormat(List<Operation> operations, LoggingStrategy loggingStrategy) {
    	try {
	    	logfileCreator = getLogfileCreator(loggingStrategy.getFileFormat());
	    	File file = logfileCreator.createLogFile(operations);
	    	logger.info("Created log file");
	    	return file;
    	}catch(Exception e) {
    		logger.info("Unable to create log file");
    		e.printStackTrace();
    	}
    	return null;
    }
    
    public Notification createNotification(LoggingStrategy loggingStrategy, File file) {
    	Notification notification = new Notification();
    	notification.setToAddress(loggingStrategy.getEmail());
    	notification.setFromAddress(SENDER_EMAIL_ADDRESS);
    	notification.setMessage(EMAIL_MESSAGE);
    	notification.setSubject(EMAIL_SUBJECT);
    	notification.setAttachment(file);
    	notification.setSignature(SENDER_SIGNATURE);
    	return notification;
    }
}
