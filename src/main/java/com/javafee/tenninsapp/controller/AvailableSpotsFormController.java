package com.javafee.tenninsapp.controller;

import com.javafee.tenninsapp.model.FilesDB;
import com.javafee.tenninsapp.model.pojo.Court;
import com.javafee.tenninsapp.view.AvailableSpotsForm;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class AvailableSpotsFormController {
    private AvailableSpotsForm availableSpotsForm;
    private FilesDB filesDB;
    private List<Court> courtsList;
    private CourtController courtController;
    private List<Court> courtsByCriteriaList;
    private LocalDateTime startingTime;
    private LocalDateTime endingTime;
    private String courtType;

    public AvailableSpotsFormController(LocalDateTime startingTime, LocalDateTime endingTime, String courtType) {
        this.filesDB = new FilesDB();
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.courtType = courtType;
        this.courtController = new CourtController();
        courtsList = courtController.getCourtsList();
        courtsByCriteriaList = courtController.getCourtsByCriteriaList(startingTime, endingTime, courtType);
    }

    public void control() {
        init();

        availableSpotsForm.getButtonReserve().addActionListener(e -> onClickButtonReserve());
    }

    private void init() {
        courtController = new CourtController();
        availableSpotsForm = new AvailableSpotsForm(startingTime, endingTime, courtType);
        filesDB = new FilesDB();
        availableSpotsForm.getFrame().setVisible(true);
    }

    public void onClickButtonReserve() {
    }
}
