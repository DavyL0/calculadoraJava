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
            case "," -> {
                // Converte os valores de entrada e resultado para strings
                String resultAsString = Double.toString(super.getResult());
                String inputAsString = Double.toString(super.getInput());

                // Checa se o número já contém um ponto decimal
                if (!resultAsString.contains(".")) {
                    // Concatena os valores de input após o ponto decimal
                    String resultFinal = resultAsString + "." + inputAsString;

                    // Retorna o valor como um número decimal
                    yield Double.parseDouble(resultFinal);
                } else {
                    // Se já existir um ponto decimal, retorna o valor atual
                    yield super.getResult();
                }
            }
            default -> super.getInput();
        };
    }


}
