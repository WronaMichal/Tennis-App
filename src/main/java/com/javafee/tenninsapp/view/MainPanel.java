package com.javafee.tenninsapp.view;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
@Getter
public class MainPanel {
    private final JFrame frame;
    private JPanel panel;
    private JButton buttonNewReservation;
    private JButton buttonPayment;

    public MainPanel() {
        frame = new JFrame("Tennis App");
        frame.setIconImage(new ImageIcon(new ImageIcon(LoginPanel.class.getResource("/images/button-bulb-ico.png"))
                .getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH)).getImage());
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buttonNewReservation.setIcon(new ImageIcon(new ImageIcon(LoginPanel.class.getResource("/images/btnAccept-ico.png"))
                .getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH)));
        buttonPayment.setIcon(new ImageIcon(new ImageIcon(LoginPanel.class.getResource("/images/btnAdd-ico.png"))
                .getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH)));

        frame.pack();
    }
}
