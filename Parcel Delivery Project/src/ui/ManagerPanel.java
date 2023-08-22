package ui;

import delegates.UserTransactionsDelegate;
import model.Manager;

import javax.swing.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ManagerPanel extends JPanel {
    private Manager manager;
    private UserPortal userPortal;
    private UserWindow userWindow;
    public static final int WIDTH = 600;
    public static final int HEIGHT = 300;
    private UserTransactionsDelegate delegate;

    public ManagerPanel(Manager manager, UserTransactionsDelegate delegate, UserPortal userPortal) {
        this.delegate = delegate;
        this.manager = manager;
        this.userPortal = userPortal;

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(new Color(240, 246, 246, 255));
        add(Box.createHorizontalStrut(10));

        JButton updatePhoneButton = new JButton(new ManagerPanel.UpdatePhoneAction());
        this.add(updatePhoneButton);

        JButton deleteAccountButton = new JButton(new ManagerPanel.DeleteAccountAction());
        this.add(deleteAccountButton);

        JButton getDriversButton = new JButton(new ManagerPanel.GetDriversAction());
        this.add(getDriversButton);

        JButton loyalCustomersButton = new JButton(new ManagerPanel.LoyalCustomersAction());
        this.add(loyalCustomersButton);

        JButton salaryByDeliveriesButton = new JButton(new ManagerPanel.SalaryByDeliveriesAction());
        this.add(salaryByDeliveriesButton);

        JButton getDriverInfoButton = new JButton(new ManagerPanel.GetDriverInfoAction());
        this.add(getDriverInfoButton);

        JButton logoutButton = new JButton(new ManagerPanel.LogoutAction());
        this.add(logoutButton);

        JButton parcelsByDeliveryTypeButton = new JButton(new ManagerPanel.GroupParcelsAction());
        this.add(parcelsByDeliveryTypeButton);

        JButton groupCustomersByOrdersPlacedButton = new JButton(new ManagerPanel.GroupCustomersByOrders());
        this.add(groupCustomersByOrdersPlacedButton);
    }

    private class GroupCustomersByOrders extends AbstractAction {
        GroupCustomersByOrders() {
            super("Group Customer By Orders Placed");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            List<String> customers = delegate.groupCustomersByOrdersPlaced();

            JOptionPane.showMessageDialog(null,
                    String.join("\n", customers),
                    "Search Results",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private class GroupParcelsAction extends AbstractAction {

        GroupParcelsAction() {super("Count Parcels By Delivery Type");}
        @Override
        public void actionPerformed(ActionEvent e) {
            List<String> parcelTypes = delegate.countParcelsByDeliveryType();

            JOptionPane.showMessageDialog(null,
                    String.join("\n", parcelTypes),
                    "Search Results",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private class UpdatePhoneAction extends AbstractAction {
        UpdatePhoneAction() { super("Update Account Phone Number"); }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String newNumber = JOptionPane.showInputDialog(null,
                    "Please enter your phone number:",
                    "Update",
                    JOptionPane.QUESTION_MESSAGE);

            manager.setPhone(Integer.parseInt(newNumber));

            delegate.updatePhoneNumber(manager);

            JOptionPane.showMessageDialog(null,
                    "Your phone number was successfully updated.",
                    "",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private class GetDriverInfoAction extends AbstractAction {
        GetDriverInfoAction() { super("Request Driver Information"); }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String driverUsername = JOptionPane.showInputDialog(null,
                    "Please enter the driver's username:",
                    "Driver Info",
                    JOptionPane.QUESTION_MESSAGE);

            // TODO: [EXCEPTION] ORA-00918: column ambiguously defined
            String driverInfo = delegate.getDriverInfo(driverUsername);

            JOptionPane.showMessageDialog(null,
                    driverInfo,
                    "Search Results",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }



    private class DeleteAccountAction extends AbstractAction {
        DeleteAccountAction() { super("Delete Account"); }

        @Override
        public void actionPerformed(ActionEvent evt) {
            int input = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to delete your account?",
                    "Delete Account",
                    JOptionPane.YES_NO_OPTION);
            if (input == 0) {
                delegate.deleteManager(manager);
                userPortal.dispose();
                userWindow = new UserWindow();
                userWindow.showFrame(delegate);
            }
        }
    }

    private class GetDriversAction extends AbstractAction {
        GetDriversAction() { super("Search Drivers by Salary"); }

        @Override
        public void actionPerformed(ActionEvent evt) {
            JTextField lowSalary = new JTextField();
            JTextField highSalary = new JTextField();
            Object[] message = {
                    "Please specify a salary range to search.",
                    "From:", lowSalary,
                    "To:", highSalary,
            };

            JOptionPane.showConfirmDialog(null,
                    message,
                    "Search Drivers",
                    JOptionPane.OK_CANCEL_OPTION);

            List<String> drivers = new ArrayList<>();
            drivers = delegate.getDriversBySalary(Double.parseDouble(lowSalary.getText()),
                    Double.parseDouble(highSalary.getText()));

            JOptionPane.showMessageDialog(null,
                    "Drivers with a salary between " + lowSalary.getText() + " and " + highSalary.getText()
                            + ": " + String.join("\n", drivers),
                    "Search Results",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private class LoyalCustomersAction extends AbstractAction {
        LoyalCustomersAction() { super("Get Loyalty Members"); }

        @Override
        public void actionPerformed(ActionEvent evt) {
            List<String> loyalCustomers = delegate.getLoyalCustomers();

            JOptionPane.showMessageDialog(null,
                    "Customers with Loyalty Memberships: \n" + String.join("\n", loyalCustomers),
                    "Search Results",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private class SalaryByDeliveriesAction extends AbstractAction {
        SalaryByDeliveriesAction() { super("Search Drivers by Salary & Orders"); }

        @Override
        public void actionPerformed(ActionEvent evt) {
            JTextField ordersDelivered = new JTextField();
            Object[] message = {
                    "How many orders do you want to group by?",
                    "Orders Delivered:", ordersDelivered,
            };

            JOptionPane.showConfirmDialog(null,
                    message,
                    "Search Drivers",
                    JOptionPane.OK_CANCEL_OPTION);

            List<String> drivers = delegate.getDriversByDeliveries(ordersDelivered.getText());

            JOptionPane.showMessageDialog(null,
                    String.join("\n", drivers),
                    "Search Results",
                    JOptionPane.INFORMATION_MESSAGE);
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