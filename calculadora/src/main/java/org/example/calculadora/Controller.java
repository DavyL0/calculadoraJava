package org.example.calculadora;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.example.calculadora.model.operation.Operation;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

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
            case "+", "-", "x", "√", "N ²", "%", "/" -> {
                // Realiza a operação pendente e armazena o resultado
                if (!currentOperation.isEmpty()) {
                    Operation operations = new Operation(currentResult, currentInput);
                    currentResult = operations.performOperation(currentOperation);
                    label.setText(String.valueOf(currentResult));
                } else {
                    currentResult = currentInput;
                }

                // Verifica se a operação é divisão e impede divisão por zero
                if (operation.equals("/") && currentInput == 0) {
                    label.setText("Erro");  // Exibe mensagem de erro em caso de divisão por zero
                    currentResult = 0.0;
                    currentOperation = "";  // Limpa a operação pendente
                } else {
                    currentOperation = operation;  // Define a nova operação
                }
                resetDisplay = true;
            }
            case "=" -> {
                // Realiza a operação final e exibe o resultado
                Operation operations = new Operation(currentResult, currentInput);
                currentResult = operations.performOperation(currentOperation);
                label.setText(String.valueOf(currentResult));
                currentOperation = "";
                resetDisplay = true;
            }
            case "," -> {
                // Adiciona um ponto decimal ao número atual
                if (!label.getText().contains(".")) {
                    label.setText(label.getText() + ".");  // Adiciona o ponto ao número exibido
                }
                resetDisplay = false;  // Não reseta o display após adicionar vírgula
            }
            case "C" -> {
                // Limpa o display e reseta as variáveis
                label.setText("0");
                currentResult = 0.0;
                currentOperation = "";
            }
        }
    }

    private boolean isNumeric(String str) {
        return str.matches("\\d");  // Verifica se o texto do botão é um dígito (número)
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label.setText(" ");  // Inicializa o display com " "
    }
}
