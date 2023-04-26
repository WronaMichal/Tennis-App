package com.javafee.tenninsapp.controller;

import com.javafee.tenninsapp.model.FilesDB;
import com.javafee.tenninsapp.model.pojo.User;
import com.javafee.tenninsapp.view.RegisterPanel;
import com.javafee.tenninsapp.view.utils.Utils;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

public class RegisterPanelController {
    private RegisterPanel registerPanel;
    private LoginPanelController loginPanelController;
    private RegisterPanelController registerPanelController;
    private FilesDB filesDB;
    private final String userFileName = "user.data";

    public RegisterPanelController() {
        this.filesDB = new FilesDB();
        //TODO czy powinienem tworzyć konstruktor czy da sie to zrobic samym init()?
    }

    public void control() {
        init();

        registerPanel.getButtonRegister().addActionListener(e -> onClickButtonRegister());
    }

    private void init() {
        registerPanel = new RegisterPanel();
        registerPanelController = new RegisterPanelController();
        registerPanel.getFrame().setVisible(true);
    }

    private void onClickButtonRegister() {
        String error = null;
        Boolean isValid = true;
        String emailAddress = registerPanel.getTextFieldEmailAddress().getText();
        String phoneNumber = registerPanel.getTextFieldPhoneNumber().getText();
        String password = registerPanel.getTextFieldPassword().getText();
        String passwordConfirm = registerPanel.getTextFieldPasswordConfirm().getText();

        if (!emailAddress.matches("^(.+)@(.+)$")) {
            isValid = false;
            error = "Adres mailowy jest nieprawidłowy";
            //TODO jak zrobić, żeby JLabel podświetlał mi się na czerwono przy błędzie? Czy potrzebne?
        }
        if (!phoneNumber.matches("^\\d{9}$")) {
            isValid = false;
            error = "Numer telefonu jest nieprawidłowy";
        }
        if (!password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$")) {
            isValid = false;
            error = "Hasło jest za słabe";
        }
        if (!password.equals(passwordConfirm)) {
            isValid = false;
            error = "Hasła nie są identyczne";
        }
        if (isValid) {
            User user = registerPanelController.register(phoneNumber, password, emailAddress);
            if (user != null) {
                loginPanelController.control();
            } else {
                Utils.displayOptionPanel("Error occurred, detailed message: " + "Such user with this phone number or email address already exists", "Error",
                        JOptionPane.ERROR_MESSAGE, registerPanel.getFrame());
                System.err.println("Such user with this phone number or email address already exists");
            }
        } else {
            Utils.displayOptionPanel("Error occurred, detailed message: " + error, "Error",
                    JOptionPane.ERROR_MESSAGE, registerPanel.getFrame());
            System.err.println(error);
        }
        System.out.println(emailAddress + " " + phoneNumber + " " + password);
    }
    private User register(String phoneNumber, String password, String emailAddress) {
        List<User> userList = filesDB.readUser(userFileName);
        //TODO Baza nie wczytuje sie gdy nie ma zadnego rekordu, muszę wpisać ręcznie pierwszy rekord do pliku
        if (!userList.stream()
                .filter(user -> user.getPhoneNumber().equals(phoneNumber) ||
                        user.getEmailAddress().equals(emailAddress))
                .collect(Collectors.toList()).isEmpty())
            return null;
        else {
            User newUser = new User(phoneNumber, password, emailAddress, false);
            userList.add(newUser);
            filesDB.saveUser(newUser);
            return newUser;
        }
    }
}
