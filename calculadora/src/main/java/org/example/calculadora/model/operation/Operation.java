package org.example.calculadora.model.operation;

import org.example.calculadora.model.entities.Numbers;

/*
 * @created 28/10/2024 - 13:44
 * @project calculadora
 * @Aauthor Davy
 */public class Operation extends Numbers {

    String operation;

    public Operation(double input, double result) {
        super(input, result);
    }


    public String getOperation() {
        return operation;
    }


    public void setOperation(String operation) {
        this.operation = operation;
    }


    public double performOperation(String operation) {
        return switch (operation) {
            case "+" -> super.getResult() + super.getInput();
            case "-" -> super.getInput() - super.getResult();
            case "x" -> super.getResult() * super.getInput();
            case "/" -> super.getInput() != 0 ? super.getResult() / super.getInput() : 0; // Evita divisão por zero
            case "√" -> Math.sqrt(super.getInput());
            case "N ²" -> Math.pow(super.getInput(), 2);
            case "%" -> (super.getInput() * super.getResult()) / 100;
            default -> super.getInput();
        };
    }


}
