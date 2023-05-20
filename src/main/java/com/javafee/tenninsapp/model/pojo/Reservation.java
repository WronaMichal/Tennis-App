package com.javafee.tenninsapp.model.pojo;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Reservation {
    private int id;
    private LocalDateTime from;
    private LocalDateTime to;
    private User user;
    private String userPhoneNumber;
    static int maxReservationCount = 0;
    private Court court;
    private int courtId;

    public Reservation(LocalDateTime from, LocalDateTime to, User user, Court court) {
        maxReservationCount++;
        this.id = maxReservationCount;
        this.from = from;
        this.to = to;
        this.user = user;
        this.court = court;
        this.userPhoneNumber = user.getPhoneNumber();
        this.courtId = court.getId();
    }

    public Reservation(int id, LocalDateTime from, LocalDateTime to, User user, Court court) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.user = user;
        this.court = court;
        this.userPhoneNumber = user.getPhoneNumber();
        this.courtId = court.getId();
        if (id > maxReservationCount) {
            maxReservationCount = id;
        }
    }
    //TODO czy potrzebny tutaj ten konstruktor? Czy powinienem ustawić court Id aby sie automatycznie zwiekszało?
    //TODO np. przy dodawaniu kortów?

    @Override
    public String toString() {
        return from + "," + to + "," + userPhoneNumber + "," + courtId;
    }

    public static Reservation fromString(String[] properties) {
        return Reservation.builder().from(LocalDateTime.parse(properties[0]))
                .to(LocalDateTime.parse(properties[1])).userPhoneNumber(properties[2]).courtId(Integer.parseInt(properties[3])).build();
    }


}
