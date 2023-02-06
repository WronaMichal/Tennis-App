package com.javafee.tenninsapp.view;

import lombok.Getter;

import javax.swing.*;

@Getter
public class MainForm {
    private JFrame frame;
    private JPanel panel;
    private JTextField textFieldName;
    private JTextField textFieldDescription;
    private JTextField textFieldComment;
    private JButton buttonAccept;

    public MainForm() {
        frame = new JFrame("Tennis App");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
    }
}
