package com.javafee.tenninsapp.controller;

import com.javafee.tenninsapp.view.ReservationPanel;
import com.javafee.tenninsapp.view.ReservationSpecification;

public class ReservationSpecificationController {
    ReservationSpecification reservationSpecification;
    public void control() {
        init();

//        reservationSpecification.getButtonSearch().addActionListener(e -> onClickButtonSearch());
    }

    private void init() {
        reservationSpecification = new ReservationSpecification();
//        reservationSpecification.getFrame().setVisible(true);
    }
}
