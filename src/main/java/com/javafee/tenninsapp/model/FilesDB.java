package com.javafee.tenninsapp.model;

import com.javafee.tenninsapp.model.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
@Getter
@AllArgsConstructor
public class FilesDB {
    public List<String> read(String path) throws IOException {
        return Files.readAllLines(Paths.get(path));
    }
    public void save(List<String> content, String path) throws IOException {
        Files.write(Paths.get(path), content);
    }
    public List<User> readUser(String path) {
        List<User> usersList = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("C:\\Users\\Michał\\git\\Tennis-App\\user.data"));
            String nextLine = br.readLine();
            while (null != nextLine) {
                String[] properties = nextLine.split(",");
                usersList.add(User.fromString(properties));
                nextLine = br.readLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException ex) {
                    ex.printStackTrace(System.err);
                }
            }
        }
        return usersList;
    }

    public void saveUser(User user) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("C:\\Users\\Michał\\git\\Tennis-App\\user.data", true));
            bw.write(System.lineSeparator() + user.toString()
            );
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
            } catch (IOException e) {
            }
        }
    }
}
