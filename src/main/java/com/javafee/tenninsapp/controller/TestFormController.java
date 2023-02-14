package com.javafee.tenninsapp.controller;

import com.javafee.tenninsapp.model.FilesDB;
import com.javafee.tenninsapp.view.MainForm;
import com.javafee.tenninsapp.view.TestForm;

public class TestFormController {
    private TestForm testForm;
    private FilesDB filesDB;
    private String userFileName = "user.data";

    public void control() {
        init();
    }

    private void init() {
        testForm = new TestForm();
        filesDB = new FilesDB();

        testForm.getFrame().setVisible(true);
    }
}
