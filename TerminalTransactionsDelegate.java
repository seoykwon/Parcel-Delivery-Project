package delegates;

import model.BranchModel;
import model.ShippingUser;

/**
 * This interface uses the delegation design pattern where instead of having
 * the TerminalTransactions class try to do everything, it will only
 * focus on handling the UI. The actual logic/operation will be delegated to the 
 * controller class (in this case Bank).
 * 
 * TerminalTransactions calls the methods that we have listed below but 
 * Bank is the actual class that will implement the methods.
 */
public interface TerminalTransactionsDelegate {
	public void databaseSetup();
	
	public void deleteBranch(int branchId);
	public void insertBranch(BranchModel model);
	public void showBranch();
	public void updateBranch(int branchId, String name);
	
	public void terminalTransactionsFinished();

	public void insertUser(ShippingUser user);

	public void deleteUser(String username);

	public void updateUser(ShippingUser user);

	public void selectDriversBySalaryRange(int lowerBound, int upperBound);

	public void listDrivers();

	public void listCustomers();

}
