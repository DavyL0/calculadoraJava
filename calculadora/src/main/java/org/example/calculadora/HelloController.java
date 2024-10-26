package org.example.calculadora;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Label label;

    private double currentResult = 0.0;  // Armazena o resultado atual
    private String currentOperation = "";  // Armazena a operação atual
    private boolean resetDisplay = false;  // Controla quando limpar o display para nova entrada

    @FXML
    private void handleButtonAction(ActionEvent event) {
        // Obtém o botão que foi clicado
        Button clickedButton = (Button) event.getSource();
        String buttonText = clickedButton.getText();

        // Verifica se o botão é um número ou uma operação
        if (isNumeric(buttonText)) {
            if (resetDisplay) {
                label.setText("");  // Limpa o display para novo número
                resetDisplay = false;
            }
            // Adiciona o número ao display
            label.setText(label.getText() + buttonText);
        } else {
            processOperation(buttonText);
        }
    }

    private void processOperation(String operation) {
        double currentInput = Double.parseDouble(label.getText());

        switch (operation) {
            case "+":
            case "-":
            case "x":
            case "/":
                // Realiza a operação pendente e armazena o resultado
                if (!currentOperation.isEmpty()) {
                    currentResult = performOperation(currentResult, currentInput, currentOperation);
                    label.setText(String.valueOf(currentResult));
                } else {
                    currentResult = currentInput;
                }
                currentOperation = operation;  // Define a nova operação
                resetDisplay = true;
                break;
            case "=":
                // Realiza a operação final e exibe o resultado
                currentResult = performOperation(currentResult, currentInput, currentOperation);
                label.setText(String.valueOf(currentResult));
                currentOperation = "";
                resetDisplay = true;
                break;
            case "C":
                // Limpa tudo
                label.setText("0");
                currentResult = 0.0;
                currentOperation = "";
                break;
        }
    }

    private double performOperation(double result, double input, String operation) {
        switch (operation) {
            case "+": return result + input;
            case "-": return result - input;
            case "x": return result * input;
            case "/": return input != 0 ? result / input : 0; // Evita divisão por zero
            default: return input;
        }
    }

    private boolean isNumeric(String str) {
        return str.matches("\\d");  // Verifica se o texto do botão é um dígito (número)
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label.setText(" ");  // Inicializa o display com "0"
    }
}
