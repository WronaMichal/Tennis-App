package com.javafee.tenninsapp.model.pojo;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Court {
    static int maxCourtsCount = 0;
    private int id;
    private SurfaceCourt surface;
    private int openingHour;
    private int closingHour;
    private double pricePerHour;
    private List<Reservation> reservationsList = new ArrayList<>();

    public Court(SurfaceCourt surface, int openingHour, int closingHour, double pricePerHour) {
        this.surface = surface;
        this.openingHour = openingHour;
        this.closingHour = closingHour;
        this.pricePerHour = pricePerHour;
    }

    public String toString() {
        return id + "," + getSurface().name() + "," + openingHour + "," + closingHour + "," + pricePerHour;
    }
    public static Court fromString(String[] properties) {
        return Court.builder().id(Integer.parseInt(properties[0])).surface(SurfaceCourt.valueOf(properties[1]))
                .openingHour( Integer.parseInt(properties[2])).closingHour(Integer.parseInt(properties[3]))
                .pricePerHour(Double.parseDouble(properties[4])).build();
    }
    public void addReservation(Reservation reservation) {reservationsList.add(reservation);
    }
}
