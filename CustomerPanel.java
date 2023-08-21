package ui;

import delegates.UserTransactionsDelegate;
import model.Customer;
import model.Driver;
import model.ShippingUser;
import ui.UserWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerPanel extends JPanel {
    private Customer customer;
    private UserPortal userPortal;
    private UserWindow userWindow;
    public static final int WIDTH = 600;
    public static final int HEIGHT = 300;
    private UserTransactionsDelegate delegate;

    public CustomerPanel(Customer customer, UserTransactionsDelegate delegate, UserPortal userPortal) {
        this.delegate = delegate;
        this.customer = customer;
        this.userPortal = userPortal;

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(new Color(240, 246, 246, 255));
        add(Box.createHorizontalStrut(10));

        JButton updatePhoneButton = new JButton(new CustomerPanel.UpdatePhoneAction());
        this.add(updatePhoneButton);

        JButton deleteAccountButton = new JButton(new CustomerPanel.DeleteAccountAction());
        this.add(deleteAccountButton);

        JButton logoutButton = new JButton(new CustomerPanel.LogoutAction());
        this.add(logoutButton);
    }

    private class UpdatePhoneAction extends AbstractAction {
        UpdatePhoneAction() { super("Update Account Phone Number"); }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String newNumber = JOptionPane.showInputDialog(null,
                    "Please enter your phone number:",
                    "Update",
                    JOptionPane.QUESTION_MESSAGE);

            customer.setPhone(Integer.parseInt(newNumber));

            delegate.updatePhone(customer);

            JOptionPane.showMessageDialog(null,
                    "Your phone number was successfully updated.",
                    "",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private class DeleteAccountAction extends AbstractAction {
        DeleteAccountAction() { super("Delete Account"); }

        @Override
        public void actionPerformed(ActionEvent evt) {


            delegate.deleteCustomer(customer);
            userPortal.dispose();
            userWindow = new UserWindow();
            userWindow.showFrame(delegate);

//            int input = JOptionPane.showConfirmDialog(null,
//                    "Are you sure you want to delete your account?",
//                    "Delete Account",
//                    JOptionPane.YES_NO_OPTION);
//            if (input == JOptionPane.OK_OPTION) {
//                delegate.deleteCustomer(customer);
//                userPortal.dispose();
//                userWindow = new UserWindow();
//                userWindow.showFrame(delegate);
//            }
        }
    }

    private class LogoutAction extends AbstractAction {
        LogoutAction() {
            super("Logout");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            userPortal.dispose();
            userWindow = new UserWindow();
            userWindow.showFrame(delegate);
        }
    }
}
