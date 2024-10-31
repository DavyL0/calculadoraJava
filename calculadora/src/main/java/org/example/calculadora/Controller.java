package org.example.calculadora;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.example.calculadora.model.archive.Archive;
import org.example.calculadora.model.operation.Operation;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Label label;

    private double currentResult = 0.0;  // Armazena o resultado atual
    private double finalResult;
    private String currentOperation = "";  // Armazena a operação atual
    private boolean resetDisplay = false;  // Controla quando limpar o display para nova entrada

    Archive archive = new Archive();  // Instância da classe Archive para salvar os dados

    @FXML
    private void handleButtonAction(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String buttonText = clickedButton.getText();

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
        try {
            double currentInput = Double.parseDouble(label.getText());


            switch (operation) {
                case "+", "-", "x", "√", "N ²", "%", "/" -> {
                    if (!currentOperation.isEmpty()) {
                        Operation operations = new Operation(currentResult, currentInput);
                        currentResult = operations.performOperation(currentOperation);
                        label.setText(String.valueOf(currentResult));
                    } else {
                        currentResult = currentInput;
                    }

                    // Verifica se a operação é divisão por zero
                    if (operation.equals("/") && currentInput == 0) {
                        label.setText("Erro");
                        currentResult = 0.0;
                        currentOperation = "";
                    } else {
                        currentOperation = operation;
                    }
                    resetDisplay = true;
                }
                case "=" -> {
                    // Realiza a operação final e salva o resultado
                    Operation operations = new Operation(currentResult, currentInput);
                    finalResult = operations.performOperation(currentOperation);
                    label.setText(String.valueOf(finalResult));
                    currentOperation = "";
                    resetDisplay = true;

                    // Salva a operação final no arquivo
                    archive.addArk(currentResult, currentOperation, currentInput, finalResult);
                }
                case "," -> {
                    // Adiciona uma vírgula (ponto decimal) ao número atual, sem salvar no arquivo
                    if (!label.getText().contains(".")) {
                        label.setText(label.getText() + ".");
                    }
                    resetDisplay = false;
                }
                case "C" -> {
                    // Limpa o display e reseta as variáveis, sem salvar no arquivo
                    label.setText("");
                    currentResult = 0.0;
                    currentOperation = "";
                    resetDisplay = false;
                }
            }
        } catch (NumberFormatException e) {
            label.setText("Erro");
            resetDisplay = true;
        }
    }

    private boolean isNumeric(String str) {
        return str.matches("\\d") || str.equals(".");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        archive.cleanArk();
        label.setText("0");  // Inicializa o display com zero
    }
}
