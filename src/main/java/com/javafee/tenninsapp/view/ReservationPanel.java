package com.javafee.tenninsapp.view;

import com.javafee.tenninsapp.model.pojo.SurfaceCourt;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
@Getter
public class ReservationPanel {

    private JFrame frame;
    private JPanel panel;
    private JButton buttonSearch;
    private JSpinner From;
    private JSpinner To;
    public static String courtSurface;

    public ReservationPanel() {
        frame = new JFrame("Tennis App");
        frame.setIconImage(new ImageIcon(new ImageIcon(LoginPanel.class.getResource("/images/button-bulb-ico.png"))
                .getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH)).getImage());
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buttonSearch.setIcon(new ImageIcon(new ImageIcon(LoginPanel.class.getResource("/images/btnAccept-ico.png"))
                .getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH)));
        frame.pack();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
