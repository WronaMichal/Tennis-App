package com.javafee.tenninsapp.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SurfaceCourt {
    GRASS("Trawka"), CLAY("Mączka"), HARD("Harda");

    String val;
}
