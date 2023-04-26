package com.javafee.tenninsapp.controller;
import com.javafee.tenninsapp.view.MainPanel;
import com.javafee.tenninsapp.view.ReservationPanel;

public class MainPanelController {
    private MainPanel mainPanel;
    private ReservationPanelController reservationPanelController;

    public void control() {
        init();

        mainPanel.getButtonPayment().addActionListener(e -> onClickButtonPayments());
        mainPanel.getButtonNewReservation().addActionListener(e -> onClickButtonNewReservation());
    }

    private void init() {
        mainPanel = new MainPanel();
        reservationPanelController = new ReservationPanelController();
        mainPanel.getFrame().setVisible(true);

    }

    private void onClickButtonNewReservation() {
        reservationPanelController.control();
    }

    private void onClickButtonPayments() {
    }
}
