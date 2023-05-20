package com.javafee.tenninsapp.controller;

import com.javafee.tenninsapp.model.pojo.User;
import com.javafee.tenninsapp.view.RegisterPanel;
import com.javafee.tenninsapp.view.utils.Utils;

import javax.swing.*;
import java.io.IOException;

public class RegisterPanelController {
    private RegisterPanel registerPanel;
    private LoginPanelController loginPanelController;
   private UserController userController;



    public void control() {
        init();

        registerPanel.getButtonRegister().addActionListener(e -> onClickButtonRegister());
    }

    private void init() {
        registerPanel = new RegisterPanel();
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
            try {
                User user = userController.register(phoneNumber, password, emailAddress);

                if (user != null) {
                    loginPanelController.control();
                } else {
                    Utils.displayOptionPanel("Error occurred, detailed message: " + "Such user with this phone number or email address already exists", "Error",
                            JOptionPane.ERROR_MESSAGE, registerPanel.getFrame());
                    System.err.println("Such user with this phone number or email address already exists");
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } else {
            Utils.displayOptionPanel("Error occurred, detailed message: " + error, "Error",
                    JOptionPane.ERROR_MESSAGE, registerPanel.getFrame());
            System.err.println(error);
        }
        System.out.println(emailAddress + " " + phoneNumber + " " + password);
    }
}
