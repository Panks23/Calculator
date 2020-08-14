package com.pankaj.calculatorJavaAssessment.logging.fileCreator;

import java.io.File;
import java.util.List;

import com.pankaj.calculatorJavaAssessment.Operation;

public interface LogfileCreator {

    File createLogFile(List<Operation> operations);
}
