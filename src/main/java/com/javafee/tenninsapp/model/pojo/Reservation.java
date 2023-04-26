package com.javafee.tenninsapp.model.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

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
}
