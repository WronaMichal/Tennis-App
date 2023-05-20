package com.javafee.tenninsapp.controller;

import com.javafee.tenninsapp.model.FilesDB;
import com.javafee.tenninsapp.model.pojo.User;
import com.javafee.tenninsapp.view.LoginPanel;
import com.javafee.tenninsapp.view.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Getter
public class LoginPanelController {
    private LoginPanel loginPanel;
    private RegisterPanelController registerPanelController;
    private FilesDB filesDB;
    private UserController userController;
    private CourtController courtController;
    private ReservationController reservationController;
    private TestFormController testFormController;
    private MainPanelController mainPanelController;
    private final String userFileName = "user.data";

    private List<User> userList;

    public LoginPanelController() {
        this.filesDB = new FilesDB();
        try {
            this.userList = filesDB.readUser("user.data");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void control() {
        init();

        loginPanel.getButtonRegister().addActionListener(e -> onClickButtonRegister());
        loginPanel.getButtonLogin().addActionListener(e -> onClickButtonLogin());
        loginPanel.getButtonOpenTable().addActionListener(e -> onClickButtonOpenTable());
    }

    private void init() {
        loginPanel = new LoginPanel();
        courtController = new CourtController();
        userController = new UserController();
        reservationController = new ReservationController();
        testFormController = new TestFormController();
        registerPanelController = new RegisterPanelController();
        mainPanelController = new MainPanelController();
        loginPanel.getFrame().setVisible(true);
    }

    private void onClickButtonLogin() {
        String username = loginPanel.getTextFieldUsername().getText();
        String password = loginPanel.getTextFieldPassword().getText();
        System.out.println(username + password);
        User user = userController.login(username, password);
        if (user == null) {
            Utils.displayOptionPanel("Error occurred, detailed message: " + "Such user with this phone number or email address doesn't exists", "Error",
                    JOptionPane.ERROR_MESSAGE, loginPanel.getFrame());
            System.err.println("Such user with this phone number or email address doesn't exists");
        } else {
            mainPanelController.control();
        }
    }

    private void onClickButtonOpenTable() {
        testFormController.control();
    }


    public void onClickButtonRegister() {
        registerPanelController.control();
    }
}
