package ui;

import delegates.UserTransactionsDelegate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Manager;

public class UnusedManagerInfoPanel extends JPanel implements ActionListener {

    private Manager manager;
    private UserTransactionsDelegate delegate;
    private final JLabel drivers;
    private JCheckBox cb1, cb2, cb3, cb4, cb5, cb6, cb7, cb8;
    private JButton button;
    private static final int LBL_WIDTH = 100;
    private static final int LBL_HEIGHT = 30;
    public UnusedManagerInfoPanel(Manager manager, UserTransactionsDelegate delegate) {
        this.manager = manager;
        this.delegate = delegate;

        setBackground(new Color(18, 178, 224, 176));

        drivers = new JLabel("Request Driver Information: ");
        add(drivers);

        cb1 = new JCheckBox("Username");
        add(cb1);
        cb2 = new JCheckBox("Phone");
        add(cb2);
        cb3 = new JCheckBox("Email");
        add(cb3);
        cb4 = new JCheckBox("Name");
        add(cb4);
        cb5 = new JCheckBox("Address");
        add(cb5);
        cb6 = new JCheckBox("Orders Delivered");
        add(cb6);
        cb7 = new JCheckBox("Salary");
        add(cb7);
        cb8 = new JCheckBox("Licence");
        add(cb8);

        button = new JButton("Search");
        button.addActionListener(this);
        add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String message = "";
        if (cb1.isSelected()) {
            message += "username\n";
        }
        if (cb2.isSelected()) {
            message += "phone\n";
        }
        if (cb3.isSelected()) {
            message += "email\n";
        }
        if (cb4.isSelected()) {
            message += "name\n";
        }
        if (cb5.isSelected()) {
            message += "address\n";
        }
        if (cb6.isSelected()) {
            message += "orders\n";
        }
        if (cb7.isSelected()) {
            message += "salary\n";
        }
        if (cb8.isSelected()) {
            message += "licence\n";
        }
        String info = delegate.getDriverInfo(message);

        JOptionPane.showMessageDialog(null,
                info,
                "Search Results",
                JOptionPane.INFORMATION_MESSAGE);

    }
}