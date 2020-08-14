package com.pankaj.calculatorJavaAssessment;

public class LoggingStrategy {
    String email;
    FileFormat fileFormat;

    public LoggingStrategy(String email, FileFormat fileFormat) {
        this.email = email;
        this.fileFormat = fileFormat;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public FileFormat getFileFormat() {
		return fileFormat;
	}

	public void setFileFormat(FileFormat fileFormat) {
		this.fileFormat = fileFormat;
	}
    
    
}
