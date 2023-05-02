package com.javafee.tenninsapp.view;

import com.toedter.calendar.JDateChooser;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class ReservationPanel {

    private JFrame frame;
    private JPanel panel;
    private JButton buttonSearch;
    private JSpinner From;
    private JSpinner To;
    private JDateChooser jDateChooserReservationDate;
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
        jDateChooserReservationDate = new JDateChooser();
        From =  new JSpinner();
        To =  new JSpinner();

        From.setModel(new SpinnerNumberModel(1, 1, 12, 1));
        To.setModel(new SpinnerNumberModel(1, 1, 12, 1));
    }
}
