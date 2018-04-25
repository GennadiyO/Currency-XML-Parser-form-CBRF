package com.gazprombank.task4;

import com.gazprombank.task4.interfaces.Printer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FilePrinter implements Printer {
    private String fileName;
    private String lineToWrite;

    public FilePrinter(String fileName, String lineToWrite) {
        this.fileName = fileName;
        this.lineToWrite = lineToWrite;
    }
    @Override
    public void print() {
        if (!Files.exists(Paths.get(fileName))) {
            try {
                Files.createFile(Paths.get(fileName));
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Can't create file: " + fileName);
            }
        }
        Path valutesFile = Paths.get(fileName);
        try {
            Files.write(valutesFile, (lineToWrite + "\r\n").getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Can't write to file: " + fileName);
        }
    }
}
