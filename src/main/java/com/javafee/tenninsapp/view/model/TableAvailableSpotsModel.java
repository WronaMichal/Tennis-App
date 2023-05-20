package com.javafee.tenninsapp.view.model;

import com.javafee.tenninsapp.controller.AvailableSpotsFormController;
import com.javafee.tenninsapp.model.FilesDB;
import com.javafee.tenninsapp.model.pojo.CourtData;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TableAvailableSpotsModel extends AbstractTableModel {
    private List<CourtData> courtsByCriteria = new ArrayList<>();
    private final AvailableSpotsFormController availableSpotsFormController;
    private final String[] columns;
    LocalDateTime startingTime;
    LocalDateTime endingTime;
    String courtType;

    @Getter
    @AllArgsConstructor
    enum TableAvailableSpotsColumn {
        COL_COURTSURFACE(0), COL_FROM(1), COL_TO (2), COL_PRICE(3), UNKNOWN(-1);

        private final Integer index;

        public static TableAvailableSpotsColumn getByIndex(Integer index) {
            return Arrays.stream(values()).filter(item -> item.getIndex().equals(index)).findFirst().orElse(UNKNOWN);
        }
    }

    public TableAvailableSpotsModel(LocalDateTime startingTime, LocalDateTime endingTime, String courtType) {
        super();
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.courtType = courtType;
        this.availableSpotsFormController = new AvailableSpotsFormController(startingTime,endingTime,courtType);
        prepareData();
        columns = new String[]{"CourtSurface", "Beginning", "Ending", "Price"};
    }

    private void prepareData() {
        try {
            courtsByCriteria = availableSpotsFormController.getCourtsByCriteriaList().stream().map(e -> CourtData.builder().price(availableSpotsFormController.getCourtsByCriteriaList().get(5).toString())
                    .to(endingTime.toString())
                    .from(startingTime.toString())
                    .courtSurface(courtType).build()).toList();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public int getRowCount() {
        return courtsByCriteria.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CourtData courtData = courtsByCriteria.get(rowIndex);
        return switch (TableAvailableSpotsColumn.getByIndex(columnIndex)) {
            case COL_COURTSURFACE -> courtData.getCourtSurface();
            case COL_FROM -> courtData.getFrom();
            case COL_TO -> courtData.getTo();
            case COL_PRICE -> courtData.getPrice();
            case UNKNOWN -> throw new UnsupportedOperationException("Invalid column index: " + columnIndex);
        };
    }
}
