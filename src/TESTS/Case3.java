package TESTS;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import SQL.PopulateSql;
import SQL.SqlQueries;

public class Case3 {
	private String schema;
	private SqlQueries s;

		public Case3() throws Exception{
			this.schema = "test";
			this.s = new SqlQueries();
		}
	@Before
	public void setUp() throws Exception {
	PopulateSql p= new PopulateSql(schema);
	}

	@After
	public void tearDown() throws Exception {
		s.dropSchema(schema);
		s.closeConnection();	
	}

	@Test
	public void test() throws Exception {
		//Joe Swanson withdraws $5,000.00 CAD from account number 5500. 
		s.removeFunds("5500", 5000.00, "CAD", schema);
		//Joe Swanson transfers $7,300.00 CAD from account number 1010 to account number 5500. 
		s.transferFunds("1010", "5500", 7300, "CAD", schema);
		//Joe Swanson deposits $13,726.00 MXN to account number 1010.
		s.addFunds("1010", 13726, "MXN", schema);

		
		
		//Account Number: 1010 Balance: $1,497.60 CAD 
		double balance_1010 = s.getBalance("1010", schema);
		assertEquals(balance_1010, 1497.60,0.0);

		//Account Number: 5500 Balance: $17,300.00 CAD
		double balance_5500 = s.getBalance("5500", schema);
		assertEquals(balance_5500, 17300,0.0);


	
	}

}
