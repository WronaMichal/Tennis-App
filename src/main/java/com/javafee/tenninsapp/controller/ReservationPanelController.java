package com.javafee.tenninsapp.controller;

import com.javafee.tenninsapp.view.MainPanel;
import com.javafee.tenninsapp.view.ReservationPanel;

public class ReservationPanelController {
    private ReservationPanel reservationPanel;
    public void control() {
        init();

        reservationPanel.getButtonSearch().addActionListener(e -> onClickButtonSearch());
    }

    private void init() {
        reservationPanel = new ReservationPanel();
        reservationPanel.getFrame().setVisible(true);
    }

    private void onClickButtonSearch(){

    }
}
