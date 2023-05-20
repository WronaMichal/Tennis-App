package com.javafee.tenninsapp.controller;

import com.javafee.tenninsapp.model.FilesDB;
import com.javafee.tenninsapp.model.pojo.User;
import lombok.Getter;


import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
@Getter
public class UserController {
    private User activeUser;
    private List<User> userList;

    private FilesDB filesDB;
    private final String userFileName = "user.data";

    public UserController()  {
        this.filesDB = new FilesDB();
        try {
            this.userList = filesDB.readUser(userFileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public User login(String username, String password) {
        Optional<User> optionalUser = userList.stream()
                .filter(user -> (user.getEmailAddress().equals(username) || user.getPhoneNumber().equals(username))
                        && user.getPassword().equals(password)
                ).findFirst();
        if (optionalUser.isPresent()) {
            activeUser = optionalUser.get();
            return activeUser;
        } else {
            return null;
        }
    }
    public User register(String phoneNumber, String password, String emailAddress) {
        List<User> userList = null;
        try {
            userList = filesDB.readUser(userFileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (!userList.stream()
                .filter(user -> user.getPhoneNumber().equals(phoneNumber) ||
                        user.getEmailAddress().equals(emailAddress))
                .collect(Collectors.toList()).isEmpty())
            return null;
        else {
            User newUser = new User(phoneNumber, password, emailAddress, false);
            userList.add(newUser);
            try {
                filesDB.saveUser(newUser, userFileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return newUser;
        }
    }
    public User getUserByPhoneNumber(String phoneNumber) {
        Optional<User> optionalUser = userList.stream()
                .filter(user -> user.getPhoneNumber().equals(phoneNumber))
                .findFirst();
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            return null;
        }
    }
    public Map<String, User> getUserMap() {
        return userList.stream()
                .collect(Collectors.toMap(User::getPhoneNumber, Function.identity()));
    }
}
