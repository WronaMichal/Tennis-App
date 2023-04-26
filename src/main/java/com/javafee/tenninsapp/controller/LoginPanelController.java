package com.javafee.tenninsapp.controller;

import com.javafee.tenninsapp.model.FilesDB;
import com.javafee.tenninsapp.model.pojo.User;
import com.javafee.tenninsapp.view.LoginPanel;
import com.javafee.tenninsapp.view.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.swing.*;
import java.util.List;
import java.util.Optional;
public class LoginPanelController {
    private LoginPanel loginPanel;
    private RegisterPanelController registerPanelController;
    private FilesDB filesDB;
    private LoginPanelController loginPanelController;
    private TestFormController testFormController;
    private MainPanelController mainPanelController;
    private final String userFileName = "user.data";
    private User activeUser;
    private List<User> userList;

    public LoginPanelController() {
        this.filesDB = new FilesDB();
        this.userList = filesDB.readUser("user.data");
    }
    public void control() {
        init();

        loginPanel.getButtonRegister().addActionListener(e -> onClickButtonRegister());
        loginPanel.getButtonLogin().addActionListener(e -> onClickButtonLogin());
        loginPanel.getButtonOpenTable().addActionListener(e -> onClickButtonOpenTable());
    }

    private void init() {
        loginPanel = new LoginPanel();
        testFormController = new TestFormController();
        registerPanelController = new RegisterPanelController();
        loginPanelController = new LoginPanelController();
        mainPanelController = new MainPanelController();
        loginPanel.getFrame().setVisible(true);
    }

//    private void onClickButtonLogin() {
//        try {
//            List<String> content = filesDB.read(userFileName);
//            content.add(MessageFormat.format("{0},{1}",
//                    loginPanel.getTextFieldLogin().getText(),
//                    loginPanel.getTextFieldPassword().getText()));
//            filesDB.save(content, userFileName);
//        } catch (IOException e) {
//            Utils.displayOptionPanel("Error occurred, detailed message: " + e.getMessage(), "Error",
//                    JOptionPane.ERROR_MESSAGE, loginPanel.getFrame());
//            System.err.println(e.getMessage());
//        }
//        mainPanelController.control();
//    }

    private void onClickButtonLogin() {
        String username = loginPanel.getTextFieldUsername().getText();
        String password = loginPanel.getTextFieldPassword().getText();
        System.out.println(username + password);
        User user = loginPanelController.login(username, password);
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

    private User login(String username, String password) {
        //TODO czemu musze ponownie inicjalizować listę jak mam ją w init()? Tak samo w Register Panel
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

    public void onClickButtonRegister() {
        registerPanelController.control();
    }
}
