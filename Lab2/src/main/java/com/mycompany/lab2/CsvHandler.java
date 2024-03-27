package com.mycompany.lab2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvHandler {

    private static FileWriter writer;

    public static void openFile(String csvFile) {
        try {
            writer = new FileWriter(csvFile, false);
            writer.append("Function, X, Result\n");
        } catch (IOException e) {
            System.out.println("Ошибка при открытии файла: " + e.getMessage());
        }
    }

    public static void writeToCsv(String func, double X, double res) {
        if (writer == null) {
            System.out.println("Файл не открыт");
        }
        try {
            writer.append(func + "," + X + "," + res + "\n");
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }
    }

    public static String[] readCsv(String csvFile) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }

        return lines.toArray(new String[0]);
    }

    public static void closeAndSave() {
        if (writer != null) {
            try {
                writer.flush();
                writer.close();
            } catch (IOException e) {
                System.out.println("Ошибка при закрытии файла: " + e.getMessage());
            }
        }
    }
}