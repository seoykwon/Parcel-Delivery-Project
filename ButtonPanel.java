package ui;

import model.Manager;
import model.Driver;
import model.Customer;
import delegates.UserTransactionsDelegate;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.*;


/**
 * Represents the panel in which the create account and login buttons are displayed.
 */
public class ButtonPanel extends JPanel {
    private UserTransactionsDelegate delegate;
    private UserPortal userPortal = null;
    private UserWindow userWindow;

    private static final int MAX_LOGIN_ATTEMPTS = 3;
    private int loginAttempts;

    public ButtonPanel(UserTransactionsDelegate delegate, UserWindow userWindow) {
        this.delegate = delegate;
        this.userWindow = userWindow;
        userPortal = new UserPortal();

        setBackground(new Color(243, 198, 50));
        add(Box.createHorizontalStrut(10));

        JButton addUserButton = new JButton(new AddUserAction());
        this.add(addUserButton);

        JButton customerLoginButton = new JButton(new CustomerLoginAction());
        this.add(customerLoginButton);

        JButton managerLoginButton = new JButton(new ManagerLoginAction());
        this.add(managerLoginButton);

        JButton driverLoginButton = new JButton(new DriverLoginAction());
        this.add(driverLoginButton);

        //JButton loginButton = new JButton(new LoginAction());
        //this.add(loginButton);
    }

    /**
     * Represents the action to be taken when the user wants to create an account.
     */
    private class AddUserAction extends AbstractAction {

        AddUserAction() {
            super("Create Account");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String userType = JOptionPane.showInputDialog(null,
                    "Are you creating a new Customer, Manager, or Driver account?",
                    "Create Account",
                    JOptionPane.QUESTION_MESSAGE);

            // Create new Customer
            if (userType.equals("Customer")) {
                JTextField username = new JTextField();
                JTextField password = new JPasswordField();
                JTextField phone = new JTextField();
                JTextField email = new JTextField();
                JTextField fname = new JTextField();
                JTextField lname = new JTextField();
                JTextField address = new JTextField();
                Object[] message = {
                        "Username:", username,
                        "Password:", password,
                        "Phone:", phone,
                        "First Name:", fname,
                        "Last Name:", lname,
                        "Email:", email,
                        "Address:", address,
                };

                JOptionPane.showConfirmDialog(null, message, "Create Customer Account",
                        JOptionPane.OK_CANCEL_OPTION);


                Customer customer = new Customer(username.getText(), password.getText(),
                        Integer.parseInt(phone.getText()), email.getText(),  fname.getText(), lname.getText(),
                        address.getText(), 0);

                delegate.insertCustomer(customer);

                userWindow.dispose();
                userPortal.showCustomerFrame(customer, delegate);

            }

            // Create new Manager
            else if (userType.equals("Manager")) {
                JTextField username = new JTextField();
                JTextField password = new JPasswordField();
                JTextField phone = new JTextField();
                JTextField email = new JTextField();
                JTextField fname = new JTextField();
                JTextField lname = new JTextField();
                JTextField address = new JTextField();
                Object[] message = {
                        "Username:", username,
                        "Password:", password,
                        "Phone:", phone,
                        "First Name:", fname,
                        "Last Name:", lname,
                        "Email:", email,
                        "Address:", address,
                };

                JOptionPane.showConfirmDialog(null, message, "Create Manager Account",
                        JOptionPane.OK_CANCEL_OPTION);


                Manager manager = new Manager(username.getText(), password.getText(), Integer.parseInt(phone.getText()),
                        email.getText(), fname.getText(), lname.getText(), address.getText(), 0, (float) 0);

                delegate.insertManager(manager);

                userWindow.dispose();
                userPortal.showManagerFrame(manager, delegate);
            }

            // Create new Driver
            else if (userType.equals("Driver")) {
                JTextField username = new JTextField();

                JTextField password = new JPasswordField();
                JTextField phone = new JTextField();
                JTextField email = new JTextField();
                JTextField fname = new JTextField();
                JTextField lname = new JTextField();
                JTextField address = new JTextField();
                JTextField licence = new JTextField();
                Object[] message = {
                        "Username:", username,

                        "Password:", password,
                        "Phone:", phone,
                        "First Name:", fname,
                        "Last Name:", lname,
                        "Email:", email,
                        "Address:", address,
                        "Licence Number:", licence
                };

                JOptionPane.showConfirmDialog(null, message, "Create Driver Account",
                        JOptionPane.OK_CANCEL_OPTION);


                Driver driver = new Driver(username.getText(), password.getText(), Integer.parseInt(phone.getText()),
                        email.getText(), fname.getText(), lname.getText(), address.getText(), 0,
                        licence.getText(), 0.00F, 0);

                delegate.insertDriver(driver);

                userWindow.dispose();
                userPortal.showDriverFrame(driver, delegate);
            }
        }
    }

    /**
     * Represents the action to be taken when the user wants to login to their account
     */
    private class CustomerLoginAction extends AbstractAction {

        CustomerLoginAction() {
            super("Customer Login");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JTextField username = new JTextField();
            JPasswordField userPassword = new JPasswordField();
            Object[] message = {
                    "Username:", username,
                    "Password:", userPassword,
            };

            int option = JOptionPane.showConfirmDialog(null, message, "Account Login",
                    JOptionPane.OK_CANCEL_OPTION);

            if (option == JOptionPane.OK_OPTION) {
                userWindow.dispose();

                userPortal.showCustomerFrame(new Customer(username.getText(), String.valueOf(userPassword.getText()),
                        null, null, null, null, null,
                        0), delegate);
            } else {
                System.out.println("Login canceled");
            }
        }
    }

    /**
     * Represents the action to be taken when the user wants to login to their account
     */
    private class ManagerLoginAction extends AbstractAction {

        ManagerLoginAction() {
            super("Manager Login");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JTextField username = new JTextField();
            JPasswordField userPassword = new JPasswordField();
            Object[] message = {
                    "Username:", username,
                    "Password:", userPassword,
            };

            int option = JOptionPane.showConfirmDialog(null, message, "Account Login",
                    JOptionPane.OK_CANCEL_OPTION);

            if (option == JOptionPane.OK_OPTION) {
                userWindow.dispose();
                userPortal.showManagerFrame(new Manager(username.getText(), String.valueOf(userPassword.getText()),
                        null, null, null, null, null,
                        0, null), delegate);
            } else {
                System.out.println("Login canceled");
            }
        }
    }

    /**
     * Represents the action to be taken when the user wants to login to their account
     */
    private class DriverLoginAction extends AbstractAction {

        DriverLoginAction() {
            super("Driver Login");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JTextField username = new JTextField();
            JPasswordField userPassword = new JPasswordField();
            Object[] message = {
                    "Username:", username,
                    "Password:", userPassword,
            };

            int option = JOptionPane.showConfirmDialog(null, message, "Account Login",
                    JOptionPane.OK_CANCEL_OPTION);

            if (option == JOptionPane.OK_OPTION) {
                userWindow.dispose();
                userPortal.showDriverFrame(new Driver(username.getText(), String.valueOf(userPassword.getText()),
                        null, null, null, null, null,
                        0, null, null, null), delegate);
            } else {
                System.out.println("Login canceled");
            }
        }
    }
//    private class LoginAction extends AbstractAction {
//
//        LoginAction() {
//            super("Customer Login");
//        }
//
//        @Override
//        public void actionPerformed(ActionEvent evt)

//        @Override
//        public void actionPerformed(ActionEvent evt) {
//            JTextField username = new JTextField();
//            JPasswordField userPassword = new JPasswordField();
//            Object[] message = {
//                    "Username:", username,
//                    "Password:", userPassword,
//            };
//
//            int option = JOptionPane.showConfirmDialog(null, message, "Account Login",
//                    JOptionPane.OK_CANCEL_OPTION);
//
//            if (option == JOptionPane.OK_OPTION) {
//                ShippingUser shippingUser = delegate.login(username.getText(), String.valueOf(userPassword.getText()));
//                if (shippingUser != null) {
//                    if (shippingUser instanceof Customer) {
//                        userWindow.dispose();
//                        userPortal.showCustomerFrame((Customer) shippingUser, delegate);
//                    } else if (shippingUser instanceof Driver) {
//                        userWindow.dispose();
//                        userPortal.showDriverFrame((Driver) shippingUser, delegate);
//                    } else {
//                        userWindow.dispose();
//                        userPortal.showManagerFrame((Manager) shippingUser, delegate);
//                    }
//                }
//            } else {
//                System.out.println("Login canceled");
//            }
//        }

}