package com.javafee.tenninsapp.view;

import com.javafee.tenninsapp.view.model.TableTestModel;
import lombok.Getter;

import javax.swing.*;

@Getter
public class TestForm {
    private JFrame frame;
    private JPanel panel;
    private JTable tableTest;
    private JButton buttonConfirm;

    public TestForm() {
        frame = new JFrame("Tennis App");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
    }

    private void createUIComponents() {
        tableTest = new JTable();
        tableTest.setModel(new TableTestModel());
        tableTest.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tableTest.setAutoCreateRowSorter(true);
    }
}
