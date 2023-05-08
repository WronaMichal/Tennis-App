package com.javafee.tenninsapp.model.pojo;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
public class Court {
    static int maxCourtsCount = 0;
    private int id;
    private SurfaceCourt surface;
    private int openingHour;
    private int closingHour;
    private double pricePerHour;
    private List<Reservation> reservationsList;


    public String toString() {
        return id + "," + getSurface().name() + "," + openingHour + "," + closingHour + "," + pricePerHour;
    }
    public static Court fromString(String[] properties) {
        return Court.builder().id(Integer.parseInt(properties[0])).surface(SurfaceCourt.valueOf(properties[1]))
                .openingHour( Integer.parseInt(properties[2])).closingHour(Integer.parseInt(properties[3]))
                .pricePerHour(Double.parseDouble(properties[4])).build();
    }
}
