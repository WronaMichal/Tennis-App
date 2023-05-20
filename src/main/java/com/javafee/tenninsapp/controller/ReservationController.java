package com.javafee.tenninsapp.controller;

import com.javafee.tenninsapp.model.FilesDB;
import com.javafee.tenninsapp.model.pojo.Court;
import com.javafee.tenninsapp.model.pojo.Reservation;
import com.javafee.tenninsapp.model.pojo.User;
import lombok.Getter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ReservationController {
    private CourtController courtController;
    private UserController userController;
    private FilesDB filesDB;
    private final String reservationFileName = "reservation.data";
    private List<Reservation> reservationsList;

    public ReservationController() {
        this.filesDB = new FilesDB();
        this.courtController = new CourtController();
        this.userController = new UserController();
        try {
            reservationsList = filesDB.readReservation(reservationFileName, courtController.getCourtMap(), userController.getUserMap());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Reservation addReservation(LocalDateTime from, LocalDateTime to, User user, Court court) {
        Reservation reservation = new Reservation(from, to, user, court);
        court.addReservation(reservation);
        reservationsList.add(reservation);
        return reservation;
    }
    public List<Reservation> getUserReservations(User user) {
        return reservationsList.stream()
                .filter(r -> r.getUser().equals(user))
                .collect(Collectors.toList());
    }
    public void save(Reservation reservation)  {
        try {
            filesDB.saveReservation(reservation, reservationFileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


