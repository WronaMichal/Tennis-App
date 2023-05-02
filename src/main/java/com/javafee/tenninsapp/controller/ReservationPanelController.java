package com.javafee.tenninsapp.controller;

import com.javafee.tenninsapp.view.ReservationPanel;

import java.time.LocalDateTime;
import java.util.Date;

public class ReservationPanelController {
    private ReservationPanel reservationPanel;
    private ReservationSpecificationController reservationSpecificationController;

    public void control() {
        init();

        reservationPanel.getButtonSearch().addActionListener(e -> onClickButtonSearch());
    }

    private void init() {
        reservationPanel = new ReservationPanel();
        reservationPanel.getFrame().setVisible(true);
    }

    private void onClickButtonSearch() {
        reservationSpecificationController.control();
        // pobranie daty z pola DateChooser
        Date date = reservationPanel.getJDateChooserReservationDate().getDate();
        // konwersja Date na LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.from(date.toInstant());
        // ustawienie na obiekcie godziny oraz minuty pobierajac wartosci ze Spinnerow
        localDateTime.withHour((Integer) reservationPanel.getFrom().getValue());
        localDateTime.withMinute((Integer) reservationPanel.getTo().getValue());
    }
}
