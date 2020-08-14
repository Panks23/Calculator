package com.pankaj.calculatorJavaAssessment.logging.fileCreator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.pankaj.calculatorJavaAssessment.Operation;
import com.pankaj.calculatorJavaAssessment.notification.NotificationConstant.ConfigLoggerProperties;
import com.pankaj.calculatorJavaAssessment.operators.DefaultAdder;

public class DocxLogfileCreator implements LogfileCreator{
	private static Logger logger = Logger.getLogger(DefaultAdder.class.getName());
	
	public DocxLogfileCreator() {
		ConfigLoggerProperties.configLoggerProperties();
	}
    @Override
    public File createLogFile(List<Operation> operations) {
        XWPFDocument document = new XWPFDocument(); 
    	File file = new File("Operations.docx");
    	logger.info("Empty docx File Created");
        try {
			FileOutputStream out = new FileOutputStream(file);
		   
		    for(int i = 0; i < operations.size(); i++) {
		    	XWPFRun run = createParagraphWithBorder(document);
		    	run.setText(operations.get(i).toString());
		    }
		      try {
		    	document.write(out);
				out.close();
			} catch (IOException e) {
	    		logger.debug("While creating docx file threw an IO exception");
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

    	logger.info("Docx file successfully created");
        return file;
    }
    
    public XWPFRun createParagraphWithBorder(XWPFDocument document) {
    	 XWPFParagraph paragraph = document.createParagraph();
		 paragraph.setBorderBottom(Borders.BASIC_BLACK_DASHES);
		 paragraph.setBorderLeft(Borders.BASIC_BLACK_DASHES);
		 paragraph.setBorderRight(Borders.BASIC_BLACK_DASHES);
		 paragraph.setBorderTop(Borders.BASIC_BLACK_DASHES);
		 XWPFRun run = paragraph.createRun();
    	return run;
    }
}
