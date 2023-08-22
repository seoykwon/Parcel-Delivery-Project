package controller;

import database.DatabaseConnectionHandler;
import delegates.UserTransactionsDelegate;
import model.Driver;
import model.Customer;
import model.Manager;
import model.ShippingUser;
import ui.UserWindow;

import java.util.List;

/**
 * This is the main controller class that will orchestrate everything.
 */
public class Shipper implements UserTransactionsDelegate {
    private DatabaseConnectionHandler dbHandler = null;
    private UserWindow userWindow = null;

    public DatabaseConnectionHandler getDbHandler() {
        return dbHandler;
    }

    public Shipper() {
        dbHandler = new DatabaseConnectionHandler();
    }

    private void start() {
        userWindow = new UserWindow();
        userWindow.showFrame(this);
    }

    /**
     * UserTransactionDelegate Implementation
     *
     * connects to Oracle database with supplied user input

    public void login(String username, String password) {
        boolean didConnect = dbHandler.login(username, password);

        if (didConnect) {
            // Once connected, remove login window and start text transaction flow
            ButtonPanel.dispose();

            ButtonPanel bp = new ButtonPanel();
            bp.setupDatabase(this);
            bp.showMainMenu(this);
        } else {
            ButtonPanel.handleLoginFailed();

            if (ButtonPanel.hasReachedMaxLoginAttempts()) {
                ButtonPanel.dispose();
                System.out.println("You have exceeded your number of allowed attempts");
                System.exit(-1);
            }
        }
    }
     */

    /**
     * UserTransactionsDelegate Implementation
     *
     * Insert a customer with the given info
     */
    public void insertCustomer(Customer customer) {
        dbHandler.insertCustomer(customer);
    }

    /**
     * UserTransactionsDelegate Implementation
     *
     * Insert a manager with the given info
     */
    public void insertManager(Manager manager) {
        dbHandler.insertManager(manager);
    }

    @Override
    public void insertDriver(Driver driver) { dbHandler.insertDriver(driver); }

    @Override
    public ShippingUser login(String username, String password) {
        return dbHandler.login(username, password);

//	 * LoginWindowDelegate Implementation
//	 *
//     * connects to Oracle database with supplied username and password
//     */
//	public void login(String username, String password) {
//		boolean didConnect = dbHandler.login(username, password);
//
//		if (didConnect) {
//			// Once connected, remove login window and start text transaction flow
//			loginWindow.dispose();
//
//			TerminalTransactions transaction = new TerminalTransactions();
//			transaction.setupDatabase(this);
//			transaction.showMainMenu(this);
//		} else {
//			loginWindow.handleLoginFailed();
//
//			if (loginWindow.hasReachedMaxLoginAttempts()) {
//				loginWindow.dispose();
//				System.out.println("You have exceeded your number of allowed attempts");
//				System.exit(-1);
//			}
//		}
//	}
        // so login sequence
    }

    @Override
    public List<String> getCustomersOrderingEveryDeliveryType() {
        return dbHandler.getCustomersOrderingEveryDeliveryType();
    }

    /**
     * Delete given customer from the database
     */
    @Override
    public void deleteCustomer(Customer customer) { dbHandler.deleteCustomer(customer); }

    @Override
    public void deleteShippingUser(ShippingUser shippingUser) { dbHandler.deleteShippingUser(shippingUser);}

    /**
     * Delete given driver from the database
     */
    @Override
    public void deleteDriver(Driver driver) { dbHandler.deleteDriver(driver); }

    /**
     * Delete given manager from the database
     */
    @Override
    public void deleteManager(Manager manager) { dbHandler.deleteManager(manager); }

    @Override
    public void updatePhoneNumber(ShippingUser shipppingUser) {
        dbHandler.updatePhoneNumber(shipppingUser);
    }

    /**
     * Update given customer in the database
     */
    @Override
    public void updatePhone(Customer customer) { dbHandler.updatePhoneNumber(customer); }

    @Override
    public List<String> getDriversBySalary(Double from, Double to) { return dbHandler.getDriversBySalary(from, to);}

    @Override
    public List<String> getLoyalCustomers() { return dbHandler.getLoyalCustomers();}

    @Override
    public String getDriverInfo(String request) { return dbHandler.getDriverInfo(request);}

    @Override
    public List<String> getDriversByDeliveries(String orders) { return dbHandler.getDriversByDeliveries(orders);}

    @Override
    public List<String> getCustomerInfo(String deliveryTypes) { return dbHandler.getCustomerInfo(deliveryTypes);}

    @Override
    public List<String> groupCustomersByOrdersPlaced() {
        return dbHandler.groupCustomersByOrdersPlaced();
    }

    @Override
    public DatabaseConnectionHandler getDBConnectionHandler() {
        return dbHandler;
    }

    /**
     * TerminalTransactionsDelegate Implementation
     *
     * The TerminalTransaction instance tells us that it is done with what it's
     * doing so we are cleaning up the connection since it's no longer needed.
     */
    public void terminalTransactionsFinished() {
        dbHandler.close();
        dbHandler = null;

        System.exit(0);
    }

    /**
     * TerminalTransactionsDelegate Implementation
     *
     * The TerminalTransaction instance tells us that the user is fine with dropping any existing table
     * called branch and creating a new one for this project to use
     */
    public void databaseSetup() {
        dbHandler.databaseSetup();;

    }

    @Override
    public List<String> countParcelsByDeliveryType() {
        return dbHandler.countParcelsByDeliveryType();
    }


    /**
     * Main method called at launch time
     */
    public static void main(String args[]) {
        Shipper shipper = new Shipper();
        shipper.start();
    }
}
