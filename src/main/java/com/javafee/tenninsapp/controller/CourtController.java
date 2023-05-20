package com.javafee.tenninsapp.controller;

import com.javafee.tenninsapp.model.FilesDB;
import com.javafee.tenninsapp.model.pojo.Court;
import com.javafee.tenninsapp.model.pojo.Reservation;
import com.javafee.tenninsapp.model.pojo.SurfaceCourt;
import lombok.Getter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public class CourtController {
    private FilesDB filesDB;
    private List<Court> courtsList;
    private final String courtFileName = "court.data";

    public CourtController() {
        this.filesDB = new FilesDB();
        try {
            this.courtsList = filesDB.readCourt(courtFileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Court> getCourtsByCriteriaList(LocalDateTime startingTime, LocalDateTime endingTime, String courtType) {
        return courtsList.stream()
                .filter(c -> c.getSurface().toString().equals(courtType))
                .filter(c -> c.getReservationsList()
                        .stream()
                        .noneMatch(r -> r.getFrom().isBefore(endingTime)
                                && r.getTo().isAfter(startingTime)))
                .collect(Collectors.toList());
    }
    public Court getCourtsById(int id) {
        Optional<Court> selectedCourt = courtsList.stream()
                .filter(c -> c.getId()==(id))
                .findAny();
        return selectedCourt.orElse(null);
    }

    public List<Reservation> getCourtReservations(int id){
        Court court = getCourtsById(id);
        return court.getReservationsList();
    }
    public void addCourt(String surface, int openingHour, int closingHour, double pricePerHour)  {
        Optional<SurfaceCourt> surfaceCourt = Arrays.stream(SurfaceCourt.values()).filter(sc -> sc.toString().equals(surface))
                .findFirst();
        if(surfaceCourt.isEmpty()){
            throw new RuntimeException("Nie znaleziono takiego kortu");
        }
        else{
            Court court = new Court(surfaceCourt.get(), openingHour, closingHour, pricePerHour);
            try {
                filesDB.saveCourt(court,courtFileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public Map<Integer,Court> getCourtMap(){
        return courtsList.stream()
                .collect(Collectors.toMap(Court::getId, Function.identity()));
    }
}
