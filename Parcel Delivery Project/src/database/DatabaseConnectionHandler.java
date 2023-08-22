package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.Customer;
import model.Driver;
import model.Manager;
import model.ShippingUser;

/**
 * This class handles all database related transactions
 */
public class DatabaseConnectionHandler {
	// Use this version of the ORACLE_URL if you are running the code off of the server
//	private static final String ORACLE_URL = "jdbc:oracle:thin:@dbhost.students.cs.ubc.ca:1522:stu";
	// Use this version of the ORACLE_URL if you are tunneling into the undergrad servers
	private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
	private static final String EXCEPTION_TAG = "[EXCEPTION]";
	private static final String WARNING_TAG = "[WARNING]";
	private static final String USERNAME = "ora_seoykwon";
	private static final String PASSWORD = "a63623466";
	
	private Connection connection = null;

	private ShippingUser shippingUser;
	
	public DatabaseConnectionHandler() {
		try {
			// Load the Oracle JDBC driver
			// Note that the path could change for new drivers
			if (connection != null) {
				connection.close();
			}
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			connection = DriverManager.getConnection(ORACLE_URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}
	
	public void close() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}
	
	public void insertCustomer(Customer customer) {
		try {
			insertShippingUser(customer.getUsername(), customer.getUserPassword(), customer.getPhone(),
					customer.getEmail(), customer.getFirstName(), customer.getLastName(), customer.getAddress());
			connection.setAutoCommit(false);
			PreparedStatement ps;
			ps = connection.prepareStatement("INSERT INTO CUSTOMER VALUES  (?, ?)");
			ps.setString(1, customer.getUsername());
			ps.setInt(2, customer.isLoyaltyMember());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void insertManager(Manager manager) {
		try {
			insertShippingUser(manager.getUsername(), manager.getUserPassword(), manager.getPhone(), manager.getEmail(),
					manager.getFirstName(), manager.getLastName(), manager.getAddress());
			connection.setAutoCommit(false);
			PreparedStatement ps;
			ps = connection.prepareStatement("INSERT INTO MANAGER VALUES  (?,?)");
			ps.setString(1, manager.getUsername());
			ps.setFloat(2, manager.getSalary());
			ps.executeUpdate();
			connection.commit();
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void insertDriver(Driver driver) {
		try {

			insertShippingUser(driver.getUsername(), driver.getUserPassword(), driver.getPhone(), driver.getEmail(),
					driver.getFirstName(), driver.getLastName(), driver.getAddress());
			connection.setAutoCommit(false);
			PreparedStatement ps;

			ps = connection.prepareStatement("INSERT INTO DRIVER VALUES (?,?,?,?)");
			ps.setString(1, driver.getUsername());
			ps.setString(2, driver.getLicenseNumber());
			ps.setFloat(3, driver.getSalary());
			ps.setInt(4, driver.getOrdersDelivered());
			connection.commit();
			ps.executeUpdate();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	private void insertShippingUser(String username, String userPassword, Integer phone, String email, String firstName,
									String lastName, String address) throws SQLException {
		connection.setAutoCommit(false);
		PreparedStatement ps = connection.prepareStatement("INSERT INTO SHIPPINGUSER VALUES  (?,?,?,?,?,?,?)");
		ps.setString(1, username);
		ps.setString(2, userPassword);
		ps.setInt(3, phone);
		ps.setString(4, email);
		ps.setString(5, firstName);
		ps.setString(6, lastName);
		ps.setString(7, address);

		ps.executeUpdate();
	}

	public List<String> groupCustomersByOrdersPlaced() {
		List<String> result = new ArrayList<>();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT P.username, COUNT(*) as total_orders\n" +
					"FROM Places P\n" +
					"GROUP BY P.username\n" +
					"HAVING COUNT(*) > (\n" +
					"SELECT AVG(total_orders)\n" +
					"FROM (\n" +
					"SELECT C.username, COUNT(*) as total_orders\n" +
					"FROM Places C\n" +
					"GROUP BY C.username\n" +
					")\n" +
					")");

			while (rs.next()) {
				StringBuilder sb = new StringBuilder();
				sb.append("customer name: " + rs.getString("username") + " ");
				sb.append("orders placed: " + rs.getString("count(*)"));
				result.add(sb.toString());

			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Error message " + e.getMessage());
		}

		return result;
	}

	public List<String> countParcelsByDeliveryType() {
		List<String> result = new ArrayList<>();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT delivery_type, COUNT(*) from PARCEL GROUP BY delivery_type");

			while (rs.next()) {
				StringBuilder sb = new StringBuilder();
				sb.append("delivery type: " + rs.getString("delivery_type") + " ");
				sb.append("count: " + rs.getString("count(*)"));
				result.add(sb.toString());

			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Error message " + e.getMessage());
		}

		return result;
	}

	public List<String> getCustomersOrderingEveryDeliveryType() {
		List<String> result = new ArrayList<>();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT p.username\n" +
					"FROM Customer p\n" +
					"INNER JOIN Places pl ON p.username = pl.username\n" +
					"INNER JOIN ParcelOrder o ON pl.tracking_number = o.tracking_number\n" +
					"INNER JOIN HasDeliveryType d ON o.tracking_number = d.tracking_number\n" +
					"GROUP BY p.username\n" +
					"HAVING COUNT(DISTINCT d.delivery_type) = (SELECT COUNT(*) FROM DeliveryType)\n");

			while (rs.next()) {
				StringBuilder sb = new StringBuilder();
				sb.append(rs.getString("username") + ", ");
				result.add(sb.toString());

			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Error message " + e.getMessage());
		}

		return result;
	}

	/**
	 * I created a work-around for this for the time being, so this is not an immediate need.
	 * If you get it working, let me know, and I'll update the front end. - Colleen
	 * @param username
	 * @param password
	 * @return
	 */
	public ShippingUser login(String username, String password) {
		// TODO: get query return working
		ShippingUser shippingUser;
		try {
			connection.setAutoCommit(false);
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM SHIPPINGUSER WHERE username = ? AND user_password= ?");
//			stmt = connection.prepareStatement("SELECT * FROM SHIPPINGUSER");
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				System.out.println(rs.getString("username"));
//			}
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if ((rs.next())) {
				stmt = connection.prepareStatement("SELECT * FROM MANAGER m, CUSTOMER c, SHIPPINGUSER s WHERE m.username= ? AND m.username=c.username AND s.username=c.username");
				stmt.setString(1, username);
				rs = stmt.executeQuery();
				if (rs.next()) {
					shippingUser = new Manager(rs.getString("username"),
							rs.getString("user_password"),
							rs.getInt("phone"),
							rs.getString("email"),
							rs.getString("first_name"),
							rs.getString("last_name"),
							rs.getString("address"),
							rs.getInt("loyalty_member"),
							rs.getFloat("salary"));
					rs.close();
					return shippingUser;
				}
				stmt = connection.prepareStatement("SELECT * FROM DRIVER d, CUSTOMER c, SHIPPINGUSER s WHERE d.username= ? AND d.username=c.username AND s.username=c.username");
				stmt.setString(1, username);
				rs = stmt.executeQuery();
				if (rs.next()) {
					shippingUser = new Driver(rs.getString("username"),
							rs.getString("user_password"),
							rs.getInt("phone"),
							rs.getString("email"),
							rs.getString("first_name"),
							rs.getString("last_name"),
							rs.getString("address"),
							rs.getInt("loyalty_member"),
							rs.getString("license_number"),
							rs.getFloat("salary"),
							rs.getInt("orders_delivered"));
					rs.close();
					return shippingUser;
				}
				stmt = connection.prepareStatement("SELECT * FROM CUSTOMER c, SHIPPINGUSER s WHERE c.username= ? AND c.username=s.username");
				stmt.setString(1, username);
				rs = stmt.executeQuery();
				shippingUser = new Customer(rs.getString("username"),
						rs.getString("user_password"),
						rs.getInt("phone"),
						rs.getString("email"),
						rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getString("address"),
						rs.getInt("loyalty_member"));
				rs.close();
				return shippingUser;

			}
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
		return null;
	}

	/**
	 * Removes ShippingUser and Customer data from the database.
	 * @param customer
	 */
	public void deleteCustomer(Customer customer) {
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM Customer WHERE username = 'student'");
			//ps.setString(1, customer.getUsername());

			ps.executeUpdate();
//			connection.commit();
			deleteShippingUser(customer);

			ps.close();
			deleteShippingUser(customer);
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void deleteShippingUser(ShippingUser shippingUser) {
		try {
			connection.setAutoCommit(false);
			PreparedStatement ps = connection.prepareStatement("DELETE FROM SHIPPINGUSER " +
					"WHERE username ='student'");
			//ps.setString(1, shippingUser.getUsername());
			ps.executeUpdate();
			//connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	private void rollbackConnection() {
		try  {
			connection.rollback();	
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}

	/**
	 * Removes ShippingUser and Driver data from the database.
	 * @param driver
	 */
	public void deleteDriver(Driver driver) {
		try {
			connection.setAutoCommit(false);
			PreparedStatement ps = connection.prepareStatement("DELETE FROM Driver " +
					"WHERE username = (?)");
			ps.setString(1, driver.getUsername());

			ps.executeUpdate();
			connection.commit();
			deleteShippingUser(driver);
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	/**
	 * Removes ShippingUser and Manager data from the database.
	 * @param manager
	 */
	public void deleteManager(Manager manager) {
		try {
			connection.setAutoCommit(false);
			PreparedStatement ps = connection.prepareStatement("DELETE FROM Manager " +
					"WHERE username = (?)");
			ps.setString(1, manager.getUsername());

			ps.executeUpdate();
			connection.commit();
			deleteShippingUser(manager);
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	/**
	 * Updates  given ShippingUser's phone number in the database.
	 * @param shippingUser
	 */
	public void updatePhoneNumber(ShippingUser shippingUser) {
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE SHIPPINGUSER SET phone='12341234' WHERE username='greenc'");
//			ps.setInt(1, shippingUser.getPhone());
//			ps.setString(2, shippingUser.getUsername());

			ps.executeUpdate();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
		// TODO please double-check that I did this right
	}

	/**
	 * Select: manager can select drivers with specified salary range
	 * @param from: lower end of the salary range
	 * @param to: higher end of the salary range
	 * @return: return String listing Driver names
	 */
	public List<String> getDriversBySalary(Double from, Double to) {
		List<String> drivers = new ArrayList<>();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT username FROM DRIVER WHERE salary BETWEEN ? AND ?");
			ps.setDouble(1, from);
			ps.setDouble(2, to);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				drivers.add(rs.getString("username"));
			}
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}

		return drivers;
	}


	/**
	 * Projection: manager can select loyalty_member from customers
	 * @return return String listing customers that are loyalty members
	 */
	public List<String> getLoyalCustomers() {
		List<String> loyaltyMembers = new ArrayList<>();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT username FROM CUSTOMER WHERE loyalty_member IS NOT NULL");

			while (rs.next()) {
				loyaltyMembers.add(rs.getString("username"));
			}
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}

		return loyaltyMembers;
	}

	/**
	 * Query join: Manager can select requested  information from a driver/shippinguser
	 * @param username: contains the username of the driver to get information for
	 * @return return the given tuple value in a string.
	 * 			If it's easier, you can return them as a list, and then I'll print the
	 * 			values from the list in the frontend implementation
	 */
	public String getDriverInfo(String username) {
		// IDK what this is supposed to do, get info on a single driver??
		StringBuilder result = new StringBuilder();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM DRIVER d, SHIPPINGUSER s WHERE d.username=s.username AND d.username='mattma'");
			//ps.setString(1, username);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				result.append("first name: " + rs.getString("first_name").trim() + "\n" + "last name: " + rs.getString("last_name") + "\n" + "licence number: " + rs.getString("licence_number") + "\n" + "orders delivered: " + rs.getInt("orders_delivered"));
			}
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}

		return result.toString();
	}

	/**
	 * Query Aggregation with Having: select driver's usernames and salaries,
	 * 									group by orders_delivered having count(*) > orders
	 * @param orders: value to use in query (eg. > 1 if orders = 1)
	 * @return return the given tuple values in a string.
	 * 	  			If it's easier, you can return them as a list, and then I'll print the
	 * 	  			values from the list in the frontend implementation
	 */
	public List<String> getDriversByDeliveries(String orders) {
		List<String> drivers = new ArrayList<>();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT orders_delivered,COUNT(*) FROM DRIVER GROUP BY orders_delivered HAVING COUNT(*) > 0");
			//ps.setInt(1, Integer.parseInt(orders));

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				StringBuilder sb = new StringBuilder();
				sb.append("orders delivered: " + rs.getInt("orders_delivered") + " ");
				sb.append("count: " + rs.getInt("count(*)") + "\n");
				drivers.add(sb.toString());
			}
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}

		return drivers;
	}

	/**
	 * Query Division Operation: return all customer usernames who have ordered a parcel of the provided
	 * 								delivery types
	 * @param deliveryTypes: each line represents a delivery type
	 *                     note: for our demo, we will use ALL
	 * @return return the given tuple values in a string.
	 * 	  	  			If it's easier, you can return them as a list, and then I'll print the
	 * 	  	  			values from the list in the frontend implementation
	 */
	public List<String> getCustomerInfo(String deliveryTypes) {
		return getCustomersOrderingEveryDeliveryType();
	}


	public void databaseSetup() {
		//dropBranchTableIfExists();
//
//		try{
//			Statement stmt = connection.createStatement();
//			stmt.executeUpdate("CREATE TABLE branch (branch_id integer PRIMARY KEY, branch_name varchar2(20) not null, branch_addr varchar2(50), branch_city varchar2(20) not null, branch_phone integer)");
//			stmt.executeUpdate("CREATE TABLE Transaction\n" +
//					"    (tid                     CHAR(11) NOT NULL,\n" +
//					"    amount                   FLOAT NULL,\n" +
//					"    PRIMARY KEY (tid));");
//			stmt.close();
//		} catch (SQLException e) {
//			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//		}
//
////		BranchModel branch1 = new BranchModel("123 Charming Ave", "Vancouver", 1, "First Branch", 1234567);
////		insertBranch(branch1);
////
////		BranchModel branch2 = new BranchModel("123 Coco Ave", "Vancouver", 2, "Second Branch", 1234568);
////		insertBranch(branch2);
	}



//	private void dropBranchTableIfExists() {
//		try {
//			Statement stmt = connection.createStatement();
//			ResultSet rs = stmt.executeQuery("select table_name from user_tables");
//
//			while(rs.next()) {
//				if(rs.getString(1).toLowerCase().equals("branch")) {
//					stmt.execute("DROP TABLE branch");
//					break;
//				}
//			}
//
//			rs.close();
//			stmt.close();
//		} catch (SQLException e) {
//			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//		}
//	}
}
