package org.example.calculadora.model.archive;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/*
 * @created 29/10/2024 - 13:42
 * @project calculadora
 * @Author Davy
 */
public class Archive {
    private String localArk = "src\\main\\resources\\out\\resultado.csv";
    private List<String> lista = new ArrayList<>();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Método para limpar o conteúdo do arquivo
    public void cleanArk() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(localArk))) {
            bw.write("");  // Escreve uma string vazia para limpar o arquivo
        } catch (IOException e) {
            System.err.println("Erro ao limpar o arquivo: " + e.getMessage());
        }
    }

    // Adiciona uma nova entrada com timestamp e o input
    public void addArk(double firstNumber, String operation, double secondNumber, double result) {
        LocalDateTime utcTimestamp = LocalDateTime.now(ZoneId.of("UTC"));
        String formattedTimestamp = utcTimestamp.format(formatter);

        // Formatação: primeiro número, operação, segundo número = resultado
        String formattedInput = String.format("%s: %.2f %s %.2f = %.2f",
                formattedTimestamp, firstNumber, operation, secondNumber, result);

        // Adiciona à lista
        lista.add(formattedInput);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(localArk, true))) {
            bw.write(formattedInput);  // Escreve no arquivo CSV
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }

    // Retorna a lista de operações registradas
    public List<String> getLista() {
        return lista;
    }

    // Define a lista de entradas (sobrescreve a lista atual)
    public void setLista(String[] lista) {
        this.lista = new ArrayList<>(List.of(lista));
    }

    // Retorna o caminho do arquivo
    public String getLocalArk() {
        return localArk;
    }

    // Define o caminho do arquivo
    public void setLocalArk(String localArk) {
        this.localArk = localArk;
    }
}
