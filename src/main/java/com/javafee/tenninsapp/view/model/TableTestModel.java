package com.javafee.tenninsapp.view.model;

import com.javafee.tenninsapp.model.FilesDB;
import com.javafee.tenninsapp.model.pojo.TennisData;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TableTestModel extends AbstractTableModel {
    private List<TennisData> tennisDataList = new ArrayList<>();
    private String[] columns;
    private FilesDB filesDB = new FilesDB();
    private String userFileName = "user.data";

    @AllArgsConstructor
    @Getter
    enum TableTestColumn {
        COL_NAME(0), COL_DESCRIPTION(1), COL_COMMENTS(2), UNKNOWN(-1);

        private final Integer index;

        public static TableTestColumn getByIndex(Integer index) {
            return Arrays.stream(values()).filter(item -> item.getIndex().equals(index)).findFirst().orElse(UNKNOWN);
        }
    }

    public TableTestModel() {
        super();
        prepareData();
        columns = new String[]{"Name", "Description", "Comments"};
    }

    private void prepareData() {
        try {
            tennisDataList = filesDB.read(userFileName).stream().map(e -> {
                String[] parts = e.split(",");
                return TennisData.builder().comments(parts[2]).description(parts[1]).name(parts[0]).build();
            }).toList();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public int getRowCount() {
        return tennisDataList.size();
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
        TennisData tennisData = tennisDataList.get(rowIndex);
        return switch (TableTestColumn.getByIndex(columnIndex)) {
            case COL_NAME -> tennisData.getName();
            case COL_COMMENTS -> tennisData.getComments();
            case COL_DESCRIPTION -> tennisData.getDescription();
            case UNKNOWN -> throw new UnsupportedOperationException("Invalid column index: " + columnIndex);
        };
    }
}
