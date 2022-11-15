package org.example.ReadFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReadFile {
    private String[] words;

    public void setSql(String linkFile) {
        try {
            words = String.join("\n", Files.readAllLines(Path.of(linkFile))).split(";");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String[] getSql() {
       return words;
    }


}
