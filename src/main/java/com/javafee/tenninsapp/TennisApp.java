package com.javafee.tenninsapp;

import com.javafee.tenninsapp.controller.LoginPanelController;

import javax.swing.*;
import java.io.IOException;

public class TennisApp {
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new LoginPanelController().control();
    }
}
