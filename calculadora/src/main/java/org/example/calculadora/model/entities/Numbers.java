package org.example.calculadora.model.entities;

/*
 * @created 28/10/2024 - 13:44
 * @project calculadora
 * @Aauthor Davy
 */public class Numbers {
     double input;
     double result;


    public Numbers(double input, double result) {
        this.input = input;
        this.result = result;
    }

    public double getInput() {
        return input;
    }

    public void setInput(double input) {
        this.input = input;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}
