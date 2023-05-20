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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class FilesDB {
    public List<String> read(String path) throws IOException {
        return Files.readAllLines(Paths.get(path));
    }

    public void save(List<String> content, String path) throws IOException {
        Files.write(Paths.get(path), content);
    }

    public List<Reservation> readReservation(String path, Map<Integer, Court> courtMap, Map<String, User> userMap) throws IOException {
        List<Reservation> reservationList = new ArrayList<>();
        BufferedReader br = null;
        br = new BufferedReader(new FileReader(path));
        String nextLine = br.readLine();
        while (null != nextLine) {
            String[] properties = nextLine.split(",");
            reservationList.add(new Reservation(Integer.parseInt(properties[0]),
                    LocalDateTime.parse(properties[1]), LocalDateTime.parse(properties[2]),
                    userMap.get(properties[3]), courtMap.get(Integer.parseInt(properties[4]))));
            nextLine = br.readLine();
        }
        br.close();
        return reservationList;
    }

    public void saveReservation(Reservation reservation, String path) throws IOException {
        Path p = Path.of(path);
        List<String> users = Files.readAllLines(p);
        users.add(reservation.toString());
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
