package ui;

import delegates.UserTransactionsDelegate;
import model.Customer;

import javax.swing.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Driver;
import model.Manager;
import ui.UserWindow;

public class DriverInfoPanel extends JPanel {

    private Driver driver;
    private UserTransactionsDelegate delegate;
    private final JLabel orders;
    private static final int LBL_WIDTH = 600;
    private static final int LBL_HEIGHT = 30;
    public DriverInfoPanel(Driver driver, UserTransactionsDelegate delegate) {
        this.driver = driver;
        this.delegate = delegate;

        setBackground(new Color(77, 204, 87, 176));

        orders = new JLabel("Total Pending Orders: " + getPendingOrders());
        orders.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        add(orders);
        add(Box.createHorizontalStrut(10));
    }

    private String getPendingOrders() {
        //TODO write function to get list of orders

        return "None.";
    }

}