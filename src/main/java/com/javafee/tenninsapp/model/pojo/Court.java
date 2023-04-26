package com.javafee.tenninsapp.model.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@RequiredArgsConstructor
public class Court {
    static int maxCourtsCount = 0;
    private int id;
    private SurfaceCourt surface;
    private int openingHour;
    private int closingHour;
    private double pricePerHour;
}
