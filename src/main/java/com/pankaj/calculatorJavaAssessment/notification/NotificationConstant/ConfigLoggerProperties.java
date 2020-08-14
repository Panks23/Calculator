package com.pankaj.calculatorJavaAssessment.notification.NotificationConstant;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.PropertyConfigurator;

public class ConfigLoggerProperties {
	
	public static void configLoggerProperties() {
			
	 	File file = new File("log4j.properties");
   		try {
   			FileWriter outputfile = new FileWriter(file);
				outputfile.write("log4j.rootLogger = info, console, file\r\n" + 
						"\r\n" + 
						"log4j.rootLogger = debug, console, file\r\n" + 
						"\r\n" + 
						"log4j.appender.console = org.apache.log4j.ConsoleAppender\r\n" + 
						"log4j.appender.console.layout = org.apache.log4j.PatternLayout\r\n" + 
						"log4j.appender.console.layout.ConversionPattern = %d{yyyy-MM-dd}-%t-%x-%-5p-%-10c:%m%n\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"log4j.appender.file = org.apache.log4j.RollingFileAppender\r\n" + 
						"log4j.appender.file.File = calculator_application.log\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"log4j.appender.file.MaxFileSize = 5mb\r\n" + 
						"log4j.appender.file.MaxBackupIndex = 5\r\n" + 
						"log4j.appender.file.layout = org.apache.log4j.PatternLayout\r\n" + 
						"log4j.appender.file.layout.ConversionPattern = %d{ISO8601} %5p [%t] %c{1}:%L  - %m%n\r\n" + 
						"");
				outputfile.close();
   		} catch (IOException e) {
   			e.printStackTrace();
   		} 
		String log4j = System.getProperty("user.dir")+"/log4j.properties";
	    PropertyConfigurator.configure(log4j);
	}

}
