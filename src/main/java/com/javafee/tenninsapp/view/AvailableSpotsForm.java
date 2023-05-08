package com.javafee.tenninsapp.view;

import com.javafee.tenninsapp.view.model.TableAvailableSpotsModel;
import lombok.Getter;
import net.coderazzi.filters.gui.TableFilterHeader;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

@Getter
public class AvailableSpotsForm {
    private JFrame frame;
    private JPanel panel;
    private JTable TableAvailableSpots;
    private JButton buttonReserve;
    LocalDateTime startingTime;
    LocalDateTime endingTime;
    String courtType;

    public AvailableSpotsForm(LocalDateTime startingTime, LocalDateTime endingTime, String courtType) {
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.courtType = courtType;
        frame = new JFrame("AvailableSpots");
        frame.setIconImage(new ImageIcon(new ImageIcon(LoginPanel.class.getResource("/images/button-bulb-ico.png"))
                .getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH)).getImage());
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buttonReserve.setIcon(new ImageIcon(new ImageIcon(LoginPanel.class.getResource("/images/btnAccept-ico.png"))
                .getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH)));
        frame.pack();
    }

    private void createUIComponents() {
        TableAvailableSpots = new JTable();
        new TableFilterHeader(TableAvailableSpots);
        TableAvailableSpots.setModel(new TableAvailableSpotsModel(startingTime, endingTime, courtType));
        TableAvailableSpots.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        TableAvailableSpots.setAutoCreateRowSorter(true);
    }
}
