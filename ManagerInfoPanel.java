package ui;

import delegates.UserTransactionsDelegate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.Manager;
import java.util.*;
import java.util.List;

public class ManagerInfoPanel extends JPanel implements ActionListener {

    private Manager manager;
    private UserTransactionsDelegate delegate;
    private final JLabel customers;
    private JCheckBox cb1, cb2, cb3, cb4;
    private JButton button;
    private static final int LBL_WIDTH = 100;
    private static final int LBL_HEIGHT = 30;
    public ManagerInfoPanel(Manager manager, UserTransactionsDelegate delegate) {
        this.manager = manager;
        this.delegate = delegate;

        setBackground(new Color(18, 178, 224, 73));

        customers = new JLabel("Request Customers with Following Delivery Types: ");
        add(customers);

        cb1 = new JCheckBox("Regular");
        add(cb1);
        cb2 = new JCheckBox("Overnight");
        add(cb2);
        cb3 = new JCheckBox("Expedited");
        add(cb3);
        cb4 = new JCheckBox("All Types");
        add(cb4);

        button = new JButton("Search");
        button.addActionListener(this);
        add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        List<String> info = new ArrayList<>();
        String message = "";
        if (cb1.isSelected()) {
            message += "regular\n";
        }
        if (cb2.isSelected()) {
            message += "overnight\n";
        }
        if (cb3.isSelected()) {
            message += "expedited\n";
        }
        if (cb4.isSelected()) {
            message = "all";
        }

        info = delegate.getCustomerInfo(message);

        JOptionPane.showMessageDialog(null,
                String.join("\n", info),
                "Search Results",
                JOptionPane.INFORMATION_MESSAGE);

    }
}