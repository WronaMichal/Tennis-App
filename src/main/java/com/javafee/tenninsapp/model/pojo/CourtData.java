package com.javafee.tenninsapp.model.pojo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CourtData {
    private String courtSurface;
    private String from;
    private String to;
    private String price;

}
