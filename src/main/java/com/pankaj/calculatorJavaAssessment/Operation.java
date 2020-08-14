package com.pankaj.calculatorJavaAssessment;

public class Operation {
    int leftOperand;
    int rightOperand;
    String operation;

    public Operation(int leftOperand, int rightOperand, String operation) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "leftOperand=" + leftOperand +
                ", rightOperand=" + rightOperand +
                ", operation='" + operation + '\'' +
                '}';
    }
}
