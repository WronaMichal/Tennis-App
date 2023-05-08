package com.javafee.tenninsapp.controller;

import com.javafee.tenninsapp.model.FilesDB;
import com.javafee.tenninsapp.model.pojo.Court;
import com.javafee.tenninsapp.view.AvailableSpotsForm;
import lombok.Getter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@Getter
public class AvailableSpotsFormController {
    private AvailableSpotsForm availableSpotsForm;
    private FilesDB filesDB;
    private List<Court> courtsList;
    private List <Court> courtsByCriteriaList;
    private LocalDateTime startingTime;
    private LocalDateTime endingTime;
    private String courtType;

    public AvailableSpotsFormController(LocalDateTime startingTime, LocalDateTime endingTime, String courtType) {
        this.filesDB =  new FilesDB();
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.courtType = courtType;
        System.out.println(startingTime);
        try {
            this.courtsList = filesDB.readCourt("court.data");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        courtsByCriteriaList = getCourtsByCriteriaList(startingTime,endingTime,courtType);
    }

    public void control() {
        init();

        availableSpotsForm.getButtonReserve().addActionListener(e -> onClickButtonReserve());
    }

    private void init() {
        availableSpotsForm = new AvailableSpotsForm(startingTime, endingTime, courtType);
        filesDB = new FilesDB();
        availableSpotsForm.getFrame().setVisible(true);
    }

    private List<Court> getCourtsByCriteriaList(LocalDateTime startingTime, LocalDateTime endingTime, String courtType) {
        return courtsList.stream()
                .filter(c -> c.getSurface().toString().equals(courtType))
                .filter(c -> c.getReservationsList()
                        .stream()
                        .noneMatch(r -> r.getFrom().isBefore(endingTime)
                                && r.getTo().isAfter(startingTime)))
                .collect(Collectors.toList());
    }

    public void onClickButtonReserve (){
    }
}
