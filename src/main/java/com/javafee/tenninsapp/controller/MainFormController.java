package com.javafee.tenninsapp.controller;

import com.javafee.tenninsapp.model.FilesDB;
import com.javafee.tenninsapp.view.MainForm;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

public class MainFormController {
    private MainForm mainForm;
    private FilesDB filesDB;
    private TestFormController testFormController;
    private String userFileName = "user.data";

    public void control() {
        init();

        mainForm.getButtonAccept().addActionListener(e -> onClickButtonAccept());
        mainForm.getButtonOpenTable().addActionListener(e -> onClickButtonOpenTable());
    }

    private void init() {
        mainForm = new MainForm();
        filesDB = new FilesDB();
        testFormController = new TestFormController();

        mainForm.getFrame().setVisible(true);
    }

    private void onClickButtonAccept() {
        try {
            List<String> content = filesDB.read(userFileName);
            content.add(MessageFormat.format("{0},{1},{2}",
                    mainForm.getTextFieldName().getText(),
                    mainForm.getTextFieldDescription().getText(),
                    mainForm.getTextFieldComment().getText()));
            filesDB.save(content, userFileName);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void onClickButtonOpenTable() {
        testFormController.control();
    }
}
