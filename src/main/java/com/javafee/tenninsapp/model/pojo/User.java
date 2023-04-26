package com.javafee.tenninsapp.model.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@RequiredArgsConstructor
public class User {
    private String phoneNumber;
    private String password;
    private String emailAddress;
    private boolean isAdmin;
    private List<Reservation> reservations = new ArrayList<>();

    public User(String phoneNumber, String password, String emailAddress, boolean isAdmin) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.emailAddress = emailAddress;
        this.isAdmin = isAdmin;
    }
    public static User fromString(String[] properties){
        return new User(properties[0],
                properties[1],
                properties[2],
                Boolean.parseBoolean(properties[3]));
    }

    @Override
    public String toString() {
        return phoneNumber + "," + password +  "," + emailAddress +  "," + isAdmin;
    }
}
