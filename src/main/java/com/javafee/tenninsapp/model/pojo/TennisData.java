package com.javafee.tenninsapp.model.pojo;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TennisData {
    private String name;
    private String description;
    private String comments;
}
