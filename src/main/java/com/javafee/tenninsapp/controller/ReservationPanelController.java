package com.javafee.tenninsapp.controller;

import com.javafee.tenninsapp.view.ReservationPanel;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class ReservationPanelController {
    private ReservationPanel reservationPanel;
    private AvailableSpotsFormController availableSpotsFormController;

    public void control() {
        init();

        reservationPanel.getButtonSearch().addActionListener(e -> onClickButtonSearch());
    }

    private void init() {
        reservationPanel = new ReservationPanel();
        reservationPanel.getFrame().setVisible(true);
    }

    private void onClickButtonSearch() {
        Date date = reservationPanel.getJDateChooserReservationDate().getDate();
        String courtType = String.valueOf(reservationPanel.getComboBoxCourtSurface().getSelectedItem().toString());
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        LocalDateTime startingTime = localDateTime.withHour((Integer) reservationPanel.getFrom().getValue());
        LocalDateTime endingTime = localDateTime.withHour((Integer) reservationPanel.getTo().getValue());
        System.out.println(courtType);
        System.out.println(startingTime);
        System.out.println(endingTime);
        availableSpotsFormController = new AvailableSpotsFormController(startingTime, endingTime, courtType);
        availableSpotsFormController.control();
    }
}
