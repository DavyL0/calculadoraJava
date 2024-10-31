package org.example.calculadora.model.operation;

import org.example.calculadora.model.entities.Numbers;

/*
 * @created 28/10/2024 - 13:44
 * @project calculadora
 * @Aauthor Davy
 */
public class Operation extends Numbers {

    private String operation;

    public Operation(double input, double result) {
        super(input, result);
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    // Método para realizar a operação com base no tipo de operação
    public double performOperation(String operation) {
        return switch (operation) {
            case "+" -> super.getResult() + super.getInput();  // Soma
            case "-" -> super.getInput() - super.getResult();  // Subtração
            case "x" -> super.getResult() * super.getInput();  // Multiplicação
            case "/" -> super.getInput() != 0 ? super.getInput() / super.getResult() : Double.NaN;  // Divisão (evita divisão por zero)
            case "√" -> Math.sqrt(super.getInput());  // Raiz quadrada do valor de input
            case "N ²" -> Math.pow(super.getInput(), 2);  // Quadrado do valor de input
            case "%" -> (super.getResult() * super.getInput()) / 100;  // Porcentagem
            default -> super.getResult();  // Retorna o valor atual caso não tenha uma operação válida
        };
    }
}
