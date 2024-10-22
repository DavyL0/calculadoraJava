package org.example.calculadora;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label label;
    @FXML
    private void handleButtonAction(ActionEvent event){
        // Obtém o botão que foi clicado
        Button clickedButton = (Button) event.getSource();
        if (clickedButton.getText().equals("C")){
            label.setText("");
        }else {
            // Define o texto do label com o texto do botão
            String currentText = label.getText();  // Texto atual do Label
            String newText = clickedButton.getText();  // Texto do botão clicado

            // Define o texto concatenado no Label
            label.setText(currentText + " " + newText);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}