package org.example.calculadora;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("calculator-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 350, 499);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("pixel.css")).toExternalForm());
        stage.setTitle("Calculadora 8bit");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}