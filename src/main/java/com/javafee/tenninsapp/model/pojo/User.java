package com.javafee.tenninsapp.model.pojo;

import lombok.*;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
        return User.builder().phoneNumber(properties[0]).password(properties[1])
                .emailAddress(properties[2]).isAdmin(Boolean.parseBoolean(properties[3])).build();
    }

    @Override
    public String toString() {
        return phoneNumber + "," + password +  "," + emailAddress +  "," + isAdmin;
    }
    //TODO czy tutaj w toString też mam używać buildera??
}
