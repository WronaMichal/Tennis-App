package com.javafee.tenninsapp.model.pojo;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@RequiredArgsConstructor
public class Reservation {
    private int id;
    private LocalDateTime from;
    private LocalDateTime to;
    private User user;
    private String userPhoneNumber;
    static int maxReservationCount=0;
    private Court court;
    private int courtId;

    public Reservation(LocalDateTime from, LocalDateTime to, String userPhoneNumber, int courtId) {
        this.from = from;
        this.to = to;
        this.userPhoneNumber = userPhoneNumber;
        this.courtId = courtId;
    }

    @Override
    public String toString() {
        return from + "," + to + "," + userPhoneNumber + "," + courtId;
    }
    public static Reservation fromString(String[] properties){
        return new Reservation(LocalDateTime.parse(properties[0]),
                LocalDateTime.parse(properties[1]),
                properties[2],
                Integer.parseInt(properties[3]));
    }
}
