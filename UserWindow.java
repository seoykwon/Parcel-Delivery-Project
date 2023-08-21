package ui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import database.DatabaseConnectionHandler;
import delegates.UserTransactionsDelegate;

/*
 * Represents the window that the ButtonPanel and WindowPanel sit in.
 * Organizes the panels and centers them on the screen.
 */
public class UserWindow extends JFrame {

    private UserTransactionsDelegate delegate;
    private ButtonPanel buttonPanel;
    private WindowPanel windowPanel;



    public UserWindow() {
        super("Quick Ship");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                delegate.getDBConnectionHandler().close();
            }
        });
        setUndecorated(false);
    }

    /* Create Frame to hold the Window Panel and Button Panel
     */
    public void showFrame(UserTransactionsDelegate delegate) {
        this.delegate = delegate;
        windowPanel = new WindowPanel(this.delegate);
        buttonPanel = new ButtonPanel(this.delegate, this);

        add(windowPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        centreOnScreen();
        setVisible(true);
    }

//    public void addUserPanel(JPanel userPanel) {
//        //add(userPanel, BorderLayout.CENTER);
//        //windowPanel.setVisible(false);
//        //buttonPanel.setVisible(false);
//        //
//    }

    private void centreOnScreen() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
    }

//    public void addCustomerPanel(Customer customer) {
//        removeAll();
//        customerPanel = new CustomerPanel(customer, delegate);
//        add(customerPanel, BorderLayout.NORTH);
//        revalidate();
//        pack();
//        centreOnScreen();
//        setVisible(true);
//    }
}
