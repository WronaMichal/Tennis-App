package com.javafee.tenninsapp.model;

import com.javafee.tenninsapp.model.pojo.Court;
import com.javafee.tenninsapp.model.pojo.Reservation;
import com.javafee.tenninsapp.model.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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

    public List<Reservation> readReservation(String path) throws IOException {
        return Files.readAllLines(Path.of(path)).stream()
                .map(e -> Reservation.fromString(e.split(","))).toList();
    }

    public void saveReservation(User user, String path) throws IOException {
        Path p = Path.of(path);
        List<String> users = Files.readAllLines(p);
        users.add(user.toString());
        Files.write(p, users, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public List<User> readUser(String path) throws IOException {
        return Files.readAllLines(Path.of(path)).stream()
                .map(e -> User.fromString(e.split(","))).toList();
    }

    public void saveUser(User user, String path) throws IOException {
        Path p = Path.of(path);
        List<String> users = Files.readAllLines(p);
        users.add(user.toString());
        Files.write(p, users, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public List<Court> readCourt(String path) throws IOException {
        return Files.readAllLines(Path.of(path)).stream()
                .map(e -> Court.fromString(e.split(","))).toList();
    }

    public void saveCourt(Court court, String path) throws IOException {
        Path p = Path.of(path);
        List<String> courts = Files.readAllLines(p);
        courts.add(court.toString());
        Files.write(p, courts, StandardOpenOption.TRUNCATE_EXISTING);
    }


}
