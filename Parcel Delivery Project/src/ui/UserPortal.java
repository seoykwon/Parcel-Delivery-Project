package ui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import delegates.UserTransactionsDelegate;
import model.Customer;
import model.Driver;
import model.Manager;

public class UserPortal extends JFrame {
    private UserTransactionsDelegate delegate;
    private CustomerPanel customerPanel;
    private CustomerInfoPanel customerInfo;
    private ManagerPanel managerPanel;
    private ManagerInfoPanel managerInfoPanel;

    //private UnusedManagerInfoPanel unusedManagerInfo;

    private DriverPanel driverPanel;
    private DriverInfoPanel driverInfo;

    private Customer customer;
    private Driver driver;
    private Manager manager;

    public UserPortal() {
        super("Quick Ship User Portal");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setUndecorated(false);
    }

    public void showCustomerFrame(Customer customer, UserTransactionsDelegate delegate) {
        this.customer = customer;
        this.delegate = delegate;

        customerPanel = new CustomerPanel(this.customer, this.delegate, this);
        customerInfo = new CustomerInfoPanel(this.customer, this.delegate);

        add(customerPanel, BorderLayout.NORTH);
        add(customerInfo, BorderLayout.SOUTH);

        pack();
        centreOnScreen();
        setVisible(true);
    }

    public void showManagerFrame(Manager manager, UserTransactionsDelegate delegate) {
        this.manager = manager;
        this.delegate = delegate;

        managerPanel = new ManagerPanel(this.manager, this.delegate, this);
        managerInfoPanel = new ManagerInfoPanel(this.manager, this.delegate);
        //unusedManagerInfo = new UnusedManagerInfoPanel(this.manager, this.delegate);

        add(managerPanel, BorderLayout.NORTH);
        //add(unusedManagerInfo, BorderLayout.CENTER);
        add(managerInfoPanel, BorderLayout.SOUTH);

        pack();
        centreOnScreen();
        setVisible(true);
    }

    public void showDriverFrame(Driver driver, UserTransactionsDelegate delegate) {
        this.driver = driver;
        this.delegate = delegate;

        driverPanel = new DriverPanel(this.driver, this.delegate, this);
        driverInfo = new DriverInfoPanel(this.driver, this.delegate);

        add(driverPanel, BorderLayout.NORTH);
        add(driverInfo, BorderLayout.SOUTH);

        pack();
        centreOnScreen();
        setVisible(true);
    }

    private void centreOnScreen() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
    }
}
