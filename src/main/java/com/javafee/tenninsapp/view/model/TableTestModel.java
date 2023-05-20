package com.javafee.tenninsapp.view.model;

import com.javafee.tenninsapp.controller.AvailableSpotsFormController;
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
    private final String[] columns;
    private final FilesDB filesDB = new FilesDB();
    private final String userFileName = "user.data";

    @AllArgsConstructor
    @Getter
    enum TableTestColumn {
        COL_LOGIN(0), COL_PASSWORD(1), UNKNOWN(-1);

        private final Integer index;

        public static TableTestColumn getByIndex(Integer index) {
            return Arrays.stream(values()).filter(item -> item.getIndex().equals(index)).findFirst().orElse(UNKNOWN);
        }
    }

    public TableTestModel() {
        super();
        prepareData();
        columns = new String[]{"Login", "Password"};
    }

    private void prepareData() {
        try {
            tennisDataList = filesDB.read(userFileName).stream().map(e -> {
                String[] parts = e.split(",");
                return TennisData.builder().password(parts[1]).login(parts[0]).build();
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
            case COL_LOGIN -> tennisData.getLogin();
            case COL_PASSWORD -> tennisData.getPassword();
            case UNKNOWN -> throw new UnsupportedOperationException("Invalid column index: " + columnIndex);
        };
    }
}
