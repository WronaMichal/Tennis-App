package com.javafee.tenninsapp.view.utils;

import lombok.experimental.UtilityClass;

import javax.swing.*;
import java.awt.*;

@UtilityClass
public class Utils {
    public void displayOptionPanel(String message, String title, int messageType, Component component) {
        JOptionPane optionPane = new JOptionPane();
        optionPane.setMessage("<html>" + message + "</html>");
        optionPane.setMessageType(messageType);
        JDialog dialog = optionPane.createDialog(component, title);
        dialog.setVisible(true);
    }
}
