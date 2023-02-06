package com.javafee.tenninsapp.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FilesDB {
    public List<String> read(String path) throws IOException {
        return Files.readAllLines(Paths.get(path));
    }

    public void save(List<String> content, String path) throws IOException {
        Files.write(Paths.get(path), content);
    }
}
