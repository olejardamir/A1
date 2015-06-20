package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import CurrencyFactory.CurrencyFactory;

public class SqlQueries {

	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	
	public SqlQueries() throws Exception {
		// This will load the MySQL driver, each DB has its own driver
		Class.forName("com.mysql.jdbc.Driver");
		// Setup the connection with the DB
		connect = DriverManager.getConnection("jdbc:mysql://localhost/?"
				+ "user=root&password=root");

	}

	
	public void createSchema(String schema) throws Exception{
		String query = "CREATE SCHEMA IF NOT EXISTS `"+schema+"` ;";
		executeManipulation(query);
	}
	
	
	public double getBalance(String accountNumber, String schema) throws Exception{
		String query = "SELECT "+schema+".accounts.current_balance FROM "+schema+".accounts WHERE account_number='"+accountNumber+"';";
		ResultSet rs = execute(query);
		rs.next();
		double balance = rs.getDouble(1);
		rs.close();
		return balance;
	}
	
	public void dropSchema(String schema) throws Exception{
		String query = "DROP DATABASE IF EXISTS `"+schema+"` ;";
		executeManipulation(query);
	}
	
	//data array is - firstName:0, lastName:1, middleName:2, schema:3
	//IDs is - customerID:0, int accountNumber:1
	public void addNewUser(String[] data, String[] IDs) throws Exception {

		// does user exist ?
		boolean userExists = checkUser(IDs[1], data[3]);
		if (userExists) {
			throw new Exception("This user is already in a Database!");
		}

		String accountsQuery = "INSERT INTO " + data[3] + ".accounts values ('"
				+ IDs[1] + "','0',now());";
		String customerssQuery = "INSERT INTO " + data[3]
				+ ".customers values ('" + IDs[0] + "','" + data[0] + "','"
				+ data[1] + "','" + data[2] + "','" + IDs[1] + "');";

		setKeyChecksOFF();
		executeManipulation(accountsQuery);
		executeManipulation(customerssQuery);
		setKeyChecksON();
	}
	
	public void addFunds(String accountNumber, double increment, String currencyType,String schema) throws Exception {
		CurrencyFactory cf = new CurrencyFactory();
		increment = cf.convert(increment, currencyType);
		String query = "UPDATE "+schema+".accounts "+
				"SET current_balance = (current_balance + ("+increment+")) "+
				"WHERE account_number = '"+accountNumber+"'";
		executeManipulation(query);
	}

	public void removeFunds(String accountNumber, double decrement, String currencyType,String schema) throws Exception {
		addFunds(accountNumber, decrement * -1, currencyType, schema);
	}

	
	public void transferFunds(String fromAccountNumber, String toAccountNumber, 
			double amount, String currencyType, String schema) throws Exception{
		amount = Math.abs(amount); //for safety reasons.
		//does user exist ?
		boolean userExists = checkUser(toAccountNumber, schema);
		if(!userExists){throw new Exception("There is no such transfer-to account!");}
		
		CurrencyFactory cf = new CurrencyFactory();
		amount = cf.convert(amount, currencyType);
		
		this.removeFunds(fromAccountNumber, amount, currencyType, schema);
		this.addFunds(toAccountNumber, amount, currencyType, schema);
	}
	
	private boolean checkUser(String accountNumber, String schema) throws Exception{
		String userQuery = "SELECT * FROM "+schema+".customers as t1 "
				+ "INNER JOIN "+schema+".accounts as t2 "
				+ "ON t1.account_number=t2.account_number AND t1.account_number="+accountNumber+";";
		ResultSet rs = execute(userQuery);
		boolean exists =  rs.first();
		rs.close();
		return exists;
	}
	
	
	private void setKeyChecksON() throws Exception{
		String query = "SET foreign_key_checks = 1;";
		executeManipulation(query);
	}
	
	private void setKeyChecksOFF() throws Exception{
		String query = "SET foreign_key_checks = 0;";
		executeManipulation(query);
	}
	
	public void closeConnection() throws Exception {
		connect.close();
	}

	protected ResultSet execute(String query) throws SQLException {
		statement = connect.createStatement();
	    preparedStatement = connect.prepareStatement(query);
		// Result set get the result of the SQL query
	    return preparedStatement.executeQuery();
	}
	
	protected void executeManipulation(String query) throws SQLException {
		statement = connect.createStatement();
		// Result set get the result of the SQL query
		statement.executeUpdate(query);
	}
}
