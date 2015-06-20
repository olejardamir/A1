package SQL;

public class PopulateSql {

	public PopulateSql(String schema){this.populate(schema);}
	
	private void populate(String schema){
		try {
			SqlQueries sq = new SqlQueries();
			sq.createSchema(schema);
			this.addAccountsTable(schema, sq);
			this.addCustomersTable(schema, sq);
			this.populateDB(schema);
			sq.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	
	private void addAccountsTable(String schema, SqlQueries sq) throws Exception{
	    String query = 	"CREATE TABLE IF NOT EXISTS "+schema+".Accounts ("+
				"account_number VARCHAR(255) PRIMARY KEY,"+
				"current_balance DOUBLE NOT NULL,"+
				"date_created TIMESTAMP NOT NULL"+
				") ";
	    sq.executeManipulation(query);
	}
	
	private void addCustomersTable(String schema, SqlQueries sq) throws Exception{
		String query = 	"CREATE TABLE IF NOT EXISTS "+schema+".Customers ("+
				"customerID VARCHAR(255), "+
				"first_name VARCHAR(100) NOT NULL, "+
				"last_name VARCHAR(100) NOT NULL ,"+
				"middle_name VARCHAR(100), "+
				"account_number VARCHAR(255),"+
				"FOREIGN KEY (account_number) REFERENCES "+schema+".accounts(account_number),"+
				"INDEX (last_name)"+
				") ";
	    sq.executeManipulation(query);
	}
	
	private void populateDB(String schema) throws Exception{
		SqlQueries s = new SqlQueries();
		String[]data = new String[4];
		String[] IDs = new String[2];
		//---------------------------------------------
		data[0] = "Stewie";
		data[1] = "Griffin"; 
		data[2] = ""; 
		data[3] = schema; 
		IDs[0] = "777";
		IDs[1] = "1234";
		s.addNewUser(data, IDs);
		s.addFunds(IDs[1], 100.00, "CAD", data[3]);
		//---------------------------------------------
		data[0] = "Glenn";
		data[1] = "Quagmire"; 
		data[2] = ""; 
		data[3] = schema; 
		IDs[0] = "504";
		IDs[1] = "2001";
		s.addNewUser(data, IDs);
		s.addFunds(IDs[1], 35000.00, "CAD", data[3]);
		//---------------------------------------------
		data[0] = "Joe";
		data[1] = "Swanson"; 
		data[2] = ""; 
		data[3] = schema; 
		IDs[0] = "002";
		IDs[1] = "5500";
		s.addNewUser(data, IDs);
		s.addFunds(IDs[1], 15000.00, "CAD", data[3]);
		//---------------------------------------------
		data[0] = "Joe";
		data[1] = "Swanson"; 
		data[2] = ""; 
		data[3] = schema; 
		IDs[0] = "002";
		IDs[1] = "1010";
		s.addNewUser(data, IDs);
		s.addFunds(IDs[1], 7425.0, "CAD", data[3]);
		//---------------------------------------------
		data[0] = "Peter";
		data[1] = "Griffin"; 
		data[2] = ""; 
		data[3] = schema; 
		IDs[0] = "123";
		IDs[1] = "0123";
		s.addNewUser(data, IDs);
		s.addFunds(IDs[1], 150.00, "CAD", data[3]);
		//---------------------------------------------
		data[0] = "Lois";
		data[1] = "Griffin"; 
		data[2] = ""; 
		data[3] = schema; 
		IDs[0] = "456";
		IDs[1] = "0456";
		s.addNewUser(data, IDs);
		s.addFunds(IDs[1], 65000.00, "CAD", data[3]);
		//--------------------------------------------- 
	}
	
	
}
