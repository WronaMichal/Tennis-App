package com.javafee.tenninsapp.view;

import com.javafee.tenninsapp.model.pojo.SurfaceCourt;
import com.toedter.calendar.JDateChooser;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Getter
public class ReservationPanel {

    private JFrame frame;
    private JPanel panel;
    private JButton buttonSearch;
    private JSpinner From;
    private JSpinner To;
    private JDateChooser jDateChooserReservationDate;
    private JComboBox comboBoxCourtSurface;
    public static String courtSurface;

    public ReservationPanel() {
        frame = new JFrame("Reservation");
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

        From.setModel(new SpinnerNumberModel(8, 8, 22, 1));
        To.setModel(new SpinnerNumberModel(9, 9, 23, 1));

        comboBoxCourtSurface = new JComboBox<SurfaceCourt>();
        DefaultComboBoxModel<SurfaceCourt> courtComboBoxModel = new DefaultComboBoxModel<>();
        courtComboBoxModel.addAll(List.of(SurfaceCourt.values()));
        comboBoxCourtSurface.setModel(courtComboBoxModel);
    }
}
