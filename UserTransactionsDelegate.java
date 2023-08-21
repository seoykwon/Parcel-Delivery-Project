package delegates;

import database.DatabaseConnectionHandler;
import model.Driver;
import model.Customer;
import model.Manager;
import model.ShippingUser;

import java.util.List;

/**
 * This interface uses the delegation design pattern where instead of having
 * the TerminalTransactions class try to do everything, it will only
 * focus on handling the UI. The actual logic/operation will be delegated to the
 * controller class (in this case Shipper).
 *
 * TerminalTransactions calls the methods that we have listed below but
 * ShippingUser subclasses are the actual classes that will implement the methods.
 */

public interface UserTransactionsDelegate {
    public void databaseSetup();

    public List<String> countParcelsByDeliveryType();

    //public void insertShippingUser(ShippingUser shippingUser);

    public void insertCustomer(Customer customer);

    public void insertManager(Manager manager);

    public void insertDriver(Driver driver);

    ShippingUser login(String username, String password);

    public List<String> getCustomersOrderingEveryDeliveryType();
    //void login(String username, String password);

    public void  deleteCustomer(Customer customer);

    public void deleteShippingUser(ShippingUser shippingUser);

    public void  deleteDriver(Driver driver);

    public void  deleteManager(Manager manager);

    public void updatePhoneNumber(ShippingUser shipppingUser);

    void updatePhone(Customer customer);

    public List<String> getDriversBySalary(Double from, Double to);

    public List<String> getLoyalCustomers();

    String getDriverInfo(String message);

    List<String> getDriversByDeliveries(String text);

    List<String> getCustomerInfo(String message);

    List<String> groupCustomersByOrdersPlaced();

    DatabaseConnectionHandler getDBConnectionHandler();
}